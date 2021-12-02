package store;

import display_demo.GUIMain;
import mgr.Factory;
import mgr.Manager;

public class Store {
	public static final int t_seats = 20;
	public static int seatsLeft;
	public static int income = 0;
	
	static Manager menuMgr = new Manager();

	public void run(){
		ReservationMgr reservationEngine = new ReservationMgr();
		WaitingMgr waitingEngine = new WaitingMgr();
		
		menuMgr.readAll("menus.txt", new Factory() {
			public Menu create(){
				return new Menu();
			}
		});
		reservationEngine.readAll("reservation.txt");
		waitingEngine.readAll("waiting.txt");
		GUIMain.startGUI(reservationEngine, waitingEngine);
	}
	
	public static void main(String args[]) {
		Store store = new Store();
		store.run();
	}
}