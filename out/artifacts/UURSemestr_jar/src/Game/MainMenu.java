package Game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static TextField playerNameTF;
    Button btnStart;
    Button btnScore;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane bp = new BorderPane();

        /*String css = MainMenu.class.getResource("MyStyle.css").toExternalForm();*/

        bp.setCenter(getCenter());
        bp.setTop(getTop());


        btnStart.setOnAction(o -> {
            if (!playerNameTF.getText().equals("")) {
                Game g = new Game();
                try {
                    g.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Enter player name!");
                alert.setTitle("PlayerNameError");
                alert.setContentText("Player name field is empty");
                alert.showAndWait();
            }
        });

        btnScore.setOnAction(o -> {
            ScoreScreen ss = new ScoreScreen();
            try {
                ss.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        Scene scene = new Scene(bp, 650, 400);


        stage.setTitle("PacmanGame");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Node getTop() {
        VBox vb = new VBox();
        Label lb = new Label("Enter Player Name");

        playerNameTF = new TextField();
        playerNameTF.setFont(new Font("Comic Sans MS", 12));
        playerNameTF.setAlignment(Pos.CENTER);
        playerNameTF.setPromptText("Player Name");

        vb.setSpacing(10);
        vb.setPadding(new Insets(30, 100, 30, 100));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(lb, playerNameTF);
        return vb;
    }

    private Node getCenter() {
        VBox vb = new VBox();
        btnStart = new Button("StartGame");
        btnScore = new Button("Score");
        Button btnExit = new Button("Exit");

        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        btnStart.setAlignment(Pos.CENTER);
        btnScore.setAlignment(Pos.CENTER);
        btnExit.setAlignment(Pos.CENTER);

        btnExit.setOnAction(o -> Runtime.getRuntime().exit(0));

        btnStart.setPadding(new Insets(20, 60, 20, 60));

        btnScore.setPrefWidth(70);
        btnScore.setPrefHeight(35);

        btnExit.setPrefWidth(50);
        btnExit.setPrefHeight(30);

        vb.getChildren().addAll(btnStart, btnScore, btnExit);
        return vb;
    }

}
