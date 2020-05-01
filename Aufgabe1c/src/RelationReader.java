import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * holds the readers for the relations
 */
public class RelationReader {

    static boolean readPersonStudyAtOrganisation() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("./../Ressources/social_network/person_studyAt_organisation_0_0.csv");
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
                    statement = DBConnection.database.createStatement();
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
    static boolean readPersonWorkAtOrganisation() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("./../Ressources/social_network/person_workAt_organisation_0_0.csv");
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
                    statement = DBConnection.database.createStatement();
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
    static boolean readTagClassIsSubclassOfTagClass() {
        // TODO: Testen
        boolean finalResult = true;
        int failures = 0;

        File file = new File("./../Ressources/social_network/tagclass_isSubclassOf_tagclass_0_0.csv");
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
                    statement = DBConnection.database.createStatement();
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
    static boolean readTagHasTypeTagClass() {
        // TODO: Testen

        boolean finalResult = true;
        int failures = 0;

        File file = new File("./../Ressources/social_network/tag_hasType_tagclass_0_0.csv");
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
                    statement = DBConnection.database.createStatement();
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
    static boolean readPersonSpeaksLanguage() {

        boolean finalResult = true;
        int failures = 0;

        File file = new File("./../Ressources/social_network/person_speaks_language_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
        }

        String failString= null;
        String currentLine;
        String insertStatement = ("");
        int iteration = 0;
        try {
            while ((currentLine = br.readLine()) != null) {
                //skip first line in csv file
                if (iteration==0){
                    iteration++;
                    continue;
                }
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                // SELECT statement to get speaks entries
                try{
                    String checkStatement = "SELECT speaks FROM person WHERE id =" + items[0] + ";";
                    Statement checkCurrent = DBConnection.database.createStatement();
                    ResultSet currentLangs = checkCurrent.executeQuery(checkStatement);
                    while (currentLangs.next()) {
                        System.out.println(currentLangs.getString(1));
                    }
                }catch (Throwable t){
                    t.printStackTrace();
                }

             /*
                insertStatement = "UPDATE person SET speaks = "+ items[1] + " WHERE id= "+ items[0] + ";";
                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                    failString=insertStatement;
                }*/

                //System.out.println(currentName); // --Debug
                System.out.println("...");
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTags() mit "+ (failures)+ " Fehlern abgeschlossen. \n");
        if (failString != null){
            System.out.println("Fehler bei: " + failString);
        }

        return finalResult;
    }
    static boolean readComment() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;

    }
    static boolean readCommentHasTag() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readForumHasMemberPerson() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readForumHasTagTag() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPersonEmailEmailAdress() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPersonHasInterestTag() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPersonKnowsPerson() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPersonLikesComment() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPersonLikesPost() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
    static boolean readPostHasTagTag() {
        //TODO: write method
        boolean finalResult = true;
        int failures = 0;

        return finalResult;
    }
}
