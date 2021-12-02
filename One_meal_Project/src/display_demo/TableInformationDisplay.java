package display_demo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TableInformationDisplay extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Font small = new Font("소돋음", Font.BOLD, 15);
	JLabel total;
	public static Vector<Vector<String>> data;
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Table-Data");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		TableInformationDisplay right = new TableInformationDisplay();
		right.setControlPane();
		frame.add(right);
		
		frame.setSize(500, 400);
		frame.setVisible(true);
	}
	
	void setControlPane() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		TableInformationDisplay data = new TableInformationDisplay();
		data.setDataPane();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		add(data, gbc);
		
		TableInformationDisplay table = new TableInformationDisplay();
		table.setDataTable();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		add(table, gbc);
		
		JButton order = new JButton("주문 입력");
		order.setFont(small);
		order.setPreferredSize(new Dimension(120, 40));
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(order, gbc);
		
		JButton reserve = new JButton("예약석 지정");
		reserve.setFont(small);
		reserve.setPreferredSize(new Dimension(120, 40));
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(reserve, gbc);
		
		JButton waiter_set = new JButton("대기 호출");
		waiter_set.setFont(small);
		waiter_set.setPreferredSize(new Dimension(120, 40));
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(waiter_set, gbc);
		
		JButton purchase = new JButton("계산");
		purchase.setFont(small);
		purchase.setPreferredSize(new Dimension(120, 40));
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(purchase, gbc);
		
		order.addActionListener(this);
		reserve.addActionListener(this);
		waiter_set.addActionListener(this);
		purchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader fr = new BufferedReader(new FileReader("customer.txt"));
					FileWriter fw = new FileWriter("customer.txt");
					fw.write("");
					fw.flush();
					fw.close();
					fr.close();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OrderDisplay();
			}
		});
	}

	void setDataPane() {
		setLayout(new BorderLayout(0, 5));
		
		JPanel layer1 = new JPanel();
		layer1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel name = new JLabel("이름");
		JTextField nameTextField = new JTextField("", 15);
		layer1.add(name);
		layer1.add(nameTextField);
		add(layer1, BorderLayout.PAGE_START);
		
		JPanel layer2 = new JPanel();
		layer2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel phone = new JLabel("전화번호");
		JTextField phoneTextField = new JTextField("", 15);
		layer2.add(phone);
		layer2.add(phoneTextField);
		add(layer2, BorderLayout.CENTER);
		
		JPanel layer3 = new JPanel();
		layer3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel heads = new JLabel("인원");
		JTextField headsTextField = new JTextField("", 15);
		JButton insert = new JButton("입력");
		layer3.add(heads);
		layer3.add(headsTextField);
		layer3.add(insert);
		add(layer3, BorderLayout.PAGE_END);
		
		insert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					FileWriter fw = new FileWriter("customer.txt",true);
					BufferedWriter bf = new BufferedWriter(fw);
					bf.write(nameTextField.getText()+" ");
					bf.write(phoneTextField.getText()+" ");
					bf.write(headsTextField.getText()+"\n");
					bf.close();
					nameTextField.setText("");
					phoneTextField.setText("");
					headsTextField.setText("");
					FileReader fr = new FileReader("customer.txt");
					BufferedReader br = new BufferedReader(fr);
					String name = null;
					StringTokenizer tz = null;
					Vector v = new Vector<>();
					while((name = br.readLine())!=null){
						tz = new StringTokenizer(name," ");
						if(name.startsWith(nameTextField.getText())){
							while(tz.hasMoreElements()){
								v.add(tz.nextElement());
							}
							nameTextField.setText((String)v.get(0));
							phoneTextField.setText((String)v.get(1));
							headsTextField.setText((String)v.get(2));
						}
					}br.close();
				}catch(Exception n){
					System.out.println(n);
				}
			}
		});
	}
	
	void setDataTable() {
		setLayout(new BorderLayout(0, 5));
		Vector<String> header = new Vector<>();
		header.add("메뉴명");
		header.add("수량");
		header.add("가격");

		DefaultTableModel tableModel = new DefaultTableModel(data, header);
		JTable table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(250, 150));
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(250, 150));
		add(scrollpane, BorderLayout.PAGE_START);
		
		total = new JLabel("총액 : 00000원");
		total.setHorizontalAlignment(JLabel.RIGHT);
		total.setFont(small);
		add(total, BorderLayout.EAST);

		JButton refreshBtn = new JButton("주문내역 불러오기");
		refreshBtn.setHorizontalAlignment(JLabel.LEFT);
		add(refreshBtn, BorderLayout.WEST);
		refreshBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();
		if(buttonName.equals("주문 입력")){
			new OrderDisplay();
		}else if(buttonName.equals("주문내역 불러오기")){
			setDataTable();
		}else if(buttonName.equals("계산")){

		}
	}
}