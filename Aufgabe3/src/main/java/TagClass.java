import javax.persistence.*;
import java.util.List;


@Entity
 @Table(name = "tagclass")
 public class TagClass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagclassid_generator")
    @SequenceGenerator(name = "tagclassid_generator", sequenceName = "tagclassid_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

     @Column(name = "name", nullable = false, length = 150)
     private String name;

     @Column(name = "url")
     private String url;

     @ManyToMany
     @JoinTable(
             name = "tagclass_isSubclassOf_tagclass",
             joinColumns = { @JoinColumn(name = "tag_parent_id" )},
             inverseJoinColumns = { @JoinColumn(name = "tag_child_id" )}
            )
     private List<TagClass> subTagclasses;

     // Getter and Setters
     public Long getId() {
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }

    public List<TagClass> getSubTagclasses() {
        return subTagclasses;
    }

    public void setSubTagclasses(List<TagClass> subTagclasses) {
        this.subTagclasses = subTagclasses;
    }
}
