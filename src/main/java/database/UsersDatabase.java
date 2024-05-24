package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import user.Roles;
import user.User;

public class UsersDatabase {
    public static void create() {
        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            try (Statement statement = connection.createStatement()) {
                String UsersTable =
                        """
                            CREATE TABLE IF NOT EXISTS users (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                username VARCHAR(255),
                                password VARCHAR(255),
                                role VARCHAR(255)
                            )
                        """;
                statement.execute(UsersTable);
                System.out.println("Users table created.....");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(User user) {
        String user_name = user.getUser();
        String password = user.getPassword();
        String role;

        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            String nr_of_users = "SELECT COUNT(*) FROM users";
            PreparedStatement countStatement = connection.prepareStatement(nr_of_users);
            ResultSet resultSet = countStatement.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                role = Roles.admin_user.toString();
            } else {
                role = Roles.standard_user.toString();
            }

            String insertIntoStatement =
                    "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoStatement);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();

            System.out.println("Insertion successful.");
        } catch (SQLException e) {
            System.out.println("Error while inserting into users table: " + e.getMessage());
        }
    }

    public static List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            String selectQuery = "SELECT * FROM users";
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");

                    User user =
                            User.builder()
                                    .id(id)
                                    .user(username)
                                    .password(password)
                                    .role(Roles.valueOf(role))
                                    .build();
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving users from database: " + e.getMessage());
        }

        return userList;
    }

    public static void updateRole(int userId) {
        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            String updateStatement = "UPDATE users SET role = ? WHERE id = ?";
            try (PreparedStatement preparedStatement =
                    connection.prepareStatement(updateStatement)) {
                preparedStatement.setString(1, Roles.admin_user.toString());
                preparedStatement.setInt(2, userId);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println(userId + " is now an administrator! ");
                } else {
                    System.out.println("Specified user does not exist!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while updating user role to ADMIN: " + e.getMessage());
        }
    }
}
