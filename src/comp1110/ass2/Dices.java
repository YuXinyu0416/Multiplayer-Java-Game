package comp1110.ass2;

import comp1110.ass2.gui.Colour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dices{
   //create a dice and roll its color
    Colour c;
    List<Colour> colors = new ArrayList<>();
    {
        colors.add(Colour.RED);
        colors.add(Colour.BLUE);
        colors.add(Colour.GREEN);
        colors.add(Colour.YELLOW);
        colors.add(Colour.PURPLE);
        colors.add(Colour.WHITE);
    }

    public Dices() {

    }

    public void rollDices(){
        //get the 6 colors set and generate a color of dice
        Random random=new Random();
        this.c= colors.get(random.nextInt(colors.size()));
    }

    public Colour get_color(){
        //get the color of this dice
        return this.c;
    }
}

