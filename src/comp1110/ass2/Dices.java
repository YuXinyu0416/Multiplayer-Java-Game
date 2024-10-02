package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dices{
   //create a dice and roll its color
    Game_Logic.Colors c;
    List<Game_Logic.Colors> colors = new ArrayList<>();
    {
        colors.add(Game_Logic.Colors.RED);
        colors.add(Game_Logic.Colors.BLUE);
        colors.add(Game_Logic.Colors.GREEN);
        colors.add(Game_Logic.Colors.YELLOW);
        colors.add(Game_Logic.Colors.PURPLE);
        colors.add(Game_Logic.Colors.WHITE);
    }

    public Dices() {

    }

    public void rollDices(){
        //get the 6 colors set and generate a color of dice
        Random random=new Random();
        this.c= colors.get(random.nextInt(colors.size()));
    }

    public Game_Logic.Colors get_color(){
        //get the color of this dice
        return this.c;
    }

//    public boolean isSelected() {
//        //determine whether this dice has been selected right now
//        return true;
//    }

//    public List get_dices_color(){
//        dices_color= new String[]{"RED", "RED", "RED", "BLUE", "WHITE"};
//        return dices_color;
//    }
}

