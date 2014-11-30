package zuul;

import java.util.Observable;

public class Time extends Observable implements Runnable{
	
	private int day;
	private int hour;
	private int minutes;
	
	private String[] days = {Game.language.get("monday"), Game.language.get("tuesday"),
			Game.language.get("wednesday"), Game.language.get("thursday"), Game.language.get("friday")};
	
	public void run() {
		
		day=0;
		hour=8;
		minutes=0;
		
		setChanged();
		notifyObservers(hour);
		
		while(true){
			try { Thread.sleep(100); }
			catch (Exception e) {e.printStackTrace();}
			
			minutes++;
			
			if(minutes>=60){
				hour++;
				minutes=0;
				if(hour>=18){
					day++;
					hour=8;
				}
				setChanged();
				notifyObservers(hour);
			}

			if((day+1)>=5){
				day=0;
			}
		}
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setHour(int hour) {
		this.minutes=0;
		if(hour>=18)
			this.hour = 8 + (this.hour-18);
		else 
			this.hour = hour;
		setChanged();
		notifyObservers(hour);
	}
	
	public String getTime(){
		String theDay = days[day];
		return Game.language.get("today")+ theDay +"   "+String.format("%02d", hour)+":"+String.format("%02d", minutes);
	}

	public void increaseDay() {
		if(day==5) day=0;
		else day++;
	}
	
}
