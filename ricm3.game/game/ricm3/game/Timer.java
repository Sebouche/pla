package ricm3.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[] m_timer; // timer avec { minutes, secondes }
	long m_lastSec;

	JLabel m_timer_name;
	JLabel m_timer_count;
	
	Timer(int min, int sec){
		super();
		m_timer = new int[2];
		if (min < 0 || sec < 0) {
			m_timer[0] = 2;
			m_timer[1] = 30;
		}
		m_timer[0] = min;
		m_timer[1] = sec;
		m_lastSec = 0;
		
		if (Options.day) {
			m_timer_name = new JLabel("Day");
		} else {
			m_timer_name = new JLabel("Night");
		}
		
		String count = "";
		if (m_timer[0] <= 9) {
			count += "0";
		}
		count += Integer.toString(m_timer[0]) + ":";
		if (m_timer[1] <= 9) {
			count += "0";
		}
		count += Integer.toString(m_timer[1]);
		m_timer_count = new JLabel(count);
		
		this.add(m_timer_name);
		this.add(m_timer_count);
	}
	
	public void step(long now) {
		if (Options.day) {
			m_timer_name.setText("Day");
		} else {
			m_timer_name.setText("Night");
		}
		
		if (m_timer[0] == 0 && m_timer[1] == 0) {
			return;
		}
		
		long elapsed = now - m_lastSec;
		if (elapsed >= 1000L) {
			m_lastSec = now;
			if (m_timer[1] == 0) {
				m_timer[0]--;
				m_timer[1] = 59;
			} else {
				m_timer[1]--;
			}
			
			String count = "";
			if (m_timer[0] <= 9) {
				count += "0";
			}
			count += Integer.toString(m_timer[0]) + ":";
			if (m_timer[1] <= 9) {
				count += "0";
			}
			count += Integer.toString(m_timer[1]);
			m_timer_count.setText(count);
		}
	}
	
	public int[] getTime() {
		return m_timer;
	}
	
	public void setTime(int min, int sec) {
		m_timer[0] = min;
		m_timer[1] = sec;
	}
}
