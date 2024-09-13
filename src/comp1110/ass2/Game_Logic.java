package comp1110.ass2;

import java.awt.*;

public class Game_Logic {
    //players click the game start, and this will be a center control area
    Player player1=new Player("name1");
    Player player2=new Player("name2");
    TilesShape B3=new TilesShape("B3","BLUE",3,0,0,1);
    TilesShape G4L=new TilesShape("G4L","GREEN",4,3,0,0);
    TilesShape R3=new TilesShape("R3","RED",3,0,0,0);
    TilesShape R4=new TilesShape("R4","RED",4,0,0,0);
    TilesShape Y3=new TilesShape("Y3","YELLOW",3,1,0,0);
    Grid[] tiles= Y3.set_tiles();

    Dices d=new Dices();
    Round rd=new Round();
    static Color[] get_color_set(){
        Color[] colors=new Color[6];
        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.YELLOW;
        colors[3]=Color.GREEN;
        colors[4]=Color.WHITE;
        colors[5]=new Color(128,0,128);//purple
        return colors;
    }

    public Game_Logic(){

    }

    public void rules_display(){
        //show players all rules of this game, it will appear at the beginning of game and can be called by button
        System.out.println("The rules of this game is as follow:" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "");
    }

    public boolean Dices_canbe_Selected(Player p, TilesShape ts){
        boolean whether;
        if(ts.state) {
        whether=p.choose_tiles_rules(rd.max_same_color(d.get_dices_color()), ts, rd.isWhite(d.get_dices_color()));
        }
        else{
            whether=false;
        }
        return whether;
    }

    public boolean Tiles_canbe_Placed(Player p, TilesShape ts, Grid[] tiles){
        boolean whether1 =true;
        boolean whether2 =true;
        boolean whether3 =false;
        for(int i=0;i<ts.num_of_tile&&whether1;i++) {
            if(p.br.whether_beyond(tiles[i].position[0],tiles[i].position[1])){
                whether1=false;
            }
        }
        for(int i=0;i<ts.num_of_tile&&whether2;i++) {
            if(p.br.whether_Occupied(tiles[i].position[0],tiles[i].position[1])){
                whether2=false;
            }
        }
        for(int i=0;i<ts.num_of_tile&&!whether3;i++) {
            if(p.br.whether_Above(tiles[i].position[0],tiles[i].position[1])){
                whether3=true;
            }
        }

        if(whether1&&whether2&&whether3) {
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        Game_Logic gl=new Game_Logic();
        System.out.println(gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles));
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.player1,gl.Y3,gl.tiles));
        gl.Y3.RIGHTER(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.player1,gl.Y3,gl.tiles));
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.player1,gl.Y3,gl.tiles));
        gl.Y3.LEFTER(gl.tiles);
        gl.Y3.UPPER(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.player1,gl.Y3,gl.tiles));
    }
}
