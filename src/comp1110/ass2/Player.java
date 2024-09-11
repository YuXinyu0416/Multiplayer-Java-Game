package comp1110.ass2;

import java.util.UUID;

public class Player{
    //create some players and support players' operations
    String id; // the primary key of player
    String name; // need to be scanner in by player
    int score=0;
    static int number_players;
    String[] abilities= {}; //store abilities players have gotten before
    int [] steps=new int[5];// how many steps in this row that players have advanced

    public Player(String name) {
       //create a player
        this.name = name;
        this.id= UUID.randomUUID().toString();
        number_players++;
    }

    public void add_score(){
        //will call the isFilled methods of Class BuildingRegion, then add this play's score

    }

    public void score_display(int score){
        //show how many scores this player get

    }

    public int get_score(){
        //return this player's score to support other methods
        return score;
    }

    public void makeMove(){
        //players operate this game through clicking to select other shapes and regions, and there
        //need to be some difference between active player in this round and other players
        BuildingRegion.grids[1][2].place_window();
    }

    public void advance_steps(int row){
        //add steps to make players advance in the ability track and return this step number to determine
        //whether players can get corresponding abilities
        this.steps[row]++;
    }

    public int get_stepNUM(int row){
        //return how many steps in this row to support other method
        return this.steps[row];
    }

    public boolean choose_tiles_rules(TilesShape ts, Round rd){
        //based on the max_same_color, which is gotten from Class RollRegion, players need to choose which
        //tiles they want, and max_same_color is a constraint on players
        boolean can_be_selected=false;
        String max_same_color=rd.max_same_color();
        if(ts.color.equals(max_same_color)){
            can_be_selected=true;
        }
        return can_be_selected;
    }

    static int get_num (){
        //return the number of players to support the turn method of Round
        return number_players;
    }

    public void store_ability(String ability_name){
        //input some abilities name to store

    }

    public void use_ability(String ability_name){
        AbilityRegion.use_abilities(ability_name);
    }

    public void show_abilities(){
        for(int i=0;i<abilities.length;i++){
            System.out.println("("+(i+1)+") "+abilities[i]);
        }
    }
}
