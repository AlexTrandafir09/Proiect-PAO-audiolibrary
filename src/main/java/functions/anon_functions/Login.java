package functions.anon_functions;

import user.All_users;
import user.User;
import database.AuditDatabase;
import exception.UserLoginException;

public class Login {
    public static User returned(User user, String username, String password) {
        try {
            All_users users = All_users.getInstance();
            User log_user = users.checkUserLogin(username, password);
            System.out.println("You are now authenticated as " + log_user.getUser());
            AuditDatabase.insert(log_user, "login", true);
            return log_user;
        } catch (UserLoginException e) {
            System.out.println(e.getMessage());
            AuditDatabase.insert(user, "login", false);
            return user;
        }
    }
}
