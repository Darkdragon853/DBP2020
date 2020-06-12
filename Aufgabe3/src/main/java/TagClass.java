import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


 @Entity
 @Table(name = "tagclass")
 public class TagClass {

     @Id
     @Column(name = "id")
     private int id;

     @Column(name = "name")
     private String name;

     @Column(name = "url")
     private String url;


     // Getter and Setters
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

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }

}
