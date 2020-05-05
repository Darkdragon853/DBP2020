import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * holds the readers for the relations
 */
public class RelationReader {

    /*static void readPersonStudyAtOrganisation() {
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

    }
    static void readPersonWorkAtOrganisation() {
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

    }
    static void readTagClassIsSubclassOfTagClass() {
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

    }
    static void readTagHasTypeTagClass() {
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

    }
    static void readPersonSpeaksLanguage() {
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

             *//*
                insertStatement = "UPDATE person SET speaks = "+ items[1] + " WHERE id= "+ items[0] + ";";
                Statement statement = null;
                try {
                    statement = database.createStatement();
                    int result = statement.executeUpdate(insertStatement);
                } catch (SQLException sqle) {
                    System.out.println("Fehler beim Statement erzeugen oder Befehl ausführen: " + sqle.getMessage());
                    failures++;
                    failString=insertStatement;
                }*//*

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
    static void readCommentHasTagTag() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO COMMENT_HASTAG_TAG(comment_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        System.out.println("readCommentHasTagTag() mit "+ (failures)+ " Fehlern abgeschlossen.");

    }
    static void readForumHasMemberPerson() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO FORUM_HASMENBER_PERSON(forum_id, person_id, join_date) VALUES (" + items[0] + ", " + items[1] + ", " + items[2] + ");";

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
        System.out.println("readForumHasMemberPerson() mit "+ (failures)+ " Fehlern abgeschlossen.");

    }
    static void readForumHasTagTag() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO FORUM_HASTAG_TAG(forum_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        System.out.println("readForumHasTagTag() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPersonEmailEmailAdress() {
        // TODO: Testen
        int failures = 0;

        File file = new File("./../Ressources/social_network/person_email_emailaddress_0_0.csv");
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

                insertStatement = "INSERT INTO PERSON_EMAIL_EMAILADDRESS(person_id, email) VALUES (" + items[0] + ", " + items[1] + ");";

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
        System.out.println("readPersonEmailEmailAdress() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPersonHasInterestTag() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_HASINTEREST_TAG(person_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        System.out.println("readPersonHasInterestTag() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPersonKnowsPerson() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_KNOWS_PERSON(person_id, person_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", " + items[2] + ");";

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
        System.out.println("readPersonKnowsPerson() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPersonLikesComment() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_LIKES_COMMENT(person_id, comment_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", " + items[2] + ");";

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
        System.out.println("readPersonLikesComment() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPersonLikesPost() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO PERSON_LIKES_POST(person_id, post_id, creation_date) VALUES (" + items[0] + ", " + items[1] + ", " + items[2] + ");";

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
        System.out.println("readPersonLikesPost() mit "+ (failures-1)+ " Fehlern abgesclossen.");

    }
    static void readPostHasTagTag() {
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
        try {
            while ((currentLine = br.readLine()) != null) {
                // Hier die momentane Eingabezeile verarbeiten
                // Obviously we have to Split the Lines by '|'
                String[] items = currentLine.split("\\|");

                insertStatement = "INSERT INTO POST_HASTAG_TAG(post_id, tag_id) VALUES (" + items[0] + ", " + items[1] + ");";

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
        System.out.println("readPostHasTagTag() mit "+ (failures-1)+ " Fehlern abgeschlossen.");

    }*/
}
