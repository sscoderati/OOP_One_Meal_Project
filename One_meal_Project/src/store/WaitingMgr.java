package store;

import java.util.List;
import facade.DataEngineInterface;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class WaitingMgr implements DataEngineInterface {
	private static final String[] labels = {"이름", "전화번호", "인원", "순서"};
	private Manager mgr = new Manager();
	public static int t_waiting = 0;
	
	@Override
	public int getColumnCount() {
		return labels.length;
	}
	
	// 테이블의 열 제목을 스트링 배열로 돌려줌
	public String[] getColumnNames() {		
		return labels;
	}
	
	// GUI에서 호출할 메소드들, DataEngineInterface의 구현 메소드들
	@Override
	public void readAll(String filename) {
		mgr.readAll(filename, new Factory() {
			public Manageable create() {
				return new WaitingCustomer();
			}
		});
		for (Manageable m : mgr.mList) {
			t_waiting += ((WaitingCustomer)m).head;
		}
	}
	
	@Override
	public List<Manageable> search(String kwd) {
		if (kwd == null)
			return mgr.mList;
		return mgr.findAll(kwd);
	}
	
	@Override
	public void update(String[] editTexts) {
		WaitingCustomer w = (WaitingCustomer)mgr.find(editTexts[2]);
		w.set(editTexts);
	}
	
	@Override
	public void remove(String kwd) {
		WaitingCustomer w = (WaitingCustomer)mgr.find(kwd);
		t_waiting -= w.head;;
		mgr.mList.remove(w);
	}

	@Override
	public void addNewItem(String[] uiTexts) {
		// TODO Auto-generated method stub
		
	}
}