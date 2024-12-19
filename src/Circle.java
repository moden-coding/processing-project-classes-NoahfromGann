import processing.core.PApplet;

public class Circle {

    private float x;
    private float y;
    private float size;
    private PApplet canvas;
    private int color;
    private int points;
    private float createTime;

    public Circle(float xPos, float yPos, PApplet S) {
        x = xPos;
        y = yPos;
        size = 50;
        canvas = S;
        color = S.color(0, 255, 128);
        points = -2;

        createTime = S.millis();

    }



    public void display() {
        canvas.fill(color);
        canvas.circle(x, y, size);
    }

    

    public boolean shouldDisappear() {
        float currentTime = canvas.millis();  
        float lifetime = (currentTime - createTime) / 1000.0f;

        return lifetime > 3;  



    }
}

