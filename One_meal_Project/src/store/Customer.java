package store;

import java.util.Scanner;

import mgr.Manageable;

public class Customer implements Manageable{
	String name;
	String phone;
	int head;

	@Override
	public void read(Scanner scan) {
		name = scan.next();
		phone = scan.next();
		head = scan.nextInt();
	}

	@Override
	public void print() {
		System.out.printf("성명: %s\t전화번호: %s\t인원: %d\n", name, phone, head);
	}

	@Override
	public boolean matches(String kwd) {
		if (name.contentEquals(kwd))
			return true;
		if (phone.contentEquals(kwd))
			return true;
		return false;
	}
}