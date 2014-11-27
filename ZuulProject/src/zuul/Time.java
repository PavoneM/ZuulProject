package zuul;

import java.util.Observable;

public class Time extends Observable implements Runnable{
	
	private int day;
	private int hour;
	private int second;
	
	private String[] days = {"Monday", "Tuesday","Wednesday", "Thursday", "Friday"};
	
	public void run() {
		
		day=0;
		hour=8;
		second=0;
		
		setChanged();
		notifyObservers(hour);
		
		while(true){
			try { Thread.sleep(100); }
			catch (Exception e) {e.printStackTrace();}
			
			second++;
			
			if(second>=60){
				hour++;
				second=0;
				setChanged();
				notifyObservers(hour);
			}
			if(hour>=17){
				day++;
				hour=8;
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
		this.second=0;
		if(hour>=17)
			this.hour = 8 + (this.hour-17);
		else 
			this.hour = hour;
	}
	
	public String getTime(){
		String theDay = days[day];
		return "Today we are "+ theDay +"   "+String.format("%02d", hour)+":"+String.format("%02d", second);
	}

	public void increaseDay() {
		if(day==5) day=0;
		else day++;
	}
	
}
