package comp1110.ass2;

import java.awt.*;

public class ShieldsShape extends Shapes{
    int width;
    int height;
    int position;

    public ShieldsShape(int position){
        this.width=1;
        this.height=1;
        this.position=position;
    }

    public void paints(int x, int y) {

    }


    public boolean isAttached(int position) {
        if(this.position==position) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isExist(){
        return false;
    }

    public void CrossOut(){

    }

    public void ability(Event e){

    }
}
