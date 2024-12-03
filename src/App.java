import processing.core.*;

public class App extends PApplet{
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        
    }

    public void settings(){
        size(800,800);
    }

    public void draw(){
       squareMaker();
       

    }
    public void squareMaker() {
        Square squares = new Square(50,50,  this );
        squares.display();

    }
}
