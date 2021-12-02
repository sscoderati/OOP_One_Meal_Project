package store;

import java.util.Scanner;

import facade.UIData;

public class ReservedCustomer extends Customer implements UIData {
	String time;

	@Override
	public void read(Scanner scan) {
		super.read(scan);
		time = scan.next();
	}

	@Override
	public void print() {
		super.print();
		System.out.printf("예약시간: %s\n", time);
	}

	@Override
	public boolean matches(String kwd) {
		if (super.matches(kwd))
			return true;
		if (kwd.length() > 3 && time.contains(kwd))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", name, time);
	}

	@Override
	public String[] getUiTexts() {
		return new String[] { time, name, phone, "" + head };
	}

	@Override
	public void set(Object[] row) {
		time = (String) row[0];
		name = (String) row[1];
		phone = (String) row[2];
		head = Integer.parseInt((String) row[3]);
	}
}