/*
package store;

import java.util.ArrayList;
import java.util.Scanner;

import facade.UIData;

public class InstantCustomer extends Customer implements UIData {
	ArrayList<Menu> orderList = new ArrayList<>();
	ArrayList<Integer> itemCountList = new ArrayList<>();
	int total = 0;

	@Override
	public void read(Scanner scan) {
		super.read(scan);
		Menu item = null;
		int count = 0;
		String code = null;
		while (true) {
			code = scan.next();
			if (code.contentEquals("0"))
				break;
			item = (Menu)Store.menuMgr.find(code);
			if (item == null) {
				System.out.println("해당 코드 상품 없음: " + code);
				scan.nextInt();
				continue;
			}
			orderList.add(item);
			count = scan.nextInt();
			itemCountList.add(count);
			total += item.price * count;	
		}
	}
	
	@Override
	public void print() {
		super.print();
		System.out.printf("금액: %s\n", total);
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", name, phone);
	}
	
	@Override
	public String[] getUiTexts() {
		return new String[] {name, phone, ""+head, ""+total};
	}
	
	@Override
	public void set(Object[] row) {
		name = (String)row[0];
		phone = (String)row[1];
		head = Integer.parseInt((String)row[2]);
		total = Integer.parseInt((String)row[3]);
	}
}*/