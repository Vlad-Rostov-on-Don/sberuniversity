import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FriendshipServiceImplTest {
    private UserService userService;
    private FriendshipService friendshipService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        friendshipService = new FriendshipServiceImpl(userService);
    }

    @Test
    void testAddFriendSuccess() {
        when(userService.getUserId("123")).thenReturn(new User("Serg", "123"));
        when(userService.getUserId("456")).thenReturn(new User("Vova", "456"));

        assertDoesNotThrow(() -> friendshipService.addFriend("123", "456"));
        verify(userService, times(1)).getUserId("123");
        verify(userService, times(1)).getUserId("456");
    }

    @Test
    void testAddFriendFailure() {
        when(userService.getUserId("123")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> friendshipService.addFriend("123", "456"));
        assertEquals("Пользователь(и) не найден", exception.getMessage());
        verify(userService, times(1)).getUserId("123");
        verify(userService, times(1)).getUserId("456");
    }
}
