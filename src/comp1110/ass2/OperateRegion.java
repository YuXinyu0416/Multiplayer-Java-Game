package comp1110.ass2;

public class OperateRegion extends CommonRegion{
    // the region where players can select and click some functional buttons
    int width;
    int height;
    int size;

    public OperateRegion(){
        this.width=0;
        this.height=0;
        this.size=width*height;
        ButtonShape bs=new ButtonShape("title");
    }

    public void paints(int x1, int y1, int x2, int y2){
        //draw

    }
}
