package comp1110.ass2;

import java.awt.*;

public class CommonRegion extends Regions{
    //the region to display all tiles shapes to all players
    int width;
    int height;
    int size;

    public CommonRegion(){
        this.width=0;
        this.height=0;
        this.size=width*height;
        TilesRegion tr;
    }

    public void paints(int x1, int y1, int x2,int y2){
        //drawn the whole region on this location

    }
}
