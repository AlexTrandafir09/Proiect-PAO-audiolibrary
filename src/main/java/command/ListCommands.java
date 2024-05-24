package command;

public class ListCommands {
    public static void print(){
        System.out.println("List of commands:");
        System.out.println("register <user> <pass>");
        System.out.println("login <user> <pass>");
        System.out.println("add byName/byId <name/id> songid1 songid2.....");
        System.out.println("create playlist <name>");
        System.out.println("list playlists");
        System.out.println("logout");
        System.out.println("search author/name <key>");
        System.out.println("create song <title> <author> <launch year> ");
        System.out.println("promote <username>");
        System.out.println("audit <username>");
    }
}
