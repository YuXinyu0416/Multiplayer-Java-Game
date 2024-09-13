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

    public void Shape_change(){
        this.rotation=(this.rotation+1)%4;
        if(this.num_of_tile==2){
            if(this.rotation==0){
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y;
            }
            else if(this.rotation==1){
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y+1;
            }
        }
        else if(this.name.equals("Y3")||this.name.equals("B3")||this.name.equals("R3")){
            if(this.rotation==0){
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y+1;
                this.tiles[2].position[0]=this.x+1;
                this.tiles[2].position[1]=this.y+1;
            }
            else if(this.rotation==1){
                this.tiles[0].position[0]=this.x+1;
                this.tiles[0].position[1]=this.y;
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y;
                this.tiles[2].position[0]=this.x;
                this.tiles[2].position[1]=this.y+1;
            }
            else if(this.rotation==2){
                this.tiles[0].position[0]=this.x+1;
                this.tiles[0].position[1]=this.y+1;
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y;
                this.tiles[2].position[0]=this.x;
                this.tiles[2].position[1]=this.y;
            }
            else if(this.rotation==3){
                this.tiles[0].position[0]=this.x;
                this.tiles[0].position[1]=this.y+1;
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y+1;
                this.tiles[2].position[0]=this.x+1;
                this.tiles[2].position[1]=this.y;
            }
        }
        else if(this.name.equals("P3")||this.name.equals("G3")){
            if(this.rotation==0){
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y;
                this.tiles[2].position[0]=this.x+2;
                this.tiles[2].position[1]=this.y;
            }
            else if(this.rotation==1){
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y+1;
                this.tiles[2].position[0]=this.x;
                this.tiles[2].position[1]=this.y+2;
            }
        }
        else if(this.name.equals("P4")){
            if(this.rotation==0){
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y;
                this.tiles[2].position[0]=this.x+2;
                this.tiles[2].position[1]=this.y;
                this.tiles[3].position[0]=this.x+3;
                this.tiles[3].position[1]=this.y;
            }
            else if(this.rotation==1){
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y+1;
                this.tiles[2].position[0]=this.x;
                this.tiles[2].position[1]=this.y+2;
                this.tiles[3].position[0]=this.x;
                this.tiles[3].position[1]=this.y+3;
            }
        }
        else if(this.name.equals("P5")){
            if(this.rotation==0){
                this.tiles[1].position[0]=this.x;
                this.tiles[1].position[1]=this.y+1;
                this.tiles[2].position[0]=this.x;
                this.tiles[2].position[1]=this.y+2;
                this.tiles[3].position[0]=this.x;
                this.tiles[3].position[1]=this.y+3;
                this.tiles[4].position[0]=this.x;
                this.tiles[4].position[1]=this.y+4;
            }
            else if(this.rotation==1){
                this.tiles[1].position[0]=this.x+1;
                this.tiles[1].position[1]=this.y;
                this.tiles[2].position[0]=this.x+2;
                this.tiles[2].position[1]=this.y;
                this.tiles[3].position[0]=this.x+3;
                this.tiles[3].position[1]=this.y;
                this.tiles[3].position[0]=this.x+4;
                this.tiles[3].position[1]=this.y;
            }
        }
    }

    public void UPPER(){
        this.x++;
    }

    public void LOWER(){
        this.x--;
    }

    public void RIGHTER(){
        this.y++;
    }

    public void LEFTER(){
        this.y--;
    }

    public void CrossOut(){
       //if this tilesShape has been selected, then it will be crossed out and will not be selected again
        this.state=false;
    }
}
