import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {

    int stage = 0;
    int score = 0;
    int highScore = 0;
    double gameStart;
    int gameMode = 0;
    ArrayList<Square> squares = new ArrayList<Square>();
    ArrayList<ReactionGame> circles = new ArrayList<ReactionGame>();
    Rectangle gameField;
    float nextCircleTime;
    float circleX, circleY;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        readHighScore();
        gameStart = millis();
        background(0);
        squares = new ArrayList<>();
        gameField = new Rectangle((width - 650) / 2, (height - 650) / 2, 650, 650, this);
        circles = new ArrayList<>();

    }

    public void draw() {
        background(0);

        fill(255);
        textSize(50);

        if (stage == 0) {
            startScreen();
        } else if (stage == 1 && gameMode == 2) {
            reactionGame();
        } else if (stage == 1 && gameMode == 1) {
            mainScreen();
        } else if (stage == 2) {
            saveHighScore();
        } else if (stage == 3) {
            endScreen();
        }
    }

    public void startScreen() {
        background(0);
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Welcome to the Game Hub!", width / 2, height / 2 - 100);
        textSize(20);
        text("Click mouse to start the Square Game", width / 2, height / 2);
        text("Press SPACE to start the Reaction Game", width / 2, height / 2 + 50);

    }

    public void mainScreen() {
        gameField.display();

        double timer = (millis() - gameStart) / 1000.0;

        if (timer < 10.0) {
            background(0);
            rectangle();

            for (Square square : squares) {
                square.display();
            }

            fill(255);
            textSize(20);
            text("" + (int) (timer * 10.0) / 10.0, width - 50, 50);
            text("Score: " + score, 50, 50);
        } else {
            stage = 2;
        }

        if (frameCount % 90 == 0) {
            squareMaker();
        }
    }

    public void endScreen() {
        background(255);
        fill(0);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2 - 100);
        text("Final Score: " + score, width / 2, height / 2);
        text("High Score: " + highScore, width / 2, height / 2 + 50);
        text("Click to restart", width / 2, height / 2 + 100);
    }

    public void rectangle() {
        if (stage == 1) {
            gameField.display();
        }
    }

    public void squareMaker() {
        squares.add(new Square(random(50, 650), random(65, 650), this));

    }

    public void mousePressed() {
        if (stage == 0 && gameMode == 0) {
            gameMode = 1;
            stage = 1;
        } else if (stage == 3) {
            resetGame();
        } else {
            if (gameMode == 1) {

                for (int i = squares.size() - 1; i >= 0; i--) {
                    if (squares.get(i).goneGame(mouseX, mouseY)) {
                        squares.remove(i);
                        score++;
                        break;
                    }
                }
            }
        }
    }

    public void keyPressed() {
        if (stage == 0 && key == ' ') {
            gameMode = 2;
            stage = 1;
            gameStart = millis();
            score = 0;
        }
    }

    public void resetGame() {
        stage = 0;
        score = 0;
        squares.clear();
        gameStart = millis();
        gameMode = 0;
    }

    public void score() {
        score++;
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("Highscore.txt"))) {
            if (scanner.hasNextLine()) {
                highScore = Integer.valueOf(scanner.nextLine());
                // System.out.println(highScore);
            }
        } catch (IOException e) {
            System.out.println("Error reading score file: " + e.getMessage());
        }
    }

    public void saveHighScore() {
        if (score <= highScore) {
            System.out.println("No new high score to save.");
            stage = 3;
            return;
        }
        if (score > highScore) {
            highScore = score;
        }

        String filePath = "Highscore.txt";

        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(score);
            System.out.println("High score successfully saved to " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: Unable to create or write to file at " + filePath);
        } catch (IOException e) {
            System.out.println("IOException: Problem encountered while writing to file " + filePath);
            e.printStackTrace();
        }
        stage = 3;
    }

    

    public void reactionGameScreen() {
        if (gameMode == 1) {
            reactionGame();
        }
        // background(0);
        // fill(255);
        // textAlign(CENTER, CENTER);
        // textSize(30);
        // text("Reaction Game!", width / 2, height / 2 - 50);
        // text("", width / 2, height / 2);

        double timer = (millis() - gameStart) / 1000.0;
        text("" + (int) (timer * 10.0) / 10.0, width - 50, 50);

        if (timer > 5.0) {
            stage = 2;
        }

    }

    public void reactionGame() {
        background(0);
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(30);
        text("Reaction Game!", width / 2, 50);

        for (int i = 0; i < 4; i++) {
            circles.add(new ReactionGame(this));
            ReactionGame c = circles.get(i);
            c.display();
        }
        // System.out.println("working");

        nextCircleTime = millis() + (int) random(3000, 10000);

        if (millis() > nextCircleTime) {
        circleX = random(width);
        circleY = random(height);
        fill(255, 0, 0);
        ellipse(circleX, circleY, 50, 50);


        }
    }

}
