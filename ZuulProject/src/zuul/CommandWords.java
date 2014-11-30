package zuul;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class CommandWords {
	// a constant array that holds all valid command words
	private String[] validCommands;

	/**
	 * Constructor - initialise the command words.
	 */
	public CommandWords() {
		String[] validCommands1 = { Game.language.get("go"), Game.language.get("quit"), Game.language.get("help"),
				Game.language.get("switch"), Game.language.get("yes"), Game.language.get("non") , Game.language.get("time"), 
				Game.language.get("check"), Game.language.get("drink"), Game.language.get("map"), Game.language.get("energy"), 
				Game.language.get("wait"),Game.language.get("backpack"),Game.language.get("take"), Game.language.get("true"), 
				Game.language.get("false")};
		validCommands=validCommands1;
		// nothing to do at the moment...
	}

	/**
	 * Check whether a given String is a valid command word.
	 * 
	 * @return true if it is, false if it isn't.
	 */
	public boolean isCommand(String aString) {
		for (int i = 0; i < validCommands.length; i++) {
			if (validCommands[i].equals(aString))
				return true;
		}
		// if we get here, the string was not found in the commands
		return false;
	}

	/**
	 * Print all valid commands to System.out.
	 */
	public void showAll() {
		for (String command : validCommands) {
			System.out.print(command + "  ");
		}
		System.out.println();
	}
}
