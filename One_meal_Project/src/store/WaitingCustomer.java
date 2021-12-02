package store;

import java.util.Scanner;

import facade.UIData;

public class WaitingCustomer extends Customer implements UIData {
	int num = 0;

	@Override
	public void read(Scanner scan) {
		super.read(scan);
		num = scan.nextInt();
	}
	
	@Override
	public void print() {
		super.print();
		System.out.printf("순서: %d\n", num);
	}

	@Override
	public boolean matches(String kwd) {
		if (super.matches(kwd))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", name, phone);
	}
	
	@Override
	public String[] getUiTexts() {
		return new String[] {name, phone, ""+head, ""+num};
	}
	
	@Override
	public void set(Object[] row) {
		name = (String)row[0];
		phone = (String)row[1];
		head = Integer.parseInt((String)row[2]);
		num = Integer.parseInt((String)row[3]);
	}
}