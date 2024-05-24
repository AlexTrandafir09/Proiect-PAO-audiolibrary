package database;

import authentification.User;

import java.sql.*;

public class AuditDatabase {
    public static void CreateAuditTable(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            System.out.println("Connected to database....");
            try (Statement statement = connection.createStatement()) {
                String AuditTable = """
                                    CREATE TABLE IF NOT EXISTS audit (
                                        user VARCHAR(255),
                                        command VARCHAR(255),
                                        success BOOLEAN
                                    )
                                """;
                statement.execute(AuditTable);
                System.out.println("Audit table created....");
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void Insert(User user, String command, Boolean success){
        String user_name = user.getUser();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")){
            String insertIntoStatement = "INSERT INTO audit (user, command, success) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertIntoStatement);

            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, command);
            preparedStatement.setBoolean(3, success);

            preparedStatement.executeUpdate();
        }

         catch (SQLException e) {
            System.out.println("Error while inserting into audit table: " + e.getMessage());
        }
    }

    public static void PrintData(String user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PAO-PROIECT", "user", "1234")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql = "SELECT * FROM audit WHERE user = '" + user + "'";
            resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
