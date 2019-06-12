package ricm3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage m_bg;
	int m_x, m_y, m_w, m_h;

	public ImagePanel(int x, int y, int w, int h, String url) {
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
		try {
			m_bg = ImageIO.read(new File(url));
		} catch (IOException e) {
			System.out.println("Probleme de lecture d'image (launcher bg)");
		}
	}
	
	public ImagePanel(int x, int y, int w, int h, BufferedImage bg) {
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
		m_bg = bg;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(m_bg, m_x, m_y, m_w, m_h, null);
	}

	public void update(int x, int y, int w, int h) {
		m_x = x;
		m_y = y;
		m_w = w;
		m_h = h;
		repaint();
	}
}
