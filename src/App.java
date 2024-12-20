//React Attack
//Rules: 
// Easy Game: 
// 1. You have 15 seconds to click as many squares as possible
// 2. Each square you click is one point
// 3. When you click a square it disappears
// 4. Good Luck!

//

//All of these are used in saving my score and highscore

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import java.util.Scanner;

//From Mr. Moden (what does it do?)
import java.util.ArrayList;

import processing.core.*;

//From Mr. Moden (what does it do?)
//PApplet is an app that makes it easy to draw shapes make them move and use the mouse
public class App extends PApplet {

    // scor and the game starting and creates an array for squares
    double startTimer = 0.0;
    int stage = 0; // Stage 0 is the startScreen with instructions: Stage 1 is mainScreen(where you
                   // play the game ) : Stage 2 endScreen: Stage 3 is endScreen
    int lives = 3;
    int score = 0;
    int highScore = 0;

    int gameMode = 1; // Game mode easy = 1 and Game Mode Hard = 2

    ArrayList<Square> squares = new ArrayList<Square>();
    ArrayList<Circle> circles = new ArrayList<Circle>();

    Rectangle gameField;
    Rectangle easyButton;
    Rectangle hardButton;
    boolean hardMode = false;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    // game window size

    public void settings() {
        size(800, 800);

    }

    public void setup() {
        readHighScore();

        // startTimer = millis();
        // System.out.println(millis());
        // System.out.println("millis = " + millis());

        background(0);
        gameField = new Rectangle((width - 650) / 2, (height - 650) / 2, 650, 650, this);
        easyButton = new Rectangle(200, 400, 150, 75, this);
        hardButton = new Rectangle(450, 400, 150, 75, this);

    }

    // game screens
    public void draw() {
        background(0);

        if (stage == 0) {
            startScreen();
        } else if (stage == 1) {
            mainScreen();
        } else if (stage == 2) {
            saveHighScore();
        } else if (stage == 3) {
            endScreen();
        }
    }

    public void startGame() {
        squares.clear();
        circles.clear();
        score = 0;
        lives = 3;
        startTimer = millis();
    }

    public void startScreen() {
        background(0);
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Welcome to the Game Hub!", width / 2, height / 2 - 100);
        textSize(20);

        easyButton.display();
        hardButton.display();

        fill(255);
        textSize(30);
        textAlign(CENTER, CENTER);
        text("Easy", 275, 440);
        text("Hard", 525, 440);

    }

    public void mainScreen() {
        gameField.display();

        double timer = (millis() - startTimer) / 1000.0;

        if (timer < 30.0) {
            // System.out.println("timer =" + timer);
            background(0);
            rectangle();

            // i is a number that you go over we are getting square zero to square size (i)
            // = num square in the array of squares called squares
            for (int i = 0; i < squares.size(); i++) {
                Square square = squares.get(i);
                if (square.shouldDisappear()) {
                    squares.remove(i);
                } else {
                    square.display();
                }
            }

            if (hardMode) {
                // .size says how many things are in your array of weird things
                for (int i = 0; i < circles.size(); i++) {
                    Circle circle = circles.get(i);
                    circle.display();

                    if (circle.shouldDisappear()) {
                        circles.remove(i);
                    }
                }

            }

            fill(255);
            textSize(20);
            text("" + (int) (timer * 10.0) / 10.0, width - 50, 50);
            text("Score: " + score, 50, 50);

            if (hardMode) {
                textSize(30);
                textAlign(CENTER, CENTER);
                text("Lives: " + lives, width / 2, 50);
            }

        } else {
            stage = 2;
        }

        if (frameCount % 90 == 0) {
            squareMaker();
            if (hardMode && frameCount % 90 == 0) {
                circleMaker();
            }
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
        float fieldX = (width - 625) / 2;
        float fieldY = (height - 625) / 2;

        // defines my play screen
        float fieldWidth = 610;
        float fieldHeight = 610;

        float randomX = random(fieldX + 25, fieldX + fieldWidth - 25);
        float randomY = random(fieldY + 25, fieldY + fieldHeight - 25);

        Square newSquare = new Square(randomX, randomY, this);

        float chance = random(1);
        if (hardMode) {

            // black squares
            if (chance < 0.3f) {
                newSquare.howlikely(0.3f);

                // shrinking square
            } else if (chance < 0.4f) {
                System.out.println("test");
                newSquare.makeShrinkingSquare();

            } else if (chance < 0.6f) {
                System.out.println("test1");
                newSquare.makeGoldenSquare();
            }
        }
        squares.add(newSquare);
    }




    public void circleMaker() {
        // where the circle can go in the rectangle
        float fieldX = (width - 625) / 2;
        float fieldWidth = 620;

        //
        float randomX = random(fieldX + 25, fieldX + fieldWidth - 25);
        float startY = (height - 625) / 2;

        Circle newCircle = new Circle(randomX, startY, this);
        circles.add(newCircle);
    }



    public void checkSquares() {
        for (int i = 0; i < squares.size(); i++) {
            Square currentSquare = squares.get(i);

            if (currentSquare.squareFound(mouseX, mouseY)) {

                if (currentSquare.isBlack()) {

                    if (lives > 1) {
                        lives = lives - 1;

                    } else {
                        lives = 0;
                    }
                    if (score > 2) {
                        score = score - 2;
                    } else {
                        score = 0;
                    }
                    if (lives <= 0) {
                        stage = 3;
                    }
                } else {
                    score = score + currentSquare.getPoints();
                }
                squares.remove(i);
            }
        }
    }

    public void checkCircles() {
        for (int i = 0; i < circles.size(); i++) {
            Circle currentCircle = circles.get(i);
            if (currentCircle.circleFound(mouseX, mouseY)) {
                score = score + currentCircle.getPoints();
            }
            circles.remove(i);
        }
    }
    

    public void mousePressed() {
        if (stage == 0) {
            if (mouseX >= 200 && mouseX <= 350 && mouseY >= 400 && mouseY <= 475) {
                gameMode = 1;
                hardMode = false;
                stage = 1;
                startGame();
            } else if (mouseX >= 450 && mouseX <= 600 && mouseY >= 400 && mouseY <= 475) {
                gameMode = 2;
                hardMode = true;
                stage = 1;
                startGame();

                // pushing buttons to start game
            }
        } else if (stage == 3) {
            resetGame();
        } else {
            checkSquares();
            checkCircles();
        }
    }

  

    public void resetGame() {
        stage = 0;
        score = 0;
        squares.clear();
        circles.clear();
        startTimer = millis();
        gameMode = 0;
        hardMode = false;
    }

    public void score() {
        score++;
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("Highscore.txt"))) {
            if (scanner.hasNextLine()) {
                highScore = Integer.valueOf(scanner.nextLine());
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
        }

        stage = 3;
    }

}
