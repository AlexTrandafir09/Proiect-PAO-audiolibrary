package functions.user_functions;

import functions.BaseFunctions;
import user.User;

public class UserFunctions extends BaseFunctions {

    public static User functions(User user, String[] values) {
        return BaseFunctions.common(user, values, new UserFunctions());
    }

    @Override
    protected int getChoice(String[] values) {
        int choice = 0;
        if (values[0].equals("search") && (values[1].equals("name") || values[1].equals("author")))
            choice = 1;
        if (values[0].equals("create") && values[1].equals("playlist")) choice = 2;
        if (values[0].equals("add") && (values[1].equals("byName") || values[1].equals("byId")) && values.length>=4)
            choice = 3;
        if (values[0].equals("logout")) choice = 4;
        if (values[0].equals("list") && values[1].equals("playlists")) choice = 5;
        if (values[0].equals("export") && values[1].equals("playlist")) choice = 6;
        return choice;
    }
}
