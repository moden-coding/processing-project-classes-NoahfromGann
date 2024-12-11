import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    int score = 0;
    double gameStart;

    ArrayList<Square> squares = new ArrayList<Square>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0, 0, 0);

        int numSquares = 100;

        squares = new ArrayList<>();
        // for (int i = 0; i < 100; i++) {

        // }

    }

    public void settings() {
        size(800, 800);
    }

    public void draw() {

        if(goneGame){
            score ++;
        }


        background(0);

        fill(255);
        textSize(50);
        double timer = (millis() - gameStart) / 1000.0;

        if (timer < 10.0) {
            for (Square square : squares) {
                square.display();
    
            }
            fill(255);
            text("" + timer, width - 150, 100);

        }
        else{
            endScreen();

        }

        if (frameCount % 90 == 0) {
            squareMaker();

        }
      

    }

    public void endScreen(){
        background(255,255,255);

    }
    public void squareMaker() {
        
        squares.add(new Square(random(0, 800), random(0, 800), this));

    }

    public void mouseMoved() {
        System.out.println(mouseX + mouseY);
    }

    public void mousePressed() {
        for (int i = squares.size() - 1; i >= 0; i--) {
            if (squares.get(i).goneGame(mouseX, mouseY)) {
                squares.remove(i);
                break;
            }
        }
    }

    public void score(){
        score ++;


    }

}
