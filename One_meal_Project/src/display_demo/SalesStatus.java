package display_demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SalesStatus extends JFrame {
	
	public SalesStatus() {
		super("판매현황");
		buildGUI();
		
		setLocation(500,200);
		setSize(400,400);
		
		setVisible(true);
	}
	
	private void buildGUI() {
		setLayout(new BorderLayout());

		Container contentpane = getContentPane();
		ResultPanel resultPanel = new ResultPanel();
        //그래프를 그릴 패널
		
		contentpane.add(resultPanel, BorderLayout.CENTER);
		//버튼을 누를 시 작동할 리스너
	}

	//결과물(그래프)이 나타날 패널
	class ResultPanel extends JPanel{
		int data1 = OrderDisplay.mm1, data2 = OrderDisplay.mm2, data3 = OrderDisplay.mm3;
		
		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight());
			g.drawLine(50,  250,  350,  250);
			
			for (int i = 1; i< 11 ; i++) {
				g.drawString(i*10 + "", 25, 255-(20*i));
				g.drawLine(50,  250-(20*i), 350, 250-(20*i));
			}
			
			g.drawLine(50, 20, 50, 250);
			g.drawString("제육볶음", 100, 270);
			g.drawString("파불고기",  200, 270);
			g.drawString("대패삼겹살", 300, 270);
			g.setColor(Color.BLUE);
			
			if(data1 > 0)
				g.fillRect(110, 250 - data1*2, 10, data1*2);	
			if(data2 > 0)
				g.fillRect(210, 250 - data2*2, 10, data2*2);	
			if(data3 > 0)
				g.fillRect(310, 250 - data3*2, 10, data3*2);
		}
		
		void setScore(int data1, int data2, int data3) {
			this.data1 = data1;
			this.data2 = data2;
			this.data3 = data3;
		}
	}
}