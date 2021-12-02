package display_demo;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class OrderManageDisplay extends JFrame {
	JLabel fList = new JLabel("파일목록");
	JLabel fList2 = new JLabel("파일내용");
	List list = new List();
	JButton bt = new JButton("▶");
	JButton del = new JButton("내용지우기");
	JButton fileDel = new JButton("파일삭제");
	JTextArea fArea = new JTextArea();
	JScrollPane sc = new JScrollPane(fArea);

	public OrderManageDisplay() {
		super("정산파일 리스트");
		setLayout(null);
		fList.setBounds(30, 20, 70, 30);
		fList2.setBounds(250, 20, 70, 30);
		list.setBounds(30, 50, 150, 400);
		bt.setBounds(190, 230, 50, 50);
		del.setBounds(430, 15, 100, 30);
		fileDel.setBounds(90, 15, 90, 30);// 이거
		fArea.setBounds(250, 50, 280, 400);
		sc.setBounds(250, 50, 280, 400);
		add(sc);
		add(fList);
		add(fList2);
		add(list);
		add(bt);
		add(del);
		add(fileDel);
		File f = new File("./");
		if (f.isDirectory()) {
			File[] fileList = f.listFiles();
			for (File tFile : fileList) {
				String filename = tFile.getName();
				if(filename.substring(filename.lastIndexOf(".") + 1).equals("txt"))
					list.add(filename);
			}
		}
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedReader br;
				if (e.getSource() == bt) { // 화살표버튼 누르면 파일내용 보여주기
					try {
						br = new BufferedReader(new FileReader("./" + list.getSelectedItem()));
						while (true) { 
							String a = br.readLine();
							if (a == null)
								break;
							fArea.append(a + "\n");
						}
						br.close();
					} catch (IOException ie) {
						System.out.println(ie.getMessage());
					}
				} else if (e.getSource() == del) { // 텍스트 내용 지우기
					fArea.setText("");
				} else if (e.getSource() == fileDel) { // 선택한 파일 지우기
					File f=new File("./"+ list.getSelectedItem());
					f.delete();
					list.remove(list.getSelectedItem());
					JOptionPane.showMessageDialog(OrderManageDisplay.this, "파일 삭제가 완료되었습니다.");
				}
			}
		};
		bt.addActionListener(listener);
		del.addActionListener(listener);
		fileDel.addActionListener(listener);
	}
}
