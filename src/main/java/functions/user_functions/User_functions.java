package functions.user_functions;

import authentification.User;
import database.AuditDatabase;
import exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class User_functions {
    public static User functions(User user) {
        if (user.getPlaylists() == null) {
            user.setPlaylists(new ArrayList<>());
        }
        Scanner sc = new Scanner(System.in);
        String sequence = sc.nextLine();
        String[] values = sequence.split(" ");
        int choice = getChoice(values, sequence);
        switch (choice) {
            case 1: { ///search
                SearchSongs.returned(user, values[1], values[2]);
                return user;
            }
            case 2: { ///create playlist
                return AddPlaylist.returned(values[2], user);
            }
            case 3: {   ///add song to playlist
                return AddSongToPlaylist.returned(values, user);
            }
            case 4: { ///logout
                return Logout.returned(user);
            }

            case 5: { //list playlists
                ListPlaylists.returned(user);
                return user;
            }
            case 6: { ///export
                ExportPlaylist.returned(values[2], user);
                return user;
            }
            default: {
                try {
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    AuditDatabase.Insert(user, e.getMessage(), false);
                    System.out.println(e.getMessage());
                    return user;
                }
            }

        }

    }

    private static int getChoice(String[] values, String sequence) {
        int choice = 0;
        if (values[0].equals("search") && (values[1].equals("name") || values[1].equals("author")))
            choice = 1;
        if (values[0].equals("create") && values[1].equals("playlist"))
            choice = 2;
        if (values[0].equals("add") && (values[1].equals("byName") || values[1].equals("byId")))
            choice = 3;
        if (sequence.equals("logout"))
            choice = 4;
        if (values[0].equals("list") && values[1].equals("playlists"))
            choice = 5;
        if (values[0].equals("export") && values[1].equals("playlist"))
            choice = 6;
        return choice;
    }
}
