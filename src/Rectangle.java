import processing.core.PApplet;

public class Rectangle{
  
    private  float x;
    private  float y;
    private  PApplet canvas;
    private  int color;
    private float width;
    private float height;

  



    public Rectangle(float xPos, float yPos, float width, float height, PApplet S) {
        x = xPos;
        y = yPos;
        width = width;
        height = height;
        canvas = S;
        color = canvas.color(245, 66, 66);

    }

    public void display() {
        
        canvas.fill(color);
        canvas.strokeWeight(4); 
        canvas.rect(x, y, width, height);

    }
}
