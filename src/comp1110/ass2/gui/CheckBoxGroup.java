package comp1110.ass2.gui;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import comp1110.ass2.Game_Start;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.function.IntConsumer;

public class CheckBoxGroup {
    int size;
    CheckBox[] selectors;
    boolean inhibitSelectionEvent = false;

    public CheckBoxGroup(int n) {
        size = n;
        selectors = new CheckBox[n];
        for (int i = 0; i < n; i++) {
            selectors[i] = new CheckBox("");
            selectors[i].setDisable(true);
        }
    }

    public CheckBox getCheckBox(int i) {
        return selectors[i];
    }

    public void setOnSelectionChanged(IntConsumer handler) {
        for (int i = 0; i < size; i++) {
            final int fi = i;
            selectors[i].setOnAction(e -> {
                if (!inhibitSelectionEvent) handler.accept(fi);
            });
        }
    }

    public List<Integer> getSelection() {
        ArrayList<Integer> sel = new ArrayList<>();
        int index =-1;
        for (int i = 0; i < selectors.length; i++) {
            if (selectors[i].isSelected()) {
                sel.add(i);
                index = i;
            }
        }
        if(sel.size()>1) {
            return sel;
        }
        else if(sel.size()==1){
            return Collections.singletonList(index);
        }
        return List.of();
    }

    public void enableRange(int from, int to) {
        for (int i = from; i < to; i++) {
            this.selectors[i].setDisable(false);
        }
    }

    public void disableRange(int from, int to) {
        inhibitSelectionEvent = true;
        for (int i = from; i < to; i++) {
            this.selectors[i].setSelected(false);
            this.selectors[i].setDisable(true);
        }
        inhibitSelectionEvent = false;
    }

    public void clearSelection() {
        inhibitSelectionEvent = true;
        for (CheckBox selector : selectors) selector.setSelected(false);
        inhibitSelectionEvent = false;
    }

//    public void Listener_dices(IntConsumer handler) {
//        inhibitSelectionEvent = true;
//        for(int i=0; i<size; i++) {
//            int finalI = i;
//                selectors[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                        int index = Game_Start.gl.rounds.size() - 1;
//                        int p = Game_Start.gui.getSelectedPlayer();
//                        if (p==Game_Start.gui.getCurrent_now()&&!Game_Start.gl.Dices_canbe_selected(finalI)&&!Game_Start.gl.rounds.get(index).colours.get(finalI).equals(Colour.WHITE.name)) {
//                            selectors[finalI].setSelected(false);
//                            selectors[finalI].setDisable(true);
//                        }
//                        if(p!=Game_Start.gui.getCurrent_now()){
//
//                        }
//                    }
//                });
//            }
//        inhibitSelectionEvent=false;
//    }
}