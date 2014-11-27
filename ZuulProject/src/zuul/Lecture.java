package zuul;

public class Lecture {
	
	private String name;
	private String acronym;
	
	public Lecture(String name, String acronym) {
		this.name = name;
		this.acronym = acronym;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
}
