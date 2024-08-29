package comp1110.ass2;

import java.awt.*;

public class Colors extends Shapes {
    static Color[] get_color_set(){
        Color[] colors=new Color[6];
        colors[0]=Color.RED;
        colors[1]=Color.BLUE;
        colors[2]=Color.YELLOW;
        colors[3]=Color.GREEN;
        colors[4]=Color.WHITE;
        colors[5]=new Color(128,0,128);//purple
        return colors;
    }
}
