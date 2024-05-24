import database.AuditDatabase;
import database.UsersDatabase;
import functions.admin_functions.Admin_functions;
import functions.anon_functions.Anon_functions;
import functions.user_functions.User_functions;
import authentification.Roles;
import authentification.User;

public class Main {
    public static void main(String[] args) {
        AuditDatabase.CreateAuditTable();
        UsersDatabase.CreateUsersTable();
        User user= User.builder().role(Roles.anonim).build();
        while(true)
            if(user.getRole()==Roles.anonim) {
                user = Anon_functions.functions(user);
            }
            else if(user.getRole()==Roles.standard_user) {
                user=User_functions.functions(user);
            }
            else if(user.getRole()==Roles.admin_user){
                user=Admin_functions.functions(user);
            }
    }
}




// primul user din baza de date = admin
// playlisturile trebuie sa fie salvate pt fiecare user
