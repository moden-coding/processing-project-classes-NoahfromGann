import processing.core.PApplet;

public class Square {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int color;
    private int squareWidth;
    private int squareHeight;

    

    public Square(int xPos, int yPos, PApplet S ) {
        x = xPos;
        y = yPos;
        squareWidth = 50;
        squareHeight = 100;
        size = 100;
        canvas = S;
        color = canvas.color(255,255,0);
        canvas.fill (color);

    }

    public void display() {
        canvas.fill(color);
        canvas.rect(50,50,200,100);
       
    }

}
