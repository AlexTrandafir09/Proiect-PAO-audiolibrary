package functions.user_functions;

import authentification.Roles;
import authentification.User;
import database.AuditDatabase;

public class Logout {
    public static User returned(User user1){
        AuditDatabase.Insert(user1,"logout",true);
        user1 = User.builder().role(Roles.anonim).build();
        System.out.println("Successfully logged out.");
        return user1;
    }
}
