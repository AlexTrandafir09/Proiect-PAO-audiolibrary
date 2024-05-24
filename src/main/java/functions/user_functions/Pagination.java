package functions.user_functions;

import audiolibrary.playlist.Playlist;
import audiolibrary.song.Song;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Pagination<T> {

    private final List<T> items;
    private final int itemsPerPage;
    private final Function<T, String> itemDisplayFunction;

    public Pagination(List<T> items, int itemsPerPage, Function<T, String> itemDisplayFunction) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.itemDisplayFunction = itemDisplayFunction;
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("Page 0 out of 0 (" + itemsPerPage + " items per page): ");
            return;
        }

        int totalPages = (items.size() + itemsPerPage - 1) / itemsPerPage;
        int currentPage = 1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int start = (currentPage - 1) * itemsPerPage;
            int end = Math.min(start + itemsPerPage, items.size());
            System.out.println(
                    "Page "
                            + currentPage
                            + " out of "
                            + totalPages
                            + " ("
                            + itemsPerPage
                            + " items per page): ");

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + itemDisplayFunction.apply(items.get(i)));
            }

            if (currentPage >= totalPages) {
                break;
            }

            System.out.println("To return the next page, enter the appropriate command:");

            String expectedCommand, expectedCommand1;
            if (items.getFirst() instanceof Song) {
                expectedCommand = "search id " + (currentPage + 1);
                expectedCommand1 = "search name " + (currentPage + 1);
                System.out.println("search name " + (currentPage + 1));
            } else if (items.getFirst() instanceof Playlist) {
                expectedCommand = "list playlists " + (currentPage + 1);
                expectedCommand1 = null;
                System.out.println("list playlists " + (currentPage + 1));
            } else {
                break;
            }

            String command = scanner.nextLine().trim().toLowerCase();

            if (!command.equals(expectedCommand) && !command.equals(expectedCommand1)) {
                break;
            }
            currentPage++;
        }
    }
}
