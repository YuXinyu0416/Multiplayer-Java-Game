package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.input.MouseButton;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import comp1110.ass2.Game_Start;
import javafx.stage.Stage;


public class GameGUI extends BorderPane {

    static final int BUILDING_WIDTH = 5;
    static final int BUILDING_HEIGHT = 9;

    static final int TITLE_FONT_SIZE = 24;
    static final int LARGE_FONT_SIZE = 20;
    static final int MEDIUM_FONT_SIZE = 18;
    static final int SMALL_FONT_SIZE = 12;
    static int current_now = 0;

    private static final Border boxBorder = new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderStroke.MEDIUM));

    // GUI components
    //public static Game_Logic gl;
    private GridPane player_pane;
    private Label current_player_view;
    private TabPane player_selector;
    public PlayerStateView player_view;
    public BuildingView building_view;
    private LibraryView library_view;
    private FlowPane price_view;
    public DiceView dice_view;
    private StackPane control_view;
    private Node game_setup_controls;
    private Node current_player_controls;
    private Button b_reroll;
    private Button b_confirm;
    private Button b_pass;
    private Button t_hint1;
    private Button t_hint2;
    private Button b_again;
    private MenuButton b_use;
    private MenuButton b_colour_change;
    public MenuButton b_rabbits;
    private MenuButton b_action;

    private BiConsumer<Integer, boolean[]> onStartGame;
    private Consumer<String> onTileSelected;
    private Consumer<TilesShape> onTilePlaced;
    private Consumer<String> onConfirm;
    private Consumer<String> onPass;
    private Consumer<String> onGameAction;
    private Consumer<String> onAbilityAction;
    private Consumer<String> onColourChange;
    private Consumer<String> onRabbits;

    private int candidate_index = -1;
    public TilesShape candidate = null;
    private static String[] players;
    public static List<String> colours = new ArrayList<>();
    {
        Collections.addAll(colours,"Red", "Blue", "Green", "Yellow", "Purple");
    }
    public static List<String> availableTS = new ArrayList<>();
    {
        Collections.addAll(availableTS,"R2", "R3", "R4", "R4", "R5", "B2", "B3", "B4L", "B4R", "B5", "P2","P3","P4","P4","P5","G2", "G3", "G4L", "G4R", "G5", "Y2", "Y3", "Y4L", "Y4R", "Y5");
    }
    public static List<String> rabbit_a = new ArrayList<>();
    {
        Collections.addAll(rabbit_a,"Draw one tile with carrot", "Choose an ability track and advance 2 steps");
    }

    private int which_player= 1;
    private boolean last_turn;
    public boolean ys_ability =false;
    public boolean can_place = true;
    private TilesShape dice_remainder;
    public boolean whether_click = true;

    public void setonAvailablePlayers(int np){
        players = new String[np];
        players[0] = "has player one time";
    }

    private void makeSetupControls() {
        VBox controls = new VBox();
        controls.setSpacing(4);
        ToggleGroup np = new ToggleGroup();
        FlowPane npPane = new FlowPane();
        npPane.setHgap(2);
        npPane.getChildren().add(new Text("Number of players:"));
        for (int i = 2; i <= 4; i++) {
            RadioButton b = new RadioButton(Integer.toString(i));
            b.setToggleGroup(np);
            b.setUserData(i);
            npPane.getChildren().add(b);
        }
        controls.getChildren().add(npPane);
        FlowPane aiPane = new FlowPane();
        aiPane.setHgap(2);
        aiPane.getChildren().add(new Text("AI:"));
        for (int i = 0; i < 4; i++) {
            CheckBox b = new CheckBox(Integer.toString(i + 1));
            aiPane.getChildren().add(b);
        }
        controls.getChildren().add(aiPane);
        FlowPane gsPane = new FlowPane();
        gsPane.setHgap(2);
        gsPane.getChildren().add(new Text("Init state:"));
        TextField initStateString = new TextField();
        gsPane.getChildren().add(initStateString);
        controls.getChildren().add(gsPane);
        Button b_start = new Button("Start");
        b_start.setOnAction(e -> {
		Toggle np_selected = np.getSelectedToggle();
		boolean[] isAI = new boolean[4];
		for (int i = 0; i < 4; i++)
		    isAI[i] = ((CheckBox) (aiPane.getChildren().get(i + 1))).isSelected();
		String gameString = initStateString.getText();
		if (gameString.length() > 0) {
		    doStart(0, gameString, isAI);
		}
		else if (np_selected != null) {
		    int n = (Integer) np_selected.getUserData();
		    doStart(n, null, isAI);
		}
	    });
        controls.getChildren().add(b_start);
        game_setup_controls = controls;
    }

    private void doStart(int nPlayers, String gameString, boolean[] isAI) {
        player_selector.getTabs().clear();
        for (int i = 0; i < nPlayers; i++) {
            Tab t = new Tab("Player " + Integer.toString(i));
            player_selector.getTabs().add(t);
        }
        control_view.getChildren().clear();
        control_view.getChildren().add(current_player_controls);
        player_selector.getSelectionModel().select(0);
        player_selector.getSelectionModel().selectedIndexProperty().addListener(
            (property, old_value, new_value) -> {
		showState();
	    });
	if (onStartGame != null)
	    onStartGame.accept(nPlayers, isAI);
        showState();
    }

    private Pane makeGameOverControls(int[] finalScores) {
        GridPane controls = new GridPane();
        controls.setAlignment(Pos.CENTER);
        controls.setHgap(10);
        controls.setVgap(4);
        Text header = new Text("Final scores");
        header.setFont(Font.font(TITLE_FONT_SIZE));
        controls.add(header, 0, 0, 2, 1);
        GridPane.setHalignment(header, HPos.CENTER);
	for (int i = 0; (i < finalScores.length) && (i < 4); i++) {
	    Text player_i = new Text("Player" + Integer.toString(i));
	    controls.add(player_i, 0, 1 + i, 1, 1);
	    GridPane.setHalignment(player_i, HPos.LEFT);
	    Text score_i = new Text(Integer.toString(finalScores[i]));
	    controls.add(score_i, 1, 1 + i, 1, 1);
	    GridPane.setHalignment(score_i, HPos.RIGHT);
	}
        b_again = new Button("Play again");
        b_again.setOnAction(e -> {
           control_view.getChildren().clear();
           control_view.getChildren().add(game_setup_controls);
           showState();
            Game_Start again = new Game_Start();
            again.start(new Stage());
        });
	    int n = Math.min(finalScores.length, 4);
        controls.add(b_again, 0, n + 1, 2, 1);
        GridPane.setHalignment(b_again, HPos.CENTER);
        Button b_quit = new Button("Quit");
        b_quit.setOnAction(e -> Platform.exit());
        controls.add(b_quit, 0, n + 2, 2, 1);
        GridPane.setHalignment(b_quit, HPos.CENTER);
        return controls;
    }

    private void makePlayerControls() {
        GridPane controls = new GridPane();
        controls.setHgap(3);
        controls.setVgap(3);
        controls.setPadding(new Insets(3, 3, 3, 3));
        b_action = new MenuButton("Action");
        controls.add(b_action,0,0);
        b_confirm = new Button("Confirm (player #)");
        controls.add(b_confirm, 0, 1);
	    b_confirm.setOnAction((e) -> {
            int p = player_selector.getSelectionModel().getSelectedIndex();
            Player player = Game_Start.gl.players.get(p);
		if (candidate != null&&can_place&&((p ==current_now&&Game_Start.gl.tilesCanBeSelected(player, candidate))||candidate.name.equals("S1O")||candidate.name.equals("S1X")||(ys_ability&&candidate.num_of_tile>=4))) {
		    TilesShape tmp = new TilesShape(candidate);
            if(!candidate.name.equals("S1O")&&!candidate.name.equals("S1X")) {
                dice_remainder = new TilesShape(candidate);
            }
		    candidate = null;
		    library_view.clearSelection();
		    if (onTilePlaced != null) {
                onTilePlaced.accept(tmp);
            }
            Grid[] tiles = tmp.set_tiles();
            tmp.Shape_change(tiles);
            if(Game_Start.gl.Tiles_canbe_Placed(player,tmp,tiles)){
                setOnTilePlaced(onTilePlaced,tmp);
                can_place = false;
                if(!ys_ability&&(tmp.num_of_tile>3||tmp.name.equals("S1O")||tmp.name.equals("S1X"))) {
                    availableTS.remove(tmp.name);
                    setAvailableTiles(availableTS);
                }
                showState();
                player.br.is_Occupied(player,tmp);
                for(int i=0;i<tiles.length;i++) {
                    if (i == 0) {
                        player.br.isFilled_row(player, tiles[i].position[1]);
                        player.br.isFilled_column(player, tiles[i].position[0]);
                    } else {
                        if (tiles[i].position[1] != tiles[i - 1].position[1]) {
                            player.br.isFilled_row(player, tiles[i].position[1]);
                        }
                        if (tiles[i].position[0] != tiles[i - 1].position[0]) {
                            player.br.isFilled_column(player, tiles[i].position[0]);
                        }
                    }
                }
                player_view.setScore(p,player.get_score());
                showState();
                whether_endGame(getSelectedPlayer());
            }
		}
        else if(!Game_Start.gl.tilesCanBeSelected(player,candidate)){
            candidate = null;
            showState();
        }
		else if (onConfirm != null) {
		    onConfirm.accept(b_confirm.getText());
		    showState();
		}
	    });
        t_hint1 = new Button("You can place just one tile shape this turn~");
        t_hint2 = new Button("You cannot place any tile this turn~");
        t_hint2.setDisable(true);
        controls.add(t_hint1,0,6);
        controls.add(t_hint2,0,7);
        b_use = new MenuButton("Use Ability (player #)");
        controls.add(b_use, 0, 3);
        b_use.setOnAction((e) -> {
                });
        b_pass = new Button("Pass (player #)");
        controls.add(b_pass, 0, 2);
	    b_pass.setOnAction((e) -> {
        if (onPass != null) {
            whether_click = false;
            int pno = players.length;
            b_rabbits.setDisable(true);
            b_colour_change.setDisable(true);
            setAbilityMenu(List.of("You have no ability now"));
            whether_endGame(getSelectedPlayer());
            ys_ability = false;
            t_hint1.setDisable(true);
            t_hint2.setDisable(true);
            can_place = true;
            player_view.selectors.clearSelection();
            player_view.selectors.enableRange(0,5);
            dice_view.selected.clearSelection();
            if(which_player == 0){
                players = new String[pno];
                current_now=(current_now+1)%pno;
                players[current_now]= "has played one time";
                setSelectedPlayer(current_now);
                t_hint1.setDisable(false);
                updateAbilityMenu();
                last_turn =false;
                setMessage("Now You Are Active Player!" );
                onPass.accept(b_pass.getText());
                setAvailableTiles(availableTS);
                setAvailableActions(List.of("End the game"));
                Game_Start.gl.rounds.add(Game_Start.gl.rounds.size(),new Round());
                setAvailableDice(Game_Start.gl.rounds.get(Game_Start.gl.rounds.size()-1).colours);
                clear_DicesSelection();
                showState();
                which_player = (which_player+1)%pno;
            }
            else {
                int this_turn = 0;
                for(int i=0;i<pno;i++){
                    if(players[i]==null){
                        this_turn=i;
                        break;
                    }
                }
                    setSelectedPlayer(this_turn);
                    can_place = false;
                    whether_click = true;
                    advanceListener();
                    t_hint2.setDisable(false);
                    updateAbilityMenu();
                    which_player = (which_player+1)%pno;
                    players[this_turn] = "has played one time";
                    setAvailableActions(List.of("End the game"));
                    if(!last_turn) {
                        dices_remainder();
                        last_turn = true;
                    }
                    clear_DicesSelection();
                    setMessage("Now You Are Other Player!");
                    onPass.accept(b_pass.getText());
                    showState();
                }
            }
	    });
        b_rabbits = new MenuButton("Rabbits (shields)");
        controls.add(b_rabbits, 0,4);
        b_rabbits.setDisable(true);
        b_colour_change = new MenuButton("Colour Change (player #)");
        b_colour_change.setDisable(true);
        controls.add(b_colour_change, 1, 3);
        current_player_controls = controls;
    }

    public void showState() {
	int i = player_selector.getSelectionModel().getSelectedIndex();
	if (candidate != null && (i == candidate_index))
	    building_view.show(i, candidate, false);
	else
	    building_view.show(i);
	if (i >= 0) {
	    player_view.show(i);
	}
}

    private void makeMainLayout() {
        current_player_view = new Label("                 ");
        current_player_view.setFont(Font.font(24));
        this.setTop(current_player_view);

        StackPane left = new StackPane();
        left.setBorder(boxBorder);
        left.setAlignment(Pos.CENTER);
        Color yellow = Color.rgb(250, 249, 240);
        left.setBackground(new Background(new BackgroundFill(yellow,CornerRadii.EMPTY, Insets.EMPTY)));

        player_selector = new TabPane();
        player_selector.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        left.getChildren().add(player_selector);
        player_pane = new GridPane();
        player_pane.setHgap(10);
        player_pane.setVgap(4);
        player_pane.setPadding(new Insets(2, 2, 2, 2));
        player_pane.setAlignment(Pos.CENTER);

        player_view = new PlayerStateView();
        player_pane.add(player_view, 0, 0);
        building_view = new BuildingView(BUILDING_WIDTH, BUILDING_HEIGHT);
        building_view.setFocusTraversable(true);
        player_pane.add(building_view, 1, 0, 1, 2);

        for (int x = 0; x < BUILDING_WIDTH; x++)
            for (int y = 0; y < BUILDING_HEIGHT; y++) {
                final int fx = x;
                final int fy = y;
                building_view.getSquare(x, y).setOnMouseClicked(e -> {
			if (candidate != null) {
			    if (e.getButton() == MouseButton.SECONDARY) {
			    }
			    else {
				candidate.setPosition(fx, fy);
			    }
			    showState();
			}
			building_view.requestFocus();
		    });
            }

        building_view.setOnKeyPressed(e -> {
		if (candidate == null) {
		    e.consume();
		    return;
		}
		switch (e.getCode()) {
                case RIGHT -> {
		    candidate.movePosition(1, 0);
		    showState();
		}
                case LEFT -> {
		    candidate.movePosition(-1, 0);
		    showState();
		}
                case UP -> {
		    candidate.movePosition(0, 1);
		    showState();
		}
                case DOWN -> {
		    candidate.movePosition(0, -1);
		    showState();
		}
                case SPACE, R -> {
		            candidate.rotateClockwise();
                    showState();

                }
                case DIGIT0, NUMPAD0 -> { candidate.setNoBrick(); showState(); }
                case DIGIT1, NUMPAD1 -> { candidate.setBrick(0); showState(); }
                case DIGIT2, NUMPAD2 -> { candidate.setBrick(1); showState(); }
                case DIGIT3, NUMPAD3 -> { candidate.setBrick(2); showState(); }
                case DIGIT4, NUMPAD4 -> { candidate.setBrick(3); showState(); }
                case DIGIT5, NUMPAD5 -> { candidate.setBrick(4); showState(); }
		}
		e.consume();
	    });

        left.getChildren().add(player_pane);
        VBox.setVgrow(player_pane, Priority.ALWAYS);
        this.setCenter(left);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setMargin(left, new Insets(4, 4, 4, 4));

        VBox right = new VBox();
        right.setSpacing(4);
        right.setFillWidth(true);
        library_view = new LibraryView();
        library_view.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,CornerRadii.EMPTY,Insets.EMPTY) ));
        library_view.setPrefWidth(400);
        library_view.setPrefHeight(200);
        library_view.setBorder(boxBorder);
        library_view.setPadding(new Insets(2, 2, 2, 2));
	library_view.setOnSelectionChanged((tile) -> {
		if (tile != null) {
		    if(tile.contains("R")&&tile.length()<3) {
                candidate = new TilesShape(tile,Colour.RED, library_view.getItemSize(tile), 0, 0, 0);
            }
            else if(tile.contains("B")) {
                candidate = new TilesShape(tile,Colour.BLUE, library_view.getItemSize(tile), 0, 0, 0);
            }
            else if(tile.contains("G")) {
                candidate = new TilesShape(tile,Colour.GREEN, library_view.getItemSize(tile), 0, 0, 0);
            }
            else if(tile.contains("Y")) {
                candidate = new TilesShape(tile,Colour.YELLOW, library_view.getItemSize(tile), 0, 0, 0);
            }
            else if(tile.contains("P")) {
                candidate = new TilesShape(tile,Colour.PURPLE, library_view.getItemSize(tile), 0, 0, 0);
            }
            else if(tile.equals("S1O")|| tile.equals("S1X")){
                candidate = new TilesShape(tile,Colour.GRAY, library_view.getItemSize(tile), 0, 0, 0);
            }
		    if (tile.equals("S1O")) candidate.setNoBrick();
		    candidate_index = player_selector.getSelectionModel().getSelectedIndex();
		}
		else {
		    candidate = null;
		}
		if (onTileSelected != null)
		    onTileSelected.accept(tile);
		showState();
	    });
        right.getChildren().add(library_view);
        price_view = new FlowPane();
        price_view.setAlignment(Pos.CENTER_LEFT);
        price_view.setBorder(boxBorder);
        price_view.setPadding(new Insets(2, 2, 2, 2));
        price_view.setMinHeight(40);
        dice_view = new DiceView(5);
        dice_view.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,CornerRadii.EMPTY,Insets.EMPTY) ));
        dice_view.setBorder(boxBorder);
        dice_view.setPadding(new Insets(2, 2, 2, 2));
        right.getChildren().add(dice_view);
        control_view = new StackPane();
        control_view.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,CornerRadii.EMPTY,Insets.EMPTY) ));
        control_view.setBorder(boxBorder);
        control_view.setPadding(new Insets(2, 2, 2, 2));
        control_view.setAlignment(Pos.CENTER);
        right.getChildren().add(control_view);
        VBox.setVgrow(control_view, Priority.ALWAYS);
        BorderPane.setAlignment(right, Pos.CENTER_RIGHT);
        BorderPane.setMargin(right, new Insets(4, 4, 4, 4));
        this.setRight(right);
    }

    //////////////////////////////////////////////////////////////////////
    // public interface

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static final int MAX_N_PLAYERS = 4;
    
    public GameGUI() {
	super(); // BorderPane no-arg constructor
	makeMainLayout();
        makeSetupControls();
        makePlayerControls();
        control_view.getChildren().add(game_setup_controls);
        showState();
    }

    public void setAbilityMenu(List<String> abilities){
        b_use.getItems().clear();
        for (String s : abilities) {
            MenuItem act = new MenuItem(s);
            act.setOnAction((e) -> {
                if (onAbilityAction != null)
                    onAbilityAction.accept(act.getText());
                showState();
            });
            b_use.getItems().add(act);
        }
    }

    public void updateAbilityMenu() {
        int p = getSelectedPlayer();
        Player player = Game_Start.gl.players.get(p);
        boolean whether_empty = true;
        for(Map.Entry<AbilityRegion.Abilities,Integer> pairs:player.abilities.entrySet()){
            if(pairs.getValue()!=0){
                whether_empty = false;
            }
        }
        List<String> abilities = new ArrayList<>();
        if (!whether_empty) {
            for (Map.Entry<AbilityRegion.Abilities, Integer> pairs : Game_Start.gl.players.get(p).abilities.entrySet()) {
                if(pairs.getValue()>0) {
                    int num = pairs.getValue();
                    for(int i=0;i<num;i++) {
                        abilities.add(String.valueOf(pairs.getKey()));
                    }
                }
            }
            setAbilityMenu(abilities);
        }
        else{
            setAbilityMenu(List.of("You have no ability now"));
        }
        showState();
    }

    public void setColourMenu(List<String> colours){
        b_colour_change.getItems().clear();
        for (String s : colours) {
            MenuItem act = new MenuItem(s);
            act.setOnAction((e) -> {
                if (onColourChange != null)
                    onColourChange.accept(act.getText());
                showState();
            });
            b_colour_change.getItems().add(act);
        }
    }

    public void setShieldsMenu(List<String> ability){
        b_rabbits.getItems().clear();
        for (String s : ability) {
            MenuItem act = new MenuItem(s);
            act.setOnAction((e) -> {
                if (onRabbits != null)
                    onRabbits.accept(act.getText());
                showState();
            });
            b_rabbits.getItems().add(act);
        }
    }

    public void colourChange(String colour){
        int index = Game_Start.gl.rounds.size()-1;
        List<Integer> c_index = Game_Start.gui.dice_view.selected.getSelection();
        List<String> colours = new ArrayList<>(Game_Start.gl.rounds.get(index).colours);
        List<String> s_c = new ArrayList<>();
        for(int i = 0; i< c_index.size();i++){
            s_c.add(colours.get(c_index.get(i)));
        }
        String first = s_c.get(0);
        boolean is_equal = true;
        for(String c:s_c){
            if(!first.equals(c)){
                is_equal= false;
            }
        }
        if(is_equal) {
            for(int i =0; i<colours.size();i++) {
                int num = Game_Start.gl.rounds.get(index).dices_color.get(Colour.getColour(colours.get(c_index.get(i))));
                Game_Start.gl.rounds.get(index).dices_color.put(Colour.getColour(colours.get(c_index.get(i))), num - 1);
                Game_Start.gl.rounds.get(index).dices_color.put(Colour.getColour(colour), Game_Start.gl.rounds.get(index).dices_color.getOrDefault(Colour.getColour(colour), 0) + 1);
                Game_Start.gl.rounds.get(index).colours.set(i,colours.get(i));
                colours.set(c_index.get(i), colour);
            }
            Game_Start.gui.setAvailableDice(colours);
            b_colour_change.setDisable(true);
            showState();
        }
    }

    /**
     * Set text in the message field at the top.
     */
    public void setMessage(String msg) {
	current_player_view.setText(msg);
    }

    /**
     * Set list of tiles to be shown in the tile "library" (top right).
     * This will also clear any current selection.
     */
    public void setAvailableTiles(List<String> tiles) {
	candidate = null;
	library_view.clearSelection();
	library_view.show(tiles);
    }

    /**
     * Clear selected tile. This does not change which tiles are shown
     * in the tile library, but will unselect the currently selected
     * tile, if any. This also means the selected tile will disappear
     * from the building view.
     */
    public void clearTileSelection() {
	candidate = null;
	library_view.clearSelection();
    }

    /**
     * Set list of dice (colours) to be shown in the dice view.
     * This will also clear any current dice selection.
     */
    public void setAvailableDice(List<String> colours) {
	dice_view.selectors().clearSelection();
	dice_view.show(colours);
    }

    public int getCurrent_now(){
        return current_now;
    }

    public void changeDisable(){
        b_colour_change.setDisable(false);
    }

    /**
     * Clear dice selection. This does not change which dice are shown
     * in the dice view, but will unselect any currently selected dice.
     */
    public void clearDiceSelection() {
	dice_view.selectors().clearSelection();
    }

    public void checkBoxInitial(){
        player_view.selectors.disableRange(0,5);
    }

    /**
     * Get the currently selected dice.
     * @return a list of indices of the currently selected dice.
     */
    public List<Integer> getSelectedDice() {
        return dice_view.selectors().getSelection();
    }

    public void dices_remainder() {
        dice_view.selected.enableRange(0, 4);
        int index = Game_Start.gl.rounds.size() - 1;
        List<String> colours = Game_Start.gl.rounds.get(index).colours;
        Colour c = dice_remainder.get_Color();
        int n = dice_remainder.num_of_tile;
        int num_colours=0;
        List<Integer> index_colours = new ArrayList<>();
        for (int i = 0; i < colours.size(); i++) {
            if (colours.get(i).equals(c.toString())) {
                num_colours++;
                index_colours.add(i);
            }
        }
        if(num_colours==n){
            for(int i=0; i<index_colours.size();i++){
                dice_view.selected.selectors[index_colours.get(i)].setDisable(true);
            }
        }
        else if(num_colours>n){
            for(int i=0; i<n;i++){
                dice_view.selected.selectors[index_colours.get(i)].setDisable(true);
            }
        }
        else{
            List<Integer> index_white = new ArrayList<>();
            for(int i=0;i<colours.size();i++){
                if(colours.get(i).equals(Colour.WHITE.name)){
                    index_white.add(i);
                }
            }
            int difference = n-num_colours;
            for(int i=0;i<num_colours;i++){
                dice_view.selected.selectors[index_colours.get(i)].setDisable(true);
            }
            for(int i=0;i<difference;i++){
                dice_view.selected.selectors[index_white.get(i)].setDisable(true);
            }
        }
    }

    /**
     * Set the square at (x,y) in the specified player's building
     * facade to show the specified colour and window
     *
     * Use colour = "White" and window = false to make a square empty.
     * @param player The player whose building should be updated
     *        (0 to number of players - 1).
     * @param x The x position (column) of the square (0-4)
     * @param y The y position (row) of the square (0-8)
     * @param colour The colour to show. Must be one of the strings
     *        "Red", "Blue", "Purple", "Green", "Yellow", "Gray", or
     *        "White". The colour can be abbreviated to the initial
     *        letter only.
     * @param window true iff the square should show a window, false
     *        if it should not.
     */
    public void setFacadeSquare(int player, int x, int y, String colour, boolean window) {
	Colour c = Colour.getColour(colour);
	building_view.setSquare(player, x, y, c, window);
    }

    /**
     * Toggle display status of the coat-of-arms next to a row.
     * @param player The player whose building should be updated
     *        (0 to number of players - 1).
     * @param y The row index. Note that this must be one of 1, 3 or 5.
     * @param highlightOn Whether the CoA should be highlighted (shown
     *        in gold colour) or not (shown in black).
     */
    public void setRowCoA(int player, int y, boolean highlightOn) {
	building_view.setRowCoA(player, y, highlightOn);
    }

    /**
     * Toggle display status of the coat-of-arms on top of a column
     * @param player The player whose building should be updated
     *        (0 to number of players - 1).
     * @param x The row index. Note that this must be one of 1 or 3.
     * @param highlightOn Whether the CoA should be highlighted (shown
     *        in gold colour) or not (shown in black).
     */
    public void setColumnCoA(int player, int x, boolean highlightOn) {
	    building_view.setColumnCoA(player, x, highlightOn);
    }

    /**
     * Set the score shown for one of the players.
     */
    public void setScore(int player, int score) {
        player_view.setScore(player, score);
    }

    /**
     * Set/update the information to be shown for one of a player's
     * ability tracks.
     * @param player The player whose ability track should be updated
     *        (0 to number of players - 1).
     * @param colour The colour of the track. Must be one of "Red",
     *        "Blue", "Purple", "Green" or "Yellow".
//     * @param nMarked Number to be shown in the "X" column.
//     * @param nBonusAvailable Number to show in the "Avail/+" column.
//     * @param nAbilityAvailable Number to show in the "Avail/star" column.
//     * @param nBonusToNext Number to show in the "Next/+" column.
//     * @param nAbilityToNext Number to show in the "Next/star" column.
     */

    /**
     * Clear track selection.
     */
    public void clearTrackSelection() {
	    player_view.getTrackSelectors().clearSelection();
    }

    /**
     * Get the currently selected ability track(s).
     * @return a list of indices of the currently selected track(s).
     */
    public List<Integer> getSelectedTracks() {
	    return player_view.getTrackSelectors().getSelection();
    }

    public void use_a(String a){
        int p = getSelectedPlayer();
        Player player = Game_Start.gl.players.get(p);
        AbilityRegion.Abilities a_r = AbilityRegion.Abilities.getAbility(a);
        player.abilities.put(a_r,player.abilities.get(a_r)-1);
    }

    /**
     * End the current game.
     * This will bring up the end of game screen (in the lower right
     * corner), which shows the final scores and offers the choice to
     * quit or play again.
     */
    public void endGame(int[] finalScores) {
        for(int i=0; i<finalScores.length;i++){
            finalScores[i] = Game_Start.gl.players.get(i).get_score();
        }
	    Pane gameOverControls = makeGameOverControls(finalScores);
	    control_view.getChildren().clear();
	    control_view.getChildren().add(gameOverControls);
    }

    public void whether_endGame(int p){
           Player player = Game_Start.gl.players.get(p);
           if(player.get_score()>=12){
            boolean whether = true;
            for(int i=0;i<players.length;i++) {
                if (players[i] == null || players[i].isEmpty()) {
                    whether = false;
                }
            }
            if(whether){
                endGame(new int[players.length]);
            }
        }
    }

    /**
     * Returns the index of the player whose score sheet is currently
     * being shown in the left part of the GUI.
     */
    public int getSelectedPlayer() {
	int i = player_selector.getSelectionModel().getSelectedIndex();
	return i;
    }

    /**
     * Set the player whose score sheet should be shown in the left part
     * of the GUI.
     * @param player The player to display (0 to number of players - 1).
     */
    public void setSelectedPlayer(int player) {
        player_selector.getSelectionModel().select(player);
    }

    /**
     * Set the list of actions to appear in the "Action..." menu.
     */
    public void setAvailableActions(List<String> actions) {
	b_action.getItems().clear();
	for (String s : actions) {
            MenuItem act = new MenuItem(s);
	    act.setOnAction((e) -> {
		    if (onGameAction != null)
			onGameAction.accept(act.getText());
		    showState();
		});
            b_action.getItems().add(act);
        }
    }

    /**
     * Update the text on the Confirm and Pass buttons to indicate that
     * the current decision belongs to player i.
     */
    public void setControlPlayer(int i) {
	b_confirm.setText("Confirm (player " + Integer.toString(i) + ")");
	b_pass.setText("Pass (player " + Integer.toString(i) + ")");
    }

    // register event callbacks

    /**
     * Set the event handler to be called when a new game is started.
     * The handler will receive two arguments: the number of players
     * (an integer) and an array of boolean values, of the same length
     * as the number of players, indicating which players should be AI
     * controlled. (Of course, if your game does not have an AI, you
     * can ignore the second argument.)
     */
    public void setOnStartGame(BiConsumer<Integer, boolean[]> handler) {
        onStartGame = handler;
    }

    /**
     * Set the event handler to be called when the user selects a tile
     * from the "tile library" (on the top-right in the display). The
     * handler will receive one argument, which is the name of the
     * tile.
     *
     * The selected tile will be displayed as a "candidate" (outline)
     * on the building display of the currently selected player's
     * score sheet.
     */
    public void setOnTileSelected(Consumer<String> handler) {
        onTileSelected = handler;
    }

    /**
     * Set the event handler to be called when the user confirms
     * placement of a selected tile.
     * The handler will receive one argument, which is an object of
     * type `Placement` that contains all the details of the intended
     * placement.
     */
    public void setOnTilePlaced(Consumer<TilesShape> handler, TilesShape ts) {
        onTilePlaced = handler;
        Grid[] tiles = ts.set_tiles();
        ts.Shape_change(tiles);
        if(ts.getRotation()!=0) {
            ts.Shape_change(tiles);
            for (int i = 0; i < ts.num_of_tile; i++) {
                building_view.setSquare(getSelectedPlayer(), tiles[i].position[0], tiles[i].position[1], ts.get_Color(), ts.windows[i]);
            }
        }
        else {
            for (int i = 0; i < ts.num_of_tile; i++) {
                building_view.setSquare(getSelectedPlayer(), tiles[i].position[0], tiles[i].position[1], ts.get_Color(), ts.windows[i]);
            }
        }
    }

    /**
     * Set the event handler to be called when the user changes the
     * selection of any die in the dice diplay. The event handler will
     * receive one argument, which is the index of of the die whose
     * selection status has changed.
     * This event only informs the handler that a dies selection has
     * changed, not whether the die is now selected or unselected. You
     * can use the `getSelectedDice()` method to get the indices of
     * currently selected dice.
     *
     */

    public void setOnDiceSelectionChanged(IntConsumer handler) {
            dice_view.selected.setOnSelectionChanged((i)-> {
            });

    }

    public void clear_DicesSelection(){
        dice_view.selectors().clearSelection();
    }

    /**
     * Set the event handler to be called when the user changes the
     * selection (checkbox) of any of the ability tracks. The event
     * handler will receive one argument, which is the index (0-4)
     * of the track whose selection status has changed.
     * This event only informs the handler that a selection has
     * changed, not whether the track is now selected or unselected.
     * You can use the `getSelectedTracks()` method to get the indices
     * of currently selected tracks.
     */
    public void setOnTrackSelectionChanged(IntConsumer handler) {
        player_view.getTrackSelectors().setOnSelectionChanged((i) -> {
            handler.accept(i);
            int p = getSelectedPlayer();
            Player player = Game_Start.gl.players.get(p);
            List<Integer> index = getSelectedTracks();
            Colour c = Game_Start.gl.players.get(p).ar.get_color(index.get(0));
            int e = Game_Start.gl.rounds.size() - 1;
            if(p!=current_now&&!dice_view.selected.getSelection().isEmpty()){
                int d_i = dice_view.selected.getSelection().get(0);
                String d_c = Game_Start.gl.rounds.get(e).colours.get(d_i);
                if(d_c.equals(c.toString())||d_c.equals(Colour.WHITE.name)) {
                    setTrackInfo(p, c.toString(),1);
                    player.advance_steps(player, c, 1);
                    player_view.setScore(p,player.get_score());
                    updateAbilityMenu();
                    whether_endGame(p);
                }
            }

            showState();
        });
    }

    public void setTrackInfo(int player, String colour, int step) {
        int[] info = player_view.getTrackInfo(player,colour);
        for(int i =info.length-1;i>=0;i--){
            if(info[i]==0){
                info[i]=info[i]+step;
                if(info[i]>1) {
                    info[i] = info[i] - 1;
                    info[i - 1] = info[i - 1] + 1;
                }
                break;
            }
        }
        player_view.setTrackInfo(player, colour, info);
    }

    public void withdrawTrackInfo(int player, String colour, int step){
        int[] info = player_view.getTrackInfo(player,colour);
        for(int i=0; i<info.length&&step>0;i++){
            if(info[i]!=0){
                info[i] = info[i]-1;
                step--;
            }
        }
    }

    public void withdrawListener() {
            player_view.selectors.inhibitSelectionEvent = true;
            int size = player_view.selectors.size;
            int p = getSelectedPlayer();
            Player player = Game_Start.gl.players.get(p);
            for (int i = 0; i < size; i++) {
                int finalI = i;
                player_view.selectors.selectors[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (oldValue && !newValue&&whether_click) {
                            player_view.selectors.enableRange(0, 5);
                            withdrawTrackInfo(p, player.ar.get_color(finalI).toString(), 1);
                            player.withdrawSteps(player.ar.get_color(finalI), 1);
                            player_view.setScore(p, player.get_score());
                            updateAbilityMenu();
                            showState();
                        }
                    }
                });
            }
            player_view.selectors.inhibitSelectionEvent = false;
        }

    public void advanceListener(){
        player_view.selectors.inhibitSelectionEvent=true;
        int size= player_view.selectors.size;
        int p = getSelectedPlayer();
        Player player = Game_Start.gl.players.get(p);
        for(int i=0;i<size;i++){
            int finalI = i;
            player_view.selectors.selectors[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(!oldValue&&newValue){
                       for(int j=0; j<size;j++){
                           if(!player_view.selectors.selectors[j].isSelected()){
                               player_view.selectors.selectors[j].setDisable(true);
                           }
                       }
                    }
                }
            });
        }
        player_view.selectors.inhibitSelectionEvent=false;
    }

    /**
     * Set the event handler to be called when the "Confirm" button is
     * pressed in any situation except when it is pressed to confirm a
     * tile placement (this will generate a TilePlaced event instead).
     * The event handler will receive one argument, which is the
     * current label of the button.
     */
    public void setOnConfirm(Consumer<String> handler) {
	onConfirm = handler;
    }

    /**
     * Set the event handler to be called when the "Pass" button is
     * pressed. The event handler will receive one argument, which is
     * the current label of the button.
     */
    public void setOnPass(Consumer<String> handler) {
        onPass = handler;
    }

    /**
     * Set the event handler to be called when an item from the "Action"
     * menu button is selected. The event handler will receive one
     * argument, which is the label of the menu item.
     */
    public void setOnGameAction(Consumer<String> handler) {
        onGameAction = handler;
    }

    public void setOnAbilityAction(Consumer<String> handler) {
        onAbilityAction = handler;
    }

    public void setOnRabbitAction(Consumer<String> handler) {
        onRabbits = handler;
    }

    public void setOnColourChange(Consumer<String> handler) {
        onColourChange = handler;
    }

    public void executeAbility(String ability, int player) {
        Player which_player = Game_Start.gl.players.get(player);
        int i=Game_Start.gl.rounds.size()-1;
        if(!ability.equals("Draw one tile with carrot")&&!ability.equals("Choose an ability track and advance 2 steps")) {
            use_a(ability);
            updateAbilityMenu();
        }
        switch (ability){
            case "Draw one tile with carrot":
                TilesShape S1O = new TilesShape("S1O", Colour.GRAY, 1,0,0,0);
                S1O.windows[0] = true;
                can_place = true;
                GameGUI.availableTS.add("S1O");
                if(player==current_now) {
                    Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                }
                else{
                    Game_Start.gui.setAvailableTiles(List.of("S1X"));
                }
                break;
            case "Choose an ability track and advance 2 steps":
                int index = Game_Start.gui.player_view.selectors.getSelection().get(0);
                String c = Game_Start.gl.players.get(player).ar.ability_region.get(index).toString();
                setTrackInfo(player,c,2);
                which_player.advance_steps(which_player, which_player.ar.get_color(index),2);
                player_view.setScore(player,which_player.get_score());
                updateAbilityMenu();
                whether_endGame(player);
                showState();
                break;
            case "redStar":
                which_player.ar.redStar(Game_Start.gl.rounds.get(i).dices_color,Game_Start.gl.rounds.get(i).colours, Game_Start.gui.dice_view.selected.getSelection());
                Game_Start.gui.setAvailableDice(Game_Start.gl.rounds.get(i).colours);
                break;
            case "blueStar":
                which_player.ar.blueStar(Game_Start.gui.candidate);
                break;
            case "greenStar":
                Game_Start.gui.changeDisable();
                break;
            case "yellowStar":
                ys_ability = true;
                can_place = true;
                Game_Start.gui.setAvailableTiles(List.of("R2", "R3", "R4", "R4", "R5", "B2", "B3", "B4L", "B4R", "B5", "P2","P3","P4","P4","P5","G2", "G3", "G4L", "G4R", "G5", "Y2", "Y3", "Y4L", "Y4R", "Y5"));
                //Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                //which_player.ar.yellowStar_pick_one(Game_Start.gui.candidate);
                break;
            case "purpleStar":
                TilesShape S1X = new TilesShape("S1X", Colour.GRAY, 1,0,0,0);
                GameGUI.availableTS.add("S1X");
                can_place = true;
                if(player==current_now) {
                    Game_Start.gui.setAvailableTiles(GameGUI.availableTS);
                }
                else{
                    Game_Start.gui.setAvailableTiles(List.of("S1X"));
                }
                break;
            case "RedPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.RED, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.RED, 0) + 1);
                break;
            case "YellowPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.YELLOW, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.YELLOW, 0) + 1);
                break;
            case "GreenPlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.GREEN, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.GREEN, 0) + 1);
                break;
            case "PurplePlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.PURPLE, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.PURPLE, 0) + 1);
                break;
            case "BluePlusSign":
                Game_Start.gl.rounds.get(i).dices_color.put(Colour.BLUE, Game_Start.gl.rounds.get(i).dices_color.getOrDefault(Colour.BLUE, 0) + 1);
                break;
        }
    }
}
