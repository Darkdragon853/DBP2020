import java.sql.*;

/**
 * class to establish the database connection
 */
public class DBConnection {

        Connection database = null;

            public DBConnection() {
                try{
                    Class.forName("org.postgresql.Driver"); // Load the Driver
                    database = DriverManager.getConnection("jdbc:postgresql:social_network", "postgres", "Latarius853");
                } catch(ClassNotFoundException cnfe){
                    System.out.println("Driver ist not available!\n" + cnfe.getMessage());
                } catch(SQLException sqle) {
                    System.out.println("Database could not be accessed!\n" + sqle.getMessage());
                } catch(Throwable t) {
                    System.out.println("Unestimated Failure.\n" + t.getMessage());
                }

                // Connection to Database established.

            }
}
