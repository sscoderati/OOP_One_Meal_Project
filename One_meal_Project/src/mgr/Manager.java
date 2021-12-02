package mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
	public ArrayList<Manageable> mList = new ArrayList<>();

	public Manageable find(String kwd) {
		for (Manageable m : mList)
			if (m.matches(kwd))
				return m;
		return null;
	}

	public List<Manageable> findAll(String kwd) {
		List<Manageable> results = new ArrayList<>();
		for (Manageable m : mList)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}
	
	public void readAll(String filename, Factory fac) {
		Scanner filein = openFile(filename);
		Manageable m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println(filename + ": 파일 입력 오류");
			System.exit(0);
		}
		return filein;
	}

	void printAll() {
		for (Manageable m : mList) {
			m.print();
		}
	}

	void search(Scanner scan) {
		String kwd = null;
		while (true) {
			System.out.print(">>");
			kwd = scan.next();
			if (kwd.equals("end"))
				break;
			for (Manageable m : mList) {
				if (m.matches(kwd))
					m.print();
			}
		}
	}
}