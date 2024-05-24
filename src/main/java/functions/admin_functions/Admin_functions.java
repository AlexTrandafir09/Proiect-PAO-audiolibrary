package functions.admin_functions;

import authentification.User;
import database.AuditDatabase;
import exception.UnknownCommandException;
import functions.user_functions.User_functions;



public final class Admin_functions extends User_functions {
    public static User functions(User user,String[] values) {
        User temp =User_functions.functions(user,values);
        if(temp!=null) {
            user=temp;
            return user;
        }
        int choice = getChoice(values);
        switch (choice) {
            case 7:{
                return Promote.returned(values,user);
            }
            case 8: {
                return CreateSong.returned(user);
            }
            case 9:{
                AuditDatabase.print(values[1]);
                return user;
            }
            default:{
                try{
                    throw new UnknownCommandException();
                }
                catch (UnknownCommandException e){
                    AuditDatabase.insert(user,"unknown command",false);
                    System.out.println(e.getMessage());
                    return user;
                }
            }

        }
    }

    private static int getChoice(String[] values) {
        int choice=0;
        if(values[0].equals("promote"))
            choice=7;
        if(values[0].equals("create") && values[1].equals("song"))
            choice=8;
        if(values[0].equals("audit"))
            choice=9;
        return choice;
    }
}
