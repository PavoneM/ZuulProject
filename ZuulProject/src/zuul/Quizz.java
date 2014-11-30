package zuul;

import java.util.ArrayList;
import java.util.HashMap;

import zuul.item.Cheat;
import zuul.item.Item;

public class Quizz {
	
	private HashMap<String, ArrayList<String>> QA;
	private ArrayList<Item> bp;
	private Parser p;
	private int grade;
	
	public Quizz(HashMap<String, ArrayList<String>> QA, Parser p, ArrayList<Item> b) {
		this.QA = QA;
		this.p = p;
		this.grade = 0;
		this.bp = b;
	}

	public boolean start(){
		
		for(int i=1; i<=Config.oopLecture.size()/2;i++){
			
			int random = (int) (Math.random()*(QA.get("question"+i).size()-1));
			System.out.println("Question "+i+ " : " + QA.get("question"+i).get(random));
			String answer = QA.get("answer"+i).get(random);
			if(verifyCheat(i)) System.out.println(Game.language.get("retcheat")+" "+ QA.get("answer"+i).get(random));
			String givenAnswer = null;
			while (true){
				if((givenAnswer = p.getCommand().getCommandWord()) == null){ 
					System.out.println(Game.language.get("retcheat"));
					continue;
				}
				else if(!givenAnswer.equals(Game.language.get("true")) && !givenAnswer.equals(Game.language.get("false"))){ 
					System.out.println(Game.language.get("answermustbe"));
					continue;
				}
				else break;
			}
			
			if( givenAnswer.equals(Game.language.get(answer)))
				grade++;
			else grade--;
		}
		System.out.println(Game.language.get("yourmark")+grade + "/" + Config.oopLecture.size()/2);
		if(grade >= Config.oopLecture.size()/4) return true;
		else return false;
	}
	
	private boolean verifyCheat(int sub){
		for(int i=0;i<bp.size();i++)
			if(bp.get(i) instanceof Cheat && ((Cheat) bp.get(i)).getNum() == sub)
				return true;
		return false;
	}
}
