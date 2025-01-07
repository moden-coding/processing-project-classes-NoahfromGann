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
import java.util.ArrayList;

import processing.core.*;

//PApplet is an app that makes it easy to draw shapes make them move and use the mouse
public class App extends PApplet {
    

    // score and the game starting and creates an array for squares
    double startTimer = 0.0;
    int stage = 0; // Stage 0 is the startScreen : Stage 1 is instructions : Stage 2 is mainScreen
                   // : Stage 3 is saveHighScore : Stage 4 is endScreen:: my code just commenting
                   // on it
    int lives = 3;
    int score = 0;
    int highScore = 0;

    //oops dont need this becasue i do everything with boolean hard mode true or false
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

    // Prepepares the game
    public void setup() {
        readHighScore();

        background(0);
        gameField = new Rectangle((width - 650) / 2, (height - 650) / 2, 650, 650, this);
        easyButton = new Rectangle(200, 400, 150, 75, this);
        hardButton = new Rectangle(450, 400, 150, 75, this);

    }

    // gGme screens and goes over and over to show the different game screens
    public void draw() {
        background(0);

        if (stage == 0) {
            startScreen();
        } else if (stage == 1) {
            instructionsScreen();
        } else if (stage == 2) {
            mainScreen();
        } else if (stage == 3) {
            saveHighScore();
        } else if (stage == 4) {
            endScreen();
        }
    }

    // instructions of the Hard Mode and Easy Mode and where they are place
    public void instructionsScreen() {
        background(0);
        fill(255);
        textAlign(CENTER, CENTER);
        

        textSize(40);
        if (hardMode == false) {

            //(RGB Came From CHAT GPT I looked up retro game colors)
            fill(0, 191, 255); //blue
            text("Welcome to Easy Mode", width / 2, height / 2 - 220);
            textSize(30);
            fill(255, 20, 147); //pink
            text("In this game the goal is to click", width / 2, height / 2 - 140);
            text("as many squares as possible in 15 seconds", width / 2, height / 2 - 100);
            text("Each square is worth one point", width / 2, height / 2 - 60);
            text("Squares will disappear when clicked", width / 2, height / 2 - 20);
            fill(255, 164, 27);  // Bright orange
            text("Good Luck!", width / 2, height / 2 + 40);

        } else {
            fill(0, 191, 255); //blue
            text("Welcome to Hard Mode!", width / 2, height / 2 - 250);

            textSize(30);
            fill(255, 20, 147); //pink

            text("In this game you need to be careful!", width / 2, height / 2 - 140);
            text("Black squares will subtract points and lives", width / 2, height / 2 - 100);
            text("Golden squares will give you bonus points", width / 2, height / 2 - 60);
            text("Falling circles must be caught for 3 points or you lose a life", width / 2, height / 2 - 20);
            fill(255, 215, 0);
            text("Good Luck!", width / 2, height / 2 + 40);

        }
        fill(255, 215, 0);
        textSize(25);
        text("Press SPACE to start", width / 2, height / 2 + 120);
    }

    // Resets everything when new game
    public void startGame() {
        squares.clear();
        circles.clear();
        score = 0;
        lives = 3;
        startTimer = millis();
    }

    // Shows the welcome screen with easy and hard buttons
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

    // This is where everything in my game is actually happening like my circles,
    // squares and timer
    

