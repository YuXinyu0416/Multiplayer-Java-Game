package comp1110.ass2;

import comp1110.ass2.gui.Colour;
import comp1110.ass2.gui.TilesShape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game_Logic {
    //players click the game start, and this will be a center control area
    int p_number;
    boolean isRunning = true;
    public List<Player> players = new ArrayList<>();
    HashMap<Player,Integer> players_turn= new HashMap<>();
    TilesShape B3=new TilesShape("B3", Colour.BLUE,3,0,0,1);
    TilesShape G4L=new TilesShape("G4L", Colour.GREEN,4,3,0,0);
    TilesShape R3=new TilesShape("R3", Colour.RED,3,0,0,0);
    TilesShape R4=new TilesShape("R4", Colour.RED,4,0,0,0);
    TilesShape Y3=new TilesShape("Y3", Colour.YELLOW,3,1,0,0);
    public List<Round> rounds = new ArrayList<>();
    Grid[] tiles= Y3.set_tiles();
//    enum Colors{
//        RED, BLUE, YELLOW, PURPLE, WHITE, GREEN
//    }

    public Game_Logic(){
        //this.p_number=player_number;
        rounds.add(new Round());
    }

    public void set_players(int player_number){
        this.p_number = player_number;
        for(int i=0; i< p_number;i++){
            int name = i;
            players.add(new Player(name));
        }
//        players.get(0).br.is_Occupied(players.get(0),B3);
//        players.get(0).br.is_Occupied(players.get(0),G4L);
    }

    public void add_turn(Player p){
        players_turn.put(p,players_turn.getOrDefault(p,0)+1);
    }

//    public void play(){
//        while(isRunning){
//            if(players.get(0).get_score()>=12||players.get(1).get_score()>=12){
//                last_round();
//            }
//            rounds.add(new Round());
//
//
//
//
//
//        }
//    }

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

    public boolean Dices_canbe_selected(int i) {
        int index = rounds.size() - 1;
        boolean whether = true;
        List<String> max_color = new ArrayList<>();
        int max_value = 0;
        for (Map.Entry<Colour, Integer> pair : rounds.get(index).dices_color.entrySet()) {
            if (max_value == 0 || pair.getValue().compareTo(max_value) > 0) {
                max_value = pair.getValue();
                max_color.clear();
                max_color.add(pair.getKey().toString());
            } else if (pair.getValue().compareTo(max_value) == 0) {
                max_color.add(pair.getKey().toString());
            }
        }

        if(max_color.size()==1&&!max_color.get(0).equals(rounds.get(index).colours.get(i))){
            whether = false;
        }
        else if(max_color.size()>2){
            whether =false;
            System.out.println("Must be rerolled!");
        }
        else{
            boolean whether1 = true;
            boolean whether2 = true;
            whether = false;
            if(!rounds.get(index).colours.get(i).equals(max_color.get(0))){
                whether1 = false;
            }
            if(!rounds.get(index).colours.get(i).equals(max_color.get(1))){
                whether2 = false;
            }
            if(whether1||whether2){
                whether =true;
            }
        }

        if(whether){
            if(rounds.get(index).selected.isEmpty()){
                rounds.get(index).selected.add(rounds.get(index).colours.get(i));
            }
            else{
                if(!rounds.get(index).selected.contains(rounds.get(index).colours.get(i))){
                    if(!max_color.get(0).equals(Colour.WHITE.name))
                    whether=false;
                }
            }
        }
        return whether;
    }

    public boolean tilesCanBeSelected(Player p, TilesShape ts){
        boolean whether;
        if(ts.get_state()) {
        whether=p.choose_tiles_rules(rounds.get(rounds.size()-1).dices_color, ts, rounds.get(rounds.size()-1).isWhite(rounds.get(rounds.size()-1).dices_color));
        }
        else{
            whether=false;
        }
        return whether;
    }

    public boolean Tiles_canbe_Placed(Player p, TilesShape ts, Grid[] tiles){
        boolean isBeyond =true;
        boolean isOccupied =true;
        boolean isAbove =false;
        for(int i = 0; i<ts.num_of_tile&& isBeyond; i++) {
            if(p.br.whether_beyond(tiles[i].position[0],tiles[i].position[1])){
                isBeyond =false;
            }
        }
        for(int i=0;i<ts.num_of_tile&&isOccupied;i++) {
            if(p.br.whether_Occupied(tiles[i].position[0],tiles[i].position[1])){
                isOccupied=false;
            }
        }
        //
        for(int i = 0; i<ts.num_of_tile&&!isAbove; i++) {
            if(p.br.whether_Above(tiles[i].position[0],tiles[i].position[1])){
                isAbove =true;
            }
        }

        if(isBeyond &&isOccupied&& isAbove) {
            return true;
        }
        else{
            return false;
        }
    }

    public void last_round(){
        //need some if sentences to determine whether this round is the last round,
        //for example players[i].score>=12, and all players can make move in the last round
        //int which_op = (players.indexOf(p)+1)%players.size();
        int max_turn=0;
        for(Map.Entry<Player,Integer> pairs:players_turn.entrySet()) {
            if (pairs.getValue().compareTo(max_turn)>0){
                max_turn = pairs.getValue();
            }
        }
        boolean whether_end = true;
        for(Map.Entry<Player,Integer> pairs:players_turn.entrySet()){
            if(pairs.getValue()!=max_turn){
                whether_end = false;
            }
        }
        if(whether_end){
            end_game();
        }
    }

    public void end_game(){
        isRunning = false;
    }

    public static void main(String[] args) {
        Game_Logic gl=new Game_Logic();
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
