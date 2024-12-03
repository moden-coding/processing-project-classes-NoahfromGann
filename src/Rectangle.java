import processing.core.PApplet;

public class Rectangle {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int color;
    private int rectWidth;
    private int rectHeight;

    public Rectangle(int xPos, int yPos, PApplet S ) {
        x = xPos;
        y = yPos;
        rectWidth = 300;
        rectHeight = 300;
        size = 100;
        canvas = S;
        color = canvas.color(255,255,0, 128);
        canvas.fill (color);

    }

    public void display() {
        canvas.fill(color);
        canvas.rect
       
    }
}

