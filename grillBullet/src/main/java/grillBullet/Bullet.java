package grillBullet;

import abstractClasses.Entity;

public class Bullet extends Entity implements IDrawable, IPlugin, IProcessing{
    private static final URL sprite = Bullet.class.getResource("images/bullet.png");

    public Bullet(){
        this(new int[]{0,0},new Vector2D(1.0,1.0));

    }

    public Bullet(int[] position, Vector2D direction){
        super(1, sprite, Types.BULLET, new double[]{0.01, 0.01});
        setPosition(position);
        setDirection(direction);
        setMaxSpeed(3);
        setAcceleration(1);
    }

    @Override
    public void draw(Graphics2D g, JPanel panel, GameData gameData) {
        int[] position = getPosition();
        g.drawImage(getSprite().getImage(), position[0], position[1], panel);
    }

    @Override
    public Layers getLayer() {
        return Layers.MIDDLEGROUND;
    }


    @Override
    public Entity create(GameData gameEngine) {
        Entity newBullet;
        newBullet = new Bullet(getPosition(), getDirection());
        return newBullet;
    }

    @Override
    public Entity delete(GameData gameData) {
        return null;
    }

    @Override
    public void process(ArrayList<Inputs> inputs, GameData gameData) {
        for (IMovement iMovement : getPlugin()){
            setPosition(iMovement.defaultMove(new ArrayList<>(List.of(Inputs.KEY_W)), this,gameData));
        }
    }

    @Override
    public String toString(){
        return Types.BULLET.toString();
    }
    private Collection<IMovement> getPlugin(){
        return SPIlocator.locateAll(IMovement.class);
    }
}
