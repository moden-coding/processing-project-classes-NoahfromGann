import processing.core.*;

public class App extends PApplet {
    Square square1;
    Square square2;
    Rectangle rectangle;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        square1 = new Square(50, 50, this);
        square2 = new Square(100,100, this);
        rectangle = new Rectangle(200, 200, this);

        background(0, 0, 0);
    }

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        square1.display();
        square2.display();

        rectangle.display();

    }

    public void squareMaker() {

        // for(int i = 0; i < 160; i+= 2) {
        // Square squares = new Square(50* (i % 20) + 20 ,100 * (i/20) + 20, this );
        // squares.display();
        // }

    }
}
