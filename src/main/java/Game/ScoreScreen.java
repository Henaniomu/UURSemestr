package Game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScoreScreen extends Application {

    TableView<PlayerData> table;
    ObservableList<PlayerData> data;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        bp.setCenter(getCenter());
        bp.setBottom(getBottom(stage));


        Scene scene = new Scene(bp, 600, 600);
        stage.setMinHeight(450);
        stage.setMinWidth(450);
        stage.setScene(scene);
        stage.setTitle("ScoreTable");
        stage.show();
    }

    private Node getBottom(Stage stage) {
        Button btnReset = new Button("Reset");
        Button btnExit = new Button("Exit");
        HBox hb = new HBox();

        btnExit.setOnAction(o -> {
            MainMenu mm = new MainMenu();
            try {
                mm.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        btnReset.setOnAction(o -> {
            resetScore();
            try {
                readData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        hb.getChildren().addAll(btnReset, btnExit);
        hb.setSpacing(60);


        return hb;
    }

    private Node getCenter() throws IOException {

        readData();


        table = new TableView<>();
        table.getItems().addAll(data);
        table.setItems(data);
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        TableColumn<PlayerData, String> colN = new TableColumn<>("Name");
        colN.setCellValueFactory(new PropertyValueFactory<>("name"));
        colN.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<PlayerData, String> colS = new TableColumn<>("Score");
        colS.setCellValueFactory(new PropertyValueFactory<>("score"));
        colS.setCellFactory(TextFieldTableCell.forTableColumn());


        table.getColumns().addAll(colN, colS);

        return table;
    }

    private void resetScore() {
        File file = new File("score.txt");
        
        if (file.delete()) {
            System.out.println("score.txt deleted");
        }

        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < 5; i++) {
                fw.write("0 Player\n" +
                        "0 Player\n" +
                        "0 Player\n" +
                        "0 Player\n");
            }
            fw.flush();
            fw.close();
            file = new File("high_score.txt");
            if (file.delete()) {
                System.out.println("high_score.txt deleted");
            }

            readData();

            table.refresh();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readData() throws IOException {
        data = FXCollections.observableArrayList();

        Path path = Paths.get("score.txt");
        Scanner sc = new Scanner(path);

        for (int i = 0; i < 3; i++) {
            String temp = sc.nextLine();
            data.add(new PlayerData(temp.substring(temp.indexOf(" ")),
                    Integer.parseInt(temp.substring(0, temp.indexOf(" ")))));
        }
    }
}
