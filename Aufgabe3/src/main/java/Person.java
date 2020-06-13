import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    private int id;

    // Passt das so?
    @Column(name = "creationDate", nullable = false)
    private Timestamp creationDate;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "gender", nullable = false, length = 7)
    private String gender;

    // passt das so?
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    // passt das so?
    @Column(name = "email")
    private String[] emails;

    @Column(name = "speaks", nullable = false)
    private String[] speaks;

    @Column(name = "browserUsed", nullable = false, length = 50)
    private String browserUsed;

    @Column(name = "locationIP", nullable = false, length = 40)
    private String locationIP;

    // ForeignKey
    @Column(name = "city_id")
    private int city_id;

    // Constraints fehlen noch.



    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public String[] getSpeaks() {
        return speaks;
    }

    public void setSpeaks(String[] speaks) {
        this.speaks = speaks;
    }

    public String getBrowserUsed() {
        return browserUsed;
    }

    public void setBrowserUsed(String browserUsed) {
        this.browserUsed = browserUsed;
    }

    public String getLocationIP() {
        return locationIP;
    }

    public void setLocationIP(String locationIP) {
        this.locationIP = locationIP;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
