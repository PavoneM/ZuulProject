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

import zuul.Lecture;

public class Planning {
	
	// Matrice de Cours
	private Lecture[][] table;
	
	/**
	 * Constructeur d'un objet de type Planning
	 * @param table Matrice de cours
	 */
	public Planning(Lecture[][] table) {
		this.table = table;
	}

	public Lecture[][] getTable() {
		return table;
	}

	@Override
	public String toString(){
		return   " ╔═══════╤═══════════╤═══════════╤═══════════╤═══════════╤═══════════╗\n"
				+" ║       │  Monday   │  Tuesday  │ Wednesday │  Thursday │  Friday   ║\n"
				+" ╠═══════╪═══════════╪═══════════╪═══════════╪═══════════╪═══════════╣\n"
				+" ║08h-10h│    "+table[0][0].getAcronym()+"    │    "+table[0][1].getAcronym()+"    │    "+table[0][2].getAcronym()+"    │    "+table[0][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║10h-12h│    "+table[1][0].getAcronym()+"    │    "+table[1][1].getAcronym()+"    │    "+table[1][2].getAcronym()+"    │    "+table[1][3].getAcronym()+"    │    "+table[1][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║12h-14h│    "+table[2][0].getAcronym()+"    │    "+table[2][1].getAcronym()+"    │    "+table[2][2].getAcronym()+"    │    "+table[2][3].getAcronym()+"    │    "+table[2][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║14h-16h│    "+table[3][0].getAcronym()+"    │    "+table[3][1].getAcronym()+"    │    "+table[3][2].getAcronym()+"    │    "+table[3][3].getAcronym()+"    │    "+table[3][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║16h-18h│    "+table[4][0].getAcronym()+"    │    "+table[4][1].getAcronym()+"    │    "+table[4][2].getAcronym()+"    │    "+table[4][3].getAcronym()+"    │    "+table[4][4].getAcronym()+"    ║\n"
				+" ╚═══════╧═══════════╧═══════════╧═══════════╧═══════════╧═══════════╝\n";
		
	}
	
	
}
