import java.sql.*;

/**
 * class to establish the database connection
 */
public class DBConnection {

        Connection database = null;

            public DBConnection() {
                this.database = database;
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

                try {
                    PreparedStatement st = database.prepareStatement("SELECT * FROM PERSON");
                    ResultSet resultSet = st.executeQuery();

                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt(1) + resultSet.getString(2));
                    }
                    System.out.println("Ja moin!");

                    // Ordnungsgemäß abschließen
                    resultSet.close();
                    st.close();
                }
                catch (SQLException sqle) {
                    System.out.println("Database Access Error or Closed Connection or no ResultSet returned: " + sqle.getMessage());
                } catch (Throwable t) {
                    System.out.println("Unhandled Error: " + t.getMessage());
                }
            }
}
