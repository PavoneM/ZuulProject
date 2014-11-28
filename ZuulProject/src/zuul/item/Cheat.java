package zuul.item;

import zuul.Config;

public class Cheat extends Item{
	
	private Item lesson;
	
	public Cheat(String name) {
		super(name);
		this.generateCheat();
	}
	
	private void generateCheat(){
		lesson = Config.oopLecture.get((int) (Math.random()*Config.oopLecture.size()));
	}
	
	@Override
	public String toString(){
		return super.getName() + " about " + lesson.getName();
	}

	public Item getLesson() {
		return lesson;
	}

	public void setLesson(Item lesson) {
		this.lesson = lesson;
	}

}
