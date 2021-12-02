package display_demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import facade.DataEngineInterface;

public class TableSelectionDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	static TableController tableController;
	static BottomPane bottom;
	DataEngineInterface engine;

	public TableSelectionDemo(DataEngineInterface en) {
		super(new BorderLayout());
		engine = en;
	}

	void addComponentsToPane() {
		tableController = new TableController();
		tableController.init(engine);
		JScrollPane center = new JScrollPane(tableController.table);
		add(center, BorderLayout.CENTER);

		bottom = new BottomPane();
		bottom.init(engine.getColumnCount());
		add(bottom, BorderLayout.PAGE_END);

		setupTopPane();
	}

	void setupTopPane() {
		JPanel topPane = new JPanel();
		JTextField kwdTextField = new JTextField("", 20);
		topPane.add(kwdTextField, BorderLayout.LINE_START);
		JButton search = new JButton("검색");
		topPane.add(search, BorderLayout.LINE_END);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {
					tableController.loadData(kwdTextField.getText());
				}
			}
		});
		add(topPane, BorderLayout.PAGE_START);
	}
}