package display_demo;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import facade.DataEngineInterface;
import facade.UIData;

public class TableController implements ListSelectionListener {
	DefaultTableModel tableModel;
	JTable table;
	int selectedIndex = -1;
	DataEngineInterface dataMgr;

	@SuppressWarnings("serial")
	void init(DataEngineInterface en) {
		dataMgr = en;
		tableModel = new DefaultTableModel(dataMgr.getColumnNames(), 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		loadData(null);

		table = new JTable(tableModel);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	void loadData(String kwd) {
		List<?> result = dataMgr.search(kwd);
		tableModel.setRowCount(0);
		for (Object m : result)
			tableModel.addRow(((UIData) m).getUiTexts());
	}

	void addRow(String[] editTexts) {
		try {
			dataMgr.addNewItem(editTexts);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가 데이터 오류");
			return;
		}
		tableModel.addRow(editTexts);
		loadData(null);
	}

	void removeRow() {
		if (selectedIndex < 0)
			return;
		String key = (String) tableModel.getValueAt(selectedIndex, 0);
		dataMgr.remove(key);
		tableModel.removeRow(selectedIndex);
	}

	// 현재 선택된 행을 편집창의 내용으로 수정
	void updateRow(String[] editTexts) {
		if (selectedIndex < 0)
			return;
		try {
			dataMgr.update(editTexts);
		} catch (Exception ex) { // SongMgr에서 수정 중 오류 발생
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정 중 데이터 오류");
			return;
		}
		for (int i = 0; i < editTexts.length; i++) {
			tableModel.setValueAt(editTexts[i], selectedIndex, i);
		}
	}

	// 선택된 행이 변경되면 그 내용을 편집창으로 보냄
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			String[] rowTexts = new String[tableModel.getColumnCount()];
			for (int i = 0; i < rowTexts.length; i++)
				rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
			TableSelectionDemo.bottom.moveSelectedToEdits(rowTexts);
		}
	}
}