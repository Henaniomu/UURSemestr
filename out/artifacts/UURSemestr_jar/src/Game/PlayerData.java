package Game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerData {
    private StringProperty name;
    private StringProperty score;

    public PlayerData(String name, int score){
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleStringProperty(Integer.toString(score));
    }

    public StringProperty nameProperty() {
        return name;
    }


    public StringProperty scoreProperty() {
        return score;
    }

}
