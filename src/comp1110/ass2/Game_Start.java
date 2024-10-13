package comp1110.ass2;

import comp1110.ass2.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;

public class Game_Start extends Application {

    static GameGUI gui;
	public static Game_Logic gl;

    @Override
    public void start(Stage stage) throws Exception {
		gui = new GameGUI();
        Scene scene = new Scene(gui, GameGUI.WINDOW_WIDTH, GameGUI.WINDOW_HEIGHT);
		//scene.setFill(Color.LEMONCHIFFON);

	// This is where you should set up callbacks (or at least one
	// callback, for the start-of-game event).

	// The following are only provided as examples; you should
	// replace them with your own.
	
	gui.setOnStartGame((np, isAI) -> {
		gui.setMessage("start new game with " + np + " players");
		gl = new Game_Logic(np);
		gl.set_players();
		gui.setAvailableTiles(List.of("R2", "R3", "R4", "R4", "R5", "B2", "B3", "B4L", "B4R", "B5", "P2","P3","P4","P4","P5","G2", "G3", "G4L", "G4R", "G5", "Y2", "Y3", "Y4L", "Y4R", "Y5"));
		gui.setAvailableDice(gl.rounds.get(0).colours);
		gui.setAvailableActions(List.of("Reroll", "Give up", "End the game", "Colour change"));
	    });

//	gui.setOnTilePlaced((p) -> {
//		gui.setMessage("tile placed: " + p);
//		if (p.getTileName().equals("R5"))
//		    gui.endGame(new int[4]);
//	    });

	gui.setOnDiceSelectionChanged((i) -> {
		gui.setMessage("dice selection: " + gui.getSelectedDice());
	    });

	gui.setOnTrackSelectionChanged((i) -> {
		gui.setMessage("track selection: " + gui.getSelectedTracks());
	    });

	gui.setOnGameAction((s) -> {
		gui.setMessage("action: " + s);
		if(s.equals("End the game")){
			gui.endGame(new int[gl.p_number]);
		}
		if (s.equals("Give up")) {
			gui.setAvailableActions(List.of("Reroll", "End the game"));
		}
		if(s.equals("Reroll")){
			gl.rounds.add(gl.rounds.size(),new Round());
			gui.setAvailableDice(gl.rounds.get(gl.rounds.size()-1).colours);
		}
	    });

	gui.setOnConfirm((s) -> {
		gui.setMessage("confirm: " + s);
	    });

	gui.setOnPass((s) -> {
		gui.setMessage("pass: " + s);
	    });

	// Start the application:
        stage.setScene(scene);
        stage.setTitle("Copenhagen Roll & Write");
		stage.setFullScreen(true);
        stage.show();

//		gl = new Game_Logic(2);
//		gl.set_players();
//		gl.play();
    }
}
