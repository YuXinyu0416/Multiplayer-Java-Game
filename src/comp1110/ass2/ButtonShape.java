package comp1110.ass2;

public class ButtonShape extends Shapes {
    String title;

    ButtonShape(String title){
        this.title=title;
    }

    public void paints(int x1, int y1,int x2,int y2){
        //draw the buttons on this location

    }

    public boolean isSelected(){
        //determine whether this button has been selected right now
        return false;
    }
}
