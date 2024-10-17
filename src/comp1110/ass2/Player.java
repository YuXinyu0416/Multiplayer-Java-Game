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

    public void withdrawSteps(Colour c, int step){
        int total_step = ability_steps.get(c);
        ability_steps.put(c, ability_steps.get(c) - step);
        if(total_step==ar.star1||total_step==ar.star2){
            if(c.equals(Colour.RED)){
                abilities.put(AbilityRegion.Abilities.redStar,abilities.get(AbilityRegion.Abilities.redStar)-1);
            }
            else if(c.equals(Colour.GREEN)){
                abilities.put(AbilityRegion.Abilities.greenStar,abilities.get(AbilityRegion.Abilities.greenStar)-1);
            }
            else if(c.equals(Colour.BLUE)){
                abilities.put(AbilityRegion.Abilities.blueStar,abilities.get(AbilityRegion.Abilities.blueStar)-1);
            }
            else if(c.equals(Colour.YELLOW)){
                abilities.put(AbilityRegion.Abilities.yellowStar,abilities.get(AbilityRegion.Abilities.yellowStar)-1);
            }
            else if(c.equals(Colour.PURPLE)){
                abilities.put(AbilityRegion.Abilities.purpleStar,abilities.get(AbilityRegion.Abilities.purpleStar)-1);
            }
        }
        else if(total_step==ar.plus_sign1||total_step==ar.plus_sign2){
            if(c.equals(Colour.RED)){
                abilities.put(AbilityRegion.Abilities.RedPlusSign,abilities.get(AbilityRegion.Abilities.RedPlusSign)-1);
            }
            else if(c.equals(Colour.GREEN)){
                abilities.put(AbilityRegion.Abilities.GreenPlusSign,abilities.get(AbilityRegion.Abilities.GreenPlusSign)-1);
            }
            else if(c.equals(Colour.BLUE)){
                abilities.put(AbilityRegion.Abilities.BluePlusSign,abilities.get(AbilityRegion.Abilities.BluePlusSign)-1);
            }
            else if(c.equals(Colour.YELLOW)){
                abilities.put(AbilityRegion.Abilities.YellowPlusSign,abilities.get(AbilityRegion.Abilities.YellowPlusSign)-1);
            }
            else if(c.equals(Colour.PURPLE)){
                abilities.put(AbilityRegion.Abilities.PurplePlusSign,abilities.get(AbilityRegion.Abilities.PurplePlusSign)-1);
            }
        }
    }

    public int get_stepNUM(Colour c){
        //return how many steps in this row to support other method
       return ability_steps.get(c);
    }

    public boolean choose_tiles_rules(HashMap<Colour,Integer> dices_color, TilesShape ts, boolean isWhite) {
        //based on the max_same_color, which is gotten from Class RollRegion, players need to choose which
        //tiles they want, and max_same_color is a constraint on players
        List<Colour> max_color = new ArrayList<>();
        int num_white=-1;
        if (isWhite) {
            num_white = dices_color.get(Colour.WHITE);
            //dices_color.remove(Colour.WHITE);
        }
        int max_value = 0;
        for (Map.Entry<Colour, Integer> pair : dices_color.entrySet()) {
            if (!pair.getKey().equals(Colour.WHITE)) {
                if (max_value == 0 || pair.getValue().compareTo(max_value) > 0) {
                    max_value = pair.getValue();
                    max_color.clear();
                    max_color.add(pair.getKey());
                } else if (pair.getValue().compareTo(max_value) == 0) {
                    max_color.add(pair.getKey());
                }
            }
        }

        boolean can_be_selected = false;
        if (isWhite) {
                if (max_color.contains(ts.get_Color())) {
                    int num = max_value+num_white;
                    if (ts.num_of_tile <= num) {
                        can_be_selected = true;
                    }
                }
            }
        else {
            if (max_color.size() > 2) {
                System.out.println("You cannot select any tiles!");
            }
            else {
                if (max_color.contains(ts.get_Color())) {
                    int num = max_value;
                    if (ts.num_of_tile <= num) {
                        can_be_selected = true;
                    }
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
        AbilityRegion.Abilities a_u = AbilityRegion.Abilities.getAbility(a);
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
