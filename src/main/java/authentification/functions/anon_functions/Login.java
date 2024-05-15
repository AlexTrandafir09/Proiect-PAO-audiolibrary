package authentification.functions.anon_functions;

import authentification.users.All_users;
import authentification.users.Roles;
import authentification.users.User;
import exception.UserLoginException;


public class Login {
    public static User returned(String username,String password){
        User log_user= User.builder().user(username).password(password).role(Roles.standard_user).build();
        All_users users = All_users.getInstance();
        try{
            log_user=users.checkUserLogin(log_user);
            System.out.println("You are now authenticated as "+ log_user.getUser());
            return log_user;
        }
        catch(UserLoginException e){
            System.out.println(e.getMessage());
           return null;
        }
    }

}
