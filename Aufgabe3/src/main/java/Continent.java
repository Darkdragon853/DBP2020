import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continent")

public class Continent extends Place {

    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries;

    // Getter und Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
