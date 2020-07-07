/**
 * Main Class, entry point of the input reader function tool to build the database and fill it with given data
 */
    // Nomenklatur: Befehle CAPSLOCK, tablenames klein, Attribute in lowercaseCamelCase, foreign keys als Parent.child
public class Main {

    public static void main(String[] args) {
    // Use/test the data input reader functions

        // Ladebalken
        System.out.println("Work: >>>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Done: ");

        EntityReader er = new EntityReader();

        er.readPlace();
        er.readPerson();
        er.readTag();
        er.readTagClass();
        er.readOrganisation();
        er.readForum();
        er.readPost();
        er.readComment();

        // Neu
        er.readEmail();
        er.readLanguages();

        // Continue with the relations

        RelationReader rr = new RelationReader();

        rr.readPersonStudyAtUniversity();
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

        System.out.println("\nEinlesen der Daten abgeschlossen.");


    }
}
