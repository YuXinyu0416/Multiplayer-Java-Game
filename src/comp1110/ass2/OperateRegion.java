package comp1110.ass2;

public class OperateRegion extends CommonRegion{
    // the region where players can select and click some functional buttons
    static ButtonShape bs=new ButtonShape("confirm");
    static void paints(int x1, int y1, int x2, int y2){
        //draw
        bs.paints();
    }
}
