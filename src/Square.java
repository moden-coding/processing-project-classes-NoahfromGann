import processing.core.PApplet;

public class Square {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int color;
    // private int squareWidth;
    // private int squareHeight;

    

    public Square(int xPos, int yPos, PApplet S ) {
        x = xPos;
        y = yPos;
        // size = 50;
        // squareWidth = 50;
        // squareHeight = 50;
        size = 50;
        canvas = S;
        color = canvas.color(255,255,0);
        // color = canvas.color(211,211,211, 5);

        canvas.fill (color);

    }

    public void display() {
        canvas.fill(color);

        canvas.rect(x,y,size,size);
       
    }

}
