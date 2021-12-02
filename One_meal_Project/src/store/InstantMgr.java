/*
package store;

import java.util.List;
import facade.DataEngineInterface;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class InstantMgr implements DataEngineInterface {
	private static final String[] labels = {"이름", "전화번호", "인원", "금액"};
	private Manager mgr = new Manager();
	public static int t_instant = 0;
	public static int sum_total = 0;
	
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
				return new InstantCustomer();
			}
		});
		for (Manageable m : mgr.mList) {
			t_instant += ((InstantCustomer)m).head;
			sum_total += ((InstantCustomer)m).total;
		}
	}
	
	@Override
	public List<Manageable> search(String kwd) {
		if (kwd == null)
			return mgr.mList;
		return mgr.findAll(kwd);
	}
	
	@Override
	public void addNewItem(String[] editTexts) {
		InstantCustomer s = new InstantCustomer();
		s.set(editTexts);
		mgr.mList.add(s);
		t_instant += s.head;
		sum_total += s.total;
	}
	
	@Override
	public void update(String[] editTexts) {
		InstantCustomer s = (InstantCustomer)mgr.find(editTexts[2]);
		s.set(editTexts);
	}
	
	@Override
	public void remove(String kwd) {
		InstantCustomer s = (InstantCustomer)mgr.find(kwd);
		t_instant -= s.head;
		sum_total -= s.total;
		mgr.mList.remove(s);
	}
}*/