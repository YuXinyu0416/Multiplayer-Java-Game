package comp1110.ass2;

public class RollWriteGame {
    //players click the game start, and this will be a center control area
    Player player1=new Player("name1");
    Player player2=new Player("name2");
    Player player3=new Player("name3");

    static void paints(){
        Regions.paints();
    }

    static void show_rules(){
        Rules_Display.rules();
    }
}
