

import authentification.Roles;
import authentification.User;
import command.Command;
import database.AuditDatabase;
import database.UsersDatabase;
import functions.admin_functions.Admin_functions;
import functions.anon_functions.Anon_functions;
import functions.user_functions.User_functions;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        AuditDatabase.create();
        UsersDatabase.create();
        User user = User.builder().role(Roles.anonim).build();
        while (true) {
            String[] values= Command.returned();
            if (user.getRole() == Roles.anonim) {
                user = Anon_functions.functions(user,values);
            } else if (user.getRole() == Roles.standard_user) {
                user = User_functions.functions(user,values);
            } else if (user.getRole() == Roles.admin_user) {
                user = Admin_functions.functions(user,values);
            }
            if(Objects.equals(values[0], "end"))
                System.exit(0);
        }
    }
}

