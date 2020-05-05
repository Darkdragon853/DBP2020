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
    //TODO: refactor all reader methods to be no more static.. then recheck the main()
public class Main {

    public static void main(String[] args) throws ParseException {
    // Use/test the data input reader functions

        // Start with the Entities
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


        /*
        RelationReader.readPersonStudyAtOrganisation();
        RelationReader.readPersonWorkAtOrganisation();
        RelationReader.readTagClassIsSubclassOfTagClass();
        RelationReader.readTagHasTypeTagClass();
        RelationReader.readPersonSpeaksLanguage();
        RelationReader.readCommentHasTagTag();
        RelationReader.readForumHasMemberPerson();
        RelationReader.readForumHasTagTag();
        RelationReader.readPersonEmailEmailAdress();
        RelationReader.readPersonHasInterestTag();
        RelationReader.readPersonKnowsPerson();
        RelationReader.readPersonLikesComment();
        RelationReader.readPersonLikesPost();
        RelationReader.readPostHasTagTag();*/
    }
}
