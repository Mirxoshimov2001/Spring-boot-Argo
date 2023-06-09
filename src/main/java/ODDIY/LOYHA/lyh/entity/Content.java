package ODDIY.LOYHA.lyh.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Content")
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "quality")
    private long quality;

    @Column(name = "price")
    private String price;

    @Column(name = "classes")
    private String classes;

    @Column(name = "HashID")
    private String contentFileHashId;

    @Column(name = "aboutContent")
    private String aboutContent;

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }

    public String getContentFileHashId() {
        return contentFileHashId;
    }

    public void setContentFileHashId(String contentFileHashId) {
        this.contentFileHashId = contentFileHashId;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getQuality() {
        return quality;
    }

    public void setQuality(long quality) {
        this.quality = quality;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", quality=" + quality +
                ", price='" + price + '\'' +
                ", classes='" + classes + '\'' +
                ", contentFileHashId='" + contentFileHashId + '\'' +
                ", aboutContent='" + aboutContent + '\'' +
                '}';
    }
}
