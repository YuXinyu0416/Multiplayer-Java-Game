package comp1110.ass2;

import java.awt.*;

public class PlayerRegion extends Regions{
    //the whole region belongs to players, which is private
    int width;
    int height;
    int size;

    public PlayerRegion(){
        this.width=0;
        this.height=0;
        this.size=width*height;
        AbilityRegion ar;
        BuildingRegion br;
        RollRegion rr;
        ScoreRegion sr=new ScoreRegion("Score");
    }

    public void paints(int x1, int y1, int x2, int y2) {
        //draw

    }
}
