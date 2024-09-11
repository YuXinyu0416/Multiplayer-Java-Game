package comp1110.ass2;

import java.awt.*;

public class RollWriteGame {
    //players click the game start, and this will be a center control area
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

    public static void main(String[] args) {
        Player player1=new Player("name1");
        Player player2=new Player("name2");
        TilesShape ts=new TilesShape("R1","RED",3);
        Dices d=new Dices();
        ButtonShape bs=new ButtonShape("confirm");
        Round rd=new Round();
        rules_display();
    }
}
