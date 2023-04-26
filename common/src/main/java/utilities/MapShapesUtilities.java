package utilities;

import abstractClasses.Entity;
import interfaces.IDrawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapShapesUtilities extends Entity implements IDrawable {

    private MapShapesUtilities environmentArray[];

   public Graphics rectangleTest;

   public MapShapesUtilities Graphics(int x, int y) {
       this.x = x;
       this.y = y;
       return null;
   }

   public Graphics create(int x, int y, int width, int height){


       return null;
   }


    int x = 2;
    int y = 2;
    int width = x*x;
    int height = y*y;

    // lav createShape
    // oprette

    Rectangle[] rectangleArray;

    public MapShapesUtilities(int health, BufferedImage sprite) {
        super(health, sprite);
    }

    public Rectangle[] getRectangleArray() {
        try {
            rectangleArray[0] = new Rectangle(100,100,50,50);
            // insert type on each rectangle
            rectangleArray[1] = new Rectangle(22,22,33,33);
            rectangleArray[2] = new Rectangle(44,44,66,66);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rectangleArray;
    }
/*
    public void draw(Graphics2D g2) {
        g2.draw(rectangleArray[0]);
        g2.draw(rectangleArray[1]);
        g2.draw(rectangleArray[2]);
    }
 */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(rectangleArray[0]);
        g2.draw(rectangleArray[1]);
        g2.draw(rectangleArray[2]);
    }

}
