import java.sql.*;

// Still only a Try from an Tutorial
public class Main {

    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql:tempo", "postgres", "Latarius853");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + resultSet.getString(2));
            }

        } catch(Throwable t){
            System.out.println("Etwas ist schhief gelaufen!\n" + t.getMessage());
        }

        System.out.println("Ja moin!");
    }
}
