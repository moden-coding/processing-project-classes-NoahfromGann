import processing.core.PApplet;

public class Circle {

    private float x;
    private float y;
    private float size;
    private PApplet canvas;
    private int color;
    private int points = 3;
    private float createTime;
    private float yVelocity;

    public Circle(float xPos, float yPos, PApplet S) {
        x = xPos;
        y = yPos;
        size = 50;
        canvas = S;
        color = S.color(0, 255, 128);
        points = 3;

        createTime = S.millis();
        yVelocity = 6f;


    }

    public void display() {
        canvas.fill(color);
        canvas.circle(x, y, size);
        gravity();

    }

    public boolean circleFound(int mouseX, int mouseY) {
        return (mouseX >= x - size / 2 && mouseX <= x + size / 2 && mouseY >= y - size / 2 && mouseY <= y + size / 2);
    }

    public boolean shouldDisappear() {
        float currentTime = canvas.millis();
        float lifetime = (currentTime - createTime) / 1000.0f;

        return lifetime > 4;

    }

    public void gravity() {
        y += yVelocity;
    }

    public int getPoints() {
        return points;
    }
}