    public void mainScreen() {
        gameField.display();

        double timer = (millis() - startTimer) / 1000.0;

        //Shows me how timer and millis are different and how millis starts right when i click play or recent but timer starts at 0 and starts when my actual game starts 
        // System.out.println("Timer = " + timer);
        // System.out.println("Millis = " + millis());

        if (timer < 15.0) {
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
                // size says how many things are in your array of weird things
                for (int i = 0; i < circles.size(); i++) {
                    Circle circle = circles.get(i);
                    circle.display();
                    float yPos = circle.loseLives();

                    if (yPos >= 800) {
                        circleLives(yPos);
                        circles.remove(i);

                    }

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
            stage = 3;
        }

        if (frameCount % 30 == 0) {
            squareMaker();
            if (hardMode && frameCount % 90 == 0) {
                circleMaker();

            }
        }
    }

    // Shows the game over screen with your score
    public void endScreen() {
        background(0);
        textSize(40);
        textAlign(CENTER, CENTER);
        fill(255,0,0);
        text("Game Over", width / 2, height / 2 - 100);
        text("Final Score: " + score, width / 2, height / 2);
        if (score == highScore) {
            textSize(40);
            fill(0,255,0);
            text("YOU ROCK! YOU GOT A NEW HIGH SCORE!! ", width / 2, TOP + 100);

        }

        text("High Score: " + highScore, width / 2, height / 2 + 50);
        text("Click to restart", width / 2, height / 2 + 100);
    }

    // Displays my rectangle
    public void rectangle() {
        if (stage == 2) {
            gameField.display();
        }
    }

    // Makes new squares appear in random spots
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
                newSquare.makeShrinkingSquare();

            } else if (chance < 0.6f) {
                newSquare.makeGoldenSquare();
            }
        }
        squares.add(newSquare);
    }

    // Makes circles drop from the top of the screen and where the circle can go in
    // the rectangle
    public void circleMaker() {
        float fieldX = (width - 625) / 2;
        float fieldWidth = 620;

        float randomX = random(fieldX + 25, fieldX + fieldWidth - 25);
        float startY = (height - 625) / 2;

        Circle newCircle = new Circle(randomX, startY, this);
        circles.add(newCircle);
    }
 
    // Checks if you clicked any squares
    public void checkSquares() {
        for (int i = 0; i < squares.size(); i++) {
            //not possible for human to click in half a second so square.size() will never be 0
            //in fact its value changes all the time depending on the time and how many squres you get rid of
            Square currentSquare = squares.get(i); 
            // int j = squares.size();
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

    // Checks if you clicked any circles in hard mode
    //when you click the mouse in stage 2 or 3 and not in 1 or 4 
    public void checkCircles() {
        for (int i = 0; i < circles.size(); i++) {
            Circle currentCircle = circles.get(i);
            if (currentCircle.circleFound(mouseX, mouseY)) {
                score = score + currentCircle.getPoints(); //could havre just siad score = score + 3 but now I can change the amount of points clicking on a cricle gives you if i did a more advanced version of the game 
                circles.remove(i);

            }

        }
    }

    // Handles all mouse clicks for buttons and game objects
    public void mousePressed() {
        if (stage == 0) {
            if (mouseX >= 200 && mouseX <= 350 && mouseY >= 400 && mouseY <= 475) {
                // gameMode = 1;
                hardMode = false;
                stage = 1;
                startGame();
            } else if (mouseX >= 450 && mouseX <= 600 && mouseY >= 400 && mouseY <= 475) {
                // gameMode = 2;
                hardMode = true;
                stage = 1;
                startGame();

                // pushing buttons to start game
            }
        } else if (stage == 4) {
            resetGame();
        } else {
            checkSquares();
            checkCircles();
        }
    }

    public void keyPressed() {
        if (stage == 1) {
            if (key == ' ') {
                stage = 2;
                //oops dont need this next line 
                startGame();
            }
        }

    }

    // Puts everything back to the start when you want to play again

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

    // Gets the highest score from the file
    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("Highscore.txt"))) {
            if (scanner.hasNextLine()) {
                highScore = Integer.valueOf(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading score file: " + e.getMessage());
        }
    }

    // Saves your score if you beat the high score

    public void saveHighScore() {
        if (score <= highScore) {
            System.out.println("No new high score to save.");
            stage = 4;
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

        stage = 4;
    }

    // circle lives removing
    // Losing a life for circle
    public void circleLives(float circleY) {
        if (circleY >= 800) {
            lives--;

            if (lives <= 0) {
                stage = 3;
            }
            

        }

    }   


}


