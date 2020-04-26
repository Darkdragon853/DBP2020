import java.io.*;
import java.sql.*;
// TODO: 2-fache Ausführung von ReadPlaces noch notwendig, da Kontinente erst zuletzt gelesen werden.



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
            System.out.println("Database Access Error or Closed Connection or no ResultSet returned: " + sqle.getMessage());
        } catch (Throwable t) {
            System.out.println("Unhandled Error: " + t.getMessage());
        }

        // Personen einlesen
        boolean test = readPlaces();


    }


    // Personen einlesen
    private static boolean readPersons() {
        boolean finalResult = true;
        //File file = new File("D:\\Universität\\Datenbankpraktikum\\Ressources\\social_network\\person_0_0.csv");
        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\person_0_0.csv");
        BufferedReader br = null;
        try {
             br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        String currentLine;
        String insertStatement = ("");
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("|");

                // Sprachen werden nachgeholt also einfach Filler verwenden
                insertStatement = "INSERT INTO PERSON(id, creationDate, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, city_id) VALUES ("
                                          + items[0] +", " + items[5] + ", " + items[1] +", " + items[2] + ", " + items[3] +", " + items[4] +", " + "{\"filler@gmx.de\"}" + ", " +  "{\"notDefined\"}"  + ", " +
                                          items[7] + ", " + items[6] + ", " + items[8] + ");";

                System.out.println(currentLine);
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }


        Statement statement = null;
        int result = -1;
        try {
            statement = database.createStatement();
            result = statement.executeUpdate(insertStatement);
        } catch (SQLException sqle) {
            System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
        }
        System.out.println("Antwort auf SQL Befehl: "+ result);

        return finalResult;
    }

    private static boolean readPlaces() {
        boolean finalResult = true;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\place_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        String currentLine;
        String insertStatement = ("");
        try {
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                for(int element = 0; element < items.length; element++) {
                    System.out.println("Element: " + element + ", Inhalt: " + items[element]);
                }

                System.out.println("Typ: " + items[3]);
                if(items[3].trim().equals("continent")) {
                    insertStatement = "INSERT INTO CONTINENT(id, name) VALUES (" + items[0] + ", \'" + items[1] + "\');";

                } else if(items[3].trim().equals("country")) {
                    insertStatement = "INSERT INTO COUNTRY(id, name, continent_id) VALUES (" + items[0] + ", \'" + items[1] + "\', " + items[4] + ");";

                } else if(items[3].trim().equals("city")) {
                    insertStatement = "INSERT INTO CITY(id, name, country_id) VALUES (" + items[0] + ", \'" + items[1] + "\', " + items[4] + ");";

                } else {
                    System.out.println("Irgendwas außer City, Continent oder Country gelesen");
                }

                System.out.println("BEFEHL: " + insertStatement);

                Statement statement = null;
                int result = -1;
                try {
                    statement = database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                }
                System.out.println("Antwort auf SQL Befehl: "+ result);
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }


        return finalResult;
    }



















































}
