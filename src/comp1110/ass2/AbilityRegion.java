package comp1110.ass2;

public class AbilityRegion extends PlayerRegion {
    //this class is Ability tracks with different colors, can be chosen by players and there is some abilities
    //can be gotten
    static int star1=1; // ability signs and locations
    static int star2 =3;
    static int plus_sign1=2;
    static int plus_sign2=4;
    static String[] rows=new String[5];//store color of every row and distinguish them


    static void paints(int x1,int y1, int x2, int y2, int row) {
        //draw this region in GUI, maybe need to implement an interface
    }

    static void beColor(int row, String color){
        //set the color of ability tracks in five different colors and return this color to support other methods
        rows[row]=color;
    }

    static String get_color(int row){
        return rows[row];
    }

    static boolean isSelected(Player player, int row) {
        //determine whether an ability track is selected right now(maybe by clicking )
        player.advance_steps(row);
        return false;
    }

    static void get_ability(Player player, int row){
        //there are some abilities and bonus of different signs and colors, so there need to be some if-else
        //sentences to determine which color ability track and its related ability, then call some other
        //methods to apply this ability
        String ability_name;
        if(player.steps[row]==star1){
            ability_name=get_color(row)+" star";
            player.store_ability(ability_name);
        }
        else if(player.steps[row]==star2){

        }
        //......and so on, then players can store abilities they get right now
    }
}
