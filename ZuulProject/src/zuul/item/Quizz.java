package zuul.item;

import java.util.ArrayList;

public class Quizz {
	
	private ArrayList<String> questions;
	private ArrayList<String> answers;
	
	public Quizz(ArrayList<String> questions, ArrayList<String> answers) {
		this.questions = questions;
		this.answers = answers;
	}

	public void addQA(String question, String answer){
		questions.add(question);
		answers.add(answer);
	}
	
	public ArrayList<String> getQuestions() {
		return questions;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
}
