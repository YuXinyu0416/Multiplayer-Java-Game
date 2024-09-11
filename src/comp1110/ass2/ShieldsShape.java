package comp1110.ass2;

public class ShieldsShape{
    int position;
    boolean state=true;// determine whether this shield has been crossed out or not

    public ShieldsShape(int position){
        this.position=position;
    }

    public boolean isTouched(int position) {
       //determine whether this shield can be used
        if(this.position==position) {
            this.CrossOut();
            return true;
        }
        else {
            return false;
        }
    }

    public void CrossOut(){
        //if a shield is touched by a row or a column, then it will be crossed out and players can use the
        //ability of it
        //if a shield has been crossed out, it will not be used again
        this.state=false;
    }
}
