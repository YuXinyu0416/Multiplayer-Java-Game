package comp1110.ass2;

import java.awt.*;

public class ScoreRegion extends PlayerRegion{
    int width;
    int height;
    int size;
    String title;

    public ScoreRegion(String title){
        this.title=title;
        this.width=0;
        this.height=0;
        this.size=width*height;
    }

    public void paints(int x, int y) {

    }

    public void score_display(int score){

    }
}
