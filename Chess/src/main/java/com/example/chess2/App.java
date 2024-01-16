package com.example.chess2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
class mt extends Thread{
    public void run(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\04 The Cranberries - Zombie.mp3");
            javazoom.jl.player.Player player = new javazoom.jl.player.Player(fileInputStream);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
public class App extends Application {

    private static Scene scene;
    public static BorderPane borderPane;
    public static Timeline timeline;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        borderPane = new BorderPane();
//        borderPane.getStylesheets().add(getClass().getResource("fxStyle.css").toExternalForm());
//        borderPane.setStyle(".button{\n" +
//                "    -fx-background-color: rgb(17, 94, 6);\n" +
//                "    -fx-border-radius: 0;\n" +
//                "    -fx-background-radius: 0;\n" +
//                "    -fx-background-color: rgb(88, 18, 6);\n" +
//                "    -fx-background-color: rgb(223, 220, 45);\n" +
//                "    -fx-text-fill: white;\n" +
//                "}");
        String musicFile = "04 The Cranberries - Zombie.mp3";
        System.out.println("ooooooooo");
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        System.out.println("uygugy");
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();

        new mt().start();
        Image img = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\xp.jpeg");
        BackgroundSize bsize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,false);
        Background background = new Background(new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,bsize));
        borderPane.setBackground(background);
//        borderPane.setStyle("-fx-background-image: url(D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\xp.jpeg)");
        HBox leftSide = new HBox();
        leftSide.setAlignment(Pos.CENTER);
        leftSide.setPadding(new Insets(10));
        leftSide.setSpacing(10);
        VBox leftLeftSide = new VBox();
        leftLeftSide.setAlignment(Pos.CENTER);
        VBox leftRightSide= new VBox();
        leftRightSide.setAlignment(Pos.CENTER);
        HBox rightSide = new HBox();
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(10));
        rightSide.setSpacing(10);
        VBox rightLeftSide= new VBox();
        VBox rightRightSide= new VBox();
        HBox topSide = new HBox();
        Label label = new Label("30");
        topSide.setAlignment(Pos.CENTER);
        topSide.getChildren().add(label);
        rightLeftSide.setAlignment(Pos.CENTER);
        rightRightSide.setAlignment(Pos.CENTER);
        leftSide.getChildren().addAll(leftLeftSide,leftRightSide);
        rightSide.getChildren().addAll(rightLeftSide,rightRightSide);

        borderPane.setCenter(gridPane);
        borderPane.setLeft(leftSide);
        borderPane.setRight(rightSide);
        borderPane.setTop(topSide);
        timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            //get the current value of the label as an integer
            int value = Integer.parseInt(label.getText());

            //if the value is greater than zero, subtract one and set the new value to the label
            if (value >= 1) {
                value--;
                label.setText(String.valueOf(value));
            } else {
                //if the value is zero, stop the timeline and print something
                if((CustomButton.turn+1)%2==1){
                    AlertBox.show("time ran out","Black won because white didnt play in time");
                    for(int i=0;i<64;i++){
                        gridPane.getChildren().get(i).setDisable(true);
                    }
                }else{
                    AlertBox.show("time ran out","White won because black didnt play in time");
                    for(int i=0;i<64;i++){
                        gridPane.getChildren().get(i).setDisable(true);
                    }
                }
                timeline.stop();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        //play the timeline
        timeline.play();
        // CustomButton button[][] = new CustomButton[8][8];
        // for(int i=0;i<8;i++){
        //     for(int j=0;j<8;j++){
        //         final int I = i;
        //         final int J = j;
        //         button[i][j] = new CustomButton(button,gridPane);
        //         button[i][j].setText(i+" : "+j);
        //         // button[i][j].setStyle("-fx-background-color:grey; "+
        //         //             "-fx-background-radius: 0;"+
        //         //             "-fx-border-color:yellow;");
        //         // button[i][j].setOnAction(e -> {System.out.println("im number "+ I +" "+J);
        //         //     for(int y=0;y<8;y++){
        //         //         for(int x=0;x<8;x++){
        //         //             button[y][x].setStyle("-fx-background-color:grey; "+
        //         //             "-fx-background-radius: 0;"+
        //         //             "-fx-border-color:yellow;");
        //         //         }
        //         //   }
        //         // }
        //         // );
        //         button[i][j].setMinSize(80,80);
        //         gridPane.setPadding(new Insets(8));
        //         gridPane.setAlignment(Pos.CENTER);
        //         gridPane.add(button[i][j], j, i);
        //     }
        // }
        // button[5][6].setStyle("-fx-background-color: blue;");
    //     button[5][6].setStyle("-fx-background-color: blue;");
    //     button[5][6].setStyle("-fx-background-color: grey;");


    //     int x =4;
    //     int y=4;
    //    for(int j=0;j<8;j++){ 
    //    gridPane.getChildren().remove(button[7][j]); 
    //    button[7][j] = new Pawn(button, 1,false);
    //    gridPane.add(button[7][j], j, 7);
    //    } 
    //    for(int j=0;j<8;j++){ 
    //    gridPane.getChildren().remove(button[0][j]); 
    //    button[0][j] = new Pawn(button, 2,false);
    //    gridPane.add(button[0][j], j, 0);
    //    }
    //    gridPane.getChildren().remove(button[4][4]);
    //    button[4][4] = new Bishop(button, 1);
    //    gridPane.add(button[4][4], 4, 4);
    //    gridPane.getChildren().remove(button[3][3]);
    //    button[3][3] = new Rook(button, 2);
    //    gridPane.add(button[3][3], 3, 3);
    //    gridPane.getChildren().remove(button[2][2]);
    //    button[2][2] = new Knight(button, 1);
    //    gridPane.add(button[2][2], 2, 2);
    //    gridPane.getChildren().remove(button[5][5]);
    //    button[5][5] = new Queen(button, 2);
    //    gridPane.add(button[5][5], 5, 5);
       
    //     // button[y][x].setOnAction(e -> {
    //     //     int i=y;
    //     //     int j=x;
    //     //     while( i>=0 && i<=7 && j>=0 && j<=7){
    //     //         button[i][j].setStyle("-fx-background-color: blue");
    //     //         i--;j--;
    //     //         System.out.println("done");
    //     //     }
    //     //     i=y;
    //     //     j=x;
    //     //                 while( i>=0 && i<=7 && j>=0 && j<=7){
    //     //         button[i][j].setStyle("-fx-background-color: blue");
    //     //         i++;j--;
    //     //     }
    //     //     i=y;
    //     //     j=x;
    //     //                 while( i>=0 && i<=7 && j>=0 && j<=7){
    //     //         button[i][j].setStyle("-fx-background-color: blue");
    //     //         i--;j++;
    //     //     }
    //     //     i=y;
    //     //     j=x;
    //     //                 while( i>=0 && i<=7 && j>=0 && j<=7){
    //     //         button[i][j].setStyle("-fx-background-color: blue");
    //     //         i++;j++;
    //     //     }
    //     //     button[y][x].setStyle("-fx-background-color: gold");
                       
        // });
        new Game(gridPane);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
        
    }
}

