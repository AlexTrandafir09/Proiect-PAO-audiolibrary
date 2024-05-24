package functions.admin_functions;

import functions.BaseFunctions;
import functions.user_functions.UserFunctions;
import database.AuditDatabase;
import exception.UnknownCommandException;
import user.User;

public class AdminFunctions extends UserFunctions {

    public static User functions(User user, String[] values) {
        User temp = BaseFunctions.common(user, values, new AdminFunctions());
        if (temp != null) {
            user = temp;
            return user;
        }
        int choice = new AdminFunctions().getChoice(values);
        switch (choice) {
            case 7:
                return Promote.returned(values, user);
            case 8:
                return CreateSong.returned(user, values[2], values[3], values[4]);
            case 9:
                AuditDatabase.print(values[1]);
                return user;
            default:
                try {
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    AuditDatabase.insert(user, "unknown command", false);
                    System.out.println(e.getMessage());
                    return user;
                }
        }
    }

    @Override
    protected int getChoice(String[] values) {
        int choice = 0;
        if (values[0].equals("promote")) choice = 7;
        if (values[0].equals("create") && values[1].equals("song") && values.length == 5) choice = 8;
        if (values[0].equals("audit")) choice = 9;
        if (choice == 0) {
            choice = super.getChoice(values);
        }
        return choice;
    }
}
