import processing.core.PApplet;

//circle class
//Sets size of the different circle and gives the x and y postion of the center of the circle
//where circle can and can't go on the screen
//if you click a circle you lose or gain points 
//puts circle down in certain place
public class Circle {

    private float x;
    private float y;
    private float size;
    private PApplet canvas;
    private int color;
    private int points = 3;
    private float createTime;
    private float yVelocity;
    int lives;

    // defines circle
    public Circle(float xPos, float yPos, PApplet S) {
        x = xPos;
        y = yPos;
        size = 50;
        canvas = S;
        color = S.color(0, 255, 128);
        points = 3;

        createTime = S.millis();
        yVelocity = 4f;

    }

    // displays circle
    public void display() {
        canvas.fill(color);
        canvas.circle(x, y, size);
        gravity();

    }

    // if the MOUSE is in the circle it is true
    public boolean circleFound(int mouseX, int mouseY) {
        return (mouseX >= x - size / 2 && mouseX <= x + size / 2 && mouseY >= y - size / 2 && mouseY <= y + size / 2);
    }

    // after a certain time the circle should disappear
    public boolean shouldDisappear() {
        float currentTime = canvas.millis();
        float lifetime = (currentTime - createTime) / 1000.0f;

        //drops through the bottom of the canvas if yVelocity is fast and time is long 
        return lifetime > 6;

    }

    // gravity of the circle
    public void gravity() {
        y += yVelocity;
    }

    public float loseLives() {
        return y;

    }

    // points for the circle
    public int getPoints() {
        return points;
    }
}
