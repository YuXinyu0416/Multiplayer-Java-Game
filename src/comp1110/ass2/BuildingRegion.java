package comp1110.ass2;

import java.awt.*;

public class BuildingRegion extends PlayerRegion{
    int width;
    int height;
    int size;
    Grid[][] grids;
    ShieldsShape row_ss=new ShieldsShape(2);
    ShieldsShape column_ss=new ShieldsShape(4);

    BuildingRegion(int row, int column){
        this.width=0;
        this.height=0;
        this.size=width*height;
        this.grids=new Grid[row][column];
    }

    public void paints(int x1, int y1, int x2, int y2) {
        //draw the whole region
    }

    public void placed_rules(TilesShape ts, Event e, int row, int column){
        //determine whether the grid (players have chosen) is occupied, and whether this area is valid and
        //whether this tiles shape is built on a previous one, and here need to call the choose_tiles_rules
        //method in Class Player to guarantee the tiles players have chosen are under the dice_color constraint
        if(!this.grids[row][column].isOccupied()) {

        }
    }

    public boolean isFilled_row(int row){
        //determine whether this row is filled(and the situations of shields and windows), then players can get some scores
        if(row_ss.isAttached(row)) {
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

    public boolean isFilled_column(int column){
        //determine whether this column is filled(and the situations of shields and windows), then players can get some scores
        if(column_ss.isAttached(column)) {
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
