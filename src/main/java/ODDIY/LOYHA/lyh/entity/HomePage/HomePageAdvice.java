package ODDIY.LOYHA.lyh.entity.HomePage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HomePageAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    private String pictureProduct_hashId;
    private String textProduct;
    private String nameProduct;
    private String aboutUse;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getPictureProduct_hashId() {
        return pictureProduct_hashId;
    }

    public void setPictureProduct_hashId(String pictureProduct_hashId) {
        this.pictureProduct_hashId = pictureProduct_hashId;
    }

    public String getTextProduct() {
        return textProduct;
    }

    public void setTextProduct(String textProduct) {
        this.textProduct = textProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getAboutUse() {
        return aboutUse;
    }

    public void setAboutUse(String aboutUse) {
        this.aboutUse = aboutUse;
    }
}
