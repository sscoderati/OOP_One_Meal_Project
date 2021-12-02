/*package display_demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.DataEngineInterface;
import store.WaitingMgr;
import store.ReservationMgr;
import store.Store;

public class IntroGridDemo extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc = new GridBagConstraints();
	DataEngineInterface re, ie;
	JLabel reserved_c, seats, income;

	public IntroGridDemo() {
		super(new GridBagLayout());
	}

	public void frameSetup() {
		re = GUIMain.reservationEngine;
		ie = GUIMain.instantEngine;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		JButton reserve_b = new JButton("예약 관리");
		reserve_b.setPreferredSize(new Dimension(170, 90));
		reserve_b.setBackground(new Color(153, 255, 255));
		JButton waiting_b = new JButton("대기 관리");
		waiting_b.setPreferredSize(new Dimension(170, 90));
		waiting_b.setBackground(new Color(153, 255, 255));
		JButton reset = new JButton("REFRESH");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(reserve_b, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		add(waiting_b, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(reset, gbc);

		JLabel reserved_c = new JLabel("예약자 수: " + ReservationMgr.t_reservation);
		JLabel seats = new JLabel("남은 좌석: " + (Store.t_seats - WaitingMgr.t_waiting) + "/" + Store.t_seats);
		JLabel income = new JLabel("매출: " );
		reserved_c.setHorizontalAlignment(0);
		seats.setHorizontalAlignment(0);
		income.setHorizontalAlignment(0);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(reserved_c, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(seats, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(income, gbc);

		reserve_b.addActionListener(this);
		waiting_b.addActionListener(this);
		reset.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if (buttonName.equals("예약 관리")) {
			GUIMain.createAndShowTableGUI(re);
		} else if (buttonName.equals("대기 관리")) {
			GUIMain.createAndShowTableGUI(ie);
		} else if (buttonName.equals("REFRESH")) {
			reserved_c.setText("예약자 수: " + ReservationMgr.t_reservation);
			seats.setText("남은 좌석 수: " + (Store.t_seats - WaitingMgr.t_waiting) + "/" + Store.t_seats);
			income.setText("매출: " );
		}
	}
}*/