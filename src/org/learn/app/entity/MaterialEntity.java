package org.learn.app.entity;

import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Pattern;

@Data
public class MaterialEntity {
    private int id;
    private String title;
    private int countInPack;
    private String unit;
    private int countInStock;
    private int minCount;
    private String description;
    private double cost;
    private String imagePath;
    private String materialType;

    private ImageIcon image;

    public MaterialEntity(int id, String title, int countInPack, String unit, int countInStock, int minCount, String description, double cost, String imagePath, String materialType) {
        this.id = id;
        this.title = title;
        this.countInPack = countInPack;
        this.unit = unit;
        this.countInStock = countInStock;
        this.minCount = minCount;
        this.description = description;
        this.cost = cost;
        this.imagePath = imagePath.replaceFirst(Pattern.quote("\\"), "").replaceAll(Pattern.quote("\\"), "/");
        this.materialType = materialType;

        if (imagePath.equals("нет")) {
            this.imagePath = "materials/picture.png";
        }

        try {
            this.image = new ImageIcon(
                    ImageIO.read(MaterialEntity.class.getClassLoader().getResource(this.imagePath)).getScaledInstance(50, 50, Image.SCALE_DEFAULT)
            );
        } catch (Exception e) {
        }
    }

    public MaterialEntity(String title, int countInPack, String unit, int countInStock, int minCount, String description, double cost, String imagePath, String materialType) {
        this(-1, title, countInPack, unit, countInStock, minCount, description, cost, imagePath, materialType);
    }
}
