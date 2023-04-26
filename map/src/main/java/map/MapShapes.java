package map;

import abstractClasses.Shapes;
import interfaces.IDrawable;
import map.tile.Tile;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Objects;



public class MapShapes extends Shapes implements IDrawable {



    int x = 2;
    int y = 2;
    int width = x*x;
    int height = y*y;

    Rectangle[] rectangleArray;

    private MapShapes environmentArray[];

   private Graphics rectangleTest;

    public BufferedImage image;
    JPanel jPanel;
    Tile[] tileArrayRedRectangle;
    Tile[] tileArrayOrangeHexagon;



    public MapShapes() throws IOException {
        super(50,50,25,25);
    }

    public JPanel createJPanel() {
        final int originalTileSize = 16;
        final int scale = 3;

        final int tileSize = originalTileSize * scale;
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;
        return jPanel;
    }

    public Shapes create() {
        Shapes newRectangle;
        try {
            newRectangle = new MapShapes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newRectangle;
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
        g.setColor(Color.GREEN);
        g.fillRect(100,100,50,50);
        g.setColor(Color.RED);
        g.fillRect(250, 50,30,30);
        g.setColor(Color.BLUE);
        g.fillOval(175,300,30,30);
        g.setColor(Color.MAGENTA);
        g.fillRoundRect(250,250,100,100,25,25);
        g.setColor(Color.CYAN);
        Polygon tri = new Polygon(new int[] {0,25,50},new int[]{50,0,50}, 3);
        g.drawPolygon(tri);
        g.fillPolygon(tri);
        g.setColor(Color.ORANGE);
        // Polygon triTest = new Polygon(new int[] {123,432,682,812},new int[]{403,604,805,1007}, 4);
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++)
            hexagon.addPoint((int) (300 + 40 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (300 + 40 * Math.sin(i * 2 * Math.PI / 6)));
        g.drawPolygon(hexagon);
        g.fillPolygon(hexagon);

        Polygon hexagon2 = new Polygon();
        for (int i = 0; i < 6; i++)
            hexagon.addPoint((int) (600 + 70 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (600 + 70 * Math.sin(i * 2 * Math.PI / 6)));
        g.drawPolygon(hexagon2);
        g.fillPolygon(hexagon2);



        g.setColor(Color.MAGENTA);



        tileArrayRedRectangle = new Tile[50];
        tileArrayOrangeHexagon = new Tile[20];
        // loops through array and draws tiles equal to the length of array
        for (int i = 0; i < tileArrayRedRectangle.length; i++) {
            g.setColor(Color.RED);
            g.fillRect(35*i,50,30,30);

        }


        for (int i = 0; i < tileArrayOrangeHexagon.length; i++) {
            g.setColor(Color.ORANGE);
            g.drawPolygon(hexagon);
            g.fillPolygon(hexagon);

            g.drawPolygon(hexagon2);
            g.fillPolygon(hexagon2);

        }
            g.setColor(Color.YELLOW);


        /*
        KAN IKKE FINDE PNG
        Graphics2D g2 = (Graphics2D) g;
        getTileImage();
        drawTiles(g2);
        */

        // INSERT IMAGE DRAW

        /*
        g.fillPolygon(new Polygon(new int[50],new int[50],3));
        g.drawPolygon(new Polygon(new int[50],new int[50],3));
        g.drawPolyline(new int[200],new int[200],33);
        */
    }

    public void drawTiles(Graphics2D g2) {
        g2.drawImage(tileArrayRedRectangle[0].image,0,0,Color.YELLOW,null);

    }

    // KAN IKKE FINDE PNG
    public void getTileImage() {

        try {
            tileArrayRedRectangle[0] = new Tile();
            tileArrayRedRectangle[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/grass.png")));
           // tile[1] = new Tile();
           // tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/earth.png")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




    public void draw2D(Graphics2D graphics2D) {
        graphics2D.draw(rectangleArray[0]);
        graphics2D.draw(rectangleArray[1]);
        graphics2D.draw(rectangleArray[2]);
    }






}
