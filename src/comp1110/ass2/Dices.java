package comp1110.ass2;

import java.awt.*;
import java.util.Random;

public class Dices extends Shapes {
   //create a dice and roll its color
    int width;
    int height;
    Color c;

    public Dices() {
        this.width = 1;
        this.height = 1;
    }

    public void paints(int x1, int y1, int x2, int y2){
        //draw
    }

    public boolean isSelected() {
        //determine whether this dice has been selected right now
        return false;
    }

    public void rollDices(){
        Color[] colors=Colors.get_color_set();
        //get the 6 colors set and generate a color of dice
        Random random=new Random();
        this.c=colors[random.nextInt(colors.length)];
    }

    public String get_color(){
        //get the color of this dice
        return this.c.toString();
    }
}
