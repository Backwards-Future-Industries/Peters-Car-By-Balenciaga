package utilities;

public class Shapes {

    int[] position;
    Point[] rectangle;
    int height;
    int width;

    Type type;

    public Shapes(int width, int height) {
        this(width,height,new int[]{0,0}, Type.UNDEFINED);
    }

    public Shapes(int width, int height, int[] position, Type type) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.type = type;
        this.rectangle = new Point[4];

        rectangle[0] = new Point(position[0],position[1]);
        rectangle[1] = new Point(position[0], width);
        rectangle[2] = new Point(width,height);
        rectangle[3] = new Point(height,position[1]);

    }


    public Point[] getPositions(double rotation) {
        for(int i = 1; i<rectangle.length; i++){
            double doubleNewX = rectangle[i].x + Math.cos(rotation) * (rectangle[i].x-rectangle[0].x) - Math.sin(rotation) * (rectangle[i].y-rectangle[0].y);
            double doubleNewY = rectangle[i].y + Math.sin(rotation) * (rectangle[i].x-rectangle[0].x) - Math.cos(rotation) * (rectangle[i].y-rectangle[0].y);
            int newX = (int) Math.round(doubleNewX);
            int newY = (int) Math.round(doubleNewY);
            rectangle[i].setLocation(new Point(newX,newY));
            }
        return rectangle;
    }


    public void setPosition(int[] position) {
        this.position = position;
        rectangle[0] = new Point(position[0],position[1]);
    }


    public void getType(Types type){
        this.type = type;
    }



    @Override
    public String toString() {
        return "Shapes{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
