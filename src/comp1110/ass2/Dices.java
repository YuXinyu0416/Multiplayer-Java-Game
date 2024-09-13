package comp1110.ass2;

import java.awt.*;
import java.util.Random;

public class Dices{
   //create a dice and roll its color
    Color c;
    static String[] dices_color=new String[5];

    public Dices() {

    }

    public boolean isSelected() {
        //determine whether this dice has been selected right now
        return false;
    }

    public void rollDices(){
        Color[] colors= Game_Logic.get_color_set();
        //get the 6 colors set and generate a color of dice
        Random random=new Random();
        this.c=colors[random.nextInt(colors.length)];
    }

    public String get_color(){
        //get the color of this dice
        return this.c.toString();
    }

    static String[] get_dices_color(){
        dices_color= new String[]{"RED", "RED", "RED", "BLUE", "WHITE"};
        return dices_color;
    }
}

