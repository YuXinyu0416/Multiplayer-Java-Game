package comp1110.ass2.gui;

import comp1110.ass2.AbilityRegion;
import comp1110.ass2.Game_Start;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
    public void start(Stage stage) {
        dialog.setX(stage.getX() + (stage.getWidth() - dialog.getWidth()) / 2);
        dialog.setY(stage.getY() + (stage.getHeight() - dialog.getHeight()) / 2);
        dialog.showAndWait();
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
        grid.add(new Label("Rabbit Ability:"), 0, 0);
        grid.add(choiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK, ButtonType.CANCEL
        );
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String selectedOption = choiceBox.getValue();
                handleSelectedOption(selectedOption, p);
                return selectedOption;
            }
            return null;
        });
        dialog.showAndWait();
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
        grid.add(new Label("Your Ability:"), 0, 0);
        grid.add(choiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(
                ButtonType.OK, ButtonType.CANCEL
        );
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String selectedOption = choiceBox.getValue();
                handleSelectedOption(selectedOption, player);
                return selectedOption;
            }
            return null;
        });
        dialog.showAndWait();
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
        dialog.showAndWait();
    }

    public void setHint(){
        this.title = "Attention please~";
    }

    //, , , , , , , , ,
    //        ,
    private void handleSelectedOption(String selectedOption, int player) {
        Player which_player = Game_Start.gl.players.get(player);
        int i=Game_Start.gl.rounds.size()-1;
        switch (selectedOption){
            case "Draw one tile with carrot":
                TilesShape S1O = which_player.ar.Shield1_window_tile();
                GameGUI.availableTS.add("S1O");
                Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                break;
            case "Choose an ability track and advance 2 steps":
                int index = Game_Start.gui.dice_view.selected.getSelection().get(0);
                which_player.advance_steps(which_player.ar.get_color(index),2);
                break;
            case "redStar":
                which_player.ar.redStar_reroll(Game_Start.gl.rounds.get(i).dices_color,Game_Start.gl.rounds.get(i).colours, Game_Start.gui.dice_view.selected.getSelection());
                Game_Start.gui.setAvailableDice(Game_Start.gl.rounds.get(i).colours);
                break;
            case "blueStar":
                which_player.ar.blueStar_all_windows(Game_Start.gui.candidate);
                break;
            case "greenStar":
                setColourChoice(player);
                break;
            case "yellowStar":
                Game_Start.gui.setAvailableTiles(List.of("R2", "R3", "R4", "R4", "R5", "B2", "B3", "B4L", "B4R", "B5", "P2","P3","P4","P4","P5","G2", "G3", "G4L", "G4R", "G5", "Y2", "Y3", "Y4L", "Y4R", "Y5"));
                which_player.ar.yellowStar_pick_one(Game_Start.gui.candidate);
                Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                break;
            case "purpleStar":
                TilesShape S1X = which_player.ar.purpleStar_extra_tile();
                GameGUI.availableTS.add("S1X");
                Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                break;
            case "RedPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.RED, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.RED, 0) + 1);
                break;
            case "YellowPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.YELLOW, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.YELLOW, 0) + 1);
                break;
            case "GreenPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.GREEN, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.GREEN, 0) + 1);
                break;
            case "PurplePlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.PURPLE, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.PURPLE, 0) + 1);
                break;
            case "BluePlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.BLUE, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.BLUE, 0) + 1);
                break;
        }
    }
}
