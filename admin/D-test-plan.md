
# Test plan

## List of classes

* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.

It is ok to omit trivial methods (for example, setters and getters), and
classes that have only trivial methods.

* Class [BuildingRegion]:
  * method [isFilled_row]；
  * method [isFilled_column]；
  * test whether the building region of the current player is filled in any row or column.

* Class [Dices]:
  * method [get_dices_color]；
  * test whether this method can get the colors of dices which have been rolled out and return it to support other methods.

* Class [Game_Logic]
  * method [Dices_canbe_Selected]；
  * method [Tiles_canbe_Placed];
  * test whether players can select the dice they want under the game rules and whether the tiles players selecting can be placed in the region they want.
  * these two tests have been completed in D2C class.

* Class [Player]:
  * method [add_score];
  * method [get_score];
  * test when players get scores whether the game can add the correct score on correct player.
  * and after the score has been added, the game can get the correct score to support other methods.
  * method [advance_steps];
  * method [get_stepNUM];
  * test when players choose a color of the ability region, whether the number [get_stepNUM] returns can correctly add 1 or 2.
  * method [store_ability];
  * test when players get some abilities, whether the game can store them correctly such that players can use them later.

* Class [Round]:
  * method [last_round];
  * test when the score of any player is 12 whether the game can get it can make itself into last round.
  * when the last round is finished, the game is over.

* Class [ShieldsShape]:
  * method [isTouched];
  * test when a player's row or column has been filled whether the corresponding shield can be touched then be crossed out.

