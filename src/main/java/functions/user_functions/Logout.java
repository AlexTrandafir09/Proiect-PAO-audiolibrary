package functions.user_functions;

import database.AuditDatabase;
import user.Roles;
import user.User;

public final class Logout {
    public static User returned(User user1) {
        AuditDatabase.insert(user1, "logout", true);
        user1 = User.builder().role(Roles.anonim).build();
        System.out.println("Successfully logged out..");
        return user1;
    }
}
