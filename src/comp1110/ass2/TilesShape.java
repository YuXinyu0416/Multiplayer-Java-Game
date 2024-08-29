package comp1110.ass2;

public class TilesShape extends Shapes {
    String title;
    String color;
    int num_of_tile;// how many tiles are contained in this whole shape
    boolean state=true;// determine whether this tilesShape has been crossed out or not
    Grid[] tiles;

    public TilesShape(String title,String color, int num_of_tile){
        this.title=title;
        this.color=color;
        this.num_of_tile=num_of_tile;
        this.tiles=new Grid[num_of_tile];
    }

    public void paints(int x1, int y1,int x2, int y2) {
        //draw
        Grid.paints();
    }

    public boolean isSelected() {
        //determine whether this tile has been selected or not
        boolean isSelected=false;
        if(isSelected){
            this.CrossOut();
        }
        return isSelected;
    }

    public String get_Color(){
        //return the color of this tilesShape to support other methods
        return this.color;
    }

    public int get_tileNum(){
        //return the number_tile of this tilesShape to support other methods
        return this.num_of_tile;
    }

    public void CrossOut(){
       //if this tilesShape has been selected, then it will be crossed out and will not be selected again
        this.state=false;
    }
}
