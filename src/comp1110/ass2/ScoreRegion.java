package comp1110.ass2;

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

    public void paints(int x1, int y1, int x2, int y2) {
        //draw

    }

    public void score_display(int score){
        //show how many scores this player get

    }
}
