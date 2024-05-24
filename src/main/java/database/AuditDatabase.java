package database;

import functions.user_functions.Pagination;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import user.User;

public final class AuditDatabase {
    public static void create() {
        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            System.out.println("Connected to database....");
            try (Statement statement = connection.createStatement()) {
                String AuditTable =
                        """
                            CREATE TABLE IF NOT EXISTS audit (
                                user VARCHAR(255),
                                command VARCHAR(255),
                                success BOOLEAN
                            )
                        """;
                statement.execute(AuditTable);
                System.out.println("Audit table created....");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(User user, String command, Boolean success) {
        String user_name = user.getUser();

        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            String insertIntoStatement =
                    "INSERT INTO audit (user, command, success) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoStatement);

            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, command);
            preparedStatement.setBoolean(3, success);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while inserting into audit table: " + e.getMessage());
        }
    }

    public static void print(String user) {
        try (Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql = "SELECT * FROM audit WHERE user = '" + user + "'";
            resultSet = statement.executeQuery(sql);
            List<String> results = new ArrayList<>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(resultSet.getString(i)).append("\t");
                }
                results.add(row.toString());
            }

            Pagination<String> pagination = new Pagination<>(results, 10, Function.identity());
            pagination.display();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
