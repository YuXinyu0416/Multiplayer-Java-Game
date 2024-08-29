package comp1110.ass2;

public class PlayerRegion extends Regions{
    //the whole region belongs to players, which is private
    static void paints(int x1, int y1, int x2, int y2) {
        //draw
        AbilityRegion.paints();
        BuildingRegion.paints();
        ScoreRegion.paints();
    }
}
