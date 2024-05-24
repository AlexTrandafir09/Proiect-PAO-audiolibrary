package functions.admin_functions;

import authentification.All_users;
import authentification.Roles;
import authentification.User;
import database.UsersDatabase;
import exception.UserNotExistentException;

public class Promote {
    public static User returned(String[] values,User user) {
        try {
            boolean exists = false;
            All_users all_users = All_users.getInstance();
            for (User user1 : all_users.getAllUsers())
                if (user1.getUser().equals(values[1])) {
                    user1.setRole(Roles.admin_user);
                    UsersDatabase.updateUserRoleToAdmin(user1.getId());
                    exists = true;
                }
            if (!exists)
                throw new UserNotExistentException();
            return user;
        }
        catch (UserNotExistentException e) {
            System.out.println(e.getMessage());
            return user;
        }

    }
}
