package comp1110.ass2;

import jdk.jfr.Event;

public class RollWriteGame {
    public RollWriteGame(Event e){
        //e=click game start, be like a center control of the whole game
        Player player=new Player("name");
        Regions r;
        Shapes s;
        Rules_Display.rules();
    }
}
