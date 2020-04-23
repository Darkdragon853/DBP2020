import java.io.*;
import java.sql.*;

// Still only a Try from an Tutorial
public class Main {
    static Connection database = null;

    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver"); // Load the Driver
            database = DriverManager.getConnection("jdbc:postgresql:tempo", "postgres", "Latarius853");
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
            System.out.println("Database Access Error or Closed Connection or no ResultSet returned" + sqle.getMessage());
        } catch (Throwable t) {
            System.out.println("Unhandled Error: " + t.getMessage());
        }

        // Personen einlesen
        if(readPersons()) {
            System.out.println("Personen erfolgreich eingelesen!");
        } else {
            System.out.println("Fehler beim Einlesen der Personen!");
        }


    }


    // Personen einlesen
    private static boolean readPersons() {
        boolean finalResult = true;
        File file = new File("D:\\Universität\\Datenbankpraktikum\\Ressources\\social_network\\person_0_0.csv");
        BufferedReader br = null;
        try {
             br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        String currentLine;

        try {
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }

        // Obviously we have to Split the Lines by '|'
        return finalResult;
    }
}
