package ru.chervy.demoexam.entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;


public class ProductEntity
{

    private int id;
    private String title;
    private String productType;
    private String articleNumber;
    private String description;
    private String imagePath;
    private int personCount;
    private int workshopNumber;
    private float minimalCostForAgent;
    private ImageIcon image;

    public ProductEntity(int id, String title, String productType, String articleNumber, String description, String imagePath, int personCount, int workshopNumber, float minimalCostForAgent) {
        this.id = id;
        this.title = title;
        this.productType = productType;
        this.articleNumber = articleNumber;
        this.description = description;
        this.imagePath = imagePath;
        this.personCount = personCount;
        this.workshopNumber = workshopNumber;
        this.minimalCostForAgent = minimalCostForAgent;

        this.initImage();
    }

    public ProductEntity(String title, String productType, String articleNumber, String description, String imagePath, int personCount, int workshopNumber, float minimalCostForAgent) {
        this(-1,title, productType, articleNumber,  description,  imagePath,  personCount,  workshopNumber,  minimalCostForAgent);
    }

    private void initImage()
    {
        if(imagePath.isEmpty() || imagePath.isBlank())
        {
            URL url = ProductEntity.class.getClassLoader().getResource("place_holder_picture.png");
            try {
                this.image = new ImageIcon(ImageIO.read(url).getScaledInstance(50,50,Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        URL url = ProductEntity.class.getClassLoader().getResource(this.imagePath);
        if(url != null)
        {
            try {
                this.image = new ImageIcon(ImageIO.read(url).getScaledInstance(50,50, Image.SCALE_DEFAULT));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            url = ProductEntity.class.getClassLoader().getResource("place_holder_picture.png");
            try {
                this.image = new ImageIcon(ImageIO.read(url).getScaledInstance(50,50,Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productType='" + productType + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", personCount=" + personCount +
                ", workshopNumber=" + workshopNumber +
                ", minimalCostForAgent=" + minimalCostForAgent +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && personCount == that.personCount && workshopNumber == that.workshopNumber && Float.compare(that.minimalCostForAgent, minimalCostForAgent) == 0 && Objects.equals(title, that.title) && Objects.equals(productType, that.productType) && Objects.equals(articleNumber, that.articleNumber) && Objects.equals(description, that.description) && Objects.equals(imagePath, that.imagePath) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, productType, articleNumber, description, imagePath, personCount, workshopNumber, minimalCostForAgent, image);
    }

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getWorkshopNumber() {
        return workshopNumber;
    }

    public void setWorkshopNumber(int workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

    public float getMinimalCostForAgent() {
        return minimalCostForAgent;
    }

    public void setMinimalCostForAgent(float minimalCostForAgent) {
        this.minimalCostForAgent = minimalCostForAgent;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
