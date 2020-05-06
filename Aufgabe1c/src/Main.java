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
    //TODO: Skip first line in every csv file
    //TODO: check if Strings are correctly escaped
    //TODO: copypaste the creationDate parser where needed
    //TODO: Cooler Ladebalken per System.out.println
    //TODO: Normalizer
    // TODO: Forum Probleme künstlich?
    // TODO: Filler überarbeiten?
public class Main {

    public static void main(String[] args) {
    // Use/test the data input reader functions

        // Start with the Entities
        EntityReader er = new EntityReader();

        er.readPlace();
//        er.readPerson();
//        er.readTag();
//        er.readTagClass();

        er.readOrganisation();
//        er.readForum();
        // Fehler werden durch die nicht vorhandenen Forums erzeugt
//        er.readPost();
        // Problem: Wenn der letzte Eintrag in der csv nicht gefüllt ist, kommt durch split das letzte item nicht. Mache Fallunterscheidung.
 //       er.readComment();

        // Continue with the relations

        RelationReader rr = new RelationReader();

//        rr.readPersonStudyAtOrganisation();
//        rr.readPersonWorkAtOrganisation();
//        rr.readTagClassIsSubclassOfTagClass();
//        rr.readTagHasTypeTagClass();
//        rr.readPersonSpeaksLanguage();
          // Comment ist noch leer
//        rr.readCommentHasTagTag();
//        rr.readForumHasMemberPerson();
//        rr.readForumHasTagTag();

//        rr.readPersonEmailEmailAdress();
//        rr.readPersonHasInterestTag();
//        rr.readPersonKnowsPerson();
        // Noch ist Comments leer
//        rr.readPersonLikesComment();
        // Noch ist Posts leer
//        rr.readPersonLikesPost();
            // Post noch leer
//        rr.readPostHasTagTag();

    }
}
