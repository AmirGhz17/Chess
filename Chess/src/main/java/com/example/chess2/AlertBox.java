package com.example.chess2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox{
    public static void show(String title,String message){
        Stage stage = new Stage();
        VBox layout = new VBox();
        Label label = new Label(message);
        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> stage.close());
        layout.getChildren().addAll(label,closeButton);
        Scene scene = new Scene(layout);
        layout.setAlignment(Pos.CENTER);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public static void promotion(CustomButton[][] allButtons,int team,int startRow,int startColumn,int endRow,int endColumn){
        Stage stage = new Stage();
        VBox layout = new VBox();
        HBox buttonBox = new HBox();
        Label label = new Label("Choose what piece do you want to upgrade your pawn too");
        ImageView view = new ImageView(Game.whiteRookImg);
        view.setFitHeight(75);
        view.setFitWidth(53);
        Button rookButton = new Button();
        rookButton.setGraphic(view);
        rookButton.setMaxSize(80,80);
        rookButton.setMinSize(80,80);
        rookButton.setOnAction(e -> {
            new CustomButton(allButtons).movePiece(team, startRow, startColumn, endRow, endColumn, Piece.Rook);
            stage.close();
        });
        view = new ImageView(Game.whiteKnightImg);
        view.setFitHeight(75);
        view.setFitWidth(70);
        Button knightButton = new Button();
        knightButton.setGraphic(view);
        knightButton.setMaxSize(80,80);
        knightButton.setMinSize(80,80);
        knightButton.setOnAction(e -> {
            new CustomButton(allButtons).movePiece(team, startRow, startColumn, endRow, endColumn, Piece.Knight);
            stage.close();
        });
        view = new ImageView(Game.whiteBishopImg);
        view.setFitHeight(75);
        view.setFitWidth(70);
        Button bishopButton = new Button();
        bishopButton.setGraphic(view);
        bishopButton.setMaxSize(80,80);
        bishopButton.setMinSize(80,80);

        bishopButton.setOnAction(e -> {
            new CustomButton(allButtons).movePiece(team, startRow, startColumn, endRow, endColumn, Piece.Bishop);
            stage.close();
        });
        view = new ImageView(Game.whiteQueenImg);
        view.setFitHeight(65);
        view.setFitWidth(65);
        Button queenButton = new Button();
        queenButton.setGraphic(view);
        queenButton.setMaxSize(80,80);
        queenButton.setMinSize(80,80);

        queenButton.setOnAction(e -> {
            new CustomButton(allButtons).movePiece(team, startRow, startColumn, endRow, endColumn, Piece.Queen);
            stage.close();
        });
        layout.getChildren().addAll(label,buttonBox);
        buttonBox.getChildren().addAll(rookButton,knightButton,bishopButton,queenButton);
        Scene scene = new Scene(layout);
        layout.setAlignment(Pos.CENTER);
        stage.setMinHeight(200);
        stage.setMinWidth(200);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("promotion");
        stage.setScene(scene);
        stage.showAndWait();
    }
}