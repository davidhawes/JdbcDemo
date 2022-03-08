import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {
        System.out.println("JDBC Demo");
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'David Hofmann', 'dhofmann@tsn.at'), (NULL, 'Max Mustermann', 'mmustermann@tsn.at');
        selectAllDemo();
        insertStudentDemo("Name des Studenten", "prof@email.com");
        selectAllDemo();
        updateStudentDemo("Neuer Name", "email@mail.com", 6);
        selectAllDemo();
        deleteStudentDemo(5);
    }

    public static void deleteStudentDemo(int StudentId){

        System.out.println("DELETE DEMO mit JDBC");
        String deletePerson = "DELETE FROM `student` WHERE `student`.`id` = ?";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(deletePerson);

            try{
                preparedStatement.setInt(1, StudentId);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: " + rowAffected);
            } catch (SQLException e){
                System.out.println("Fehler im SQL-DELETE Statement: " + e.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur Datenbank!: " + e.getMessage());
        }

    }

    public static void updateStudentDemo(String neuerName, String neueEmail, int id){

        System.out.println("UPDATE DEMO mit JDBC");
        String updatePerson = "UPDATE `student` SET `name` = ?, `email` = ? WHERE `student`.`id` = ?";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(updatePerson);

            try{
                preparedStatement.setString(1, neuerName);
                preparedStatement.setString(2, neueEmail);
                preparedStatement.setInt(3, id);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: " + affectedRows);
            } catch (SQLException e){
                System.out.println("Fehler im SQL-UPDATE Statement: " + e.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur Datenbank!: " + e.getMessage());
        }


    }

    public static void insertStudentDemo(String name, String email){
        System.out.println("INSERT DEMO mit JDBC");
        String insertPerson = "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(insertPerson);

            try{
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze eingefügt!");
            } catch (SQLException e){
                System.out.println("Fehler im SQL-INSERT Statement: " + e.getMessage());
            }

        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur Datenbank!: " + e.getMessage());
        }
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
