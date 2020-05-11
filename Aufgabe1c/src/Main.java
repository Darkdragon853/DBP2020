import javax.management.relation.Relation;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Main Class, entry point of the input reader function tool to build the database and fill it with given data
 */
    // TODO: Nomenklatur: Befehle CAPSLOCK, tablenames klein, Attribute in lowercaseCamelCase, foreign keys als Parent.child
    //TODO: Itemnummerierung nach Datensatzangabe
    //TODO: check if Strings are correctly escaped
    // TODO: Filler überarbeiten?
public class Main {

    public static void main(String[] args) {
    // Use/test the data input reader functions

        // Ladebalken
        System.out.println("Work: >>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Done: ");

        // Start with the Entities -- 100% done
        EntityReader er = new EntityReader();


         er.readPlace();
         er.readPerson();
         er.readTag();
         er.readTagClass();
         er.readOrganisation();
         er.readForum();
         er.readPost();
         er.readComment();

        // Continue with the relations

        RelationReader rr = new RelationReader();

        rr.readPersonStudyAtOrganisation();
        rr.readPersonWorkAtOrganisation();
        rr.readTagClassIsSubclassOfTagClass();
        rr.readTagHasTypeTagClass();
        rr.readPersonSpeaksLanguage();
        rr.readCommentHasTagTag();
        rr.readForumHasMemberPerson();
        rr.readForumHasTagTag();
        rr.readPersonHasInterestTag();
        rr.readPersonKnowsPerson();
        rr.readPersonLikesComment();
        rr.readPersonLikesPost();
        rr.readPostHasTagTag();
        rr.readPersonEmailEmailAddress(); // <- Verwirft 2 EInträge da dort doppel @ vorkommt.


        System.out.println("\nEinlesen der Daten abgeschlossen.");
    }
}
