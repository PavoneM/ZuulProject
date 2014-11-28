package zuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Quizz {
	
	private HashMap<String, ArrayList<String>> QA;
	private Parser p;
	private int grade;
	
	public Quizz(HashMap<String, ArrayList<String>> QA, Parser p) {
		this.QA = QA;
		this.p = p;
		this.grade = 0;
	}

	public boolean start(){
		
		for(int i=0; i<Config.oopLecture.size();i++){
			int random = (int) (Math.random()*QA.get("question"+i).size());
			System.out.println("Question "+i+ " : " + QA.get("question"+i).get(random));
			String answer = QA.get("answer"+i).get(random);
			if( p.getCommand().getCommandWord().equals(answer) )
				grade++;
			else grade--;
		}
		
		if(grade >= Config.oopLecture.size()/2) return true;
		else return false;
	}
}
