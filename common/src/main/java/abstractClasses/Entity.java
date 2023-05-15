package abstractClasses;

import interfaces.ICollision;
import utilities.Shapes;
import utilities.Type;
import utilities.image.Image;
import utilities.image.ImageLoader;

import utilities.Vector2D;

import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class Entity implements ICollision {
    private int health;
    private Image sprite;
    private Type type;
    private int[] position;
    private double[] scale;
    private double acceleration;
    private double maxSpeed;
    // Instead of radians, I have made a Shape-Array that contains shapes. The shapes in the array will be the mapImages
    private double radians = 0;
    private Vector2D direction;
    private Shapes[] shape;
    
    public Entity(){
        this(-1);
    }
    public Entity(int health){
        this(health, null, Type.UNDEFINED);
    }
    public Entity(int health, URL sprite, Type type){
        this(health,sprite,type,new double[]{1,1});

    }
    public Entity(int health, URL sprite, Type type, double[] scale){
        this(health,sprite,type,scale,1,10);
    }
    
    public Entity(int health, URL sprite, Type type, double[] scale, double acceleration, double maxSpeed){
        this.health = health;
        this.scale = scale;
        this.acceleration = acceleration;
        this.maxSpeed   = maxSpeed;
        this.direction = new Vector2D(0,0);
        this.position = new int[]{0,0};
        this.type = type;

        if(sprite == null){
            sprite = Entity.class.getResource("/commonImages/placeholder.png");
        }
        // the shapes-array fetches the sprite, that relates to the image and gets the values of the width && height
        this.sprite = ImageLoader.loadImage(sprite,scale);
        this.shape = new Shapes[]{
                new Shapes(this.sprite.getImage().getWidth(),this.sprite.getImage().getHeight())
        };
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(URL sprite, double[] scale, boolean updateShape) {
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(sprite, this.scale);
        if (updateShape){
            setShape(new Shapes[]{new Shapes(getSprite().getImage().getWidth(),getSprite().getImage().getHeight(),getPosition(), getType())});
        }
    }

    public void setSprite(BufferedImage bufferedImage, double[] scale, boolean updateShape) {
        this.scale = scale;
        this.sprite = ImageLoader.loadImage(bufferedImage, this.scale);
        if (updateShape){
            setShape(new Shapes[]{new Shapes(getSprite().getImage().getWidth(),getSprite().getImage().getHeight(),getPosition(), getType())});
        }
    }

    public void setPosition(int[] position) {
        this.position = position;
        // Checks if there are only 1 object in the array
        // This way we can make sure that the thing is a shape and not a map since a map contains multiple collidable
        // types
        // The object will be added to the shape-array
        if (this.shape.length == 1) {
            this.shape[0].setPosition(position);
        }

    }

    public int[] getPosition() {
        return position;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getRadians() {
        return radians;
    }

    public void setRadians(double radians) {
        this.radians = radians;
    }

    public Vector2D getDirection() {
        return direction;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }


    public void setShape(Shapes[] shape) {
        this.shape = shape;
    }

    public void setShape(){
        this.shape = new Shapes[]{
                new Shapes(this.sprite.getImage().getWidth(),this.sprite.getImage().getHeight())
        };
    }

    public Shapes[] getShape() {
        return shape;
    }

    public void setScale(double[] scale) {
        this.scale = scale;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
