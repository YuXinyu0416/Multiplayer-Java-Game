package comp1110.ass2;

import comp1110.ass2.AbilityRegion.*;

public class BuildingRegion{
    Grid[][] grids= new Grid[5][9];
    ShieldsShape row_ss1=new ShieldsShape(1);
    ShieldsShape row_ss2=new ShieldsShape(3);
    ShieldsShape row_ss3=new ShieldsShape(5);
    ShieldsShape column_ss1=new ShieldsShape(1);
    ShieldsShape column_ss2=new ShieldsShape(3);

    public BuildingRegion(){
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                grids[i][j]=new Grid();
            }
        }
    }

    public void is_Occupied(Player p, TilesShape ts){
        Grid[] tiles = ts.set_tiles();
        ts.Shape_change(tiles);
        for(int i=0;i<tiles.length;i++){
            grids[tiles[i].position[0]][tiles[i].position[1]].isOccupied();
            if(ts.windows[i]){
                grids[tiles[i].position[0]][tiles[i].position[1]].place_window();
            }
        }
        for(int i=0;i<9;i++) {
            isFilled_row(p,i);
        }
        for(int i=0;i<5;i++){
            isFilled_column(p,i);
        }
    }

    public boolean whether_Occupied(int column, int row){
        //determine whether the grid (players have chosen) is occupied, and whether this area is valid and
        //whether this tiles shape is built on a previous one, and here need to call the choose_tiles_rules
        //method in Class Player to guarantee the tiles players have chosen are under the dice_color constraint
        boolean whether=false; //here can be placed
        if(!grids[column][row].content[0].equals("null")){
            whether=true;
        }
        return whether;
    }

    public boolean whether_Above(int column, int row){
        boolean whether=false;
        if(row==0||(row!=0&&!grids[column][row-1].content[0].equals("null"))){
            whether=true;
        }
        return whether;
    }

    public boolean whether_beyond(int column, int row){
        boolean whether=false;
        if(row<0||row>8||column<0||column>4){
            whether=true;
        }
        return whether;
    }

    public void isFilled_row(Player p, int row){
        //determine whether this row is filled(and the situations of shields and windows), then players can get some scores
        boolean isFilled= true;
        for(int i=0;i<5;i++){
            if(grids[i][row].content[0].equals("null")){
                isFilled=false;
            }
        }
        if(isFilled){
           p.add_score(1);
            boolean isWindow = true;
            for(int i=0; i<5;i++){
                if(grids[i][row].content[1].equals("null")){
                    isWindow=false;
                }
            }
            if(isWindow){
                p.add_score(1);
            }
            if(row_ss1.isTouched(row)||row_ss2.isTouched(row)||row_ss3.isTouched(row)){
                if(true) {
                    p.ar.Shield1_window_tile();
                }else {
                    p.ar.Shield2_two_steps(p, 1);
                }
            }
        }
    }

    public void isFilled_column(Player p, int column){
        //determine whether this column is filled(and the situations of shields and windows), then players can get some scores
        boolean isFilled= true;
        for(int i=0;i<9;i++){
            if(grids[column][i].content[0].equals("null")){
                isFilled=false;
            }
        }
        if(isFilled){
            p.add_score(1);
            boolean isWindow = true;
            for(int i=0; i<9;i++){
                if(grids[column][i].content[1].equals("null")){
                    isWindow=false;
                }
            }
            if(isWindow){
                p.add_score(1);
            }
            if(column_ss1.isTouched(column)|| column_ss2.isTouched(column)){
                if(true) {
                    p.ar.Shield1_window_tile();
                }else {
                    p.ar.Shield2_two_steps(p, 1);
                }
            }
        }
    }
}
