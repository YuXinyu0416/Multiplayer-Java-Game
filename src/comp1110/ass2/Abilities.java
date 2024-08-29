package comp1110.ass2;

public class Abilities extends RollWriteGame{
    static String[] abilities={"red star", "blue star", "green star", "yellow star", "purple star", "plus sign", "shield"};
    // all abilities players can get in this game

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
}
