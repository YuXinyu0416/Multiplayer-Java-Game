package comp1110.ass2;

public class Grid{
    //a grid in building sheet
    String[] content=new String[2]; //something in this grid
    int[] position=new int[2];// position[0]=column, position[1]=row
    public Grid(int column, int row){
        this.content[0]="null";
        this.content[1]="null";
        this.position[0]=column;
        this.position[1]=row;
    }
    public Grid(){
        this.content[0]="null";
        this.content[1]="null";
    }

    public  boolean isOccupied(){
        //determine whether this grid has been occupied and support the place_rules of building sheet
        this.content[0]="tile";
        return true;
    }

    public void place_window(){
        //players can put a window in this grid and this method can be used to support the isFilled method
        //in Class BuildingRegion
        this.content[1]="window";



    }
}
