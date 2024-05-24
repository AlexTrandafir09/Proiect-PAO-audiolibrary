package functions.admin_functions;

import database.UsersDatabase;
import exception.UserNotExistentException;
import user.All_users;
import user.Roles;
import user.User;

public class Promote {
    public static User returned(String[] values, User user) {
        try {
            boolean exists = false;
            All_users all_users = All_users.getInstance();
            for (User user1 : all_users.getAllUsers())
                if (user1.getUser().equals(values[1])) {
                    user1.setRole(Roles.admin_user);
                    UsersDatabase.updateRole(user1.getId());
                    exists = true;
                }
            if (!exists) throw new UserNotExistentException();
            return user;
        } catch (UserNotExistentException e) {
            System.out.println(e.getMessage());
            return user;
        }
    }
}
