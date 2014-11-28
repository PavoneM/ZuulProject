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
		
		for(int i=1; i<=Config.oopLecture.size()/2;i++){
			
			int random = (int) (Math.random()*(QA.get("question"+i).size()-1));
			System.out.println("Question "+i+ " : " + QA.get("question"+i).get(random));
			String answer = QA.get("answer"+i).get(random);
			if( p.getCommand().getCommandWord().equals(answer) )
				grade++;
			else grade--;
		}
		System.out.println("Your mark is "+grade + "/" + Config.oopLecture.size()/2);
		if(grade >= Config.oopLecture.size()/4) return true;
		else return false;
	}
}
