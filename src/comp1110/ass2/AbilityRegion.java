package comp1110.ass2;

import java.awt.*;

public class AbilityRegion extends PlayerRegion {
    //this class is Ability tracks with different colors, can be chosen by players and there is some abilities
    //can be gotten
    int width;  // width of the whole region
    int height; // height of the whole region
    int size;
    int step; // how many steps players have advanced in each ability track
    String color;
    int star1=1; // ability signs and locations
    int star2 =3;
    int plus_sign1=2;
    int plus_sign2=4;

    public AbilityRegion(){
        this.width=0;
        this.height=0;
        this.size=width*height;
    }

    public void paints(int x1,int y1, int x2, int y2, int row) {
        //draw this region in GUI, maybe need to implement an interface
    }

    public void beColor(int row, Color c){
        //set the color of ability tracks in six different colors and return this color to support other methods
        color=c.toString();
    }

    public String get_color(){
        return color;
    }

    public boolean isSelected(Event e) {
        //determine whether an ability track is selected right now(maybe by clicking )
        return false;
    }

    public int advance_steps(boolean isSelected){
        //add steps to make players advance in the ability track and return this step number to determine
        //whether players can get corresponding abilities
        this.step++;
        return this.step;
    }

    public void get_ability(int step){
        //there are some abilities and bonus of different signs and colors, so there need to be some if-else
        //sentences to determine which color ability track and its related ability, then call some other
        //methods to apply this ability
        String ability_name;
        if(step==star1){
            ability_name=get_color()+" star";
            Player.store_ability(ability_name);
        }
        else if(step==star2){

        }
        //......and so on, then players can store abilities they get right now
    }
}
