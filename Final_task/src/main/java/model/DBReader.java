package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private String DBUrl = "jdbc:mysql://localhost:3306/auto_user?useSSL=false&serverTimezone=UTC";
    private String DBLogin = "root";
    private String DBPassword = "f579_Ack321";

    public List<User> getDBUsers(String query) {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBUrl, DBLogin, DBPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setNote(resultSet.getString("note"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
