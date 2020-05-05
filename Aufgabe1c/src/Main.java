/**
 * Main Class, entry point of the input reader function tool to build the database and fill it with given data
 */
public class Main {

    public static void main(String[] args) {
    // Use/test the data input reader functions

        // Start with the Entities
        EntityReader.readPerson();
        EntityReader.readPlace();
        EntityReader.readTag();
        EntityReader.readTagClass();
        EntityReader.readOrganisation();
        EntityReader.readForum();
        EntityReader.readPost();
        EntityReader.readComment();
        // Continue with the relations
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
        RelationReader.readPostHasTagTag();
    }
}
