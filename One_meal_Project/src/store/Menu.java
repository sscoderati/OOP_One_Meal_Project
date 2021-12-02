package store;

import java.util.Scanner;

import mgr.Manageable;

public class Menu implements Manageable{
	String code;
	String name;
	int price;

	@Override
	public void read(Scanner scan) {
		code = scan.next();
		name = scan.next();
		price = scan.nextInt();
	}
	
	@Override
	public void print() {
		System.out.printf("메뉴명: %s\t금액: %d\n", name, price);
	}

	@Override
	public boolean matches(String kwd) {
		if(code.contentEquals(kwd))
			return true;
		if(name.contains(kwd))
			return true;
		if((""+price).equals(kwd))
			return true;
		return false;
	}

	/*public int getSubtotal(int count) {
		return count * price;
	}*/
}