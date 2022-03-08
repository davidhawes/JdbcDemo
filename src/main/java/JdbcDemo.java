import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {
        System.out.println("JDBC Demo");
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'David Hofmann', 'dhofmann@tsn.at'), (NULL, 'Max Mustermann', 'mmustermann@tsn.at');
        selectAllDemo();
    }

    public static void selectAllDemo(){
        System.out.println("Select DEMO mit JDBC");
        String sqlSelectAllPersons = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectAllPersons);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: {ID} " + id + " {NAME} " + name + " {EMAIL} " + email);
            }
        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur Datenbank!: " + e.getMessage());
        }
    }
}
