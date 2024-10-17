package comp1110.ass2;

import comp1110.ass2.gui.Colour;
import comp1110.ass2.gui.TilesShape;

import java.util.*;

public class Player{
    //create some players and support players' operations
    //String id; // the primary key of player
    int name; // need to be scanner in by player
    int score;
    public HashMap<AbilityRegion.Abilities,Integer> abilities = new HashMap<>(); //store abilities players have gotten before
    HashMap<Colour,Integer> ability_steps = new HashMap<>();// how many steps in this row that players have advanced
    public AbilityRegion ar=new AbilityRegion();
    public BuildingRegion br=new BuildingRegion();

    public Player(int name) {
       //create a player
        this.name = name;
        score=0;
        //this.id= UUID.randomUUID().toString();
    }

    public int return_Player(){
        return this.name;
    }

    public void add_score(int s){
        //will call the isFilled methods of Class BuildingRegion, then add this play's score
        this.score+=s;
    }

    public int get_score(){
        //return this player's score to support other methods
        return this.score;
    }

    public Colour get_color(int row){
        return ar.ability_region.get(row);
    }

    public void advance_steps(Player p, Colour c, int step){
        //add steps to make players advance in the ability track and return this step number to determine
        //whether players can get corresponding abilities
        if(ability_steps.containsKey(c)&&ability_steps.get(c)<5) {
            ability_steps.put(c, ability_steps.getOrDefault(c, 0) + step);
            ar.get_ability(p, c);
            ar.isFilled(p, c);
        }
        else if(!ability_steps.containsKey(c)){
            ability_steps.put(c, ability_steps.getOrDefault(c, 0) + step);
            ar.get_ability(p, c);
            ar.isFilled(p, c);
        }
    }

    public int get_stepNUM(Colour c){
        //return how many steps in this row to support other method
       return ability_steps.get(c);
    }

    public boolean choose_tiles_rules(HashMap<Colour,Integer> dices_color, TilesShape ts, boolean isWhite){
        //based on the max_same_color, which is gotten from Class RollRegion, players need to choose which
        //tiles they want, and max_same_color is a constraint on players
        List<Colour> max_color=new ArrayList<>();
        int max_value=0;
        for(Map.Entry<Colour,Integer> pair :dices_color.entrySet()){
            if(max_value==0|| pair.getValue().compareTo(max_value)>0){
                max_value=pair.getValue();
                max_color.clear();
                max_color.add(pair.getKey());
            }
            else if(pair.getValue().compareTo(max_value)==0){
                max_color.add(pair.getKey());
            }
        }

        Colour color1;
        Colour color2;
        boolean can_be_selected=false;
        if(max_color.size()>2){
            System.out.println("You cannot select any tiles!");
        }
        else if(max_color.size()<2){
            color1=max_color.get(0);
            if(ts.get_Color().equals(color1)){
                int num=0;
                for(Map.Entry<Colour,Integer> pair:dices_color.entrySet()){
                    if(pair.getKey().equals(color1)){
                        num=pair.getValue();
                    }
                }
                if(isWhite&&(ts.num_of_tile<=(num+1))){
                    can_be_selected=true;
                }
                else if(!isWhite&&ts.num_of_tile<=num){
                    can_be_selected=true;
                }
            }
        }
        else{
            color1=max_color.get(0);
            color2=max_color.get(1);
            if(ts.get_Color().equals(color1)||ts.get_Color().equals(color2)){
                int num=0;
                for(Map.Entry<Colour,Integer> pair:dices_color.entrySet()){
                    if(pair.getKey().equals(color1)){
                        num=pair.getValue();
                    }
                }
                if(isWhite&&(ts.num_of_tile<=num+1)){
                    can_be_selected=true;
                }
                else if(!isWhite&&ts.num_of_tile<=num){
                    can_be_selected=true;
                }
            }
        }
        return can_be_selected;
    }

    public HashMap<AbilityRegion.Abilities, Integer> store_ability(AbilityRegion.Abilities a){
        //input some abilities name to store
        this.abilities.put(a,abilities.getOrDefault(a,0)+1);
        return this.abilities;
    }

    public void use_ability(String a){
        AbilityRegion.Abilities a_u = AbilityRegion.Abilities.valueOf(a);
        abilities.put(a_u,abilities.get(a_u)-1);
    }

    public void show_abilities(){
        int i=1;
        for(Map.Entry a:abilities.entrySet()){
            System.out.println("("+(i)+") "+a+" "+abilities.get(a));
        }
    }

//    public void makeMove(){
//        //players operate this game through clicking to select other shapes and regions, and there
//        //need to be some difference between active player in this round and other players
//        br.grids[1][2].place_window();
//    }

//    public boolean use_now(Event e){
//        //if players choose use the ability they get immediately, the boolean will be true, then call
//        //the method use ability, if not ,then call the method store_ability
//
//        return true;
//    }
}
