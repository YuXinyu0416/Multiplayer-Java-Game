package comp1110.ass2.gui;

import comp1110.ass2.TilesShape;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;

import java.util.Collections;
import java.util.List;

/**
 * Displays a Building.
 */
public class BuildingView extends GridPane {
    private final SquareView[][] grid;
    static final int SQUARE_SIZE = 60;

    // Display coat-of-arms on building edges
    private final ImageView[] topRow;
    private final ImageView[] rightCol;
    static final Image redRabbit = new Image("red_rabbit.png");
    static final Image yellowRabbit = new Image("yellow_rabbit.png");

    private int width;
    private int height;

    public BuildingView(int width, int height) {
        super();
	this.width = width;
	this.height = height;
        grid = new SquareView[width][height];
	content = new Square[GameGUI.MAX_N_PLAYERS][width][height];
	showColCoA = new boolean[GameGUI.MAX_N_PLAYERS][width];
	showRowCoA = new boolean[GameGUI.MAX_N_PLAYERS][height];
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                grid[x][y] = new SquareView(SQUARE_SIZE);
                //Background b = new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY,Insets.EMPTY));
                //grid[x][y].setBackground(b);
                add(grid[x][y], x, height - y);
		for (int i = 0; i < GameGUI.MAX_N_PLAYERS; i++)
		    content[i][x][y] = new Square();
            }
        topRow = new ImageView[width];
        for (int x = 0; x < width; x++) {
            topRow[x] = new ImageView();
            topRow[x].setFitWidth(SQUARE_SIZE);
            topRow[x].setFitHeight(SQUARE_SIZE);
            add(topRow[x], x, 0);
        }
        rightCol = new ImageView[height];
        for (int y = 0; y < height; y++) {
            rightCol[y] = new ImageView();
            rightCol[y].setFitWidth(SQUARE_SIZE);
            rightCol[y].setFitHeight(SQUARE_SIZE);
            add(rightCol[y], width, height - y);
        }
    }

    Node getSquare(int x, int y) {
        return grid[x][y];
    }

    private static class Square {
        Boolean window; // 0: window, 1: no-window; null: not filled
        Colour colour;

        public Square() {
            window = null;
            colour = Colour.WHITE;
        }

        public Square(Boolean window, Colour colour) {
            this.window = window;
            this.colour = colour;
        }

        public boolean isFilled() {
            return (this.window != null);
        }

        public boolean hasWindow() {
            return this.isFilled() && this.window;
        }

        public Colour getColour() {
            return this.colour;
        }

        public Color getFXColor() {
            return this.colour.getFXColor();
        }
    }

    private Square[][][] content;
    private boolean[][] showColCoA;
    private boolean[][] showRowCoA;

    /**
     * Update the view to show the state of a given building.
     */
    void show(int player) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
		if (player < 0)
		    grid[x][y].setState(false, false, Color.WHITE);
		else {
		    Square square = content[player][x][y];
		    grid[x][y].setState(square.isFilled(),
					square.hasWindow(),
					square.getFXColor());
		}
	    }
        }

        for (int x : List.of(1, 3)) {
            //Image image = ((player >= 0) && showColCoA[player][x] ? redRabbit : yellowRabbit);
            Image image;
            if(x ==1) {
                image = yellowRabbit;
            }
            else{
                image = redRabbit;
            }
            topRow[x].setImage(image);
        }
        for (int y : List.of(1, 3, 5)) {
            //Image image = ((player >= 0) && showRowCoA[player][y] ? redRabbit : yellowRabbit);
            Image image;
            if(y ==3) {
                image = redRabbit;
            }
            else{
                image = yellowRabbit;
            }
            rightCol[y].setImage(image);
        }
    }

    void show(int player, TilesShape ts, boolean valid) {
        show(player);
        var mark = new LibraryView.LibraryItem(ts.name);
        for (int i = 0; i < mark.getSize(); i++) {
            int x = ts.getX() + mark.getX(i);
            int y = ts.getY() + mark.getY(i);
            if (0 <= x && x < grid.length)
                if (0 <= y && y < grid[x].length)
                    grid[x][y].markPlacement(valid, ts.getWindow(i));
        }
    }

    static class SquareView extends StackPane {
        Rectangle outer;
        ImageView inner;
        Rectangle border;

        SquareView(int width) {
            outer = new Rectangle(width, width);
            outer.setStroke(Color.BLACK);
            outer.setFill(Color.WHITE);
            inner = new ImageView("carrot.png");
            inner.setFitHeight(60.0);
            inner.setFitWidth(60.0);
            //inner.setClip(new Rectangle(2.0, 2.0));
            border = new Rectangle(width - 8, width - 8);
            border.setStroke(Color.BLACK);
            border.setFill(null);
            border.setStrokeWidth(5);
            border.setStrokeLineJoin(StrokeLineJoin.ROUND);
            // inner.setVisible(false);
            getChildren().add(outer);
            getChildren().add(inner);
            getChildren().add(border);
        }

        void setState(boolean filled, boolean window, Color colour) {
            if (!filled) {
                outer.setFill(Color.LIGHTGREEN);
                inner.setVisible(false);
            } else {
                outer.setFill(colour);
                inner.setVisible(window);
            }
            // clear placement outline
            border.setVisible(false);
        }

        void markPlacement(boolean valid, boolean window) {
            border.setVisible(true);
            Image image = new Image("carrot.png");
            inner.setImage(image);
            if (valid) border.setStroke(Color.BLACK);
            else border.setStroke(Color.GREY);
            inner.setVisible(window);
        }
    }

    public void setSquare(int p, int x, int y, Colour colour, boolean window) {
        if (colour != Colour.WHITE) {
            content[p][x][y] = new Square(window, colour);
            SquareView sv = new SquareView(SQUARE_SIZE);
            Image carrot = new Image("carrot.png");
            BackgroundFill fill = new BackgroundFill(colour.getFXColor(), CornerRadii.EMPTY, Insets.EMPTY);
            BackgroundImage image = new BackgroundImage(carrot,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background b = new Background(Collections.singletonList(fill), Collections.singletonList(image));
            sv.setBackground(b);
        } else {
            content[p][x][y] = new Square();
        }
    }

    void setRowCoA(int player, int y, boolean highlightOn) {
	showRowCoA[player][y] = highlightOn;
    }

    void setColumnCoA(int player, int x, boolean highlightOn) {
	showColCoA[player][x] = highlightOn;
    }
}
