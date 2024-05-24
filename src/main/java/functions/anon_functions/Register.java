package functions.anon_functions;

import database.AuditDatabase;
import exception.UserRegistrationException;
import user.All_users;
import user.User;

public class Register {
    public static User returned(User user, String name, String password) {
        User new_user = User.builder().user(name).password(password).build();
        All_users users = All_users.getInstance();
        try {
            users.checkUserRegistration(new_user);
            System.out.println("Registered account with user name " + new_user.getUser());
            users.addUser(new_user);
            return new_user;
        } catch (UserRegistrationException e) {
            System.out.println(e.getMessage());
            AuditDatabase.insert(user, "register", false);
            return user;
        }
    }
}
