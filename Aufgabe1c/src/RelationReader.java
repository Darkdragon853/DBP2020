import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * holds the readers for the relations
 */
public class RelationReader {
    DBConnection con = new DBConnection();

    void readPersonStudyAtOrganisation() {
        // TODO: Testen

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

                insertStatement = "INSERT INTO person_studyAt_university(person_id, university_id, classYear) VALUES (" + items[0] + ", " + items[1] +", " + items[2] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonStudyAtOrganisation() mit  " + (failures) + " Fehlern abgeschlossen.");

    }
    void readPersonWorkAtOrganisation() {
        // TODO: Testen

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

                insertStatement = "INSERT INTO person_workAt_company(person_id, company_id, workFrom) VALUES (" + items[0] + ", " + items[1] +", " + items[2] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonWorkAtOrganisation() mit  " + (failures) + " Fehlern abgeschlossen.");

    }
    void readTagClassIsSubclassOfTagClass() {
        // TODO: Testen
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

                insertStatement = "INSERT INTO tagclass_isSubclassOf_tagclass(tag_parent_id, tag_child_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTagClassIsSubclassOfTagClass() mit "+ (failures)+ " Fehlern abgesclossen.");

    }
    void readTagHasTypeTagClass() {
        // TODO: Testen

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

                insertStatement = "INSERT INTO tag_hasType_tagclass(tag_id, tagclass_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readTagHasTypeTagClass() mit "+ (failures)+ " Fehlern abgesclossen.");

    }

    void readPersonSpeaksLanguage() {

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
                String knownSpeaks = "";

                // First we use a SELECT statement to get speaks entries and store them in knownSpeaks
                try{
                    String checkStatement = "SELECT speaks FROM person WHERE id =" + items[0] + ";";
                    Statement checkCurrent = con.database.createStatement();
                    ResultSet currentLangs = checkCurrent.executeQuery(checkStatement);
                    while (currentLangs.next()) {
                        System.out.println(currentLangs.getString(1));


                        // Nächster Schritt: Output anschauen und darauf weiterbauen
                        // Entwurf:
                        // knownSpeaks = currentLangs.getString(1);

                    }
                } catch (Throwable t){
                    t.printStackTrace();
                }
                String newSpeaks = knownSpeaks + items[1];

                insertStatement = "UPDATE person SET speaks = "+ newSpeaks + " WHERE id= "+ items[0] + ";";
                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                    failString=insertStatement;
                }

                //System.out.println(currentName); // --Debug
                System.out.println("...");
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonSpeaksLanguage() mit "+ (failures)+ " Fehlern abgeschlossen. \n");
        if (failString != null){
            System.out.println("Fehler bei: " + failString);
        }
    }

    void readCommentHasTagTag() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/comment_hasTag_tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO comment_hasTag_tag(comment_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readCommentHasTagTag() mit "+ (failures)+ " Fehlern abgeschlossen.");

    }
    void readForumHasMemberPerson() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/comment_hasTag_tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO forum_hasMember_person(forum_id, person_id, join_date) VALUES (" + items[0] + ", " + items[1] + ", \'" + timestamp + "\');";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readForumHasMemberPerson() mit "+ (failures)+ " Fehlern abgeschlossen.");

    }
    void readForumHasTagTag() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/forum_hasTag_tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO forum_hasTag_tag(forum_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readForumHasTagTag() mit "+ (failures)+ " Fehlern abgesclossen.");

    }

    void readPersonEmailEmailAdress()  {

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
                String knownEmails = "";

                // First we use a SELECT statement to get email entries and store them in knownEmails
                try{
                    String checkStatement = "SELECT email FROM person WHERE id =" + items[0] + ";";
                    Statement checkCurrent = con.database.createStatement();
                    ResultSet currentLangs = checkCurrent.executeQuery(checkStatement);
                    while (currentLangs.next()) {
                        System.out.println(currentLangs.getString(1));


                        // Nächster Schritt: Output anschauen und darauf weiterbauen
                        // Entwurf:
                        // knownEmails = currentLangs.getString(1)

                    }
                } catch (Throwable t){
                    t.printStackTrace();
                }

                // Zusammenfügen
                String newMails = knownEmails + items[1];

                insertStatement = "UPDATE person SET email = "+ newMails + " WHERE id= "+ items[0] + ";";
                Statement statement = null;
                try {
                    statement = con.database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                    failString=insertStatement;
                }

                //System.out.println(currentName); // --Debug
                System.out.println("...");
            }
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonEmailEmailaddress() mit "+ (failures)+ " Fehlern abgeschlossen. \n");
        if (failString != null){
            System.out.println("Fehler bei: " + failString);
        }
    }

    void readPersonHasInterestTag() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/person_hasInterest_tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO person_hasInterest_tag(person_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonHasInterestTag() mit "+ (failures) + " Fehlern abgesclossen.");

    }
    void readPersonKnowsPerson() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/person_knows_person_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO person_knows_person(person_id, person_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", \'" + timestamp + "\');";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonKnowsPerson() mit "+ (failures) + " Fehlern abgesclossen.");

    }
    void readPersonLikesComment() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/person_likes_comment_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO person_likes_comment(person_id, comment_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", \'" + timestamp + "\');";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonLikesComment() mit "+ (failures)+ " Fehlern abgesclossen.");

    }
    void readPersonLikesPost() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/person_likes_post_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO person_likes_post(person_id, post_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", \'" + timestamp + "\');";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPersonLikesPost() mit "+ (failures) + " Fehlern abgesclossen.");

    }
    void readPostHasTagTag() {
        // TODO: Testen

        int failures = 0;
        File file = new File("./../Ressources/social_network/post_hasTag_Tag_0_0.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Datei nicht gefunden!\n" + fnfe.getMessage() );
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

                insertStatement = "INSERT INTO post_hasTag_tag(post_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        } catch(IOException ioex) {
            System.out.println("I/O Error aufgetreten!\n" + ioex.getMessage());
        }
        System.out.println("readPostHasTagTag() mit "+ (failures)+ " Fehlern abgeschlossen.");

    }
}
