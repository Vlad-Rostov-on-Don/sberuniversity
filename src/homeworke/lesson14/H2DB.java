package homeworke.lesson14;

import java.io.*;
import java.sql.*;

public class H2DB implements Source {
    private final Connection connection;

    public H2DB() {
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:./cacheDB", "sa", "");
            createTableIfNotExists();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных H2", e);
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS cache (
                key VARCHAR(255) PRIMARY KEY,
                value BLOB
                )
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    @Override
    public void save(String key, Object value) {
        String sql = "MERGE INTO cache (key, value) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.setBytes(2, serialize(value));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении в базу данных H2", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T load(String key, Class<T> type) {
        String sql = "SELECT value FROM cache WHERE key = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return deserialize(rs.getBytes(1), type);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка загрузки из базы данных H2", e);
        }
        return null;
    }

    @Override
    public boolean contains(String key) {
        String sql = "SELECT 1 FROM cache WHERE key = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка проверки ключа в базе данных Н2", e);
        }
    }

    private byte[] serialize(Object obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сериализации", e);
        }
    }

    private <T> T deserialize(byte[] bytes, Class<T> type) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return type.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка десериализации", e);
        }
    }
}
