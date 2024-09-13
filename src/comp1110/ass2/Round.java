package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<String> max_same_color(String[] colors){
        //I need some ways to determine which color is the max_same, maybe by using HashMap
        HashMap colors_num=new HashMap<String,Integer>();
        for(String str:colors) {
            colors_num.put(str,colors_num.getOrDefault(str,0)+1);
        }
        List<String> max_color=new ArrayList<>();
        int max_value=0;
        for(Map.Entry<String,Integer> pair:colors_num.entrySet()){
            if(max_value==0|| pair.getValue().compareTo(max_value)>0){
                max_color.clear();
                max_color.add(pair.getKey().toString());
            }
            else if(pair.getValue().compareTo(max_value)==0){
                max_color.add(pair.getKey().toString());
            }
        }
        return max_color;
    }

    public boolean isWhite(String[] colors){
        boolean isWhite=false;
        for(String str:colors){
            if(str.equals("White")){
                isWhite=true;
            }
        }
        return isWhite;
    }

    public static void main(String[] args) {
        List<String> colors=max_same_color(Dices.get_dices_color());
        for(String color:colors){
            System.out.println(color);
        }
    }
}
