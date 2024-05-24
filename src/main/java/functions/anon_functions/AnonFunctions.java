package functions.anon_functions;

import database.AuditDatabase;
import exception.InvalidCommandUsageException;
import exception.UnknownCommandException;
import user.User;

public class AnonFunctions {
    public static User functions(User user, String[] values) {
        int choice = getChoice(values);
        switch (choice) {
            case 0:
                {
                    return Register.returned(user, values[1], values[2]);
                }
            case 1:
                {
                    return Login.returned(user, values[1], values[2]);
                }
            default:
                {
                    try {
                        if ((values[0].equals("register") || values[0].equals("login")))
                            throw new InvalidCommandUsageException(values[0]);
                        throw new UnknownCommandException();

                    } catch (InvalidCommandUsageException | UnknownCommandException e) {
                        AuditDatabase.insert(user, e.getMessage(), false);
                        System.out.println(e.getMessage());
                        return user;
                    }
                }
        }
    }

    private static int getChoice(String[] values) {
        if (values[0].equals("register") && values.length == 3) return 0;
        if (values[0].equals("login") && values.length == 3) return 1;
        return -1;
    }
}
