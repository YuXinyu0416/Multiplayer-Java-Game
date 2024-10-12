package comp1110.ass2;

import comp1110.ass2.gui.Colour;

import java.util.*;

public class AbilityRegion{
    //this class is Ability tracks with different colors, can be chosen by players and there is some abilities
    //can be gotten
    static int star1=1; // ability signs and locations
    static int star2 =3;
    static int plus_sign1=2;
    static int plus_sign2=4;
    static List<Colour> ability_region=new ArrayList<>();//store color of every row and distinguish them
    static List<Colour> colors = new ArrayList<>();

    {
        Collections.addAll(colors, Colour.RED, Colour.GREEN, Colour.YELLOW, Colour.BLUE, Colour.PURPLE, Colour.WHITE);
  }

    public AbilityRegion(){
        Collections.addAll(ability_region,Colour.RED, Colour.BLUE, Colour.PURPLE, Colour.GREEN, Colour.YELLOW);
    }

    public enum Abilities {
        redStar, blueStar, greenStar, yellowStar, purpleStar, RedPlusSign, YellowPlusSign, GreenPlusSign, PurplePlusSign,
        BluePlusSign, Shield1, Shield2
    }
    // all abilities players can get in this game

    public void redStar_reroll(HashMap<Colour,Integer> dices_color, List<Colour> change){
        for(int i=0;i<change.size();i++){
            Random random = new Random();
            dices_color.put(change.get(i), dices_color.get(change.get(i))-1);
            Colour new_color = AbilityRegion.colors.get(random.nextInt(6));
            dices_color.put(new_color, dices_color.getOrDefault(new_color,0)+1);
        }
    }

    public TilesShape blueStar_all_windows(TilesShape ts){
        for(int i=0;i<ts.windows.length;i++) {
            ts.windows[i] = true;
        }
        return ts;
    }

    public void greenStar_change_color(HashMap<Colour,Integer> dices_color, int num, Colour c1, Colour c2){
        dices_color.put(c1,dices_color.get(c1)-num);
        dices_color.put(c2, dices_color.getOrDefault(c2,0)+num);
    }

    public boolean yellowStar_pick_one(TilesShape ts){
        boolean whether =false;
        if(ts.num_of_tile==4||ts.num_of_tile==5){
            if(ts.state==false){
                ts.state=true;
            }
            whether = true;
        }
        return whether;
    }

    public TilesShape purpleStar_extra_tile(){
        TilesShape one = new TilesShape("one", Colour.PURPLE, 1,0,0,0);
        return one;
    }

    public void PlusSign_next_larger(HashMap<Colour,Integer> dices_color, Abilities a) {
        if (a.equals(Abilities.RedPlusSign)) {
            dices_color.put(Colour.RED, dices_color.getOrDefault(Colour.RED, 0) + 1);
        } else if (a.equals(Abilities.BluePlusSign)) {
            dices_color.put(Colour.BLUE, dices_color.getOrDefault(Colour.BLUE, 0) + 1);
        } else if (a.equals(Abilities.GreenPlusSign)) {
            dices_color.put(Colour.GREEN, dices_color.getOrDefault(Colour.GREEN, 0) + 1);
        } else if (a.equals(Abilities.YellowPlusSign)) {
            dices_color.put(Colour.YELLOW, dices_color.getOrDefault(Colour.YELLOW, 0) + 1);
        } else if (a.equals(Abilities.PurplePlusSign)) {
            dices_color.put(Colour.PURPLE, dices_color.getOrDefault(Colour.PURPLE, 0) + 1);
        }
    }

    public TilesShape Shield1_window_tile(){
        return blueStar_all_windows(purpleStar_extra_tile());
    }

    public void Shield2_two_steps(Player p, int row){
        p.advance_steps(p.ar.get_color(row),2);
    }

    public Colour get_color(int row){
        return ability_region.get(row);
    }

    public void get_ability(Player p, Colour c){
        //there are some abilities and bonus of different signs and colors, so there need to be some if-else
        //sentences to determine which color ability track and its related ability, then call some other
        //methods to apply this ability
        if(p.get_stepNUM(c) == star1||p.get_stepNUM(c) == star2){
            if(c.equals(Colour.RED)){
                p.store_ability(Abilities.redStar);
            }
            else if(c.equals(Colour.BLUE)){
                p.store_ability(Abilities.blueStar);
            }
            else if(c.equals(Colour.GREEN)){
                p.store_ability(Abilities.greenStar);
            }
            else if(c.equals(Colour.YELLOW)){
                p.store_ability(Abilities.yellowStar);
            }
            else if(c.equals(Colour.PURPLE)){
                p.store_ability(Abilities.purpleStar);
            }
        }
        else if(p.get_stepNUM(c)==plus_sign1||p.get_stepNUM(c)==plus_sign2){
            if(c.equals(Colour.RED)){
                p.store_ability(Abilities.RedPlusSign);
            }
            else if(c.equals(Colour.BLUE)){
                p.store_ability(Abilities.BluePlusSign);
            }
            else if(c.equals(Colour.GREEN)){
                p.store_ability(Abilities.GreenPlusSign);
            }
            else if(c.equals(Colour.YELLOW)){
                p.store_ability(Abilities.YellowPlusSign);
            }
            else if(c.equals(Colour.PURPLE)){
                p.store_ability(Abilities.PurplePlusSign);
            }
        }
        //......and so on, then players can store abilities they get right now
    }

    public void isFilled(Player p, Colour c){
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
