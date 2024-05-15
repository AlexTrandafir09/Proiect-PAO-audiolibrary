package authentification.functions.anon_functions;

import authentification.users.All_users;
import authentification.users.Roles;
import authentification.users.User;
import exception.InvalidCommandUsageException;
import exception.UnknownCommandException;

import java.util.Scanner;

public class Anon_functions {
    public static User functions(User user){
        while (user.getRole() == Roles.anonim) {
            Scanner sc = new Scanner(System.in);
            String sequence = sc.nextLine();
            String[] values = sequence.split(" ");
            int choice=-1;
            if (values[0].equals("register") && values.length==3)
                choice=0;
            if (values[0].equals("login") && values.length==3)
                choice = 1;
            switch (choice) {
                case 0: {
                    user = Register.returned(values[1], values[2]);
                    if (user != null) {
                        All_users users = All_users.getInstance();
                        users.addUser(user);
                        return user;
                    }
                    user = User.builder().role(Roles.anonim).build();
                    break;
                }
                case 1: {
                    user = Login.returned(values[1], values[2]);
                    if (user != null)
                        return user;
                    user = User.builder().role(Roles.anonim).build();
                    break;
                }
                default: {
                    try{
                        if((values[0].equals("register")||values[0].equals("login")) && values.length!=3)
                            throw new InvalidCommandUsageException(values[0]);
                        throw new UnknownCommandException();

                    }
                    catch(InvalidCommandUsageException e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                    catch (UnknownCommandException e){
                        System.out.println(e.getMessage());
                        return null;
                    }

                }
            }
        }


    return null;
    }
}
