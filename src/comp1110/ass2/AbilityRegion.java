package comp1110.ass2;

public class AbilityRegion{
    //this class is Ability tracks with different colors, can be chosen by players and there is some abilities
    //can be gotten
    int star1=1; // ability signs and locations
    int star2 =3;
    int plus_sign1=2;
    int plus_sign2=4;
    String[] rows=new String[5];//store color of every row and distinguish them
    static String[] abilities={"red star", "blue star", "green star", "yellow star", "purple star", "plus sign", "shield"};
    // all abilities players can get in this game

    public AbilityRegion(){

    }

    static void use_abilities(String ability_name){
        if(ability_name.equals("red star")){

        }
        else if(ability_name.equals("green star")){

        }
        else if(ability_name.equals("plus sign")){

        }
        else if(ability_name.equals("shield")){

        }
        //......and so on, there are some abilities that players can get and use
    }

    static String[] get_ability_set(){
        return abilities;
    }

    public void beColor(int row, String color){
        //set the color of ability tracks in five different colors and return this color to support other methods
        rows[row]=color;
    }

    public String get_color(int row){
        return rows[row];
    }

    public boolean isSelected(Player player, int row) {
        //determine whether an ability track is selected right now(maybe by clicking )
        player.advance_steps(row);
        return false;
    }

    public void get_ability(Player player, int row){
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
