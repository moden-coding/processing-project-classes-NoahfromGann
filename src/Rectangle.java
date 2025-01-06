import processing.core.PApplet;

//rectangle class
//gives a large teal rectangle in the middle of my game
//teal rectangle is playing board where squares can go
public class Rectangle {

    private float x;
    private float y;
    private PApplet canvas;
    private int color;
    private float width;
    private float height;
    int edgethickness = 6; // didnt end up using 
    
    // makes the rectangle position, size and color
    public Rectangle(float xPos, float yPos, float w, float h, PApplet S) {
        x = xPos;
        y = yPos;
        width = w;
        height = h;
        canvas = S;
        color = canvas.color(0, 150, 150);

    }

    // displays square should have said rectangle 
    public void display() {

        canvas.fill(color);
        
        //dont use 
        canvas.strokeWeight(edgethickness);

        canvas.rect(x, y, width, height);

    }
}