class ButtonWrapper{
    public CustomButton button;
    ButtonWrapper(CustomButton button){
        this.button = button;
    }
}
enum Player{
    White,
    Black
}
enum Piece{
    Pawn,
    Bishop,
    Knight,
    Queen,
    Rook,
    King,
    Empty
}
class CustomButtonWrapper{
    Piece piece;
    int team;
    boolean hasMoved;
    boolean En_Passant_left;
    boolean En_Passant_right;
    public CustomButtonWrapper(Piece piece, int team, boolean hasMoved, boolean en_Passant_left,boolean en_Passant_right) {
        this.piece = piece;
        this.team = team;
        this.hasMoved = hasMoved;
        En_Passant_left = en_Passant_left;
        En_Passant_right = en_Passant_right;
    }
    

}
class TreeNode{
    ArrayList<TreeNode> childNodes;
    Player player;
    Piece piece;
    int startRow;
    int startColumn;
    int endRow;
    int endColumn;
    int turn;
    String title;
    CustomButtonWrapper[][] buttonData;
    public TreeNode(Player player, Piece piece, int startRow, int startColumn, int endRow, int endColumn,CustomButton[][]allButtons, TreeNode parentNode, int turn) {
        this.player = player;
        this.piece = piece;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.turn = turn;
        buttonData = new CustomButtonWrapper[8][8];
        // this.parentNode = parentNode;
        // this.skipButton= new Button(""+player+" "+piece+" "+startRow+startColumn+":"+endRow+endColumn);
        childNodes = new ArrayList<TreeNode>();
        this.title=""+player+" "+piece+" "+startRow+startColumn+":";
        // vBox = new VBox(skipButton);
        // hBox = new HBox();
        // vBox.getChildren().add(hBox);
        // vBox.setAlignment(Pos.TOP_CENTER);
        // hBox.setAlignment(Pos.TOP_CENTER);
        // vBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(5), new BorderWidths(1))));
    }
    public TreeNode(CustomButton[][]allButtons){
        buttonData = new CustomButtonWrapper[8][8];
        turn=0;
        this.title ="start";
        childNodes = new ArrayList<TreeNode>();
        // this.skipButton= new Button("start");
        // vBox = new VBox(skipButton);
        // hBox = new HBox();
        // vBox.getChildren().add(hBox);
        // vBox.setAlignment(Pos.TOP_CENTER);
        // hBox.setAlignment(Pos.TOP_CENTER);
    }
    public void loadButtons(CustomButton[][] allButtons, GridPane gridPane,LinkedHashSet team1Moves,LinkedHashSet team2Moves){
        gridPane.getChildren().clear();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                switch (this.buttonData[i][j].piece) {
                    case Pawn:
                        Pawn pawn;
                        if(this.buttonData[i][j].team==1){
                            pawn = new Pawn(allButtons, 1, buttonData[i][j].hasMoved, team1Moves);
                        }else{
                            pawn = new Pawn(allButtons, 2, buttonData[i][j].hasMoved, team2Moves);
                        }
                        pawn.En_Passant_left = buttonData[i][j].En_Passant_left;
                        pawn.En_Passant_right = buttonData[i][j].En_Passant_right;
                        allButtons[i][j]= pawn;
                        gridPane.add(allButtons[i][j], j, i);
                        break;
                    case Knight:
                        Knight knight ;
                        if(this.buttonData[i][j].team==1){
                            knight = new Knight(allButtons, 1, team1Moves);
                        }else{
                            knight = new Knight(allButtons, 2,team2Moves);
                        }
                        allButtons[i][j]= knight;
                        gridPane.add(allButtons[i][j], j, i);
                        break;
                    case Bishop:
                        Bishop bishop ;
                        if(this.buttonData[i][j].team==1){
                            bishop = new Bishop(allButtons, 1, team1Moves);
                        }else{
                            bishop = new Bishop(allButtons, 2,team2Moves);
                        }
                        allButtons[i][j]= bishop;
                        gridPane.add(allButtons[i][j], j, i);
                        break;    
                    case Queen:
                        Queen queen ;
                        if(this.buttonData[i][j].team==1){
                            queen = new Queen(allButtons, 1, team1Moves);
                        }else{
                            queen = new Queen(allButtons, 2,team2Moves);
                        }
                        allButtons[i][j]= queen;
                        gridPane.add(allButtons[i][j], j, i);
                        break;
                    case Rook:
                        Rook rook ;
                        if(this.buttonData[i][j].team==1){
                            rook = new Rook(allButtons, 1,buttonData[i][j].hasMoved, team1Moves);
                        }else{
                            rook = new Rook(allButtons, 2,buttonData[i][j].hasMoved,team2Moves);
                        }
                        allButtons[i][j]= rook;
                        gridPane.add(allButtons[i][j], j, i);
                        break;    
                    case King:
                        King king ;
                        if(this.buttonData[i][j].team==1){
                            king = new King(allButtons, 1,buttonData[i][j].hasMoved, team1Moves,team2Moves);
                        }else{
                            king = new King(allButtons, 2,buttonData[i][j].hasMoved,team2Moves,team1Moves);
                        }
                        allButtons[i][j]= king;
                        gridPane.add(allButtons[i][j], j, i);
                        break;                        
                    case Empty:
                        CustomButton empty = new CustomButton(allButtons);
                        allButtons[i][j]= empty;
                        gridPane.add(allButtons[i][j], j, i);
                        break;
                    default:
                        break;    
                }
            }
        }
        HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
        leftLeftSide.getChildren().clear();
        rightLeftSide.getChildren().clear();
        leftRightSide.getChildren().clear();
        rightRightSide.getChildren().clear();
        int team1pawns=0;
        int team1knights=0;
        int team1rooks = 0;
        int team1bishops =0;
        int team1queens =0;
        int team2pawns=0;
        int team2knights=0;
        int team2rooks = 0;
        int team2bishops =0;
        int team2queens =0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                switch (this.buttonData[i][j].piece) {
                    case Pawn:
                        if(this.buttonData[i][j].team==1){
                            team1pawns++;
                        }else{
                            team2pawns++;
                        }
                        break;
                    case Knight:
                        if(this.buttonData[i][j].team==1){
                            team1knights++;
                        }else{
                            team2knights++;
                        }
                        break;
                    case Bishop:
                        if(this.buttonData[i][j].team==1){
                            team1bishops++;
                        }else{
                            team2bishops++;
                        }
                        break;    
                    case Queen:
                        if(this.buttonData[i][j].team==1){
                            team1queens++;
                        }else{
                            team2queens++;
                        }
                        break;
                    case Rook:
                        if(this.buttonData[i][j].team==1){
                            team1rooks++;
                        }else{
                            team2rooks++;
                        }
                        break;    
                    case King:
                        
                        break;                        
                    case Empty:
                        
                        break;
                    default:
                        break;    
                }
            }
        }
        int lostTeam1pawns = 8-team1pawns;
        int lostTeam1knights = 2-team1knights;
        int lostTeam1bishops = 2-team1bishops;
        int lostTeam1rooks = 2-team1rooks;
        int lostTeam1queens = 1-team1queens;
        int lostTeam2pawns = 8-team2pawns;
        int lostTeam2knights = 2-team2knights;
        int lostTeam2bishops = 2-team2bishops;
        int lostTeam2rooks = 2-team2rooks;
        int lostTeam2queens = 1-team2queens;
        for(int i=0;i<lostTeam1pawns;i++){
            ImageView view = new ImageView(Game.whitePawnImg);
            view.setFitHeight(75);
            view.setFitWidth(53);


            Label label = new Label();
            label.setGraphic(view);
            if(leftLeftSide.getChildren().size()<8){
                leftLeftSide.getChildren().add(label);
            }else{
                rightLeftSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam1knights;i++){
                ImageView view = new ImageView(Game.whiteKnightImg);
                view.setFitHeight(76);
                view.setFitWidth(67);
                Label label = new Label();
                label.setGraphic(view);



            if(leftLeftSide.getChildren().size()<8){
                leftLeftSide.getChildren().add(label);
            }else{
                rightLeftSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam1bishops;i++){
            ImageView view = new ImageView(Game.whiteBishopImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            Label label = new Label();
            label.setGraphic(view);

            if(leftLeftSide.getChildren().size()<8){
                leftLeftSide.getChildren().add(label);
            }else{
                rightLeftSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam1rooks;i++){
            ImageView view = new ImageView(Game.whiteRookImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            Label label = new Label();
            label.setGraphic(view);
            if(leftLeftSide.getChildren().size()<8){
                leftLeftSide.getChildren().add(label);
            }else{
                rightLeftSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam1queens;i++){
            ImageView view = new ImageView(Game.whiteQueenImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            Label label = new Label();
            label.setGraphic(view);
            if(leftLeftSide.getChildren().size()<8){
                leftLeftSide.getChildren().add(label);
            }else{
                rightLeftSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam2pawns;i++){
            ImageView view = new ImageView(Game.blackPawnImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            Label label = new Label();
            label.setGraphic(view);
            if(rightRightSide.getChildren().size()<8){
                rightRightSide.getChildren().add(label);
            }else{
                leftRightSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam2knights;i++){
            ImageView view = new ImageView(Game.blackKnightImg);
            view.setFitHeight(76);
            view.setFitWidth(67);
            Label label = new Label();
            label.setGraphic(view);

            if(rightRightSide.getChildren().size()<8){
                rightRightSide.getChildren().add(label);
            }else{
                leftRightSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam2bishops;i++){
            ImageView view = new ImageView(Game.blackBishopImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            Label label = new Label();
            label.setGraphic(view);
            if(rightRightSide.getChildren().size()<8){
                rightRightSide.getChildren().add(label);
            }else{
                leftRightSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam2rooks;i++){
            ImageView view = new ImageView(Game.blackRookImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            Label label = new Label();
            label.setGraphic(view);
            if(rightRightSide.getChildren().size()<8){
                rightRightSide.getChildren().add(label);
            }else{
                leftRightSide.getChildren().add(label);
            }
        }
        for(int i=0;i<lostTeam2queens;i++){
            ImageView view = new ImageView(Game.blackQueenImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            Label label = new Label();
            label.setGraphic(view);
            if(rightRightSide.getChildren().size()<8){
                rightRightSide.getChildren().add(label);
            }else{
                leftRightSide.getChildren().add(label);
            }
        }

        App.timeline.play();
    }
    public void saveButtons(CustomButton[][] allButtons,LinkedHashSet team2Moves){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(allButtons[i][j].getClass().equals(new Pawn(allButtons, j, false, team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Pawn, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                    System.out.println(""+i+j+""+buttonData[i][j].hasMoved+allButtons[i][j].hasMoved);
                }else if(allButtons[i][j].getClass().equals(new Knight(allButtons, j,team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Knight, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }else if(allButtons[i][j].getClass().equals(new Bishop(allButtons, j,team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Bishop, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }else if(allButtons[i][j].getClass().equals(new Queen(allButtons, j,team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Queen, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }else if(allButtons[i][j].getClass().equals(new Rook(allButtons, j,false,team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Rook, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }else if(allButtons[i][j].getClass().equals(new King(allButtons, j,false,team2Moves,team2Moves).getClass())){
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.King, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }else{
                    this.buttonData[i][j] = new CustomButtonWrapper(Piece.Empty, allButtons[i][j].team,allButtons[i][j].hasMoved , allButtons[i][j].En_Passant_left, allButtons[i][j].En_Passant_right);
                }
            }
        }
    }
    public void timeSkip(GridPane gridPane, CustomButton[][] allButtons,LinkedHashSet team1Moves,LinkedHashSet team2Moves){
        gridPane.getChildren().clear();
        Game.undoStack.clear();
        Game.currentNode = this;
        // for(int i=0;i<8;i++){
        //     for(int j=0;j<8;j++){
        //         allButtons[i][j]=this.allButtons[i][j];
        //         gridPane.add(allButtons[i][j], j, i);
        //     }
        // }
        loadButtons(allButtons, gridPane, team1Moves, team2Moves);    
        new CustomButton(allButtons).fire();
        CustomButton.turn=this.turn;
        if((CustomButton.turn+1) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
        BorderPane borderPane = (BorderPane)gridPane.getParent();
        HBox topBox = (HBox)borderPane.getChildren().get(3);
        Label timeLabel = (Label)topBox.getChildren().get(0);
        timeLabel.setText("30");
    }
}
class Tree{
    TreeNode head;
    Scene scene;
    Tree(CustomButton[][] allButtons){
        this.head = new TreeNode(allButtons);
    }
    public VBox getTree(GridPane gridPane, CustomButton[][] allButtons,LinkedHashSet team1Moves,LinkedHashSet team2Moves){
        VBox vBox2 = new VBox();
        HBox hBox2 = new HBox();
        Button skipButton = new Button("start");
        skipButton.setOnAction(e -> head.timeSkip(gridPane, allButtons, team1Moves, team2Moves));
        vBox2.getChildren().addAll(skipButton,hBox2);
        for(int i=0;i<head.childNodes.size();i++){
            loadTree(head.childNodes.get(i),gridPane,allButtons,team1Moves,team2Moves,hBox2);
        }
        return vBox2;

    }
    public void loadTree(TreeNode node,GridPane gridPane, CustomButton[][] allButtons,LinkedHashSet team1Moves,LinkedHashSet team2Moves,HBox hBox){
        // node.hBox.getChildren().clear();
        // for(int i=0;i<node.childNodes.size();i++){
        //     node.hBox.getChildren().add(node.childNodes.get(i).vBox);
        //     loadTree(node.childNodes.get(i));
        // }
        VBox vBox2 = new VBox();
        HBox hBox2 = new HBox();
        Button skipButton = new Button(node.title);
        skipButton.setOnAction(e -> node.timeSkip(gridPane, allButtons, team1Moves, team2Moves));
        vBox2.getChildren().addAll(skipButton,hBox2);
        hBox.getChildren().add(vBox2);
        for(int i=0;i<node.childNodes.size();i++){
            loadTree(node.childNodes.get(i),gridPane,allButtons,team1Moves,team2Moves,hBox2);
        }
    }
    public void showTree(GridPane gridPane, CustomButton[][] allButtons,LinkedHashSet team1Moves,LinkedHashSet team2Moves){
        Stage stage = new Stage();
        
        VBox treeBox=getTree(gridPane,allButtons,team1Moves,team2Moves);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(treeBox);
        // scrollPane.setHvalue(0.5);
        // scrollPane.setVvalue(0.5);
        Scene scene2 = new Scene(scrollPane);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.setScene(scene2);
        stage.show();
    }
    public void printTreeLog(TreeNode node){
        for(int i=1;i<node.childNodes.size();i++){
            System.out.print("(");
            printTreeLog(node.childNodes.get(i));
            System.out.print(")");
        }
        if(node.childNodes.size()>0){
            printTreeLog(node.childNodes.get(0));
        }    
    }
    public void saveTree(){
        Gson gson = new Gson();
        // String json = gson.toJson(head);
        try {
            FileWriter writer = new FileWriter("file.json"); 
            BufferedWriter bufferedWriter = new BufferedWriter(writer, 16384);
            gson.toJson(head, bufferedWriter);
            bufferedWriter.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        // try {
            // create user object

            // create object mapper instance
        //     ObjectMapper mapper = new ObjectMapper();

        //     // convert user object to JSON file
        //     mapper.writeValue(new File("user.json"), head);
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }

    }
}
class Game {
    public static LinkedHashSet team1Moves;
    public static LinkedHashSet team2Moves;
    public static TreeNode currentNode;
    public Tree tree;
    public static Stack<TreeNode> undoStack;
    public static Label turnLabel;
    public static Image whitePawnImg;
    public static Image whiteKnightImg;
    public static Image whiteRookImg;
    public static Image whiteBishopImg;
    public static Image whiteQueenImg;
    public static Image whiteKingImg;
    public static Image blackPawnImg;
    public static Image blackKnightImg;
    public static Image blackRookImg;
    public static Image blackBishopImg;
    public static Image blackQueenImg;
    public static Image blackKingImg;


    Game(GridPane gridPane){
        team1Moves = new LinkedHashSet<Integer>();
        team2Moves = new LinkedHashSet<Integer>();
        undoStack = new Stack<TreeNode>();
        CustomButton allButtons[][] = new CustomButton[8][8];  
        Button showTreeButton = new Button("show tree");
        
        HBox hBox = (HBox)App.borderPane.getChildren().get(3);
        turnLabel = new Label("Turn : White");
        hBox.getChildren().add(turnLabel);
        hBox.getChildren().add(showTreeButton);
        
        Button undoButton = new Button("undo");
        undoButton.setOnAction(e -> this.undo(gridPane, allButtons));
        hBox.getChildren().add(undoButton);
        Button redoButton = new Button("redo");
        redoButton.setOnAction(e -> this.redo(gridPane, allButtons));
        hBox.getChildren().add(redoButton);
        Button printLogButton = new Button("print log");
        printLogButton.setOnAction(e -> tree.printTreeLog(tree.head));
        hBox.getChildren().add(printLogButton);
        Button saveButton = new Button("save");
        saveButton.setOnAction(e -> tree.saveTree());
        hBox.getChildren().add(saveButton);
        System.out.println("dcfvgh");
        whitePawnImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\pawn-white.png");
        whiteRookImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\rook-white.png");
        whiteBishopImg= new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\bishop-white.png");
        whiteKnightImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\knight-white.png");
        whiteQueenImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\queen-white.png");
        whiteKingImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\king-white.png");
        blackPawnImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\pawn-black.png");
        blackRookImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\rook-black.png");
        blackBishopImg= new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\bishop-black.png");
        blackKnightImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\knight-black.png");
        blackQueenImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\queen-black.png");
        blackKingImg = new Image("D:\\java\\Chess2\\src\\main\\java\\com\\example\\chess2\\king-black.png");
        System.out.println("uybhn");
        // view.setPreserveRatio(true);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                final int I = i;
                final int J = j;
                
                allButtons[i][j] = new CustomButton(allButtons,gridPane);
                // button[i][j].setStyle("-fx-background-color:grey; "+
                //             "-fx-background-radius: 0;"+
                //             "-fx-border-color:yellow;");
                // button[i][j].setOnAction(e -> {System.out.println("im number "+ I +" "+J);
                //     for(int y=0;y<8;y++){
                //         for(int x=0;x<8;x++){
                //             button[y][x].setStyle("-fx-background-color:grey; "+
                //             "-fx-background-radius: 0;"+
                //             "-fx-border-color:yellow;");
                //         }
                //   }
                // }
                // );
                allButtons[i][j].setMinSize(80,80);
                gridPane.setPadding(new Insets(8));
                gridPane.setAlignment(Pos.CENTER);
                gridPane.add(allButtons[i][j], j, i);
            }
        }
        // loading team 1
        for(int j=0;j<8;j++){
            gridPane.getChildren().remove(allButtons[6][j]);
            allButtons[6][j] = new Pawn(allButtons, 1,false,team1Moves);
            gridPane.add(allButtons[6][j], j, 6);
        }
        gridPane.getChildren().remove(allButtons[7][0]);
        allButtons[7][0] = new Rook(allButtons, 1,false,team1Moves);
        gridPane.add(allButtons[7][0], 0, 7);
        gridPane.getChildren().remove(allButtons[7][7]);
        allButtons[7][7] = new Rook(allButtons, 1,false,team1Moves);
        gridPane.add(allButtons[7][7], 7, 7);
        gridPane.getChildren().remove(allButtons[7][1]);
        allButtons[7][1] = new Knight(allButtons, 1,team1Moves);
        gridPane.add(allButtons[7][1], 1, 7);
        gridPane.getChildren().remove(allButtons[7][6]);
        allButtons[7][6] = new Knight(allButtons, 1,team1Moves);
        gridPane.add(allButtons[7][6], 6, 7);
        gridPane.getChildren().remove(allButtons[7][2]);
        allButtons[7][2] = new Bishop(allButtons, 1,team1Moves);
        gridPane.add(allButtons[7][2], 2, 7);
        gridPane.getChildren().remove(allButtons[7][5]);
        allButtons[7][5] = new Bishop(allButtons, 1,team1Moves);
        gridPane.add(allButtons[7][5], 5, 7);
        gridPane.getChildren().remove(allButtons[7][4]);
        allButtons[7][4] = new King(allButtons, 1,false,team1Moves,team2Moves);
        gridPane.add(allButtons[7][4], 4, 7);
        gridPane.getChildren().remove(allButtons[7][3]);
        allButtons[7][3] = new Queen(allButtons, 1,team1Moves);
        gridPane.add(allButtons[7][3], 3, 7);
        //loading team 2
        for(int j=0;j<8;j++){
            gridPane.getChildren().remove(allButtons[1][j]);
            allButtons[1][j] = new Pawn(allButtons, 2,false,team1Moves);
            gridPane.add(allButtons[1][j], j, 1);
        }
        gridPane.getChildren().remove(allButtons[0][0]);
        allButtons[0][0] = new Rook(allButtons, 2,false,team2Moves);
        gridPane.add(allButtons[0][0], 0, 0);
        gridPane.getChildren().remove(allButtons[0][7]);
        allButtons[0][7] = new Rook(allButtons, 2,false,team2Moves);
        gridPane.add(allButtons[0][7], 7, 0);
        gridPane.getChildren().remove(allButtons[0][1]);
        allButtons[0][1] = new Knight(allButtons, 2,team2Moves);
        gridPane.add(allButtons[0][1], 1, 0);
        gridPane.getChildren().remove(allButtons[0][6]);
        allButtons[0][6] = new Knight(allButtons, 2,team2Moves);
        gridPane.add(allButtons[0][6], 6, 0);
        gridPane.getChildren().remove(allButtons[0][2]);
        allButtons[0][2] = new Bishop(allButtons, 2,team2Moves);
        gridPane.add(allButtons[0][2], 2, 0);
        gridPane.getChildren().remove(allButtons[0][5]);
        allButtons[0][5] = new Bishop(allButtons, 2,team2Moves);
        gridPane.add(allButtons[0][5], 5, 0);
        gridPane.getChildren().remove(allButtons[0][4]);
        allButtons[0][4] = new King(allButtons, 2,false,team2Moves,team1Moves);
        gridPane.add(allButtons[0][4], 4, 0);
        gridPane.getChildren().remove(allButtons[0][3]);
        allButtons[0][3] = new Queen(allButtons, 2,team2Moves);
        gridPane.add(allButtons[0][3], 3, 0);
        // allButtons[0][4].updateRange(2,team2Moves);
        allButtons[0][4].updateRange(4, team2Moves);
        tree = new Tree(allButtons);
        tree.head.saveButtons(allButtons, team2Moves);
        currentNode= tree.head;
        showTreeButton.setOnAction(e -> tree.showTree(gridPane,allButtons,team1Moves,team2Moves));
        Button loadDataButton = new Button("load data");
        loadDataButton.setOnAction(e -> {
            loadData(tree.head);
        } );
        hBox.getChildren().add(loadDataButton);
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> reset(gridPane,allButtons));
        hBox.getChildren().add(resetButton);
        //  loadData(tree.head);
        // System.out.println(tree.head.childNodes.get(0).piece+" "+tree.head.childNodes.get(0).player);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(tree.head.buttonData[i][j].piece);
            }
            System.out.println();
        }
        new CustomButton(allButtons).fire();
        // System.out.println(team2Moves);
        // if(team2Moves.contains(37)){
        //     System.out.println("true");
        // }
    }
    public void redo(GridPane gridPane, CustomButton[][] allButtons){
        if(undoStack.isEmpty()){
            return;
        }
        gridPane.getChildren().clear();
        currentNode = undoStack.pop();
        System.out.println("test");
        // for(int i=0;i<8;i++){
        //     for(int j=0;j<8;j++){
        //         allButtons[i][j]=currentNode.allButtons[i][j];
        //         gridPane.add(allButtons[i][j], j, i);
        //     }
        // }
        currentNode.loadButtons(allButtons, gridPane, team1Moves, team2Moves);
        new CustomButton(allButtons).fire();
        CustomButton.turn++;
        if((CustomButton.turn+1) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
        HBox topBox = (HBox)App.borderPane.getChildren().get(3);
        Label timeLabel = (Label)topBox.getChildren().get(0);
        timeLabel.setText("30");
    }
    public static void findParent(TreeNode suspectNode,TreeNode childNode,ArrayList<TreeNode> goalNode){
        System.out.println(""+suspectNode.player+suspectNode.piece+suspectNode.startRow);
        if(suspectNode.childNodes.contains(childNode)){
            goalNode.add(suspectNode);
            return;
        }else{
            for(int i=0;i<suspectNode.childNodes.size();i++){
                findParent(suspectNode.childNodes.get(i),childNode,goalNode);
            }
        }
    }
    public void undo(GridPane gridPane, CustomButton[][] allButtons){
        if(currentNode==tree.head){
            return;
        }
        gridPane.getChildren().clear();
        undoStack.add(currentNode);
        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        findParent(tree.head,currentNode,nodes);
        currentNode = nodes.get(0);
        System.out.println("test");
        // for(int i=0;i<8;i++){
        //     for(int j=0;j<8;j++){
        //         allButtons[i][j]=currentNode.allButtons[i][j];
        //         gridPane.add(allButtons[i][j], j, i);
        //     }
        // }
        currentNode.loadButtons(allButtons, gridPane, team1Moves, team2Moves);
        new CustomButton(allButtons).fire();
        CustomButton.turn--;
        System.out.println(CustomButton.turn);
        if((CustomButton.turn+1) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
        HBox topBox = (HBox)App.borderPane.getChildren().get(3);
        Label timeLabel = (Label)topBox.getChildren().get(0);
        timeLabel.setText("30");
    }
    public void reset(GridPane gridPane, CustomButton[][] allButtons){
        tree.head.childNodes.clear();
        currentNode=tree.head;
        currentNode.loadButtons(allButtons, gridPane, team1Moves, team2Moves);
        CustomButton.turn=0;
        App.timeline.play();
        HBox hbox = (HBox)App.borderPane.getChildren().get(3);
        Label label =(Label) hbox.getChildren().get(0);
        label.setText("30");
    }
    public static void loadData(TreeNode node){
        Gson gson = new Gson();
        
        try{
            TreeNode treeNode= gson.fromJson(new FileReader("file.json"),TreeNode.class);
            node.childNodes = treeNode.childNodes;
            System.out.println(treeNode.childNodes.get(0).piece+" "+treeNode.childNodes.get(0).player);
            System.out.println(node.childNodes.get(0).piece+" "+node.childNodes.get(0).player);
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
 class CustomButton extends Button{
    static GridPane gridPane;
    CustomButton allButtons[][];
    LinkedHashSet<Integer> teamMoves; 
    LinkedHashSet<Integer> allTeamMoves;
    int team;
    static int turn=0;
    boolean hasMoved=false;
    boolean En_Passant_right=false;
    boolean En_Passant_left=false;
    CustomButton(CustomButton allButtons[][]){
        super();
        teamMoves=new LinkedHashSet<Integer>();
        this.allTeamMoves = teamMoves;
        this.setMinSize(80, 80);
        this.setMaxSize(80,80);
        this.allButtons = allButtons;
        this.team = 0;
        actionSet();
    }
    
    CustomButton(CustomButton allButtons[][],GridPane gridPane){
        super();
        teamMoves=new LinkedHashSet<Integer>();
        this.allButtons = allButtons;
        this.gridPane = gridPane;
        this.setMinSize(80, 80);
        this.setMaxSize(80,80);
        this.team = 0;
        actionSet();
    }
    CustomButton(CustomButton allButtons[][],int team,LinkedHashSet teamMoves){
        super();
        this.allTeamMoves = teamMoves;
        this.teamMoves=new LinkedHashSet<Integer>();
        this.setMinSize(80, 80);
        this.setMaxSize(80,80);
        this.allButtons = allButtons;
        this.team = team;
        actionSet();
    }
    public boolean possibleMove(int team,int startRow,int startColumn,int endRow,int endColumn){
        int kingRow=0;
        int kingColumn=0;
        if(allButtons[startRow][startColumn].getClass()== new King(allButtons, team, hasMoved, teamMoves, allTeamMoves).getClass()){
            kingRow= endRow;
            kingColumn= endColumn;
        }else{
        for(int y=0;y<8;y++){
                        for(int x=0;x<8;x++){
                            if(this.allButtons[y][x].team==team && this.allButtons[y][x].getClass()== new King(allButtons, team, hasMoved, teamMoves, allTeamMoves).getClass()){
                                kingRow=y;
                                kingColumn=x;
                            }
                        }
                  }
                }
        CustomButton startPos = allButtons[startRow][startColumn];
        CustomButton finalPos = allButtons[endRow][endColumn];
        allButtons[endRow][endColumn] = startPos;
        allButtons[startRow][startColumn] = new CustomButton(allButtons);
        if(checkKingMove(kingRow, kingColumn, team, allTeamMoves)){
            allButtons[endRow][endColumn] = finalPos; 
            allButtons[startRow][startColumn] = startPos;
            return false;
        }
        allButtons[endRow][endColumn] = finalPos; 
        allButtons[startRow][startColumn] = startPos;          
        return true;          
    }
    public void updateRange(int team,LinkedHashSet enemyMoves){
        enemyMoves.clear();
        for(int y=0;y<8;y++){
                        for(int x=0;x<8;x++){
                            if(this.allButtons[y][x].team==team){
                                this.allButtons[y][x].getRange();
                                enemyMoves.addAll(allButtons[y][x].teamMoves);
                            }
                        }
                  }          
    }
    public boolean checkKingMove(int row,int column,int team,LinkedHashSet enemyMoves){
        if(team==1){
                        updateRange(2, enemyMoves);
                        }else{
                        updateRange(1, enemyMoves);
                        }
              
        if(enemyMoves.contains((row*10)+column)){

            return true;
        }
        return false;
    }
    public boolean checkMate(int row,int column,int team,LinkedHashSet enemyMoves){

        if(!checkKingMove(row, column,team, enemyMoves)){
            System.out.println("fast");
            return false;
        }
        // CustomButton c = new CustomButton(allButtons);
        // for(int y=0;y<8;y++){
        //     for(int x=0;x<8;x++){
        //         if(this.allButtons[y][x].team==team){
        //             this.allButtons[y][x].getRange();
        //             for(int move : allButtons[y][x].teamMoves){
        //                 int cMove= move%10;
        //                 int rMove = (move-cMove)/10; 
        //                 if(allButtons[rMove][cMove].team==team){
        //                     continue;
        //                 }
        //                 // CustomButton startPos = allButtons[y][x];
        //                 // CustomButton finalPos = allButtons[rMove][cMove];
        //                 // allButtons[rMove][cMove] = new Pawn(allButtons, team,true,teamMoves);
        //                 // allButtons[y][x] = c;

        //                 // if(!checkKingMove(row, column,team, enemyMoves)){
        //                 //     allButtons[y][x] = startPos;
        //                 //     allButtons[rMove][cMove] = finalPos;
        //                 //     System.out.println((y*10)+x);
        //                 //     System.out.println("start");
        //                 //     System.out.println((rMove*10)+cMove);
        //                 //     System.out.println("end");
        //                 //     return false;
        //                 // }
        //                 // allButtons[y][x] = startPos;
        //                 // allButtons[rMove][cMove] = finalPos;
        //                 if(possibleMove(team, y, x, rMove, cMove)){
        //                     return false;
        //                 }
        //             }
        //         }
        //     }
        // }
        // System.out.println("check");
        // return true;
        return(staleMate(row, column, team, enemyMoves));
    }
    public boolean staleMate(int row,int column,int team,LinkedHashSet enemyMoves){
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                if(this.allButtons[y][x].team==team){
                    this.allButtons[y][x].getRange();
                    for(int move : allButtons[y][x].teamMoves){
                        int cMove= move%10;
                        int rMove = (move-cMove)/10; 
                        if(allButtons[rMove][cMove].team==team){
                            continue;
                        }
                        if(possibleMove(team, y, x, rMove, cMove)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public void recolor(){
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                if((x+y)%2==0){
                    this.allButtons[y][x].setStyle("-fx-background-color:rgb(255,230,179); "+
                            "-fx-background-radius: 0;"+
                            "-fx-border-color:yellow;");
                }else{
                    this.allButtons[y][x].setStyle("-fx-background-color:rgb(84,14,0); "+
                            "-fx-background-radius: 0;"+
                            "-fx-border-color:yellow;");
                }
            }
        }
    }
    public void actionSet(){
        this.setOnAction(e -> {
                                for(int y=0;y<8;y++){
                                for(int x=0;x<8;x++){
                            if((x+y)%2==0){
                            this.allButtons[y][x].setStyle("-fx-background-color:rgb(32, 49, 10); "+
                            "-fx-background-radius: 0;"+
                            "-fx-border-color:yellow;");
                            }else{
                                this.allButtons[y][x].setStyle("-fx-background-color:Black; "+
                            "-fx-background-radius: 0;"+
                            "-fx-border-color:yellow;");
                            }
                        }
                  }
                  this.allActionSet();

        });
    }
    public void allActionSet(){

        for(int y=0;y<8;y++){
                        for(int x=0;x<8;x++){
                            this.allButtons[y][x].actionSet();
                            this.allButtons[y][x].recolor();
                        }
        }
    }
    public void getRange(){};
    public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn,Piece piece){
        
        HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
        HBox topBox = (HBox)App.borderPane.getChildren().get(3);
        Label timeLabel = (Label)topBox.getChildren().get(0);
        timeLabel.setText("30");
//        TreeNode newNode2 ;
        if((CustomButton.turn) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
        gridPane.getChildren().remove(allButtons[endRow][endColumn]);
        gridPane.getChildren().remove(allButtons[startRow][startColumn]);

        if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
            if(team==2) {
                if (allButtons[endRow][endColumn].getClass() == new Pawn(null, 1, true, null).getClass()) {
                    ImageView view = new ImageView(Game.whitePawnImg);
                    view.setFitHeight(75);
                    view.setFitWidth(53);

                    Label label = new Label();
                    label.setGraphic(view);
                    if (leftLeftSide.getChildren().size() < 8) {
                        leftLeftSide.getChildren().add(label);
                    } else {
                        rightLeftSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Knight(null, 1,  null).getClass()) {
                    ImageView view = new ImageView(Game.whiteKnightImg);
                    view.setFitHeight(76);
                    view.setFitWidth(67);
                    Label label = new Label();
                    label.setGraphic(view);


                    if (leftLeftSide.getChildren().size() < 8) {
                        leftLeftSide.getChildren().add(label);
                    } else {
                        rightLeftSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Bishop(null, 1, null).getClass()) {
                    ImageView view = new ImageView(Game.whiteBishopImg);
                    view.setFitHeight(65);
                    view.setFitWidth(65);
                    Label label = new Label();
                    label.setGraphic(view);

                    if (leftLeftSide.getChildren().size() < 8) {
                        leftLeftSide.getChildren().add(label);
                    } else {
                        rightLeftSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Rook(null, 1, true, null).getClass()) {
                    ImageView view = new ImageView(Game.whiteRookImg);
                    view.setFitHeight(75);
                    view.setFitWidth(53);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (leftLeftSide.getChildren().size() < 8) {
                        leftLeftSide.getChildren().add(label);
                    } else {
                        rightLeftSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Queen(null, 1,  null).getClass()) {
                    ImageView view = new ImageView(Game.whiteQueenImg);
                    view.setFitHeight(65);
                    view.setFitWidth(65);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (leftLeftSide.getChildren().size() < 8) {
                        leftLeftSide.getChildren().add(label);
                    } else {
                        rightLeftSide.getChildren().add(label);
                    }
                }
            }else {
                if (allButtons[endRow][endColumn].getClass() == new Pawn(null, 1, true, null).getClass()) {
                    ImageView view = new ImageView(Game.blackPawnImg);
                    view.setFitHeight(75);
                    view.setFitWidth(53);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (rightRightSide.getChildren().size() < 8) {
                        rightRightSide.getChildren().add(label);
                    } else {
                        leftRightSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Knight(null, 1,  null).getClass()) {
                    ImageView view = new ImageView(Game.blackKnightImg);
                    view.setFitHeight(76);
                    view.setFitWidth(67);
                    Label label = new Label();
                    label.setGraphic(view);

                    if (rightRightSide.getChildren().size() < 8) {
                        rightRightSide.getChildren().add(label);
                    } else {
                        leftRightSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Bishop(null, 1,  null).getClass()) {
                    ImageView view = new ImageView(Game.blackBishopImg);
                    view.setFitHeight(65);
                    view.setFitWidth(65);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (rightRightSide.getChildren().size() < 8) {
                        rightRightSide.getChildren().add(label);
                    } else {
                        leftRightSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Rook(null, 1, true, null).getClass()) {
                    ImageView view = new ImageView(Game.blackRookImg);
                    view.setFitHeight(75);
                    view.setFitWidth(53);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (rightRightSide.getChildren().size() < 8) {
                        rightRightSide.getChildren().add(label);
                    } else {
                        leftRightSide.getChildren().add(label);
                    }
                }
                if (allButtons[endRow][endColumn].getClass() == new Queen(null, 1, null).getClass()) {
                    ImageView view = new ImageView(Game.blackQueenImg);
                    view.setFitHeight(65);
                    view.setFitWidth(65);
                    Label label = new Label();
                    label.setGraphic(view);
                    if (rightRightSide.getChildren().size() < 8) {
                        rightRightSide.getChildren().add(label);
                    } else {
                        leftRightSide.getChildren().add(label);
                    }
                }
            }
        }
        switch (piece) {
            case Pawn:
                allButtons[endRow][endColumn] = new Pawn(allButtons, team,true,allTeamMoves);
                System.out.println(allButtons[endRow][endColumn].hasMoved);
                break;
            case Bishop:
                allButtons[endRow][endColumn] = new Bishop(allButtons, team,allTeamMoves);
                break;
            case Knight:
                allButtons[endRow][endColumn] = new Knight(allButtons, team,allTeamMoves);
                break;
            case Queen:
                allButtons[endRow][endColumn] = new Queen(allButtons, team,allTeamMoves);
                break;
            case Rook:
                allButtons[endRow][endColumn] = new Rook(allButtons, team,true,allTeamMoves);
                break;
            case King:
                King king = (King)allButtons[startRow][startColumn];
                allButtons[endRow][endColumn] = new King(allButtons, team,true,allTeamMoves,king.enemyTeamMoves);
                break;                
            default:
                break;
        }
        System.out.println(allButtons[endRow][endColumn].hasMoved);
        allButtons[startRow][startColumn] = new CustomButton(allButtons);  
        gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
        gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
        for (int i=0;i<8;i++) {
            for(int j=0;j<8;j++){
                allButtons[i][j].En_Passant_left=false;
                allButtons[i][j].En_Passant_right=false;
            }
        }
        Player player;
        if(team==1){
            player = Player.White;
        }else{
            player = Player.Black;
        }
        allButtons[endRow][endColumn].hasMoved=true;
//        newNode2 = new TreeNode(player, piece, startRow, startColumn, endRow, endColumn, allButtons,Game.currentNode,CustomButton.turn);
        TreeNode newNode = new TreeNode(player, piece, startRow, startColumn, endRow, endColumn, allButtons,Game.currentNode,CustomButton.turn+1);
        boolean repeatChecker=false;
        boolean redoChecker = false;
        System.out.println("test1");
        for(int i=0;i<Game.currentNode.childNodes.size();i++){
            if(Game.currentNode.childNodes.get(i).startRow == newNode.startRow && Game.currentNode.childNodes.get(i).startColumn == newNode.startColumn && Game.currentNode.childNodes.get(i).endRow == newNode.endRow && Game.currentNode.childNodes.get(i).endColumn == newNode.endColumn ){
                System.out.println("test2");
                repeatChecker=true;
                newNode.childNodes = Game.currentNode.childNodes.get(i).childNodes;
                Game.currentNode.childNodes.set(i,newNode);
                Game.currentNode= Game.currentNode.childNodes.get(i);
            }
            
        }

        if(!Game.undoStack.isEmpty()){
                System.out.println("test3");
                if(newNode.startRow == Game.undoStack.peek().startRow && newNode.startColumn == Game.undoStack.peek().startColumn && newNode.endRow == Game.undoStack.peek().endRow && newNode.endColumn == Game.undoStack.peek().endColumn ){
                    System.out.println("test4");
                    redoChecker=true;
                }
            }
        if(!repeatChecker){
            Game.currentNode.childNodes.add(newNode);
            Game.currentNode= newNode;
            // System.out.println("added");
        }
        if(redoChecker){
            Game.undoStack.pop();
        }else{
            Game.undoStack.clear();
        }
        
        // System.out.println(Game.currentNode.skipButton.getText());
        // System.out.println(repeatChecker);
        // System.out.println(newNode.skipButton.getText());
        // System.out.println("childs:");
        // for(int i=0;i<Game.currentNode.childNodes.size();i++){
        //     System.out.println(Game.currentNode.childNodes.get(i).skipButton.getText());
        // }
        System.out.println(allButtons[endRow][endColumn].hasMoved);
        newNode.saveButtons(allButtons, allTeamMoves);
        // newNode.skipButton.setOnAction(e -> newNode.timeSkip(gridPane, allButtons,Game.team1Moves,Game.team2Moves));
        new CustomButton(allButtons).fire();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(allButtons[i][j].getClass()== new King(allButtons, team, redoChecker, teamMoves, allTeamMoves).getClass() && allButtons[i][j].team!=team){
                    King enemyKing = (King)allButtons[i][j];
                    if(staleMate(i,j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        if(checkKingMove(i, j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        AlertBox.show("congratulations", ""+player+" has won with checkMate");
                        
                        }else{
                            Player player2;
                            if(player.equals(Player.White)){
                                player2 = Player.Black;
                            }else{
                                player2 = Player.White;
                            }
                            AlertBox.show("stale mate", "it's"+player2+"'s turn but there's no possible move");
                        }
                    }
                }
            }
        }

//       Game.currentNode.timeSkip(gridPane,allButtons,Game.team1Moves,Game.team2Moves);
    }
}
class Pawn extends CustomButton{
    public boolean hasMoved = false;
    Pawn(CustomButton allButtons[][],int team,boolean hasMoved,LinkedHashSet teamMoves){
        super(allButtons,team,teamMoves);
        this.hasMoved = hasMoved;
        System.out.println(this.hasMoved);
        this.actionSet();
//        this.setText("PAWN");
        if(team==2){
        this.setStyle("-fx-background-color:blue;");
        
        }else{
            this.setStyle("-fx-background-color:red;");
        }
    }
    public void recolor(){
//        this.setStyle("-fx-background-color: blue;");
        if(this.team==1){
            ImageView view = new ImageView(Game.whitePawnImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackPawnImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            this.setGraphic(view);
        }    
    }
    //  public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
        
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     allButtons[endRow][endColumn] = new Pawn(allButtons, team,true,allTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     Player player;
    //     if(team==1){
    //         player = Player.White;
    //     }else{
    //         player = Player.Black;
    //     }
    //     TreeNode newNode = new TreeNode(player, Piece.Pawn, startRow, startColumn, endRow, endColumn, allButtons,Game.currentNode,CustomButton.turn+1);
    //     boolean repeatChecker= false;
    //     boolean redoChecker = false;
    //     for(int i=0;i<Game.currentNode.childNodes.size();i++){
    //         if(Game.currentNode.childNodes.get(i).startRow == newNode.startRow && Game.currentNode.childNodes.get(i).startColumn == newNode.startColumn && Game.currentNode.childNodes.get(i).endRow == newNode.endRow && Game.currentNode.childNodes.get(i).endColumn == newNode.endColumn ){
    //             repeatChecker=true;
    //         }
    //         if(Game.currentNode.childNodes.get(i).startRow == Game.undoStack.peek().startRow && Game.currentNode.childNodes.get(i).startColumn == Game.undoStack.peek().startColumn && Game.currentNode.childNodes.get(i).endRow == Game.undoStack.peek().endRow && Game.currentNode.childNodes.get(i).endColumn == Game.undoStack.peek().endColumn ){
    //             redoChecker=true;
    //             break;
    //         }
    //     }
    //     if(!repeatChecker){
    //         Game.currentNode.childNodes.add(newNode);
    //     }
    //     if(redoChecker){
    //         Game.undoStack.pop();
    //     }else{
    //         Game.undoStack.clear();
    //     }
    //     Game.currentNode= newNode;
    //     new CustomButton(allButtons).fire();
        
        
    // };
    public void actionSet(){
        this.setOnAction(e ->{
            
            new CustomButton(allButtons).fire();
            if((turn+1)%2 == this.team%2){
                
            for (int i=0;i<8;i++) {
                for(int j=0;j<8;j++){
                    
                    if(allButtons[i][j].equals(this)){
                        allButtons[i][j].setStyle("-fx-background-color: gold");
                        if(this.team==2){
                            
                            if(i<7){
                                if(En_Passant_left){
                                    
                                    if(allButtons[i+1][j-1].getClass()== new CustomButton(allButtons).getClass()){
                                        if(!possibleMove(team, i, j, i, j-1)){
                                        }else{
                                        allButtons[i+1][j-1].setStyle("-fx-background-color: rgb(230,166,255);");
                                        final int I=i;
                                        final int J=j;
                                        
                                        allButtons[i+1][j-1].setOnAction(new EventHandler<ActionEvent>() {
                                            public void handle(ActionEvent arg0) {
                                                // movePiece(team, I, J, I, J-1,Piece.Pawn);
                                                
                                                // gridPane.getChildren().remove(allButtons[I + 1][J - 1]);
                                                gridPane.getChildren().remove(allButtons[I][J]);
                                                // gridPane.getChildren().remove(allButtons[I][J - 1]);
                                                // allButtons[I + 1][J - 1] = new Pawn(allButtons, team, true, allTeamMoves);
                                                // gridPane.add(allButtons[I + 1][J - 1], J - 1, I + 1);
                                                allButtons[I][J] = new CustomButton(allButtons);
                                                gridPane.add(allButtons[I][J], J, I);
                                                movePiece(team, I, J-1, I+1, J-1,Piece.Pawn);
                                                
                                                // allButtons[I][J - 1] = new CustomButton(allButtons);
                                                // gridPane.add(allButtons[I][J - 1], J - 1, I);
                                                // allButtons[I][J].fire();
                                                HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);

                                                    ImageView view = new ImageView(Game.whitePawnImg);
                                                    view.setFitHeight(75);
                                                    view.setFitWidth(53);


                                                    Label label = new Label();
                                                    label.setGraphic(view);
                                                    if(leftLeftSide.getChildren().size()<8){
                                                        leftLeftSide.getChildren().add(label);
                                                    }else{
                                                        rightLeftSide.getChildren().add(label);
                                                    }
                                                
                                                turn++;

                                            }
                                        });
                                    }
                                    }
                                }
                                if(En_Passant_right){
                                    final int I=i;
                                    final int J=j;
                                    if(allButtons[I+1][J+1].getClass()== new CustomButton(allButtons).getClass()){
                                        if(!possibleMove(team, i, j, i, j+1)){
                                        }else{
                                        allButtons[i+1][j+1].setStyle("-fx-background-color: rgb(230,166,255);");
                                        allButtons[i+1][j+1].setOnAction(new EventHandler<ActionEvent>() {
                                            
                                            public void handle(ActionEvent arg0) {
                                                // movePiece(team, I, J, I, J+1, Piece.Pawn);
                                                


                                                // gridPane.getChildren().remove(allButtons[I + 1][J + 1]);
                                                gridPane.getChildren().remove(allButtons[I][J]);
                                                // gridPane.getChildren().remove(allButtons[I][J + 1]);
                                                // allButtons[I + 1][J + 1] = new Pawn(allButtons, team, true, allTeamMoves);
                                                // gridPane.add(allButtons[I + 1][J + 1], J + 1, I + 1);
                                                allButtons[I][J] = new CustomButton(allButtons);
                                                gridPane.add(allButtons[I][J], J, I);
                                                movePiece(team, I, J+1, I+1, J+1,Piece.Pawn);
                                                // allButtons[I][J + 1] = new CustomButton(allButtons);
                                                // gridPane.add(allButtons[I][J + 1], J + 1, I);
                                                // allButtons[I][J].fire();
                                                 HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
                                                ImageView view = new ImageView(Game.whitePawnImg);
                                                view.setFitHeight(75);
                                                view.setFitWidth(53);


                                                Label label = new Label();
                                                label.setGraphic(view);
                                                if(leftLeftSide.getChildren().size()<8){
                                                    leftLeftSide.getChildren().add(label);
                                                }else{
                                                    rightLeftSide.getChildren().add(label);
                                                }
                                                turn++;
                                            }
                                        });
                                    }
                                }
                                }
                                if(allButtons[i+1][j].team == 0){
                                    
                                    final int I = i;
                                    final int J = j;
                                if(!possibleMove(team, i, j, i+1, j)){
                                        }else{    
                                allButtons[i+1][j].setStyle("-fx-background-color: rgb(77,166,255);");
                                    allButtons[i+1][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                             if(I+1 == 7){
                                                AlertBox.promotion(allButtons, team, I, J, I+1, J);
                                             }else{
                                                movePiece(team, I, J, I+1, J,Piece.Pawn);
                                             }
                                             turn++;
                                             
                                        }
                                    });
                                }   
                                    if(i<6){    
                                    if(!this.hasMoved && allButtons[i+2][j].team == 0){
                                        if(!possibleMove(team, i, j, i+2, j)){
                                        }else{    
                                    allButtons[i+2][j].setStyle("-fx-background-color: rgb(177,166,255);");    
                                    allButtons[i+2][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            movePiece(team, I, J, I+2, J,Piece.Pawn);
                                             if(J-1>=0){
                                                 if(allButtons[I+2][J-1].getClass()== new Pawn(allButtons,team,true,teamMoves).getClass()){
                                                    allButtons[I+2][J-1].En_Passant_right= true;
                                                 }
                                             }
                                            if(J+1<=7){
                                                if(allButtons[I+2][J+1].getClass()== new Pawn(allButtons,team,true,teamMoves).getClass()){
                                                    allButtons[I+2][J+1].En_Passant_left= true;
                                                }
                                            }
                                             turn++;
                                        }
                                    });
                                    }
                                }
                                }
                                }
                            if(j>0){
                            if(allButtons[i+1][j-1].team == 1){
                                final int I = i;
                                    final int J = j;
                                if(!possibleMove(team, i, j, i+1, j-1)){
                                        }else{        
                                allButtons[i+1][j-1].setStyle("-fx-background-color: red;");
                                    allButtons[i+1][j-1].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            if(I+1 == 7){
                                                AlertBox.promotion(allButtons, team, I, J, I+1, J-1);
//                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                            }else{
                                                movePiece(team, I, J, I+1, J-1,Piece.Pawn);
                                            }

                                             turn++;
                                        }
                                    });
                            }
                        }
                        }   
                            if(j<7){
                            if(  allButtons[i+1][j+1].team == 1){
                                final int I = i;
                                    final int J = j;
                                    if(!possibleMove(team, i, j, i+1, j+1)){
                                        }else{    
                                allButtons[i+1][j+1].setStyle("-fx-background-color: red;");
                                    allButtons[i+1][j+1].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            if(I+1 == 7){
                                                AlertBox.promotion(allButtons, team, I, J, I+1, J+1);
//                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                            }else{
                                                movePiece(team, I, J, I+1, J+1,Piece.Pawn);
                                            }
                                             turn++;
                                        }
                                    });
                            }
                        }
                            }
                        }
                        }else{
                            if(i>0){
                                if(En_Passant_left){
                                    if(allButtons[i-1][j-1].getClass()== new CustomButton(allButtons).getClass()){
                                        final int I=i;
                                        final int J=j;
                                        if(!possibleMove(team, i, j, i, j-1)){
                                        }else{    
                                        allButtons[i-1][j-1].setStyle("-fx-background-color: rgb(230,166,255);");
                                        allButtons[i-1][j-1].setOnAction(new EventHandler<ActionEvent>() {
                                            public void handle(ActionEvent arg0) {
                                                // movePiece(team, I, J, I, J-1,Piece.Pawn);
                                                
                                                // gridPane.getChildren().remove(allButtons[I - 1][J - 1]);
                                                gridPane.getChildren().remove(allButtons[I][J]);
                                                // gridPane.getChildren().remove(allButtons[I][J - 1]);
                                                // allButtons[I - 1][J - 1] = new Pawn(allButtons, team, true, allTeamMoves);
                                                // gridPane.add(allButtons[I - 1][J - 1], J - 1, I - 1);
                                                allButtons[I][J] = new CustomButton(allButtons);
                                                gridPane.add(allButtons[I][J], J, I);
                                                movePiece(team, I, J-1, I-1, J-1,Piece.Pawn);
                                                // allButtons[I][J - 1] = new CustomButton(allButtons);
                                                // gridPane.add(allButtons[I][J - 1], J - 1, I);
                                                // allButtons[I][J].allActionSet();
                                                 HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
                                                ImageView view = new ImageView(Game.blackPawnImg);
                                                view.setFitHeight(75);
                                                view.setFitWidth(53);
                                                Label label = new Label();
                                                label.setGraphic(view);
                                                if(rightRightSide.getChildren().size()<8){
                                                    rightRightSide.getChildren().add(label);
                                                }else{
                                                    leftRightSide.getChildren().add(label);
                                                }
                                                turn++;
                                            }
                                        });
                                    }
                                }
                                }
                                if(En_Passant_right){
                                    final int I=i;
                                    final int J=j;
                                    if(allButtons[I-1][J+1].getClass()== new CustomButton(allButtons).getClass()){
                                        if(!possibleMove(team, i, j, i, j+1)){
                                        }else{    
                                        allButtons[i-1][j+1].setStyle("-fx-background-color: rgb(230,166,255);");
                                        allButtons[i-1][j+1].setOnAction(new EventHandler<ActionEvent>() {
                                            public void handle(ActionEvent arg0) {
                                                // movePiece(team, I, J, I, J+1,Piece.Pawn);
                                                


                                                // gridPane.getChildren().remove(allButtons[I - 1][J + 1]);
                                                gridPane.getChildren().remove(allButtons[I][J]);
                                                // gridPane.getChildren().remove(allButtons[I][J + 1]);
                                                // allButtons[I - 1][J + 1] = new Pawn(allButtons, team, true, allTeamMoves);
                                                // gridPane.add(allButtons[I - 1][J + 1], J + 1, I - 1);
                                                allButtons[I][J] = new CustomButton(allButtons);
                                                gridPane.add(allButtons[I][J], J, I);
                                                movePiece(team, I, J+1, I-1, J+1,Piece.Pawn);
                                                // allButtons[I][J + 1] = new CustomButton(allButtons);
                                                // gridPane.add(allButtons[I][J + 1], J + 1, I);
                                                // allButtons[I][J].allActionSet();
                                                 HBox leftBox =(HBox)App.borderPane.getChildren().get(1);
        VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
        VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
        HBox rightBox =(HBox)App.borderPane.getChildren().get(2);
        VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
        VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
                                                ImageView view = new ImageView(Game.blackPawnImg);
                                                view.setFitHeight(75);
                                                view.setFitWidth(53);
                                                Label label = new Label();
                                                label.setGraphic(view);
                                                if(rightRightSide.getChildren().size()<8){
                                                    rightRightSide.getChildren().add(label);
                                                }else{
                                                    leftRightSide.getChildren().add(label);
                                                }
                                                turn++;
                                            }
                                        });
                                    }
                                }
                                }
                                if(allButtons[i-1][j].team == 0){
                                    final int I = i;
                                    final int J = j;
                                    if(!possibleMove(team, i, j, i-1, j)){
                                        }else{    
                                    allButtons[i-1][j].setStyle("-fx-background-color: rgb(77,166,255);");
                                    allButtons[i-1][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            if(I-1 == 0){
                                               AlertBox.promotion(allButtons, team, I, J, I-1, J);
//                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                             }else{
                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                             }
                                             turn++;
                                             
                                        }
                                    });
                                }
                                    if(!this.hasMoved && allButtons[i-2][j].team == 0){
                                        if(!possibleMove(team, i, j, i-2, j)){
                                        }else{    
                                        allButtons[i-2][j].setStyle("-fx-background-color: rgb(177,166,255);");
                                    allButtons[i-2][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            movePiece(team, I, J, I-2, J,Piece.Pawn);
                                             if(J-1>=0){
                                                if(allButtons[I-2][J-1].getClass()== new Pawn(allButtons,team,true,teamMoves).getClass()){
                                                    allButtons[I-2][J-1].En_Passant_right= true;
                                                }
                                            }
                                            if(J+1<=7){
                                                if(allButtons[I-2][J+1].getClass()== new Pawn(allButtons,team,true,teamMoves).getClass()){
                                                    allButtons[I-2][J+1].En_Passant_left= true;
                                                }
                                            }
                                             turn++;
                                        }
                                    });
                                    }
                                }
                                }
                            if(j>0){
                            if( allButtons[i-1][j-1].team == 2){
                                final int I = i;
                                    final int J = j;
                                if(!possibleMove(team, i, j, i-1, j-1)){
                                        }else{        
                                allButtons[i-1][j-1].setStyle("-fx-background-color: red;");
                                    allButtons[i-1][j-1].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            if(I-1 == 0){
                                                AlertBox.promotion(allButtons, team, I, J, I-1, J-1);
//                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                            }else{
                                                movePiece(team, I, J, I-1, J-1,Piece.Pawn);
                                            }
                                             turn++;
                                        }
                                    });
                            }
                        }
                        }   if(j<7){
                            if(allButtons[i-1][j+1].team == 2){
                                final int I = i;
                                    final int J = j;
                                    if(!possibleMove(team, i, j, i+1, j)){
                                        }else{                                        
                                allButtons[i-1][j+1].setStyle("-fx-background-color: red;");
                                    allButtons[i-1][j+1].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            if(I-1 == 0){
                                                AlertBox.promotion(allButtons, team, I, J, I-1, J+1);
//                                                movePiece(team, I, J, I-1, J,Piece.Pawn);
                                            }else{
                                                movePiece(team, I, J, I-1, J+1,Piece.Pawn);
                                            }
                                             turn++;
                                        }
                                    });
                            }
                        }
                        }
                            }
                        }
                    }
                }
            
                
            }


        }  
        });
    }
    
    
    public void changePiece(CustomButton piece,int y,int x){
                piece = new Pawn(allButtons, team,true, teamMoves);
                allButtons[y][x]= new CustomButton(allButtons);
    }
    public void getRange(){
        teamMoves.clear();
        for (int i=0;i<8;i++) {
                for(int j=0;j<8;j++){
                    if(allButtons[i][j].equals(this)){
                        if(this.team==2){
                            if(i<7){
                                if(allButtons[i+1][j].team==0){
                                    teamMoves.add(((i+1)*10)+j);
                                    if(!this.hasMoved)
                                    if(allButtons[i+2][j].team==0){
                                    teamMoves.add(((i+2)*10)+j);
                                    }
                                }
                            if(j>0){
                                if(allButtons[i+1][j-1].team!=0){
                                teamMoves.add(((i+1)*10)+j-1);
                                }

                        }   
                            if(j<7){
                                if(allButtons[i+1][j+1].team!=0){
                                teamMoves.add(((i+1)*10)+j+1);
                                }
                            }
                        }
                        }else{
                            if(i>0){
                                if(allButtons[i-1][j].team==0){
                                    teamMoves.add(((i-1)*10)+j);
                                    if(!this.hasMoved)
                                    if(allButtons[i-2][j].team==0){
                                    teamMoves.add(((i-2)*10)+j);
                                    }
                                }
                            if(j>0){
                                if(allButtons[i-1][j-1].team!=0){
                                teamMoves.add(((i-1)*10)+j-1);
                                }

                        }   if(j<7){

                                if(allButtons[i-1][j+1].team!=0){
                                teamMoves.add(((i-1)*10)+j+1);
                                }
                            
                        }
                            }
                        }
                    }
                } 
        }
        
    }
    
}
class Bishop extends CustomButton{
    Bishop(CustomButton allButtons[][],int team, LinkedHashSet teamMoves){
        super(allButtons, team,teamMoves);
//        this.setText("Bishop");
        this.setStyle("-fx-background-color: silver;");
        // this.actionSet();
    }
    public void recolor(){
//        this.setStyle("-fx-background-color: silver;");
        if(this.team==1){
            ImageView view = new ImageView(Game.whiteBishopImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackBishopImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            this.setGraphic(view);
        }
    }
    // public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     allButtons[endRow][endColumn] = new Bishop(allButtons, team,allTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     new CustomButton(allButtons).fire();
    // };
    public void actionSet(){
        this.setOnAction(e ->{
            
        new CustomButton(allButtons).fire();
        if((turn+1)%2 == this.team%2){
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                        allButtons[y][x].setStyle("-fx-background-color: gold");
            int i=y-1;
            int j=x-1;
            final int Y = y;
            final int X = x;
            int color = 77;
            while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                
                if(allButtons[i][j].team==0){
                 if(!possibleMove(team, y, x, i, j)){
                    i--;j--;
                    continue;
                }  
                allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;         
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    break;
                                    
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                             
                                        }
                                    });
                                    break;
                                }
                                    i--;j--;
           
            }
            i=y+1;
            j=x-1;
             color = 77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;j--;
                    continue;
                }
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                             movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                             movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                             
                                        }
                                    });
                                    break;
                                }
                i++;j--;
            }
            i=y-1;
            j=x+1;
             color = 77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i--;j++;
                    continue;
                }
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;

                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i--;j++;
            }
            i=y+1;
            j=x+1;
            color = 77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;j++;
                    continue;
                }
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;

                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Bishop);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i++;j++;
            }
        }
        }
    }
    }
        });
        
    }
    public void getRange(){
        teamMoves.clear();
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
            int i=y-1;
            int j=x-1;
            final int Y = y;
            final int X = x;
            while( i>=0 && i<=7 && j>=0 && j<=7){

                if(allButtons[i][j].team==0){
                    teamMoves.add((i*10)+j);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((i*10)+j);
                                    break;
                                }else{
                                    teamMoves.add((i*10)+j);

                                    break;
                                }
                                    i--;j--;
           
            }
            i=y+1;
            j=x-1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((i*10)+j);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((i*10)+j);                                   

                                    break;
                                }
                i++;j--;
            }
            i=y-1;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){

                    teamMoves.add((i*10)+j);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    
                                    break;
                                }else{
                                   
                                        teamMoves.add((i*10)+j);
                                    break;
                                }
                i--;j++;
            }
            i=y+1;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((i*10)+j);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((i*10)+j);
                                    break;
                                }
                i++;j++;
            }
        }
    }
}
    
    }
}
class Knight extends CustomButton{
    Knight(CustomButton allButtons[][],int team,LinkedHashSet teamMoves){
        super(allButtons, team,teamMoves);
//        this.setText("Knight");
//        this.setStyle("-fx-background-color: purple;");
    }
    public void recolor(){
//        this.setStyle("-fx-background-color: purple;");
        if(this.team==1){
            ImageView view = new ImageView(Game.whiteKnightImg);
            view.setFitHeight(76);
            view.setFitWidth(67);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackKnightImg);
            view.setFitHeight(76);
            view.setFitWidth(67);
            this.setGraphic(view);
        }
    }
    // public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     allButtons[endRow][endColumn] = new Knight(allButtons, team,allTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     new CustomButton(allButtons).fire();
    // };
    public void actionSet(){
        this.setOnAction(e ->{
            new CustomButton(allButtons).fire();
            if((turn+1)%2 == this.team%2){
            for (int y=0;y<8;y++) {
                for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                        allButtons[y][x].setStyle("-fx-background-color: gold");
                        final int Y = y;
                        final int X = x;
                        System.out.println(Y+" "+X);
                        if(y+2 <8 && x+1<8){
                            final int I = y+2;
                            final int J = x+1;
                            if(allButtons[I][J].team == team){
                            }else{
                                if(possibleMove(team, Y, X, I, J)){
                                System.out.println("Knight");
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,76,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                        }
                        if(y+2 <8 && x-1>=0){
                            final int I = y+2;
                            final int J = x-1;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,255,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                             movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                        }
                        if(y+1 <8 && x+2<8){
                            final int I = y+1;
                            final int J = x+2;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,236,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                             movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                    }
                    if(y+1 <8 && x-2>=0){
                            final int I = y+1;
                            final int J = x-2;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,106,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                    }
                    if(y-1 >=0 && x-2>=0){
                            final int I = y-1;
                            final int J = x-2;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,146,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                }
                    if(y-1 >=0 && x+2<8){
                            final int I = y-1;
                            final int J = x+2;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,176,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                }   
                
                    if(y-2 >=0 && x+1<8){
                            final int I = y-2;
                            final int J = x+1;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,206,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                }
                    if(y-2 >=0 && x-1>=0){
                            final int I = y-2;
                            final int J = x-1;
                            if(allButtons[I][J].team == team){
                            }else{
                                 if(possibleMove(team, Y, X, I, J)){
                                allButtons[I][J].setStyle("-fx-background-color:rgb(77,236,255);");
                                if(allButtons[I][J].team!=0){
                                  allButtons[I][J].setStyle("-fx-background-color:red;");  
                                }
                                allButtons[I][J].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Knight);
                                             turn++;
                                             
                                        }
                                    });
                            }
                        }
                }             
            }
                }
            }
        }   
        });
    }
    public void getRange(){
        teamMoves.clear();
        for (int y=0;y<8;y++) {
                for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                        if(y+2 <8 && x+1<8){
                            final int I = y+2;
                            final int J = x+1;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                               teamMoves.add((I*10)+J);
                            }
                        }
                        if(y+2 <8 && x-1>=0){
                            final int I = y+2;
                            final int J = x-1;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);
                            }
                        }
                        if(y+1 <8 && x+2<8){
                            final int I = y+1;
                            final int J = x+2;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);
                            }
                    }
                    if(y+1 <8 && x-2>=0){
                            final int I = y+1;
                            final int J = x-2;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);
                            }
                    }
                    if(y-1 >=0 && x-2>=0){
                            final int I = y-1;
                            final int J = x-2;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                 teamMoves.add((I*10)+J);
                            }
                }
                    if(y-1 >=0 && x+2<8){
                            final int I = y-1;
                            final int J = x+2;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);
                            }
                }   
                    if(y-2 >=0 && x+1<8){
                            final int I = y-2;
                            final int J = x+1;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);       
                            }
                }
                    if(y-2 >=0 && x-1>=0){
                            final int I = y-2;
                            final int J = x-1;
                            if(allButtons[I][J].team == team){
                                teamMoves.add((I*10)+J);
                            }else{
                                teamMoves.add((I*10)+J);
                            }
                }             
            }
                }
            }
    }
}
class Queen extends CustomButton{
    Queen(CustomButton allButtons[][],int i,LinkedHashSet teamMoves){
        super(allButtons, i,teamMoves);
//        this.setText("Queen");
        this.setStyle("-fx-background-color: pink; ");
    }
    public void recolor(){
//        this.setStyle("-fx-background-color: pink;");
        if(this.team==1){
            ImageView view = new ImageView(Game.whiteKingImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackKingImg);
            view.setFitHeight(65);
            view.setFitWidth(65);
            this.setGraphic(view);
        }

    }
    // public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     allButtons[endRow][endColumn] = new Queen(allButtons, team,allTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     new CustomButton(allButtons).fire();
    // };
    public void actionSet(){
        this.setOnAction(e ->{
            new CustomButton(allButtons).fire();
            if((turn+1)%2 == this.team%2){
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                        allButtons[y][x].setStyle("-fx-background-color: gold");
                                    int i=y-1;
            int j=x-1;
            final int Y = y;
            final int X = x;
            int color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i--;j--;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                                    i--;j--;
           
            }
            i=y+1;
            j=x-1;
            color = 77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;

                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;j--;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                            turn++; 
                                             
                                        }
                                    });
                                    break;
                                }
                i++;j--;
            }
        
            i=y-1;
            j=x+1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i--;j++;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i--;j++;
            }
            i=y+1;
            j=x+1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;j++;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i++;j++;
            }
            i=y-1;
            j=x;
            color=77;
            while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                 
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i--;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                                    i--;;
           
            }
            i=y+1;
            j=x;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i++;
            }
            i=y;
            j=x+1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    j++;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                j++;
            }
            i=y;
            j=x-1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    j--;
                    continue;
                    } 
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[I][J].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Queen);
                                            turn++; 
                                             
                                        }
                                    });
                                    break;
                                }
                j--;
            }
            allButtons[y][x].setStyle("-fx-background-color: gold");
        }
        }
    }
    }
        });
    }
    public void getRange(){
        teamMoves.clear();
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                                    int i=y-1;
            int j=x-1;
            final int Y = y;
            final int X = x;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    
                                    break;
                                }
                                    i--;j--;
           
            }
            i=y+1;
            j=x-1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                i++;j--;
            }
            i=y-1;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                i--;j++;
            }
            i=y+1;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                i++;j++;
            }
            i=y-1;
            j=x;
            while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);                   

                                    break;
                                }
                                    i--;;
           
            }
            i=y+1;
            j=x;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                i++;
            }
            i=y;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                j++;
            }
            i=y;
            j=x-1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                j--;
            }
        }
        }
    }
    
    }
}
class Rook extends CustomButton{
    Rook(CustomButton allButtons[][],int team,boolean hasMoved,LinkedHashSet teamMoves){
        super(allButtons, team,teamMoves);
        this.hasMoved = hasMoved;
//        this.setText("Rook");
        this.setStyle("-fx-background-color: orange;");
    }
    public void recolor(){

//        this.setStyle("-fx-background-color: orange;");
        if(this.team==1){
            ImageView view = new ImageView(Game.whiteRookImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackRookImg);
            view.setFitHeight(75);
            view.setFitWidth(53);
            this.setGraphic(view);
        }
    }
    // public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     allButtons[endRow][endColumn] = new Rook(allButtons, team,true,allTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     new CustomButton(allButtons).fire();
    // };
    public void actionSet(){
        this.setOnAction(e ->{
            new CustomButton(allButtons).fire();
            if((turn+1)%2 == this.team%2){
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
                        allButtons[y][x].setStyle("-fx-background-color: gold");
            int i=y-1;
            int j=x;
            final int Y = y;
            final int X = x;
            int color = 77;            
            while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i--;
                    continue;
                    }  
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                             movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                                    i--;
           
            }
            i=y+1;
            j=x;
            color = 77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
               
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    i++;
                    continue;
                    } 
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                i++;
            }
            i=y;
            j=x+1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    j++;
                    continue;
                }
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                j++;
            }
            i=y;
            j=x-1;
            color=77;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                
                
                if(allButtons[i][j].team==0){
                    if(!possibleMove(team, y, x, i, j)){
                    j--;
                    continue;
                }
                    allButtons[i][j].setStyle("-fx-background-color: rgb("+color+",166,255);");    
                color+=50;
                allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                }else if(allButtons[i][j].team==team){
                                    break;
                                }else{
                                    if(!possibleMove(team, y, x, i, j)){
                                        break;
                                    }  
                                    allButtons[i][j].setStyle("-fx-background-color:red;");
                                    allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            
                                            movePiece(team, Y, X, I, J,Piece.Rook);
                                             turn++;
                                             
                                        }
                                    });
                                    break;
                                }
                j--;
            }
        }
        }
    }
}
        });
    }
    public void getRange(){
        teamMoves.clear();
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                    if(allButtons[y][x].equals(this)){
            int i=y-1;
            int j=x;
            final int Y = y;
            final int X = x;
 
            while( i>=0 && i<=7 && j>=0 && j<=7){
                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                    teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                                    i--;;
           
            }
            i=y+1;
            j=x;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                i++;
            }
            i=y;
            j=x+1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                j++;
            }
            i=y;
            j=x-1;
                        while( i>=0 && i<=7 && j>=0 && j<=7){
                                final int I = i;
                final int J = j;
                if(allButtons[i][j].team==0){
                teamMoves.add((I*10)+J);
                                }else if(allButtons[i][j].team==team){
                                    teamMoves.add((I*10)+J);
                                    break;
                                }else{
                                    teamMoves.add((I*10)+J);
                                    break;
                                }
                j--;
            }
            
        }
        }
    }
    }
}
class King extends CustomButton{
    LinkedHashSet enemyTeamMoves;
    King(CustomButton allButtons[][],int team,boolean hasMoved,LinkedHashSet teamMoves,LinkedHashSet enemyTeamMoves){
        super(allButtons, team,teamMoves);
        this.hasMoved = hasMoved;
        this.enemyTeamMoves = enemyTeamMoves;
//        this.setText("King");
//        this.setStyle("-fx-background-color: gold;");
    }
    public void recolor(){
        if(this.team==1){
            ImageView view = new ImageView(Game.whiteQueenImg);
            view.setFitHeight(67);
            view.setFitWidth(65);
            this.setGraphic(view);
        }else{
            ImageView view = new ImageView(Game.blackQueenImg);
            view.setFitHeight(67);
            view.setFitWidth(65);
            this.setGraphic(view);
        }
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                if(allButtons[y][x].equals(this)){
                    if(checkKingMove(y, x, team, enemyTeamMoves)){
                        this.setStyle("-fx-background-color: rgb(255,25,255);");
                    }
                }
            }
        }
        
    }
    // public void movePiece(int team,int startRow,int startColumn,int endRow,int endColumn){
    //     HBox leftBox =(HBox)borderPane.getChildren().get(1);
    //     VBox leftLeftSide = (VBox)leftBox.getChildren().get(0);
    //     VBox rightLeftSide  = (VBox)leftBox.getChildren().get(1);
    //     HBox rightBox =(HBox)borderPane.getChildren().get(2);
    //     VBox leftRightSide= (VBox)rightBox.getChildren().get(0);
    //     VBox rightRightSide = (VBox)rightBox.getChildren().get(1);
    //     HBox topBox = (HBox)borderPane.getChildren().get(3);
    //     Label timeLabel = (Label)topBox.getChildren().get(0);
    //     timeLabel.setText("30");
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     if(allButtons[endRow][endColumn].getClass()!= new CustomButton(allButtons).getClass()){
    //         if(team==1){
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftRightSide.getChildren().size()<9){
    //                 leftRightSide.getChildren().add(label);
    //             }else{
    //                 rightRightSide.getChildren().add(label);
    //             }
    //         }else{
    //             Label label = new Label();
    //             label.setText(allButtons[endRow][endColumn].getText());
    //             if(leftLeftSide.getChildren().size()<9){
    //                 leftLeftSide.getChildren().add(label);
    //             }else{
    //                 rightLeftSide.getChildren().add(label);
    //             }
    //         }
    //     }
    //     gridPane.getChildren().remove(allButtons[endRow][endColumn]);
    //     gridPane.getChildren().remove(allButtons[startRow][startColumn]);
    //     allButtons[endRow][endColumn] = new King(allButtons, team,true,allTeamMoves,enemyTeamMoves);
    //     allButtons[startRow][startColumn] = new CustomButton(allButtons);  
    //     gridPane.add(allButtons[endRow][endColumn], endColumn, endRow);
    //     gridPane.add(allButtons[startRow][startColumn], startColumn, startRow);
    //     for (int i=0;i<8;i++) {
    //         for(int j=0;j<8;j++){
    //             allButtons[i][j].En_Passant_left=false;
    //             allButtons[i][j].En_Passant_right=false;
    //         }
    //     }
    //     new CustomButton(allButtons).fire();
    // };
    public void actionSet(){

        this.setOnAction(e ->{
            new CustomButton(allButtons).fire();
            if((turn+1)%2 == this.team%2){
        if(this.team==1){
            updateRange(2,enemyTeamMoves);
        }else{
            updateRange(1,enemyTeamMoves);
        }
        
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                if(allButtons[y][x].equals(this)){
                    allButtons[y][x].setStyle("-fx-background-color: gold");
                    System.out.println(checkMate(y, x, team, enemyTeamMoves));
                    int i=y-1;
                    int j=x-1;
                    final int Y = y;
                    final int X = x;
                    int leftRookColumn=0;
                    int rightRookColumn=7;
                    int rookRow;
                    if(this.team==1){
                        rookRow=7;
                    }else{
                        rookRow=0;
                    }

                    if(!this.hasMoved && !checkKingMove(Y,X,team,enemyTeamMoves)){
                        if(allButtons[rookRow][leftRookColumn].getClass()== new Rook(allButtons,1,false,teamMoves).getClass()){
                            if(!allButtons[rookRow][leftRookColumn].hasMoved){
                                boolean checker = false;
                                for(int column =leftRookColumn+1;column<x;column++){
                                    if(allButtons[rookRow][column].getClass()!= new CustomButton(allButtons).getClass()){
                                        checker = true;
                                        break;
                                    }
                                    if(checkKingMove(rookRow,column,team,enemyTeamMoves)){
                                        checker = true;
                                        break;
                                    }
                                }
                                if(!checker){
                                    allButtons[rookRow][leftRookColumn+2].setStyle("-fx-background-color:rgb(85,255,0)");
                                    allButtons[rookRow][leftRookColumn+2].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            // movePiece(team, Y, X, Y, X-2,Piece.King);
                                            // Rook x =(Rook)allButtons[rookRow][leftRookColumn];
                                            // x.movePiece(team, rookRow, leftRookColumn, Y, X-1,Piece.Rook);
                                            HBox topBox = (HBox)App.borderPane.getChildren().get(3);
                                            Label timeLabel = (Label)topBox.getChildren().get(0);
                                            timeLabel.setText("30");
                                            gridPane.getChildren().remove(allButtons[Y][X]);
                                            gridPane.getChildren().remove(allButtons[Y][X-1]);
                                            gridPane.getChildren().remove(allButtons[Y][X-2]);
                                            gridPane.getChildren().remove(allButtons[Y][X-4]);
                                            allButtons[Y][X-2] = new King(allButtons, team,true, allTeamMoves,enemyTeamMoves);
                                            gridPane.add(allButtons[Y][X-2], X-2, Y);
                                            allButtons[Y][X] = new CustomButton(allButtons);
                                            gridPane.add(allButtons[Y][X], X, Y);
                                            allButtons[Y][X-1] = new Rook(allButtons,team,true,allTeamMoves);
                                            gridPane.add(allButtons[Y][X-1], X-1, Y);
                                            allButtons[Y][X-4] = new CustomButton(allButtons);
                                            gridPane.add(allButtons[Y][X-4], X-4, Y);
                                            allButtons[Y][X].fire();
                                            turn++;
                                            if((CustomButton.turn+1) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
                                            for (int i=0;i<8;i++) {
            for(int j=0;j<8;j++){
                allButtons[i][j].En_Passant_left=false;
                allButtons[i][j].En_Passant_right=false;
            }
        }
        
        Player player;
        if(team==1){
            player = Player.White;
        }else{
            player = Player.Black;
        }
        TreeNode newNode = new TreeNode(player, Piece.Empty, 10, 10, 10, 10, allButtons,Game.currentNode,CustomButton.turn);
        newNode.title = player+" Big Castling";
        
        boolean repeatChecker=false;
        boolean redoChecker = false;
        System.out.println("test1");
        for(int i=0;i<Game.currentNode.childNodes.size();i++){
            if(Game.currentNode.childNodes.get(i).startRow == newNode.startRow && Game.currentNode.childNodes.get(i).startColumn == newNode.startColumn && Game.currentNode.childNodes.get(i).endRow == newNode.endRow && Game.currentNode.childNodes.get(i).endColumn == newNode.endColumn ){
                System.out.println("test2");
                repeatChecker=true;
                newNode.childNodes = Game.currentNode.childNodes.get(i).childNodes;
                Game.currentNode.childNodes.set(i,newNode);
                Game.currentNode= Game.currentNode.childNodes.get(i);
            }
            
        }
        if(!Game.undoStack.isEmpty()){
                System.out.println("test3");
                if(newNode.startRow == Game.undoStack.peek().startRow && newNode.startColumn == Game.undoStack.peek().startColumn && newNode.endRow == Game.undoStack.peek().endRow && newNode.endColumn == Game.undoStack.peek().endColumn ){
                    System.out.println("test4");
                    redoChecker=true;
                }
            }
        if(!repeatChecker){
            Game.currentNode.childNodes.add(newNode);
            Game.currentNode= newNode;
            // System.out.println("added");
        }
        if(redoChecker){
            Game.undoStack.pop();
        }else{
            Game.undoStack.clear();
        }
        
        // System.out.println(Game.currentNode.skipButton.getText());
        // System.out.println(repeatChecker);
        // System.out.println(newNode.skipButton.getText());
        // System.out.println("childs:");
        // for(int i=0;i<Game.currentNode.childNodes.size();i++){
        //     System.out.println(Game.currentNode.childNodes.get(i).skipButton.getText());
        // }
        
        newNode.saveButtons(allButtons, allTeamMoves);
        // newNode.skipButton.setOnAction(e -> newNode.timeSkip(gridPane, allButtons,Game.team1Moves,Game.team2Moves));
        new CustomButton(allButtons).fire();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(allButtons[i][j].getClass()== new King(allButtons, team, redoChecker, teamMoves, allTeamMoves).getClass() && allButtons[i][j].team!=team){
                    King enemyKing = (King)allButtons[i][j];
                    if(staleMate(i,j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        if(checkKingMove(i, j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        AlertBox.show("congratulations", ""+player+" has won with checkMate");
                        }else{
                            Player player2;
                            if(player.equals(Player.White)){
                                player2 = Player.Black;
                            }else{
                                player2 = Player.White;
                            }
                            AlertBox.show("stale mate", "it's"+player2+"'s turn but there's no possible move");
                        }
                    }
                }
            }
        }
                                        }
                                    });
                                }
                            }
                        }
                        if(allButtons[rookRow][rightRookColumn].getClass()== new Rook(allButtons,1,false,teamMoves).getClass()){
                            if(!allButtons[rookRow][rightRookColumn].hasMoved){
                                boolean checker = false;
                                for(int column =rightRookColumn-1;column>x;column--){
                                    if(allButtons[rookRow][column].getClass()!= new CustomButton(allButtons).getClass()){
                                        checker = true;
                                        break;
                                    }
                                    if(checkKingMove(rookRow,column,team,enemyTeamMoves)){
                                        checker = true;
                                        break;
                                    }
                                }
                                if(!checker){
                                    allButtons[rookRow][rightRookColumn-1].setStyle("-fx-background-color:rgb(85,255,0)");
                                    allButtons[rookRow][rightRookColumn-1].setOnAction(new EventHandler<ActionEvent>() {
                                        public void handle(ActionEvent arg0){
                                            // movePiece(team, Y, X, Y, X+2,Piece.King);
                                            // Rook x =(Rook)allButtons[rookRow][rightRookColumn];
                                            // x.movePiece(team, rookRow, rightRookColumn, Y, X+1,Piece.Rook);
                                            gridPane.getChildren().remove(allButtons[Y][X]);
                                            gridPane.getChildren().remove(allButtons[Y][X+1]);
                                            gridPane.getChildren().remove(allButtons[Y][X+2]);
                                            gridPane.getChildren().remove(allButtons[Y][X+3]);
                                            allButtons[Y][X+2] = new King(allButtons, team,true, allTeamMoves,enemyTeamMoves);
                                            gridPane.add(allButtons[Y][X+2], X+2, Y);
                                            allButtons[Y][X] = new CustomButton(allButtons);
                                            gridPane.add(allButtons[Y][X], X, Y);
                                            allButtons[Y][X+1] = new Rook(allButtons,team,true,allTeamMoves);
                                            gridPane.add(allButtons[Y][X+1], X+1, Y);
                                            allButtons[Y][X+3] = new CustomButton(allButtons);
                                            gridPane.add(allButtons[Y][X+3], X+3, Y);
                                            allButtons[Y][X].fire();
                                            turn++;
                                            if((CustomButton.turn+1) % 2 == 1){
            Game.turnLabel.setText("Turn : White");
        }else{
            Game.turnLabel.setText("Turn : Black");
        }
                                            for (int i=0;i<8;i++) {
            for(int j=0;j<8;j++){
                allButtons[i][j].En_Passant_left=false;
                allButtons[i][j].En_Passant_right=false;
            }
        }
        
        Player player;
        if(team==1){
            player = Player.White;
        }else{
            player = Player.Black;
        }
        TreeNode newNode = new TreeNode(player, Piece.Empty, 11, 11, 11, 11, allButtons,Game.currentNode,CustomButton.turn);
        newNode.title = player+" small Castling";
        
        boolean repeatChecker=false;
        boolean redoChecker = false;
        System.out.println("test1");
        for(int i=0;i<Game.currentNode.childNodes.size();i++){
            if(Game.currentNode.childNodes.get(i).startRow == newNode.startRow && Game.currentNode.childNodes.get(i).startColumn == newNode.startColumn && Game.currentNode.childNodes.get(i).endRow == newNode.endRow && Game.currentNode.childNodes.get(i).endColumn == newNode.endColumn ){
                System.out.println("test2");
                repeatChecker=true;
                newNode.childNodes = Game.currentNode.childNodes.get(i).childNodes;
                Game.currentNode.childNodes.set(i,newNode);
                Game.currentNode= Game.currentNode.childNodes.get(i);
            }
            
        }
        if(!Game.undoStack.isEmpty()){
                System.out.println("test3");
                if(newNode.startRow == Game.undoStack.peek().startRow && newNode.startColumn == Game.undoStack.peek().startColumn && newNode.endRow == Game.undoStack.peek().endRow && newNode.endColumn == Game.undoStack.peek().endColumn ){
                    System.out.println("test4");
                    redoChecker=true;
                }
            }
        if(!repeatChecker){
            Game.currentNode.childNodes.add(newNode);
            Game.currentNode= newNode;
            // System.out.println("added");
        }
        if(redoChecker){
            Game.undoStack.pop();
        }else{
            Game.undoStack.clear();
        }
        
        // System.out.println(Game.currentNode.skipButton.getText());
        // System.out.println(repeatChecker);
        // System.out.println(newNode.skipButton.getText());
        // System.out.println("childs:");
        // for(int i=0;i<Game.currentNode.childNodes.size();i++){
        //     System.out.println(Game.currentNode.childNodes.get(i).skipButton.getText());
        // }
        
        newNode.saveButtons(allButtons, allTeamMoves);
        // newNode.skipButton.setOnAction(e -> newNode.timeSkip(gridPane, allButtons,Game.team1Moves,Game.team2Moves));
        new CustomButton(allButtons).fire();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(allButtons[i][j].getClass()== new King(allButtons, team, redoChecker, teamMoves, allTeamMoves).getClass() && allButtons[i][j].team!=team){
                    King enemyKing = (King)allButtons[i][j];
                    if(staleMate(i,j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        if(checkKingMove(i, j,enemyKing.team, enemyKing.enemyTeamMoves)){
                        AlertBox.show("congratulations", ""+player+" has won with checkMate");
                        }else{
                            Player player2;
                            if(player.equals(Player.White)){
                                player2 = Player.Black;
                            }else{
                                player2 = Player.White;
                            }
                            AlertBox.show("stale mate", "it's"+player2+"'s turn but there's no possible move");
                        }
                    }
                }
            }
        }
                                            
                                        }
                                    });
                                }
                            }
                        }
                    }
                    if(y>0){
                        
                        if(x==0){
                        j++;
                        }
                    int color = 100;    
                    while( j <= x+1){
                        if(j>7){
                            System.out.println("blocked");
                            break;
                        }
                        if(!possibleMove(team, y, x, i, j)){
                        System.out.println("cant move");
                        j++;
                        continue;
                        }  

                        final int I = i;
                        final int J = j;
                        
                        if(allButtons[i][j].team==0){
                            allButtons[I][J].setStyle("-fx-background-color:rgb(77,"+color+",255);");
                            color+=40;   
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){
                                 
                                movePiece(team, Y, X, I, J,Piece.King);

                                    turn++;                   
                                }
                            });
                        }else if(allButtons[i][j].team==team){
                        }else{
                            allButtons[i][j].setStyle("-fx-background-color:red;");
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){
;
                                movePiece(team, Y, X, I, J,Piece.King);
;
                                    turn++;                
                                }
                            });
                        }
                        j++;;
                    }
                }
                    i=y;
                    j=x-1;

                    if(x==0){
                        j+=2;
                    }
                    System.out.println(x+1);
                    int color = 220;
                    while( j <= x+1){
                        if(j>7){
                            System.out.println("blocked");
                            break;
                        }
                        if(!possibleMove(team, y, x, i, j)){
                        j+=2;
                        continue;
                        }  
                        final int I = i;
                        final int J = j;
                        
                        if(allButtons[i][j].team==0){
                            allButtons[I][J].setStyle("-fx-background-color:rgb(77,"+color+",255);");
                            color+=40;
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){
;
                                movePiece(team, Y, X, I, J,Piece.King);
;
                                     turn++;               
                                }
                            });
                        }else if(allButtons[i][j].team==team){
                        }else{
                            allButtons[i][j].setStyle("-fx-background-color:red;");
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){
;
                                movePiece(team, Y, X, I, J,Piece.King);
;
                                     turn++;               
                                }
                            });
                        }
                        j+=2;
                    }
                    i=y+1;
                    j=x-1;
                    if(y<7){
                        if(x==0){
                        j++;
                        }
                    color=0;
                    while( j <= x+1){
                        if(j>7){
                            System.out.println("blocked");
                            break;
                        }
                        if(!possibleMove(team, y, x, i, j)){
                        j++;
                        continue;
                        }  
    
                        final int I = i;
                        final int J = j;
                        
                        if(allButtons[i][j].team==0){
                            allButtons[I][J].setStyle("-fx-background-color:rgb(77,"+color+",255);");
                            color+=40;
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){

                                movePiece(team, Y, X, I, J,Piece.King);

                                    turn++;                
                                }
                            });
                        }else if(allButtons[i][j].team==team){
                        }else{
                            allButtons[i][j].setStyle("-fx-background-color:red;");
                            allButtons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent arg0){
;
                                movePiece(team, Y, X, I, J,Piece.King);
;
                                    turn++;                
                                }
                            });
                        }
                        j++;
                    }
                }
                            
                }
                }
            }
    }});
    }
    public void getRange(){
        teamMoves.clear();
        for (int y=0;y<8;y++) {
            for(int x=0;x<8;x++){
                if(allButtons[y][x].equals(this)){
                    int i=y-1;
                    int j=x-1;

                    while( j <= x+1){
                            int n = (i*10)+j;
                            if(n>=0 && n<=77){
                            teamMoves.add(n);
                            }
                            j++;
                    }
                    i=y;
                    j=x-1;
                    while( j <= x+1){
                            int n = (i*10)+j;
                            if(n>=0 && n<=77){
                            teamMoves.add(n);
                            }
                        j+=2;
                    }
                    i=y+1;
                    j=x-1;
                    while( j <= x+1){
                            int n = (i*10)+j;
                            if(n>=0 && n<=77){
                            teamMoves.add(n);
                            }
                        j++;
                    }
                            
                }
                }
            }
    }
    
}
