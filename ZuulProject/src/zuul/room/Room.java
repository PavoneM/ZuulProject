/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu très simple qui a été développé dans le cadre
 * du module de POO à Polytech Nice.
 *
 * Cette classe fait partie du package zuul.room
 * 
 * @author  Manuel Pavone et Vincent Forquet
 * @version 30.11.2014
 */

package zuul.room;

import java.util.ArrayList;
import zuul.Game;

import java.util.HashMap;
import java.util.Set;
import zuul.item.Item;
public class Room {
    protected String description;
    protected String icon;
    protected HashMap<String, Room> exits; // stores exits of this room
    protected ArrayList<Item> itemList;
    protected boolean discovered;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * @param description
     *            The room's description.
     */
    public Room(String description, String icon) {
        this.description = description;
        discovered=false;
        exits = new HashMap<>();
        this.icon = icon;
        itemList = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction
     *            The direction of the exit.
     * @param neighbor
     *            The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room (the one that was defined in
     *         the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * @return The icon of the room for the map
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Return a description of the room in the form: You are in the kitchen.
     * Exits: north west
     * 
     * @return A long description of this room
     */
    public String getLongDescription() {
        return Game.language.get("youare") + description + ".\n==>" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits.
     */
    protected String getExitString() {
        String returnString = Game.language.get("exits");
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction
     *            The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public void discover(){
    	discovered = true;
    }
    
    public boolean existexit(String direction){
    	return exits.containsKey(direction);
    }
    
    public boolean isDiscovered(){
    	return discovered;
    }
    
    public ArrayList<Item> getItemList(){
    	return itemList;
    }
    
    public void addItem(Item i){
    	itemList.add(i);
    }
}
