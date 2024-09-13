package comp1110.ass2;

import java.awt.*;

public class Game_Logic {
    //players click the game start, and this will be a center control area
    Player player1=new Player("name1");
    Player player2=new Player("name2");
    TilesShape B3=new TilesShape("B3","BLUE",3,0,0,1);
    TilesShape G4L=new TilesShape("G4L","GREEN",4,3,0,0);
    Dices d=new Dices();
    Round rd=new Round();
    static Color[] get_color_set(){
        Color[] colors=new Color[6];
        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.YELLOW;
        colors[3]=Color.GREEN;
        colors[4]=Color.WHITE;
        colors[5]=new Color(128,0,128);//purple
        return colors;
    }

    public Game_Logic(){

    }

    static void rules_display(){
        //show players all rules of this game, it will appear at the beginning of game and can be called by button
        System.out.println("The rules of this game is as follow:" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "");
    }
}
