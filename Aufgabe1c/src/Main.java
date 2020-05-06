/**
 * Main Class, entry point of the input reader function tool to build the database and fill it with given data
 */
// TODO: Nomenklatur: Befehle CAPSLOCK, tablenames klein, Attribute in lowercaseCamelCase, foreign keys als Parent.child
//TODO: Itemnummerierung nach Datensatzangabe
// TODO: Forum Probleme künstlich?
// TODO: Filler überarbeiten?
public class Main {

    public static void main(String[] args) {
        // Use/test the data input reader functions

        // Ladebalken
        System.out.println("Work: >>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Done: ");

        // Start with the Entities
        EntityReader er = new EntityReader();

        er.readPlace();
        er.readPerson();
        er.readTag();
        er.readTagClass();
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

//        rr.readPersonEmailEmailAddress();
//        rr.readPersonHasInterestTag();
//        rr.readPersonKnowsPerson();
        // Noch ist Comments leer
//        rr.readPersonLikesComment();
        // Noch ist Posts leer
//        rr.readPersonLikesPost();
        // Post noch leer
//        rr.readPostHasTagTag();

        System.out.println("\nEinlesen der Daten abgeschlossen.");
    }
}