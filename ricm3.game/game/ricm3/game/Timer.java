package ricm3.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JPanel {
	int[] timer; // timer avec { minutes, secondes }
	long m_lastSec;

	JLabel timer_name;
	JLabel timer_count;
	
	Timer(int min, int sec){
		super();
		timer = new int[2];
		if (min < 0 || sec < 0) {
			timer[0] = 2;
			timer[1] = 30;
		}
		timer[0] = min;
		timer[1] = sec;
		m_lastSec = 0;
		
		if (Options.day) {
			timer_name = new JLabel("Day");
		} else {
			timer_name = new JLabel("Night");
		}
		
		String count = "";
		if (timer[0] <= 9) {
			count += "0";
		}
		count += Integer.toString(timer[0]) + ":";
		if (timer[1] <= 9) {
			count += "0";
		}
		count += Integer.toString(timer[1]);
		timer_count = new JLabel(count);
		
		this.add(timer_name);
		this.add(timer_count);
	}
	
	public void step(long now) {
		if (Options.day) {
			timer_name.setText("Day");
		} else {
			timer_name.setText("Night");
		}
		
		if (timer[0] == 0 && timer[1] == 0) {
			return;
		}
		
		long elapsed = now - m_lastSec;
		if (elapsed >= 1000L) {
			m_lastSec = now;
			if (timer[1] == 0) {
				timer[0]--;
				timer[1] = 59;
			} else {
				timer[1]--;
			}
			
			String count = "";
			if (timer[0] <= 9) {
				count += "0";
			}
			count += Integer.toString(timer[0]) + ":";
			if (timer[1] <= 9) {
				count += "0";
			}
			count += Integer.toString(timer[1]);
			timer_count.setText(count);
		}
	}
	
	public int[] getTime() {
		return timer;
	}
	
	public void setTime(int min, int sec) {
		timer[0] = min;
		timer[1] = sec;
	}
}
