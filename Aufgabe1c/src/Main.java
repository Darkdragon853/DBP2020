import java.io.*;
import java.sql.*;
// TODO: 2-fache Ausführung von ReadPlaces noch notwendig, da Kontinente erst zuletzt gelesen werden.
// TODO: TAG name Limit erweitern, urls überall mit rein, University Name länger


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

        // Funktionen nutzen / testen
        boolean test = readPersonStudyAtOrganisation();


    }


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
                String[] items = currentLine.split("\\|");

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
        // TODO: Soweit 100% fertig
        // Ganz vorne Extradurchlauf der nur auf Kontinente zielt
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\place_0_0.csv");
        BufferedReader br = null;

        // Erster Durchlauf
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        String currentLine;
        String insertStatement = ("");
        try {
            while ((currentLine = br.readLine()) != null) {
                //System.out.println(currentLine); -- Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'. In addition we need to replace ' by ` bc it interacts like the end of a string
                String[] items = currentLine.split("\\|");

                if(items[3].trim().equals("continent")) {
                    insertStatement = "INSERT INTO CONTINENT(id, name) VALUES (" + items[0] + ", \'" + items[1] + "\');";

                } else {
                    // Alles was kein Kontinent ist interessiert uns hier nicht
                    continue;
                }

                // Befehl ist bereit, nun STatement vorbereiten

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }

        // Zweiter Durchlauf
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        // Reset
        currentLine = "";
        insertStatement = "";
        try {
            while ((currentLine = br.readLine()) != null) {
                //System.out.println(currentLine); -- Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'. In addition we need to replace ' by ` bc it interacts like the end of a string
                String[] items = currentLine.split("\\|");

                if(items[3].trim().equals("continent")) {
                    // Kontinente sind schon drin
                    continue;

                } else if(items[3].trim().equals("country")) {
                    insertStatement = "INSERT INTO COUNTRY(id, name, continent_id) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', " + items[4] + ");";

                } else if(items[3].trim().equals("city")) {
                    insertStatement = "INSERT INTO CITY(id, name, country_id) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', " + items[4] + ");";

                } else {
                    System.out.println("Irgendwas außer City, Continent oder Country gelesen");
                }

                // Befehl ist bereit, nun STatement vorbereiten

                Statement statement = null;
                int result = -1;
                try {
                    statement = database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }

        System.out.println("readPlaces() mit " + (failures-1) + " Fehlern abgeschlossen."); // 7 Fehler ist normal wegen 1. Zeile und 6 Kontinenten die schon da sind
        return finalResult;
    }

    private static boolean readTags() {
        // TODO: Ca. 90 % Fertig. limit noch erhöhen und prüfen

        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\tag_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO TAG(id, name) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\');";

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }

                System.out.println(currentLine); // --Debug
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTags() mit "+ (failures-1)+ " Fehlern abgesclossen.");

        return finalResult;
    }

    private static boolean readTagClasses() {
        // TODO: Seems like 100%
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\tagclass_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO TAGCLASS(id, name) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\');";


                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }

                System.out.println(currentLine); // --Debug
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }


        System.out.println("readTagClasses() mit " + (failures-1) + " Fehlern abgeschlossen.");

        return finalResult;
    }

    private static boolean readOrganisations() {
        // TODO: Namenlänge anpassen, nochmal testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\organisation_0_0.csv");
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
                System.out.println(currentLine); // --Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                if(items[1].trim().equals("company")) {
                    insertStatement = "INSERT INTO COMPANY(id, name, country_id) VALUES (" + items[0] + ", \'" + items[2].replace("'", "`") + "\', " + items[4] + ");";

                } else if(items[1].trim().equals("university")) {
                    insertStatement = "INSERT INTO UNIVERSITY(id, name, city_id) VALUES (" + items[0] + ", \'" + items[2].replace("'", "`") + "\', " + items[4] + ");";

                } else {
                    System.out.println("Irgendwas außer university oder company gelesen");
                }
                //System.out.println("momentanter Befehl: " + insertStatement);

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readOrganisations() mit "+ (failures-1) + " Fehlern abgeschlossen.");
        return finalResult;
    }

    private static boolean readForums() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\forum_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO FORUM(id, title, creationDate, moderator) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', \'" + items[2] + "\', " + items[3] + ");";

                System.out.println(currentLine); // -- Debug

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readForums() mit  " + (failures-1) + " Fehlern abgeschlossen.");

        return finalResult;
    }

    private static boolean readPersonStudyAtOrganisation() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\person_studyAt_organisation_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_STUDYAT_UNIVERSITY(person_id, university_id, classYear) VALUES (" + items[0] + ", " + items[1] +", " + items[2] + ");";

                System.out.println(currentLine); // -- Debug

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonStudyAtOrganisation() mit  " + (failures-1) + " Fehlern abgeschlossen.");

        return finalResult;
    }

    private static boolean readPersonWorkAtOrganisation() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\person_workAt_organisation_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_WORKAT_COMPANY(person_id, company_id, workFrom) VALUES (" + items[0] + ", " + items[1] +", " + items[2] + ");";

                System.out.println(currentLine); // -- Debug

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonWorkAtOrganisation() mit  " + (failures-1) + " Fehlern abgeschlossen.");

        return finalResult;
    }

    private static boolean readTagClassIsSubclassOfTagClass() {
        // TODO: Testen

        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\tagclass_isSubclassOf_tagclass_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO TAGCLASS_ISSUBCLASSOF_TAGCLASS(tag_parent_id, tag_child_id) VALUES (" + items[0] + ", " + items[1] + ");";

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }

                System.out.println(currentLine); // --Debug
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTagClassIsSubclassOfTagClass() mit "+ (failures-1)+ " Fehlern abgesclossen.");

        return finalResult;
    }

    private static boolean readTagHasTypeTagClass() {
        // TODO: Testen

        boolean finalResult = true;
        int failures = 0;

        File file = new File("C:\\Coding\\Datenbankpraktikum\\Ressources\\social_network\\tag_hasType_tagclass_0_0.csv");
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
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO TAG_HASTYPE_TAGCLASS(tag_id, tagclass_id) VALUES (" + items[0] + ", " + items[1] + ");";

                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }

                System.out.println(currentLine); // --Debug
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTagHasTypeTagClass() mit "+ (failures-1)+ " Fehlern abgesclossen.");

        return finalResult;
    }



}
