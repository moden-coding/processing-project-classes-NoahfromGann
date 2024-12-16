
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
    ArrayList<Square> squares = new ArrayList<Square>();
    Rectangle gameField;


    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 800);
    }

    public void rectangle() {
        if (stage == 1) {
            gameField.display();
            
        }
    }

    public void setup() {
        

        readHighScore();
        gameStart = millis();
        background(0);
        squares = new ArrayList<>();
        gameField = new Rectangle(50, 50, 700, 700, this);



    }        

    public void draw() {
        background(0);

        System.out.println(score + ", " + highScore);
        fill(255);
        textSize(50);
        saveHighScore();
    
        if (stage == 0) {
            startScreen();
        } else if (stage == 1) {
            mainScreen();
        } else if (stage == 2) {
            endScreen();
        }
    }
    

    public void startScreen() {
        background(0);
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Welcome to the Game!", width / 2, height / 2 - 100);
        textSize(20);
        text("Click to start", width / 2, height / 2);
    }

    public void mainScreen() {



        double timer = (millis() - gameStart) / 1000.0;

        if (timer < 20.0) {
            background(0);
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

    public void squareMaker() {
        squares.add(new Square(random(50, 750), random(50, 750), this));

    }


    public void mousePressed() {
        if (stage == 0) {
            stage = 1;
        } else if (stage == 2) {
            resetGame();
        } else {
            for (int i = squares.size() - 1; i >= 0; i--) {
                if (squares.get(i).goneGame(mouseX, mouseY)) {
                    squares.remove(i);
                    score++;
                    break;
                }
            }
        }
    }

    public void resetGame() {
        stage = 0;
        score = 0;
        squares.clear();
        // setup();
    }

    public void score() {
        score++;
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("Highscore.txt"))) {
            if (scanner.hasNextLine()) {
                highScore = Integer.valueOf(scanner.nextLine());
                System.out.println(highScore);
            } 
            } catch (IOException e) {
            System.out.println("Error reading score file: " + e.getMessage());
        }
    }

    public void saveHighScore() {
    if (score <= highScore) {
        System.out.println("No new high score to save.");
        return;

    }
    if (score > highScore){
        highScore = score;
    }

    String filePath = "Highscore.txt";

    try(PrintWriter writer = new PrintWriter(filePath)) {
        writer.println(score);
        System.out.println("High score successfully saved to " + filePath);
    } catch (FileNotFoundException e) {
        System.out.println("FileNotFoundException: Unable to create or write to file at " + filePath);
    } catch (IOException e) {
        System.out.println("IOException: Problem encountered while writing to file " + filePath);
        e.printStackTrace();
    }
}

    }
    
