package user;

import database.AuditDatabase;
import database.UsersDatabase;
import exception.UserLoginException;
import exception.UserRegistrationException;
import java.util.List;

public class All_users {
    private static All_users instance;
    private List<User> userList;

    private All_users() {
        userList = UsersDatabase.getUsers();
    }

    public static All_users getInstance() {
        if (instance == null) {
            synchronized (All_users.class) {
                if (instance == null) {
                    instance = new All_users();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userList.add(user);
        AuditDatabase.insert(user, "register", true);
        UsersDatabase.insert(user);
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void checkUserRegistration(User user) throws UserRegistrationException {
        for (User u : userList)
            if (u.getUser().equals(user.getUser())) throw new UserRegistrationException();
    }

    public User checkUserLogin(String username, String password) throws UserLoginException {
        for (User u : userList)
            if (u.getUser().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        throw new UserLoginException();
    }
}
