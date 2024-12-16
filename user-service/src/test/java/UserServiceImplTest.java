import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserServiceImplTest {
    @Test
    void testGetUserId() {
        UserService userService = new UserServiceImpl();
        User user = userService.getUserId("123");
        assertNotNull(user);
        assertEquals("123", user.getId());
        assertEquals("Ivan Ivanov", user.getName());
    }
}
