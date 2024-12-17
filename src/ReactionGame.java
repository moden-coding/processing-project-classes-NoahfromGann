import processing.core.PApplet;

public class ReactionGame {

    PApplet canvas;
    private float nextCircleTime;
    private  float x;
    private  float y;
    
    public ReactionGame(PApplet s) {
        canvas = s; 

    }
    public void display() {
        nextCircleTime = canvas.millis() + canvas.random(3000, 10000);
    }
    public void update() {
                    System.out.println("Working");

        if (canvas.millis() > nextCircleTime) {
            x = canvas.random(canvas.width);
            y = canvas.random(canvas.height);

            canvas.fill(255, 0, 0); 
            canvas.ellipse(x, y, 50, 50); 

            // nextCircleTime = canvas.millis() + canvas.random(3000, 10000); 
        }
    }
    }

