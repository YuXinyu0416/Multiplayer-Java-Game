package comp1110.ass2;
import comp1110.ass2.gui.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class D2CTest {
    public D2CTest(){

    }

    @Test
    public void test_tilesCanBeSelected(){
        System.out.println("Game_Logic gl=new Game_Logic(2)");
        Game_Logic gl=new Game_Logic(2);
        gl.set_players();

        System.out.println("TilesShape B3=new TilesShape(B3,BLUE,3,0,0,1)");
        TilesShape B3=new TilesShape("B3", Colour.BLUE,3,0,0,1);
        System.out.println("TilesShape R3=new TilesShape(R3,RED,3,0,0,0)");
        TilesShape R3=new TilesShape("R3",Colour.RED,3,0,0,0);
        System.out.println("TilesShape R4=new TilesShape(R4,RED,4,0,0,0)");
        TilesShape R4=new TilesShape("R4",Colour.RED,4,0,0,0);

        System.out.println("Checking gl.Dices_canbe_Selected(player1, R3)");
        Assertions.assertEquals(true, gl.tilesCanBeSelected(gl.players.get(0), R3));
        System.out.println("Checking gl.Dices_canbe_Selected(player1, R4)");
        Assertions.assertEquals(true, gl.tilesCanBeSelected(gl.players.get(0), R4));
        System.out.println("Checking gl.Dices_canbe_Selected(player1, B3)");
        Assertions.assertEquals(false, gl.tilesCanBeSelected(gl.players.get(0), B3));
    }

    @Test
    public void testTiles_canbe_Placed() {
        System.out.println("Game_Logic gl=new Game_Logic(2)");
        Game_Logic gl=new Game_Logic(2);
        gl.set_players();

        System.out.println("TilesShape Y3=new TilesShape(Y3,YELLOW,3,1,0,0)");
        TilesShape Y3=new TilesShape("Y3",Colour.YELLOW,3,1,0,0);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Rotating Y3 clockwise by 270 degrees");
        gl.Y3.rotateClockwise(gl.tiles);
        gl.Y3.rotateClockwise(gl.tiles);
        gl.Y3.rotateClockwise(gl.tiles);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(true, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Moving Y3 one space to the right");
        gl.Y3.RIGHTER(gl.tiles);
        System.out.println("Checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Rotating Y3 clockwise by 180 degrees");
        gl.Y3.rotateClockwise(gl.tiles);
        gl.Y3.rotateClockwise(gl.tiles);
        System.out.println("checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(true, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));

        System.out.println("Moving Y3 one space to the left and one space to the top, then rotating Y3 clockwise by 180 degrees");
        gl.Y3.LEFTER(gl.tiles);
        gl.Y3.UPPER(gl.tiles);
        gl.Y3.rotateClockwise(gl.tiles);
        gl.Y3.rotateClockwise(gl.tiles);
        System.out.println("checking gl.Tiles_canbe_Placed(gl.player1,gl.Y3, gl.tiles)");
        Assertions.assertEquals(false, gl.Tiles_canbe_Placed(gl.players.get(0),gl.Y3, gl.tiles));
    }
}
