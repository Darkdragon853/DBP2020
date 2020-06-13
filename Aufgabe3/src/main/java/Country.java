import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // ForeignKey
    @Column(name = "continent_id")
    private int continent_id;

    @Column(name = "url")
    private String url;

    // Getter und Setters

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

    public int getContinent_id() {
        return continent_id;
    }

    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
