package comp1110.ass2;

public class TilesShape{
    String name;
    int x;
    int y;
    int rotation;
    String color;
    int num_of_tile;// how many tiles are contained in this whole shape
    boolean state=true;// determine whether this tilesShape has been crossed out or not

    public TilesShape(String name,String color, int num_of_tile, int x, int y, int r){
        this.name = name;
        this.color=color;
        this.num_of_tile=num_of_tile;
        this.x = x;
        this.y = y;
        this.rotation = r;
    }

    public Grid[] set_tiles(){
        Grid[] tiles=new Grid[num_of_tile];
        for(int i=0;i<num_of_tile;i++){
            tiles[i]=new Grid(x,y);
        }
        return tiles;
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

    public boolean isPlaced(){
        return false;
    }

    public  void rotation(Grid[] tiles){
        this.rotation=(this.rotation+1)%4;
        Shape_change(tiles);
    }

    public void Shape_change(Grid[] tiles) {
        if (this.num_of_tile == 2) {
            if (this.rotation == 0 || this.rotation == 2) {
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y+1;
            } else if (this.rotation == 1 || this.rotation == 3) {
                tiles[1].position[0] = this.x+1;
                tiles[1].position[1] = this.y;
            }
        } else if (this.name.equals("Y3") || this.name.equals("B3") || this.name.equals("R3")) {
            if (this.rotation == 0) {
                tiles[1].position[0] = this.x+1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x+1;
                tiles[2].position[1] = this.y+1;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y+1;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x+1;
                tiles[2].position[1] = this.y;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x+1;
                tiles[0].position[1] = this.y+1;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y+1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x+1;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x+1;
                tiles[1].position[1] = this.y+1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y+1;
            }
        } else if (this.name.equals("P3") || this.name.equals("G3")) {
            if (this.rotation == 0 || this.rotation == 2) {
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y+1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y+2;
            } else if (this.rotation == 1 || this.rotation == 3) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 2;
                tiles[2].position[1] = this.y;
            }
        } else if (this.name.equals("P4")) {
            if (this.rotation == 0 || this.rotation == 2) {
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 2;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 3;
            } else if (this.rotation == 1 || this.rotation == 3) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 2;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 3;
                tiles[3].position[1] = this.y;
            }
        } else if (this.name.equals("P5")) {
            if (this.rotation == 0 || this.rotation == 2) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 2;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 3;
                tiles[3].position[1] = this.y;
                tiles[4].position[0] = this.x + 4;
                tiles[4].position[1] = this.y;
            } else if (this.rotation == 1 || this.rotation == 3) {
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 2;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 3;
                tiles[4].position[0] = this.x;
                tiles[4].position[1] = this.y + 4;
            }
        } else if (this.name.equals("R4")) {
            if (this.rotation == 0 || this.rotation == 1 || this.rotation == 2 || this.rotation == 3) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
            }
        } else if (this.name.equals("G4L")) {
            if (this.rotation == 0) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 2;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 2;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y + 1;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 1;
            }
        } else if (this.name.equals("G4R")) {
            if (this.rotation == 0) {
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 2;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 1;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 2;
                tiles[3].position[1] = this.y + 1;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y;
            }
        } else if (this.name.equals("R5")) {
            if (this.rotation == 0) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 2;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 2;
                tiles[4].position[1] = this.y + 1;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 1;
                tiles[4].position[1] = this.y;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y;
                tiles[4].position[0] = this.x;
                tiles[4].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 2;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x;
                tiles[4].position[1] = this.y + 2;
            }
        } else if (this.name.equals("G5")) {
            if (this.rotation == 0 || this.rotation == 1 || this.rotation == 2 || this.rotation == 3) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 2;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 1;
                tiles[4].position[1] = this.y + 2;
            }
        }
        else if (this.name.equals("B4L")) {
            if (this.rotation == 0) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 2;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 1;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 2;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 2;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 2;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 1;
            }
        } else if (this.name.equals("B4R")) {
            if (this.rotation == 0) {
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x;
                tiles[3].position[1] = this.y + 2;
            } else if (this.rotation == 1) {
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 1;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 2;
                tiles[3].position[1] = this.y + 1;
            } else if (this.rotation == 2) {
                tiles[0].position[0] = this.x + 1;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x;
                tiles[1].position[1] = this.y + 2;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y;
            } else if (this.rotation == 3) {
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x + 2;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x + 1;
                tiles[2].position[1] = this.y + 1;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y;
            }
        }
        else if(this.name.equals("B5")){
            if(this.rotation==0){
                tiles[1].position[0] = this.x + 1;
                tiles[1].position[1] = this.y;
                tiles[2].position[0] = this.x + 2;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 1;
                tiles[4].position[1] = this.y + 2;
            }
            else if(this.rotation==1){
                tiles[0].position[0] = this.x;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x ;
                tiles[1].position[1] = this.y + 1;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 2;
                tiles[4].position[1] = this.y + 1;
            }
            else if(this.rotation==2){
                tiles[0].position[0] = this.x + 2;
                tiles[0].position[1] = this.y + 2;
                tiles[1].position[0] = this.x + 1 ;
                tiles[1].position[1] = this.y + 2;
                tiles[2].position[0] = this.x;
                tiles[2].position[1] = this.y + 2;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x + 1;
                tiles[4].position[1] = this.y;
            }
            else if(this.rotation==3){
                tiles[0].position[0] = this.x+2;
                tiles[0].position[1] = this.y;
                tiles[1].position[0] = this.x+2;
                tiles[1].position[1] = this.y+1;
                tiles[2].position[0] = this.x+2;
                tiles[2].position[1] = this.y+2;
                tiles[3].position[0] = this.x + 1;
                tiles[3].position[1] = this.y + 1;
                tiles[4].position[0] = this.x;
                tiles[4].position[1] = this.y + 1;
            }
        }
    }

    public void UPPER(Grid[] tiles){
        this.y++;
        Shape_change(tiles);
    }

    public void LOWER(Grid[] tiles){
        this.y--;
        Shape_change(tiles);
    }

    public void RIGHTER(Grid[] tiles){
        this.x++;
        Shape_change(tiles);
    }

    public void LEFTER(Grid[] tiles){
        this.x--;
        Shape_change(tiles);
    }

    public void CrossOut(){
       //if this tilesShape has been selected, then it will be crossed out and will not be selected again
        this.state=false;
    }
}
