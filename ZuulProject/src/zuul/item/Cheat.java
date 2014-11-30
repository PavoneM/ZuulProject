package zuul.item;
import zuul.Game;
import zuul.Config;

public class Cheat extends Item{
	
	private Item lesson;
	private int num;
	
	public Cheat(String name) {
		super(name);
		this.generateCheat();
	}
	
	private void generateCheat(){
		int random = (int) (Math.random()*(Config.oopLecture.size()/2));
		this.num = random;
		lesson = Config.oopLecture.get(random);
	}
	
	@Override
	public String toString(){
		return super.getName() + Game.language.get("about") + lesson.getName();
	}

	public Item getLesson() {
		return lesson;
	}

	public void setLesson(Item lesson) {
		this.lesson = lesson;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
