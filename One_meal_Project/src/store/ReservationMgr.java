package store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import facade.DataEngineInterface;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class ReservationMgr implements DataEngineInterface {
	private static final String[] labels = { "시간", "이름", "전화번호", "인원" };
	private Manager mgr = new Manager();
	public static int t_reservation = 0;

	@Override
	public int getColumnCount() {
		return labels.length;
	}

	// 테이블의 열 제목을 스트링 배열로 돌려줌
	public String[] getColumnNames() {
		return labels;
	}

	Comparator<Manageable> comparator = new Comparator<Manageable>() {
		@Override
		public int compare(Manageable x, Manageable y) {
			return ((ReservedCustomer) x).time.compareTo(((ReservedCustomer) y).time);
		}
	};

	// GUI에서 호출할 메소드들, DataEngineInterface의 구현 메소드들
	@Override
	public void readAll(String filename) {
		mgr.readAll(filename, new Factory() {
			public Manageable create() {
				return new ReservedCustomer();
			}
		});
		Collections.sort(mgr.mList, comparator);
		for (Manageable m : mgr.mList)
			t_reservation += ((ReservedCustomer) m).head;
	}

	@Override
	public List<Manageable> search(String kwd) {
		if (kwd == null)
			return mgr.mList;
		return mgr.findAll(kwd);
	}

	@Override
	public void addNewItem(String[] editTexts) {
		ReservedCustomer s = new ReservedCustomer();
		s.set(editTexts);
		mgr.mList.add(s);
		t_reservation += s.head;
		Collections.sort(mgr.mList, comparator);
	}

	@Override
	public void update(String[] editTexts) {
		ReservedCustomer s = (ReservedCustomer) mgr.find(editTexts[2]);
		s.set(editTexts);
	}

	@Override
	public void remove(String kwd) {
		ReservedCustomer s = (ReservedCustomer) mgr.find(kwd);
		t_reservation -= s.head;
		mgr.mList.remove(s);
	}
}