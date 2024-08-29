package comp1110.ass2;

import jdk.jfr.Event;

public class ButtonShape {
    int width;
    int height;
    String title;

    ButtonShape(String title){
        this.title=title;
        this.width=1;
        this.height=1;
    }

    public void paints(int x1, int y1,int x2,int y2){
        //draw the buttons on this location

    }

    public boolean isSelected(){
        //determine whether this button has been selected right now
        return false;
    }
}
