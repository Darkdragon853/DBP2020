public interface PersonRelatedAPI {
    void getProfil(Long personID);
    void getCommonInterestsOfMyFriends(Long personID);
    void getCommonFriends(Long personID);
    void getPersonsWithMostCommonInterests(Long personID);
    void getJobRecommendation(Long personID);
    void getShorthestFriendshipPath(Long personID);
}
