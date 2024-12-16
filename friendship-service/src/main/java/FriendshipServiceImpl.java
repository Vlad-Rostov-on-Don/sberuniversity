public class FriendshipServiceImpl implements FriendshipService{
    private final UserService userService;

    public FriendshipServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addFriend(String userId1, String userId2) {
        if (userService.getUserId(userId1) != null && userService.getUserId(userId2) != null) {
            System.out.println("Дружба, возникшая между: " + userId1 + "и" + userId2);
        } else {
            throw new IllegalArgumentException("Пользователь(и) не найден");
        }
    }
}
