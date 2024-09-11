package comp1110.ass2;

import java.awt.*;

public class Round{
    static int number_rounds;
    Player[] players=new Player[Player.number_players];

    public Round(){
        number_rounds++;
    }

    public void turn(){
       //which player is operating
        for(int i=0;i<players.length;i++){
            players[i].makeMove();
        }
    }

    public int get_num(){
        //show which round now
        return number_rounds;
    }

    static void last_round(){
        //need some if sentences to determine whether this round is the last round,
        //for example players[i].score>=12, and all players can make move in the last round

    }

    String max_same_color(){
        //I need some ways to determine which color is the max_same, maybe by using HashMap
        return "waiting";
    }
}
