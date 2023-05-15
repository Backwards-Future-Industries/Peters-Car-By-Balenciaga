package utilities;

public class Shapes {

    int[] position;
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

        setPosition(new int[]{1,1});

    }




    //
    public int[] getPosition() {
        return position;
    }


    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }



}
