import processing.core.PApplet;

public class Square {

    private float x;
    private float y;

    private float size;
    private PApplet canvas;
    private int color;
    private boolean isShrinking = false;
    private int points = 1;
    private float createTime;

    private boolean isGolden = false;
    private boolean isBlack = false;

    public Square(float xPos, float yPos, PApplet S) {
        x = xPos;
        y = yPos;
        size = 50;

        canvas = S;
        color = S.color(S.random(0, 255), S.random(0, 255), S.random(0, 255));
        S.fill(color);

        createTime = S.millis();

    }

    public void display() {
        canvas.fill(color);
        canvas.rect(x, y, size, size);

        if (isShrinking && size > 15) {
            size = size - 0.5f;
        }

    }

    public boolean shouldDisappear() {
        float currentTime = canvas.millis();
        float lifetime = (currentTime - createTime) / 1000.0f;

        if (isShrinking && lifetime > 1) {
            return true;
        }
        if (isGolden && lifetime > 2) {
            return true;

        }
        if (isBlack && lifetime > 3) {
            return true;
        }
        return false;
    }

    public boolean squareFound(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size);
    }

    public void howlikely(float howlikely) {
        if (canvas.random(1) < howlikely) {
            makeBlackSquare();
        } else {
            color = canvas.color(canvas.random(0, 255), canvas.random(0, 255), canvas.random(0, 255));
        }
    }

    public void makeBlackSquare() {

        color = canvas.color(0, 0, 0);
        points = -2;
        isBlack = true;

    }

    public void makeGoldenSquare() {
        color = canvas.color(255, 215, 0);
        points = 3;
        isGolden = true;
    }

    public void makeShrinkingSquare() {
        color = canvas.color(147, 112, 219);
        points = 10;
        isShrinking = true;

    }

    public boolean isBlack() {
        return isBlack;
    }

    public int getPoints() {
        return points;
    }

    public boolean isShrinking() {
        return isShrinking;
    }

}
