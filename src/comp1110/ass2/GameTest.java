package comp1110.ass2;
import comp1110.ass2.gui.Colour;
import comp1110.ass2.gui.TilesShape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;


public class GameTest {
    public GameTest(){

    }

    @Test
    public void test_tilesCanBeSelected(){
        System.out.println("Game_Logic gl=new Game_Logic()");
        Game_Logic gl = new Game_Logic();
        gl.set_players(2);
        gl.rounds.get(0).dices_color.clear();
        gl.rounds.get(0).dices_color.put(Colour.RED,3);
        gl.rounds.get(0).dices_color.put(Colour.BLUE,1);
        gl.rounds.get(0).dices_color.put(Colour.WHITE,1);

        System.out.println("TilesShape B3=new TilesShape(B3,BLUE,3,0,0,1)");
        TilesShape B3=new TilesShape("B3", Colour.BLUE,3,0,0,1);
        System.out.println("TilesShape R3=new TilesShape(R3,RED,3,0,0,0)");
        TilesShape R3=new TilesShape("R3",Colour.RED,3,0,0,0);
        System.out.println("TilesShape R4=new TilesShape(R4,RED,4,0,0,0)");
        TilesShape R4=new TilesShape("R4",Colour.RED,4,0,0,0);

        System.out.println("gl.tilesCanBeSelected(gl.players.get(0), R3)");
        Assertions.assertEquals(true, gl.tilesCanBeSelected(gl.players.get(0), R3));
        System.out.println("Checking gl.tilesCanBeSelected(gl.players.get(0), R4)");
        Assertions.assertEquals(true, gl.tilesCanBeSelected(gl.players.get(0), R4));
        System.out.println("Checking gl.tilesCanBeSelected(gl.players.get(0), B3)");
        Assertions.assertEquals(false, gl.tilesCanBeSelected(gl.players.get(0), B3));
    }

    @Test
    public void testTiles_canbe_Placed() {
        System.out.println("Game_Logic gl=new Game_Logic()");
        Game_Logic gl=new Game_Logic();
        gl.set_players(2);

        System.out.println("Firstly new some tiles and put them on the building:");
        TilesShape B3 = new TilesShape("B3", Colour.BLUE,3, 0,0,1);
        TilesShape G4L = new TilesShape("G4L",Colour.GREEN,4,3,0,0);
        gl.players.get(0).br.is_Occupied(gl.players.get(0),B3);
        gl.players.get(0).br.is_Occupied(gl.players.get(0),G4L);

        System.out.println("TilesShape Y3=new TilesShape(Y3,YELLOW,3,1,0,0)");
        TilesShape Y3=new TilesShape("Y3",Colour.YELLOW,3,1,0,0);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Rotating Y3 clockwise by 270 degrees");
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(true, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Moving Y3 one space to the right");
        gl.Y3.RIGHTER(gl.tiles);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Rotating Y3 clockwise by 180 degrees");
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println("checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(true, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Moving Y3 one space to the left and one space to the top, then rotating Y3 clockwise by 180 degrees");
        gl.Y3.LEFTER(gl.tiles);
        gl.Y3.UPPER(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        gl.Y3.rotation(gl.tiles);
        System.out.println("checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));
    }

    @Test
    public void test_advance_steps(){
        System.out.println("Player player = new Player(0);");
        Player player = new Player(0);

        System.out.println("Checking player.advance_steps(player,Colour.RED,2)");
        player.advance_steps(player,Colour.RED,2);
        System.out.println("This method need to be test together with get_stepNUM(Colour.RED)");
        Assertions.assertEquals(2, player.get_stepNUM(Colour.RED));
        System.out.println("When players advance, they will get some new abilities:");
        Assertions.assertEquals(1, player.abilities.get(AbilityRegion.Abilities.redStar));
        Assertions.assertEquals(1, player.abilities.get(AbilityRegion.Abilities.RedPlusSign));
        System.out.println("When players complete a track, they will get two scores:");
        player.advance_steps(player,Colour.RED,3);
        Assertions.assertEquals(2, player.get_score());
    }

    @Test
    public void test_withdrawSteps() {
        System.out.println("Player player = new Player(0);");
        Player player = new Player(0);

        System.out.println("Firstly put some abilities in the hashmap and make some advance steps:");
        player.advance_steps(player, Colour.RED, 3);
        player.abilities.clear();
        player.abilities.put(AbilityRegion.Abilities.redStar, 2);
        player.abilities.put(AbilityRegion.Abilities.RedPlusSign, 1);
        System.out.println("Checking player.withdrawSteps(player,Colour.RED,1)");
        player.withdrawSteps(Colour.RED, 1);
        System.out.println("This method need to be test together with get_stepNUM(Colour.RED) and player.abilities");
        Assertions.assertEquals(2, player.get_stepNUM(Colour.RED));
        Assertions.assertEquals(1, player.abilities.get(AbilityRegion.Abilities.redStar));
        Assertions.assertEquals(1, player.abilities.get(AbilityRegion.Abilities.RedPlusSign));
    }

    @Test
    public void test_isFilled_row(){
        System.out.println("This method need to be test together with is_Occupied((Player p, TilesShape ts) and get_score()");
        System.out.println("New a player:");
        Player player = new Player(0);

        System.out.println("New some tiles and make them all windows:");
        TilesShape P41 = new TilesShape("P4", Colour.PURPLE, 4, 0,1 ,0);
        P41.setNoBrick();
        TilesShape P42 = new TilesShape("P4", Colour.PURPLE, 4, 0,5 ,0);
        P42.setNoBrick();
        TilesShape P5 = new TilesShape("P5", Colour.PURPLE, 5, 0,0 ,0);
        P5.setNoBrick();

        System.out.println("Put these tiles on the building:");
        player.br.is_Occupied(player,P5);
        player.br.is_Occupied(player,P41);
        player.br.is_Occupied(player,P42);

        System.out.println("Checking player.br.isFilled_row(player, 1)");
        player.br.isFilled_row(player, 1);
        System.out.println("By testing the return of method getScore()");
        Assertions.assertEquals(0,player.get_score());

        System.out.println("Checking player.br.isFilled_row(player, 0)");
        player.br.isFilled_row(player, 0);
        System.out.println("By testing the return of method getScore()");
        Assertions.assertEquals(2,player.get_score());
    }

    @Test
    public void test_isFilled_column(){
        System.out.println("This method need to be test together with is_Occupied((Player p, TilesShape ts) and get_score()");
        System.out.println("New a player:");
        Player player = new Player(0);

        System.out.println("New some tiles and make them all windows:");
        TilesShape P41 = new TilesShape("P4", Colour.PURPLE, 4, 0,1 ,0);
        P41.setNoBrick();
        TilesShape P42 = new TilesShape("P4", Colour.PURPLE, 4, 0,5 ,0);
        P42.setNoBrick();
        TilesShape P5 = new TilesShape("P5", Colour.PURPLE, 5, 0,0 ,0);
        P5.setNoBrick();

        System.out.println("Put these tiles on the building:");
        player.br.is_Occupied(player,P5);
        player.br.is_Occupied(player,P41);
        player.br.is_Occupied(player,P42);

        System.out.println("Checking player.br.isFilled_column(player, 1)");
        player.br.isFilled_column(player, 1);
        System.out.println("By testing the return of method getScore()");
        Assertions.assertEquals(0,player.get_score());

        System.out.println("Checking player.br.isFilled_column(player, 0)");
        player.br.isFilled_column(player, 0);
        System.out.println("By testing the return of method getScore()");
        Assertions.assertEquals(4,player.get_score());
    }
}
