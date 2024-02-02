package Game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane bp = new BorderPane();

        /*String css = MainMenu.class.getResource("MyStyle.css").toExternalForm();*/
        VBox vb = new VBox();
        Button btnRes = new Button("Restart");
        Button btnMenu = new Button("Main Menu");
        Button btnScore = new Button("Score");
        Label label = new Label("GAME OVER");

        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(60));
        label.setFont(new Font("Comic Sans MS", 25));


        btnRes.setOnAction(o -> {
            Game g = new Game();
            try {
                g.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        btnMenu.setOnAction(o -> {
            MainMenu mm = new MainMenu();
            try {
                mm.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
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


        Button btnExit = new Button("Exit");

        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        btnRes.setAlignment(Pos.CENTER);
        btnMenu.setAlignment(Pos.CENTER);
        btnScore.setAlignment(Pos.CENTER);
        btnExit.setAlignment(Pos.CENTER);

        btnRes.setPadding(new Insets(20, 60, 20, 60));

        btnScore.setPrefWidth(70);
        btnScore.setPrefHeight(35);

        btnMenu.setPrefWidth(90);
        btnMenu.setPrefHeight(50);

        btnExit.setPrefWidth(50);
        btnExit.setPrefHeight(30);

        btnExit.setOnAction(o -> Runtime.getRuntime().exit(0));


        vb.getChildren().addAll(label, btnRes, btnMenu, btnScore, btnExit);


        bp.setCenter(vb);


        Scene scene = new Scene(bp, 750, 500);


        stage.setTitle("PacmanGame");
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().clear();
        stage.show();
    }
}
