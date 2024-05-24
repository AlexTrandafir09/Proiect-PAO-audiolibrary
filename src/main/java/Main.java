import command.Command;
import command.ListCommands;
import database.AuditDatabase;
import database.UsersDatabase;
import functions.admin_functions.AdminFunctions;
import functions.anon_functions.AnonFunctions;
import functions.user_functions.UserFunctions;
import java.util.Objects;
import user.Roles;
import user.User;

public class Main {
    public static void main(String[] args) {
        AuditDatabase.create();
        UsersDatabase.create();
        ListCommands.print();
        User user = User.builder().role(Roles.anonim).build();

        while (true) {
            String[] values = Command.returned();
            if (user.getRole() == Roles.anonim) {
                user = AnonFunctions.functions(user, values);
            } else if (user.getRole() == Roles.standard_user) {
                user = UserFunctions.functions(user, values);
            } else if (user.getRole() == Roles.admin_user) {
                user = AdminFunctions.functions(user, values);
            }
            if (Objects.equals(values[0], "end")) System.exit(0);
        }
    }
}
