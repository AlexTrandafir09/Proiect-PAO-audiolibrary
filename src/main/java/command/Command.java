package command;

import java.util.Scanner;

public class Command {
    public static String[] returned() {
        Scanner sc = new Scanner(System.in);
        String sequence = sc.nextLine();
        return sequence.split(" ");
    }
}
