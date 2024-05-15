package authentification.users;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString( of = {"id", "user", "role"})
public class User {
    private static int lastId = 0;
    private int id;
    private String user;
    private String password;
    private Roles role;


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id = ++lastId;
        private Roles role = Roles.standard_user;

    }

}
