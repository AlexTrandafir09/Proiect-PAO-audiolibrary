package authentification.functions.anon_functions;

import authentification.users.All_users;
import authentification.users.Roles;
import authentification.users.User;
import exception.UserRegistrationException;

public class Register {
    public static User returned(String user,String password) {
        User new_user = User.builder().user(user).password(password).role(Roles.standard_user).build();
        All_users users = All_users.getInstance();
        try {
            users.checkUserRegistration(new_user);
            System.out.println("Registered account with user name "+ new_user.getUser());
            return new_user;
        } catch (UserRegistrationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
