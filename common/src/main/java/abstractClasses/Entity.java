package abstractClasses;

import utilities.Shapes;
import utilities.image.Image;
import utilities.image.ImageLoader;

import utilities.Vector2D;

import java.net.URL;

public abstract class Entity {
    private int health;
    private Image sprite;
    private int[] position;
    private double[] scale;
    private double radius;
    private double acceleration;
    private double maxSpeed;
    // Instead of radians, I have made a Shape-Array that contains shapes. The Shapes will be the mapImages
   // private double radians = 0;
    private Vector2D direction;

    private Shapes[] mapShapes;
    
    public Entity(){
        this(-1);
    }
    public Entity(int health){
        this(health, null);
    }
    public Entity(int health, URL sprite){
        this(health,sprite,new double[]{1,1});

    }
    public Entity(int health, URL sprite, double[] scale){
        this(health,sprite,scale,1,10);
    }
    
    public Entity(int health, URL sprite, double[] scale, int acceleration, int maxSpeed){
        this.health = health;
        this.scale = scale;
        this.acceleration = acceleration;
        this.maxSpeed   = maxSpeed;
        this.direction = new Vector2D(0,0);
        this.position = new int[]{0,0};
        if(sprite == null){
            sprite = Entity.class.getResource("/commonImages/placeholder.png");
        }
        this.sprite = ImageLoader.loadImage(sprite,scale);
        this.mapShapes = new Shapes[]{};

       // radius = 20; //placeholder default value
    }




    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public synchronized Image getSprite() {
        return sprite;
    }

    public void setSprite(URL sprite, double[] scale) {
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(sprite, this.scale);
    }

    public void setPosition(int[] position) {
        this.position = position;

    }

    public int[] getPosition() {
        return position;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    /*
    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }
    */
    public Vector2D getDirection() {
        return direction;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }
}
