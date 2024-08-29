package comp1110.ass2;

import java.awt.*;

public class BuildingRegion extends PlayerRegion{
    static Grid[][] grids= new Grid[9][5];
    static ShieldsShape row_ss=new ShieldsShape(2);
    static ShieldsShape column_ss=new ShieldsShape(4);

    static void paints(int x1, int y1, int x2, int y2) {
        //draw the whole region
        ShieldsShape.paints();
        Grid.paints();
    }

    static void placed_rules(TilesShape ts, int row, int column){
        //determine whether the grid (players have chosen) is occupied, and whether this area is valid and
        //whether this tiles shape is built on a previous one, and here need to call the choose_tiles_rules
        //method in Class Player to guarantee the tiles players have chosen are under the dice_color constraint
        if(!grids[row][column].isOccupied()) {

        }
    }

    static boolean isFilled_row(int row){
        //determine whether this row is filled(and the situations of shields and windows), then players can get some scores
        if(row_ss.isTouched(row)) {
            String[] abilities = Abilities.get_ability_set();
            int index = 0;
            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i].equals("shield")) {
                    index = i;
                    break;
                }
            }
            Abilities.use_abilities(abilities[index]);
        }
        return false;
    }

    static boolean isFilled_column(int column){
        //determine whether this column is filled(and the situations of shields and windows), then players can get some scores
        if(column_ss.isTouched(column)) {
            String[] abilities = Abilities.get_ability_set();
            int index = 0;
            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i].equals("shield")) {
                    index = i;
                    break;
                }
            }
            Abilities.use_abilities(abilities[index]);
        }
        return false;
    }
}
