package zuul.room;

import zuul.Resume;

public class Planning {
	
	private Resume[][] table;

	public Planning(Resume[][] table) {
		this.table = table;
	}

	public Resume[][] getTable() {
		return table;
	}

	public void setTable(Resume[][] table) {
		this.table = table;
	}
	
	@Override
	public String toString(){
		return   " ╔═══════╤═══════════╤═══════════╤═══════════╤═══════════╤═══════════╗\n"
				+" ║       │  Monday   │  Tuesday  │ Wednesday │  Thursday │  Friday   ║\n"
				+" ╠═══════╪═══════════╪═══════════╪═══════════╪═══════════╪═══════════╣\n"
				+" ║08h-10h│    "+table[0][0].getAcronym()+"    │    "+table[0][1].getAcronym()+"    │    "+table[0][2].getAcronym()+"    │    "+table[0][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║08h-10h│    "+table[1][0].getAcronym()+"    │    "+table[1][1].getAcronym()+"    │    "+table[1][2].getAcronym()+"    │    "+table[1][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║08h-10h│    "+table[2][0].getAcronym()+"    │    "+table[2][1].getAcronym()+"    │    "+table[2][2].getAcronym()+"    │    "+table[2][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║08h-10h│    "+table[3][0].getAcronym()+"    │    "+table[3][1].getAcronym()+"    │    "+table[3][2].getAcronym()+"    │    "+table[3][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╟───────┼───────────┼───────────┼───────────┼───────────┼───────────╢\n"
				+" ║08h-10h│    "+table[4][0].getAcronym()+"    │    "+table[4][1].getAcronym()+"    │    "+table[4][2].getAcronym()+"    │    "+table[4][3].getAcronym()+"    │    "+table[0][4].getAcronym()+"    ║\n"
				+" ╚═══════╧═══════════╧═══════════╧═══════════╧═══════════╧═══════════╝\n";
		
	}
	
	
}
