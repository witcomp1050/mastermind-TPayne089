package edu.wit.comp1050;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class Main extends Application {
    

    public static void main (String[] args) {
        Application.launch(args);

        Scanner input = new Scanner(System.in);
        int[] code = GameCode.stringCodeToIntCode(GameCode.createSecretCode());
        int[] guess = new int[4];

        System.out.println("Welcome to Mastermind.\nInput the correct 4 digit code of numbers" +
                " ranging between 1-6 within 10 rounds to win. Ex: 1 2 3 4" +
                "\nBlack pegs: Correct digit + order" +
                "\nWhite pegs: Correct digit\n");
        int blackPegs = 0;
        int tries = 0;
        while (blackPegs != 4 && tries < 10) {
            System.out.println("Input guess:");
            for (int i = 0; i < guess.length; i++) {
                guess[i] = input.nextInt();
            }
            int[] test = GameCode.pegFeedback(code, guess);
            System.out.printf("blackPegs %d whitepegs %d%n", test[0],test[1]);
            blackPegs = test[0];
            if (blackPegs == 4) {
                break;
            }
            tries++;
            System.out.printf("Number of tries left: %d%n",10 - tries);
        }
        System.out.println((blackPegs == 4)? "You win!" : "You lose!\nThe code was: ");
        for (int i = 0; i < code.length; i++) {
            System.out.print(code[i]);
        }


        //Commented out code below is for manually inputting a code for testing purposes
//        System.out.println("Input secret code");
//        for (int i = 0; i < code.length; i++) {
//            code[i] = input.nextInt();
//        }
        //testing methods in GameCode
//        System.out.println(GameCode.createSecretCode());
//        int[] testingMethod = GameCode.stringCodeToIntCode(GameCode.createSecretCode());
//        for (int i = 0; i < testingMethod.length; i++) {
//            System.out.println(testingMethod[i]);
//        }
    }


    //JAVAFX GUI CODE
    public void start(Stage startStage) throws Exception {
        //Gave up on scenebuilder and fxml
//        Parent root = FXMLLoader.load(getClass().getResource("Mastermind Game.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Mastermind Game");
        AnchorPane welcomeStage = new AnchorPane();
        Scene startScene = new Scene(welcomeStage, 650, 700);

        Label instructions = new Label();
        instructions.setLayoutX(200);
        instructions.setLayoutY(50);
        instructions.setText("Welcome to Mastermind.\n" +
                "Input the correct 4 color code within 10 rounds to win.\n" +
                "Black pegs: Correct color + order\n" +
                "White pegs: Correct color\n");
        welcomeStage.getChildren().add(instructions);

        //Button that will close the instructions screen and open the game screen
        Button startButton = new Button("Play");
        startButton.setLayoutX(200);
        startButton.setLayoutY(200);
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(100);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                makeGameStage();
            }
        });
        welcomeStage.getChildren().add(startButton);

        primaryStage.setScene(startScene);
        primaryStage.show();

    }
    //This is the stage that the game will be played on
    public void makeGameStage() {
        Stage gameStage = new Stage();
        gameStage.setTitle("Mastermind Game");
        AnchorPane gamePane = new AnchorPane();
        Scene gameScene = new Scene(gamePane, 650, 700);

        //Section for adding the rectangle board
        Rectangle boardBackground = new Rectangle(50, 75, 450, 600);
        boardBackground.setFill(Color.GRAY);
        gamePane.getChildren().add(boardBackground);

        //Section for adding circles that the user will use to input secret code colors
        HBox inputCircles = new HBox();
        inputCircles.setSpacing(30);
        inputCircles.setLayoutX(100);
        inputCircles.setLayoutY(600);
        inputCircles.setPrefHeight(100);
        inputCircles.setPrefWidth(200);

        for (int i=0; i < 4; i++) {
            Circle c = new Circle(30);
            inputCircles.getChildren().add(c);

        }
        gamePane.getChildren().add(inputCircles);

        //Section for adding the enter button to submit a guess
        //No functionality implemented yet
        Button enter = new Button("Enter Guess\n(No functionality yet)");
        enter.setPrefWidth(130);
        enter.setPrefHeight(75);
        enter.setLayoutX(510);
        enter.setLayoutY(600);
        gamePane.getChildren().add(enter);

        gameStage.setScene(gameScene);
        gameStage.show();
    }

}
