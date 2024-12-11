import processing.core.PApplet;

public class Square {

    private  float x;
    private  float y;
    private  int size;
    private  PApplet canvas;
    private  int color;
    // private int squareWidth;
    // private int squareHeight;

    public Square(float xPos, float yPos, PApplet S) {
        x = xPos;
        y = yPos;
        size = 50;
        canvas = S;

        color = S.color(S.random(0, 255), S.random(0, 255), S.random(0, 255)); 
        canvas.fill(color);

    }

    public void display() {
        canvas.fill(color);

        canvas.rect(x, y, size, size);

    }

    public boolean goneGame(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size);

    }
    

}
