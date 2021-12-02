package display_demo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class OrderDisplay extends JFrame {
	// 여기서부터 모양임
	JLabel main = new JLabel(new ImageIcon("menu.png"));
	JLabel menu1Name = new JLabel("제육볶음");
	JLabel menu1 = new JLabel(new ImageIcon("ze6.png"));// 5000원
	JLabel menu1c = new JLabel("수량");
	JTextField cont1 = new JTextField(3);
	JButton bt1 = new JButton("담기");
	JLabel menu2Name = new JLabel("파불고기");
	JLabel menu2 = new JLabel(new ImageIcon("bulgogi.png"));
	JLabel menu2c = new JLabel("수량");
	JTextField cont2 = new JTextField(3);
	JButton bt2 = new JButton("담기");
	JLabel menu3Name = new JLabel("대패삼겹살");
	JLabel menu3 = new JLabel(new ImageIcon("daepae.png"));
	JLabel menu3c = new JLabel("수량");
	JTextField cont3 = new JTextField(3);
	JButton bt3 = new JButton("담기");
	JLabel menu4Name = new JLabel("음료");
	JLabel menu4 = new JLabel(new ImageIcon("drink.png"));
	JLabel menu4c = new JLabel("수량");
	JTextField cont4 = new JTextField(3);
	JButton bt4 = new JButton("담기");
	JLabel box = new JLabel(new ImageIcon("selec.png"));
	JTable list;
	JScrollPane jsp;
	Vector<String> colummNames;
	Vector<Vector<String>> rowData;
	Vector<String> v;
	JButton del = new JButton("취소");
	JButton confirm = new JButton("주문확인");
	// 정산금액 구하기
	public static int dailyTotal = 0; // 총매출금액
	int orderTotal = 0; // 손님주문금액
	// 각 메뉴 판매수량 구하기
	static int mm1 = 0;
	static int mm2 = 0;
	static int mm3 = 0;
	static int mm4 = 0;
	int mTot = 0;

	public OrderDisplay() {
		super("주문 화면");
		setLayout(null);
		colummNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		colummNames.add("품명");
		colummNames.add("수량");
		colummNames.add("단가");
		colummNames.add("총액");
		list = new JTable(rowData, colummNames);
		jsp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(50, 595, 270, 150);
		add(jsp);
		add(main).setBounds(120, 10, 300, 80); // 메뉴 글자
		add(menu1).setBounds(50, 100, 170, 170);
		add(menu1Name).setBounds(110, 230, 100, 100);
		add(menu1c).setBounds(50, 250, 100, 100);
		add(cont1).setBounds(100, 285, 50, 30);
		add(bt1).setBounds(155, 285, 60, 30);
		add(menu2).setBounds(330, 100, 170, 170);
		add(menu2Name).setBounds(390, 230, 100, 100);
		add(menu2c).setBounds(330, 250, 100, 100);
		add(cont2).setBounds(380, 285, 50, 30);
		add(bt2).setBounds(435, 285, 60, 30);
		add(menu3).setBounds(50, 330, 170, 170);
		add(menu3Name).setBounds(100, 460, 100, 100);
		add(menu3c).setBounds(50, 480, 100, 100);
		add(cont3).setBounds(100, 515, 50, 30);
		add(bt3).setBounds(155, 515, 60, 30);
		add(menu4).setBounds(330, 330, 170, 170);
		add(menu4Name).setBounds(400, 460, 100, 100);
		add(menu4c).setBounds(330, 480, 100, 100);
		add(cont4).setBounds(380, 515, 50, 30);
		add(bt4).setBounds(435, 515, 60, 30);
		add(box).setBounds(50, 560, 180, 30);// 선택한메뉴
		add(del).setBounds(240, 560, 80, 30);
		add(confirm).setBounds(350, 595, 160, 60);
		setSize(580, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == bt1) { // 1번메뉴(제육,5000원)담기
					v = new Vector<String>();
					if (cont1.getText().equals("")) {
						noCount();
						return;
					}
					try {
						mTot = Integer.parseInt(cont1.getText());
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "숫자만 입력해주세요");
						cont1.setText("");
						return;
					}
					if (mTot <= 0) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "1개 이상의 개수를 입력해주세요.");
						cont1.setText("");
						return;
					}

					int subtotal = menuSum(cont1.getText(), 5000);
					orderTotal += subtotal;

					plusList("제육", cont1.getText(), "5000");
					list.updateUI();
					cont1.setText("");

				} else if (e.getSource() == bt2) {// 2번메뉴(파불고기,8000원)담기
					v = new Vector<String>();
					if (cont2.getText().equals("")) {
						noCount();
						return;
					}
					try {
						mTot = Integer.parseInt(cont2.getText());
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "숫자만 입력해주세요");
						cont2.setText("");
						return;
					}
					if (mTot <= 0) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "1개 이상의 개수를 입력해주세요.");
						cont2.setText("");
						return;
					}
					int subtotal = menuSum(cont2.getText(), 8000);
					orderTotal += subtotal;
					plusList("파불고기", cont2.getText(), "8000");
					list.updateUI();
					cont2.setText("");
				} else if (e.getSource() == bt3) {// 3번메뉴(대패삼겹살,6500원)담기
					v = new Vector<String>();
					if (cont3.getText().equals("")) {
						noCount();
						return;
					}
					try {
						mTot = Integer.parseInt(cont3.getText());
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "숫자만 입력해주세요");
						cont3.setText("");
						return;
					}

					if (mTot <= 0) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "1개 이상의 개수를 입력해주세요.");
						cont3.setText("");
						return;
					}
					int subtotal = menuSum(cont3.getText(), 6500);
					orderTotal += subtotal;
					plusList("대패삼겹살", cont3.getText(), "6500");
					list.updateUI();
					cont3.setText("");
				} else if (e.getSource() == bt4) {// 4번메뉴(음료,2000원)담기
					v = new Vector<String>();
					if (cont4.getText().equals("")) {
						noCount();
						return;
					}
					try {
						mTot = Integer.parseInt(cont4.getText());
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "숫자만 입력해주세요");
						cont4.setText("");
						return;
					}

					if (mTot <= 0) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "1개 이상의 개수를 입력해주세요.");
						cont4.setText("");
						return;
					}
					int subtotal = menuSum(cont4.getText(), 2000);
					orderTotal += subtotal;
					plusList("음료", cont4.getText(), "2000");
					list.updateUI();
					cont4.setText("");
				} else if (e.getSource() == del) {// 취소버튼
					rowData.clear();
					orderTotal = 0;
					list.updateUI();
				} else if (e.getSource() == confirm) { // 주문완료버튼
					if (orderTotal == 0) {
						JOptionPane.showMessageDialog(OrderDisplay.this, "주문할 메뉴를 담아주세요~!");
						return;
					}
					menuCount();
					int result = JOptionPane.showConfirmDialog(OrderDisplay.this, 
							"주문 목록: \n" +
							"제육볶음: " + mm1 + "인분\n" +
							"파불고기: " + mm2 + "인분\n" +
							"대패삼겹살: " + mm3 + "인분\n" +
							"음료: " + mm4 + "개\n" +
							"총 주문금액은 " + orderTotal + "원 입니다.",
							"주문 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						dailyTotal += orderTotal; // 매출 더하기
						JOptionPane.showMessageDialog(OrderDisplay.this, "주문되었습니다!");
						TableInformationDisplay.data = transmitRowData(rowData);
						rowData.removeAllElements();// 메뉴 결정되면 리스트 전체 지우기
						list.updateUI();
						dispose();
					}
					orderTotal = 0;// 손님 결제금액 다시 0으로 초기화함
				}
			}
		};
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		bt3.addActionListener(listener);
		bt4.addActionListener(listener);
		del.addActionListener(listener);
		confirm.addActionListener(listener);
	}
	public static Vector<Vector<String>> transmitRowData(Vector<Vector<String>> v){
		return v;
	}
	public void noCount() { // 숫자입력x창나오는거 메소드
		JOptionPane.showMessageDialog(OrderDisplay.this, "수량을 입력해주세요!!!");
	}

	public void menuCount() { // 각 메뉴 판매수량 구하는 메소드
		for (int i = 0; i < rowData.size(); i++) {
			Vector<String> f = rowData.get(i);
			if (f.get(0).equals("제육")) {
				int b = Integer.parseInt(f.get(1));
				mm1 = +b;
			} else if (f.get(0).equals("파불고기")) {
				int b = Integer.parseInt(f.get(1));
				mm2 = +b;
			} else if (f.get(0).equals("대패삼겹살")) {
				int b = Integer.parseInt(f.get(1));
				mm3 = +b;
			} else if (f.get(0).equals("음료")) {
				int b = Integer.parseInt(f.get(1));
				mm4 = +b;
			}
		}
	}

	public void plusList(String mName, String cM, String p) { // 리스트에 추가하는거 메소드
		v.add(mName);// 메뉴이름
		v.add(cM);// 개수
		v.add(p + "원");// 가격
		int a = Integer.parseInt(cM);
		int b = Integer.parseInt(p);
		int tot1 = a * b;
		String tot2 = Integer.toString(tot1);
		v.add(tot2 + "원");
		rowData.add(v); // 위에 저장된 벡터 rowData에 담기
	}

	public int menuSum(String countM, int pri) {// 메뉴카운트, 각 메뉴가격
		int con = Integer.parseInt(countM);
		int m1 = pri * con;
		return m1;
	}

	public static void settleMenu() {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			String dd = date.format(today); // 날짜
			try {
				File f = new File("./");
				if (f.exists()) { // 파일 오늘날짜로 저장함
					BufferedWriter bw = new BufferedWriter(new FileWriter(f + "정산-" + dd + ".txt"));
					bw.write("총매출 : " + dailyTotal + "원\r\n");
					bw.write("제육 판매수량:" + mm1 + "개\r\n");
					bw.write("파불고기 판매수량:" + mm2 + "개\r\n");
					bw.write("대패삼겹살 판매수량:" + mm3 + "개\r\n");
					bw.write("음료 판매수량:" + mm4 + "개\r\n");
					bw.flush();
					bw.close();
				} else {
					f.mkdirs(); // 파일이 없으면 생성
					BufferedWriter bw = new BufferedWriter(new FileWriter(f + "정산-" + dd + ".txt"));
					bw.write("총매출 : " + dailyTotal + "원\r\n");
					bw.write("제육 판매수량:" + mm1 + "개\r\n");
					bw.write("파불고기 판매수량:" + mm2 + "개\r\n");
					bw.write("대패삼겹살 판매수량:" + mm3 + "개\r\n");
					bw.write("음료 판매수량:" + mm4 + "개\r\n");
					bw.flush();
					bw.close();
				}
			} catch (IOException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}