package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class Game_Logic {
    //players click the game start, and this will be a center control area
    int p_number;
    List<Player> players= new ArrayList<>();
    TilesShape B3=new TilesShape("B3", Colors.BLUE,3,0,0,1);
    TilesShape G4L=new TilesShape("G4L", Colors.GREEN,4,3,0,0);
    TilesShape R3=new TilesShape("R3", Colors.RED,3,0,0,0);
    TilesShape R4=new TilesShape("R4", Colors.RED,4,0,0,0);
    TilesShape Y3=new TilesShape("Y3", Colors.YELLOW,3,1,0,0);
    Grid[] tiles= Y3.set_tiles();
    Round rd=new Round();
//    static Color[] get_color_set(){
//        Color[] colors=new Color[6];
//        colors[0]=Color.RED;
//        colors[1]=Color.BLUE;
//        colors[2]=Color.YELLOW;
//        colors[3]=Color.GREEN;
//        colors[4]=Color.WHITE;
//        colors[5]=new Color(128,0,128);//purple
//        return colors;
//    }
    enum Colors{
        RED, BLUE, YELLOW, PURPLE, WHITE, GREEN
    }

    public Game_Logic(int player_number){
        this.p_number=player_number;
    }

    public void set_players(){
        for(int i=0; i< p_number;i++){
            String name = "player"+(i+1);
            players.add(new Player(name));
        }
        players.get(0).br.is_Occupied(players.get(0),B3);
        players.get(0).br.is_Occupied(players.get(0),G4L);
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

    public boolean Dices_canbe_Selected (Player p, TilesShape ts){
        boolean whether;
        if(ts.state) {
        whether=p.choose_tiles_rules(rd.dices_color, ts, rd.isWhite(rd.dices_color));
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

    static void last_round(){
        //need some if sentences to determine whether this round is the last round,
        //for example players[i].score>=12, and all players can make move in the last round


    }

    public static void main(String[] args) {
        Game_Logic gl=new Game_Logic(2);
        System.out.println(gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3,gl.tiles));
        gl.Y3.RIGHTER(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3,gl.tiles));
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3,gl.tiles));
        gl.Y3.LEFTER(gl.tiles);
        gl.Y3.UPPER(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println(gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3,gl.tiles));
    }
}

//java --module-path /Users/xinyuyu/Desktop/Java/javafx-sdk-17.0.8/lib --add-modules=javafx.base,javafx.controls -jar out/artifacts/game/game.jar
