import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    // ForeignKey
    @Column(name = "city_id")
    private int city_id;

    @Column(name = "url")
    private String url;


    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
