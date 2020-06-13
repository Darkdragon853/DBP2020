import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "forum")
public class Forum {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "creationDate", nullable = false)
    private Timestamp creationDate;

    // ForeignKey
    @Column(name = "moderator")
    private int moderator;

    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getModerator() {
        return moderator;
    }

    public void setModerator(int moderator) {
        this.moderator = moderator;
    }
}
