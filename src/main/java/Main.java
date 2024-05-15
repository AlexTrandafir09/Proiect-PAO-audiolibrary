import authentification.functions.anon_functions.Anon_functions;
import authentification.functions.user_functions.User_functions;
import authentification.users.Roles;
import authentification.users.User;

public class Main {
    public static void main(String[] args) {
        User user= User.builder().role(Roles.anonim).build();
        User temp;
        while(true)
            if(user.getRole()==Roles.anonim) {
                temp = Anon_functions.functions(user);
                if(temp!=null)
                    user=temp;
            }
            else if(user.getRole()==Roles.standard_user) {
                temp = User_functions.functions(user);
                if(temp!=null)
                    user=temp;
            }
    }
}
