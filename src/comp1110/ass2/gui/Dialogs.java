package comp1110.ass2.gui;

import comp1110.ass2.AbilityRegion;
import comp1110.ass2.Game_Start;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dialogs extends Application {
    String title;
    Dialog<String> dialog = new Dialog<>();
    int width = 60;
    int height = 40;

    @Override
    public  void start(Stage stage){

    }

    public void show() {
//        dialog.setX(stage.getX() + (stage.getWidth() - dialog.getWidth()) / 2);
//        dialog.setY(stage.getY() + (stage.getHeight() - dialog.getHeight()) / 2);
//        dialog.showAndWait();
            dialog.setResizable(true);
            dialog.getDialogPane().setStyle("-fx-padding: 20; -fx-background-color: #dafafa;");
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        dialogStage.setX((screenBounds.getWidth() - dialogStage.getWidth()) / 2);
        dialogStage.setY((screenBounds.getHeight() - dialogStage.getHeight()) / 2);
        dialog.show();
    }

    public void setShieldChoice(int p) {
        this.title = "Make a choice please~";
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ObservableList<String> options = FXCollections.observableArrayList(
                "Draw one tile with carrot",
                "Choose an ability track and advance 2 steps"
        );
        choiceBox.setItems(options);
        dialog.setTitle(title);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Rabbit Ability:"), 0, 0);
        grid.add(choiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK, ButtonType.CANCEL
        );
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String selectedOption = choiceBox.getValue();
                //handleSelectedOption(selectedOption, p);
                return selectedOption;
            }
            return null;
        });
        //dialog.showAndWait();
    }

    public void setAbilityChoice(int player){
        this.title = "Make a choice please~";
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        List<String> p_abilities = new ArrayList<>();
        for(Map.Entry<AbilityRegion.Abilities,Integer> pairs:Game_Start.gl.players.get(player).abilities.entrySet()){
            p_abilities.add(pairs.getKey().toString());
        }
        ObservableList<String> options = FXCollections.observableArrayList(p_abilities);
        choiceBox.setItems(options);
        dialog.setTitle(title);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Your Ability:"), 0, 0);
        grid.add(choiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK, ButtonType.CANCEL
        );
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String selectedOption = choiceBox.getValue();
                //handleSelectedOption(selectedOption, player);
                return selectedOption;
            }
            return null;
        });
        //dialog.showAndWait();
    }


    public void setColourChoice(int p){
        this.title = "Make a choice please~";
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ObservableList<String> options = FXCollections.observableArrayList(
                "Yellow", "Red", "Green", "Blue", "Purple", "White"
        );
        choiceBox.setItems(options);
        dialog.setTitle(title);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Colours:"), 0, 0);
        grid.add(choiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK, ButtonType.CANCEL
        );
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String selectedOption = choiceBox.getValue();
                int index = Game_Start.gl.rounds.size()-1;
                int i = Game_Start.gui.dice_view.selected.getSelection().get(0);
                List<String> colours = Game_Start.gl.rounds.get(index).colours;
                int num = Game_Start.gl.rounds.get(i).dices_color.get(colours.get(i));
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.valueOf(colours.get(i)),num-1);
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.valueOf(selectedOption),Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.valueOf(selectedOption),0)+1);
                colours.set(i,selectedOption);
                Game_Start.gui.setAvailableDice(colours);
            }
            return null;
        });
        //dialog.showAndWait();
    }

    public void setHint(){
        this.title = "Attention please~";
    }

    //, , , , , , , , ,
    //        ,

}

