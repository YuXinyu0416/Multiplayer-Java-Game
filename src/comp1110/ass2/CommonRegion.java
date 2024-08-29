package comp1110.ass2;

public class CommonRegion extends Regions{
    //the region to display all tiles shapes to all players
    static TilesShape ts=new TilesShape("R1","RED",3);
    static public Dices d=new Dices();
    static void paints(int x1, int y1, int x2,int y2){
        //drawn the whole region on this location
        OperateRegion.paints();
        RollRegion.paints();
        ts.paints();
        d.paints();
    }
}
