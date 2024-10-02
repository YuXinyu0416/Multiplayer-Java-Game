package comp1110.ass2;

import java.util.HashMap;

public class Round{
    static int number_rounds;
    Dices[] new_dices = new Dices[5];
    HashMap<Game_Logic.Colors,Integer> dices_color=new HashMap<>();
    {
        dices_color.put(Game_Logic.Colors.RED,3);
        dices_color.put(Game_Logic.Colors.BLUE,1);
        dices_color.put(Game_Logic.Colors.WHITE,1);

    }
//    {
//        for(int i=0; i<new_dices.length;i++){
//            new_dices[i]=new Dices();
//            new_dices[i].rollDices();
//            Game_Logic.Colors d_c = new_dices[i].get_color();
//            dices_color.put(d_c,dices_color.getOrDefault(d_c,0)+1);
//        }
//    }

    public Round(){
        number_rounds++;
    }

    public boolean isWhite(HashMap<Game_Logic.Colors, Integer> dices_color){
        return dices_color.containsKey(Game_Logic.Colors.WHITE)? true:false;
    }

//    public void turn(){
//       //which player is operating
//        for(int i=0;i<players.length;i++){
//            players[i].makeMove();
//        }
//    }

//    public int get_num(){
//        //show which round now
//        return number_rounds;
//    }

//    public HashMap<String, Integer> max_same_color(String[] colors){
//        //I need some ways to determine which color is the max_same, maybe by using HashMap
//        HashMap<String,Integer> colors_num=new HashMap();
//        for(String str:colors) {
//            colors_num.put(str,colors_num.getOrDefault(str,0)+1);
//        }
//        return colors_num;
//    }
}
