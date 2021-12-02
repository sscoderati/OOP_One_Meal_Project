package display_demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facade.DataEngineInterface;
import store.WaitingMgr;

public class PosMainDisplay extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	static final String PASSWORD = "1234";
	DataEngineInterface re = GUIMain.reservationEngine;
	DataEngineInterface we = GUIMain.instantEngine;
	Font basic = new Font("돋음", Font.PLAIN, 25);
	Font small = new Font("소돋음", Font.BOLD, 15);
	JButton table_b1 = new JButton();
	JButton table_b2 = new JButton();
	JButton table_b3 = new JButton();
	JButton table_b4 = new JButton();
	JButton table_b5 = new JButton();
	JButton table_b6 = new JButton();
	JButton table_b7 = new JButton();
	JButton table_b8 = new JButton();
	JButton table_b9 = new JButton();
	JButton table_b10 = new JButton();
	JLabel wait_num, income;

	public PosMainDisplay() {
		super(new GridBagLayout());
	}

	void setupTablePane() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		for (int i = 1; i <= 10; i++)
			setTableButton(loadButton(i), i);
		JButton counter = new JButton("Pos-Counter");
		counter.setHorizontalAlignment(0);
		counter.setPreferredSize(new Dimension(200, 100));
		counter.setFont(basic);
		counter.setOpaque(true);
		counter.setBackground(new Color(200, 200, 200));
		counter.addActionListener(this);

		for (int i = 0; i < 9; i++) {
			gbc.gridx = i % 4;
			gbc.gridy = (int) i / 4;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			add(loadButton(i + 1), gbc);
		}

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		add(counter, gbc);

		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(table_b10, gbc);

		JPanel right_menu = new JPanel();
		right_menu.setLayout(new BorderLayout(0, 10));
		PosMainDisplay list = new PosMainDisplay();
		list.setupReservationList();
		PosMainDisplay wait = new PosMainDisplay();
		wait.setWaitingIndicater();
		right_menu.add(list, BorderLayout.PAGE_START);
		right_menu.add(wait, BorderLayout.CENTER);
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		add(right_menu, gbc);

		for (int i = 1; i <= 10; i++)
			loadButton(i).addActionListener(this);

		GUIMain gm = new GUIMain();
		table_b1.addActionListener(this);
		table_b2.addActionListener(this);
		table_b3.addActionListener(this);
		table_b4.addActionListener(this);
		table_b5.addActionListener(this);
		table_b6.addActionListener(this);
		table_b7.addActionListener(this);
		table_b8.addActionListener(this);
		table_b9.addActionListener(this);
		table_b10.addActionListener(this);
		table_b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
		table_b10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gm.createAndShowTableInfoGUI();
			}
		});
	}

	void createAndShowPOSManage() {
		JFrame manageFrame = new JFrame("POS 관리화면");
		JButton statusBtn = new JButton("판매현황");
		JButton settleBtn = new JButton("정산");
		JButton fileBtn = new JButton("파일");
		JButton endBtn = new JButton("장사 종료");
		manageFrame.setSize(350, 75);
		manageFrame.setLocationRelativeTo(null);
		manageFrame.setLayout(new FlowLayout());
		manageFrame.getContentPane().add(statusBtn);
		manageFrame.getContentPane().add(settleBtn);
		manageFrame.getContentPane().add(fileBtn);
		manageFrame.getContentPane().add(endBtn);
		manageFrame.setVisible(true);

		statusBtn.addActionListener(this);
		settleBtn.addActionListener(this);
		fileBtn.addActionListener(this);
		endBtn.addActionListener(this);
	}

	void setupReservationList() {
		setLayout(new BorderLayout(0, 10));

		JLabel list = new JLabel("예약 List");
		list.setHorizontalAlignment(0);
		add(list, BorderLayout.PAGE_START);

		String[] header = { "시간", "이름" };
		DefaultTableModel model = new DefaultTableModel(header, 0) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setPreferredSize(new Dimension(150, 220));
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(150, 220));
		add(scrollpane, BorderLayout.CENTER);

		JButton reservation_b = new JButton("예약 관리");
		reservation_b.setPreferredSize(new Dimension(70, 30));
		reservation_b.addActionListener(this);
		add(reservation_b, BorderLayout.PAGE_END);
	}

	void setWaitingIndicater() {
		setLayout(new BorderLayout(0, 10));

		JButton waiting_b = new JButton("대기자 관리");
		waiting_b.setPreferredSize(new Dimension(70, 30));
		waiting_b.addActionListener(this);
		add(waiting_b, BorderLayout.PAGE_START);

		wait_num = new JLabel("대기 인원 : " + WaitingMgr.t_waiting);
		wait_num.setHorizontalAlignment(0);
		wait_num.setFont(small);
		add(wait_num, BorderLayout.CENTER);

		/*
		 * income = new JLabel("매출 : " + OrderDisplay.dailyTotal + "원");
		 * income.setHorizontalAlignment(0);
		 * income.setFont(small);
		 * add(income, BorderLayout.PAGE_END);
		 */
	}

	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if (buttonName.equals("예약 관리")) {
			GUIMain.createAndShowTableGUI(re);
		} else if (buttonName.equals("대기자 관리")) {
			GUIMain.createAndShowTableGUI(we);
		} else if (buttonName.equals("Pos-Counter")) {
			String pw = JOptionPane.showInputDialog(PosMainDisplay.this, "관리자 번호를 입력해주세요.");
			if (pw == null)
				return;
			else if (pw.equals(PASSWORD))
				createAndShowPOSManage();
			else if (!pw.equals(PASSWORD))
				JOptionPane.showMessageDialog(PosMainDisplay.this, "관리자번호가 틀렸습니다.");
		} else if (buttonName.equals("판매현황")) {
			new SalesStatus();
		} else if (buttonName.equals("정산")) {
			JOptionPane.showMessageDialog(PosMainDisplay.this, "총 매출액:" + OrderDisplay.dailyTotal);
			int result = JOptionPane.showConfirmDialog(PosMainDisplay.this, "정산파일을 저장하시겠습니까?", "정산메뉴",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				try {
					OrderDisplay.settleMenu();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				JOptionPane.showMessageDialog(PosMainDisplay.this, "정산파일 저장완료!");
			} else if (result == JOptionPane.NO_OPTION) {
				return;
			}
		} else if (buttonName.equals("파일")) {
			new OrderManageDisplay();
		} else if (buttonName.equals("장사 종료")) {
			int result = JOptionPane.showConfirmDialog(PosMainDisplay.this, "장사를 종료할까요?!", "종료창",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(PosMainDisplay.this, "장사를 종료합니다!!!");
				System.exit(0);
			} else if (result == JOptionPane.NO_OPTION) { // 아니오
				return;
			}
		}
	}

	void setTableButton(JButton b, int i) {
		b.setText(i + "번");
		b.setPreferredSize(new Dimension(100, 100));
		b.setFont(basic);
		b.setBackground(new Color(165, 165, 165));
	}

	JButton loadButton(int num) {
		switch (num) {
			case 1:
				return table_b1;
			case 2:
				return table_b2;
			case 3:
				return table_b3;
			case 4:
				return table_b4;
			case 5:
				return table_b5;
			case 6:
				return table_b6;
			case 7:
				return table_b7;
			case 8:
				return table_b8;
			case 9:
				return table_b9;
			case 10:
				return table_b10;
			default:
				return null;
		}
	}
}