import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    // int squareX = random(0,800);
    // int squareY = random(0,800);
    
    ArrayList<Square> squares = new ArrayList<Square>();
    // Rectangle rectangle;


    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0, 0, 0);
        // rectangle = new Rectangle(400, 400, this);

        int numSquares = 10;

        squares = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            //ask mr moden about x and y and how to make a grid
            // int x = 100 + (i * 20);
            // int y = 200;
            // squares.add(new Square(x, y, this));
        }


    }

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        if (frameCount%180==0) {
            squareMaker();
            
        }
        for (Square square : squares) {
            square.display();
        }
        // rectMode(CENTER);
        // rectangle.display();
        

    }

    public void squareMaker() {
        
        squares.add(new Square(random(0,800),random(0,800), this));
        
     

    }
    public void mousePressed() {
        for (int i = squares.size() - 1; i >= 0; i--) {
            Square s = squares.get(i);
            if (squares.get(i).goneGame(mouseX, mouseY)) {
                squares.remove(i);
                break; 
            }
        }
    }
    
    
}


