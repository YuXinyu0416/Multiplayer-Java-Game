package comp1110.ass2;

public class BuildingRegion{
    Grid[][] grids= new Grid[9][5];
    ShieldsShape row_ss=new ShieldsShape(2);
    ShieldsShape column_ss=new ShieldsShape(4);

    public BuildingRegion(){

    }

    public void is_Occupied(int row, int column){
        grids[row][column].isOccupied();
    }

    public boolean whether_Occupied(int row, int column){
        //determine whether the grid (players have chosen) is occupied, and whether this area is valid and
        //whether this tiles shape is built on a previous one, and here need to call the choose_tiles_rules
        //method in Class Player to guarantee the tiles players have chosen are under the dice_color constraint
        boolean whether=true; //here can be placed
        grids[0][0].isOccupied();
        grids[0][1].isOccupied();
        grids[0][4].isOccupied();
        grids[1][0].isOccupied();
        grids[1][3].isOccupied();
        grids[1][4].isOccupied();
        grids[2][4].isOccupied();
        if(grids[row][column].content[0]!=null){
            whether=false;
        }
        return whether;
    }

    public boolean whether_Above(int row, int column){
        boolean whether=false;
        if(row==0||(row!=0&&grids[row-1][column].content[0]!=null)){
            whether=true;
        }
        return whether;
    }

    public boolean whether_beyond(int row, int column){
        boolean whether=false;
        if(row<0||row>8||column<0||column>4){
            whether=true;
        }
        return whether;
    }

    public boolean isFilled_row(int row){
        //determine whether this row is filled(and the situations of shields and windows), then players can get some scores
        if(row_ss.isTouched(row)) {
            String[] abilities = AbilityRegion.get_ability_set();
            int index = 0;
            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i].equals("shield")) {
                    index = i;
                    break;
                }
            }
            AbilityRegion.use_abilities(abilities[index]);
        }
        return false;
    }

    public boolean isFilled_column(int column){
        //determine whether this column is filled(and the situations of shields and windows), then players can get some scores
        if(column_ss.isTouched(column)) {
            String[] abilities = AbilityRegion.get_ability_set();
            int index = 0;
            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i].equals("shield")) {
                    index = i;
                    break;
                }
            }
            AbilityRegion.use_abilities(abilities[index]);
        }
        return false;
    }
}
