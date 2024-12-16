public class UserServiceImpl implements UserService{
    @Override
    public User getUserId(String id) {
        return new User("Ivan Ivanov", "123");
    }
}
