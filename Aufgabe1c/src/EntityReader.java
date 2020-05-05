import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;

/**
 * holds the reader for the entity types
 */
public class EntityReader {
    DBConnection con = new DBConnection();

    void readPerson() {
        // TODO: NOCH TESTEN
        File file = new File("./../Ressources/social_network/person_0_0.csv");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");


                String timestamp = Utils.getTimestamp(items[5]);

                // Sprachen werden nachgeholt also einfach Filler verwenden
                insertStatement = "INSERT INTO person(id,  firstName, lastName, gender, birthday, creationDate, locationIP, browserUsed, city_id, email, speaks,) VALUES ("
                        + items[0] + ", \'"  + items[1] + "\', \'" + items[2] + "\',\' " + items[3] + "\',\' " + items[4] + "\', \'" +
                timestamp + "\', \'" + items[5] + "\', \'" + items[6] + "\', " + items[7] + ", \'" + "{filler@gmx.de}" + "\', \'" + "{filler}" + "\');";

                /* safetycopy insertStatement = "INSERT INTO person(id, creationDate, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, city_id) VALUES ("
                        + items[0] + ",\' " + timestamp + "\',\' " + items[1] + "\',\' " + items[2] + "\',\' " + items[3] + "\',\' " + items[4] + "\', \'" + "{filler@gmx.de}" + "', \'" + "{filler}" + "\',\' " +
                        items[7] + "\',\' " + items[6] + "\', " + items[8] + ");"; */

                System.out.println(currentLine);
                System.out.println(insertStatement);

                Statement statement = null;
                int result = -1;
                try {
                    statement = con.database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                }
                System.out.println("Antwort auf SQL Befehl: " + result);
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
    }
    void readPlace() {
        // TODO: Soweit 100% fertig
        // Ganz vorne Extradurchlauf der nur auf Kontinente zielt
        int continentFailures = 0;
        int otherFailures = 0;

        File file = new File("./../Ressources/social_network/place_0_0.csv");
        BufferedReader br = null;

        // Erster Durchlauf
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                //System.out.println(currentLine); -- Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'. In addition we need to replace ' by ` bc it interacts like the end of a string
                String[] items = currentLine.split("\\|");

                if (items[3].trim().equals("continent")) {
                    insertStatement = "INSERT INTO continent(id, name, url) VALUES (" + items[0] + ", \'" + items[1]  + "\' , \'" + items[2] + "\');";

                } else {
                    // Alles was kein Kontinent ist interessiert uns hier nicht
                    continue;
                }

                // Befehl ist bereit, nun STatement vorbereiten

                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    continentFailures++;
                }
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }

        // Zweiter Durchlauf
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }
        // Reset
        currentLine = "";
        insertStatement = "";
        iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                //System.out.println(currentLine); -- Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'. In addition we need to replace ' by ` bc it interacts like the end of a string
                String[] items = currentLine.split("\\|");

                if (items[3].trim().equals("continent")) {
                    // Kontinente sind schon drin
                    continue;

                } else if (items[3].trim().equals("country")) {
                    insertStatement = "INSERT INTO country(id, name, url, continent_id) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', \'" + items[2] + "\'," + items[4] + ");";

                } else if (items[3].trim().equals("city")) {
                    insertStatement = "INSERT INTO city(id, name, url, country_id) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', \'" + items[2] + "\'," + items[4] + ");";

                } else {
                    System.out.println("Irgendwas außer City, Continent oder Country gelesen");
                }

                // Befehl ist bereit, nun STatement vorbereiten

                Statement statement = null;
                int result = -1;
                try {
                    statement = con.database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    otherFailures++;
                }
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }

        System.out.println("readPlaces() mit " + (continentFailures) + " Fehlern bei den Kontinenten und " + (otherFailures) + " anderen Fehlern abgeschlossen."); // 7 Fehler ist normal wegen 1. Zeile und 6 Kontinenten die schon da sind
    }
    void readTag() {
        // TODO: Ca. 90 % Fertig. limit noch erhöhen und prüfen
        // TODO: urls als solche einlesen
        // TODO: search for Facefucker

        int failures = 0;
        File file = new File("./../Ressources/social_network/tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String failString = null;
        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");
                //normalize strings with NFD
                String currentName = Normalizer.normalize(items[1], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                insertStatement = "INSERT INTO tag(id, name) VALUES (" + items[0] + ", \'" + currentName.replace("'", "`") + "\');";

                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                    failString = insertStatement;
                }

                //System.out.println(currentName); // --Debug
                System.out.println("...");
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTags() mit " + (failures) + " Fehlern abgeschlossen. \n");
        if (failString != null) {
            System.out.println("Fehler bei: " + failString);
        }
    }
    void readTagClass() {
        // TODO: Seems like 100%

        int failures = 0;
        File file = new File("./../Ressources/social_network/tagclass_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO tagclass(id, name) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\');";


                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }

                System.out.println(currentLine); // --Debug
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTagClasses() mit " + (failures) + " Fehlern abgeschlossen.");
    }
    void readOrganisation() {
        // TODO: Namenlänge anpassen, nochmal testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/organisation_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                System.out.println(currentLine); // --Debug
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                if (items[1].trim().equals("company")) {
                    insertStatement = "INSERT INTO company(id, name, country_id) VALUES (" + items[0] + ", \'" + items[2].replace("'", "`") + "\', " + items[4] + ");";

                } else if (items[1].trim().equals("university")) {
                    insertStatement = "INSERT INTO university(id, name, city_id) VALUES (" + items[0] + ", \'" + items[2].replace("'", "`") + "\', " + items[4] + ");";

                } else {
                    System.out.println("Irgendwas außer university oder company gelesen");
                }

                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readOrganisations() mit " + (failures) + " Fehlern abgeschlossen.");
    }
    void readForum() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/forum_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");
                String timestamp = Utils.getTimestamp(items[2]);

                insertStatement = "INSERT INTO forum(id, title, creationDate, moderator) VALUES (" + items[0] + ", \'" + items[1].replace("'", "`") + "\', \'" + timestamp + "\', " + items[3] + ");";

                System.out.println(currentLine); // -- Debug

                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readForums() mit  " + (failures) + " Fehlern abgeschlossen.");
    }
    void readPost() {
        //TODO: recheck method

        int failures = 0;
        File file = new File("./../Ressources/social_network/post_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");
                String timestamp = Utils.getTimestamp(items[2]);

                insertStatement = "INSERT INTO post(id, imageFile, creationDate, locationIP, browserUsed, language, content, length, creator, Forum.id, place) VALUES ("
                        + items[0] + ", \'" + items[1] + "\', \'" + timestamp + "\', \'" + items[3] + "\', \'" + items[4] + "\', \'" + items[5] + "\', \'" + items[6] + "\', " + items[7] + ", " + items[8] + ", " + items[9] + ", " + items[10] + ");";

                Statement statement = null;
                int result = -1;
                try {
                    statement = con.database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
                System.out.println("Antwort auf SQL Befehl: " + result);
                System.out.println(currentLine);
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPost() mit " + (failures) + " Fehlern abgeschlossen.");
    }
    void readComment() {

        int failures = 0;
        File file = new File("./../Ressources/social_network/comment_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage());
        }

        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");
                String timestamp = Utils.getTimestamp(items[1]);

                insertStatement = "INSERT INTO comment(id, creationDate, locationIP, browserUsed,  content, length, creator, place, replyOfPost, replyOfComment) VALUES ("
                        + items[0] + ", \'" + timestamp + "\', \'" + items[2] + "\', \'" + items[3] + "\', \'" + items[4] + "\', " + items[5] + ", " + items[6] + ", " + items[7] + ", " + items[8] + ", " + items[9] + ");";


                System.out.println(currentLine);

                Statement statement = null;
                int result = -1;
                try {
                    statement = con.database.createStatement();
                    result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                }
                System.out.println("Antwort auf SQL Befehl: " + result);
            }
        } catch (IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readComment() mit " + (failures) + " Fehlern abgeschlossen.");
    }
}