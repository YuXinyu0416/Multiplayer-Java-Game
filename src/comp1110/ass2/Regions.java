package comp1110.ass2;

public abstract class Regions extends RollWriteGame {
    static void paints(){
        CommonRegion.paints();
        PlayerRegion.paints();
    }
}
