package comp1110.ass2;

import java.awt.*;

public class TilesShape extends Shapes {
    int width;
    int height;
    String title;
    String color;

    public TilesShape(String title,String color){
        this.title=title;
        this.color=color;
        this.width=1;
        this.height=1;
    }

    public void paints(int x, int y) {
    }


    public boolean isSelected(Event e) {
        return false;
    }

    public void beColor(Color c){
        this.setBackground(c);
    }

    public boolean isExist(){
        return false;
    }

    public void CrossOut(){

    }
}
