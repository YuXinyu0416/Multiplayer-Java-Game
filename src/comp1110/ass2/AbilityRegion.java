package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class AbilityRegion{
    //this class is Ability tracks with different colors, can be chosen by players and there is some abilities
    //can be gotten
    static int star1=1; // ability signs and locations
    static int star2 =3;
    static int plus_sign1=2;
    static int plus_sign2=4;
    List<Game_Logic.Colors> ability_region=new ArrayList<>();//store color of every row and distinguish them
    enum Abilities
    {
        redStar, blueStar, greenStar, yellowStar, purpleStar, plusSign, Shield
    }
    // all abilities players can get in this game

    public AbilityRegion(){
        ability_region.add(Game_Logic.Colors.RED);
        ability_region.add(Game_Logic.Colors.BLUE);
        ability_region.add(Game_Logic.Colors.PURPLE);
        ability_region.add(Game_Logic.Colors.GREEN);
        ability_region.add(Game_Logic.Colors.YELLOW);
    }

    void use_abilities(Abilities a){
        if(a.equals(Abilities.redStar)){

        }
        else if(a.equals(Abilities.blueStar)){

        }
        else if(a.equals(Abilities.greenStar)){

        }
        else if(a.equals(Abilities.yellowStar)){

        }
        else if(a.equals(Abilities.purpleStar)){

        }
        else if(a.equals(Abilities.plusSign)){

        }
        else if(a.equals(Abilities.Shield)){

        }
        //......and so on, there are some abilities that players can get and use
    }

    public Game_Logic.Colors get_color(int row){
        return ability_region.get(row);
    }

    public void get_ability(Player p, Game_Logic.Colors c){
        //there are some abilities and bonus of different signs and colors, so there need to be some if-else
        //sentences to determine which color ability track and its related ability, then call some other
        //methods to apply this ability
        if(p.get_stepNUM(c) == star1||p.get_stepNUM(c) == star2){
            if(c.equals(Game_Logic.Colors.RED)){
                p.store_ability(Abilities.redStar);
            }
            else if(c.equals(Game_Logic.Colors.BLUE)){
                p.store_ability(Abilities.blueStar);
            }
            else if(c.equals(Game_Logic.Colors.GREEN)){
                p.store_ability(Abilities.greenStar);
            }
            else if(c.equals(Game_Logic.Colors.YELLOW)){
                p.store_ability(Abilities.yellowStar);
            }
            else if(c.equals(Game_Logic.Colors.PURPLE)){
                p.store_ability(Abilities.plusSign);
            }
        }
        else if(p.get_stepNUM(c)==plus_sign1||p.get_stepNUM(c)==plus_sign2){
            p.store_ability(Abilities.plusSign);
        }
        //......and so on, then players can store abilities they get right now
    }

    public void isFilled(Player p, Game_Logic.Colors c){
        if(p.get_stepNUM(c)==5){
            p.add_score(2);
        }
    }

//    static List<Abilities> get_ability_set(){
//        List<Abilities> abilities =new ArrayList<>();
//        abilities.add(Abilities.redStar);
//        return abilities;
//    }

//    public void beColor(){
//        //set the color of ability tracks in five different colors and return this color to support other methods
//
//    }

//    public boolean isSelected(Player player, Game_Logic.Colors c) {
//        //determine whether an ability track is selected right now(maybe by clicking )
//        player.advance_steps(c);
//        return true;
//    }
}
