package authentification.users;

import exception.UserLoginException;
import exception.UserRegistrationException;

import java.util.ArrayList;
import java.util.List;

public class All_users {
    private static All_users instance;
    private List<User> userList;

    private All_users() {
        userList = new ArrayList<>();
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
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public boolean checkUserRegistration(User user) throws UserRegistrationException {
        for(User u : userList)
            if(u.getUser().equals(user.getUser())) {
                throw new UserRegistrationException();
            }
        return true;
    }
    public User checkUserLogin(User user) throws UserLoginException {
        for(User u : userList)
            if(u.getUser().equals(user.getUser()) && !u.getPassword().equals(user.getPassword())) {
                throw new UserLoginException();
            }
            else if(u.getUser().equals(user.getUser()) && u.getPassword().equals(user.getPassword())) {
                return u;
            }
        throw new UserLoginException();
    }

}
