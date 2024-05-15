package authentification.functions.user_functions;

import authentification.users.Roles;
import authentification.users.User;

import java.util.Scanner;

public class User_functions {
    public static User functions(User user){
        while(user.getRole()== Roles.standard_user){
            Scanner sc = new Scanner(System.in);
            String sequence=sc.nextLine();
            String[] values=sequence.split(" ");
            int choice=0;
            if(values[0].equals("search"))
                choice=1;
            if(values[0].equals("create") && values[1].equals("playlist"))
                choice=2;
            if(values[0].equals("add"))
                choice=3;
            if(sequence.equals("logout") )
                choice=4;
            switch(choice){
                case 4:{
                    user= User.builder().role(Roles.anonim).build();
                    System.out.println("Successfully logged out.");
                    return user;
                }
            }

        }
        return null;
    }
}
