package designbook.solutions8.exercise07;

import designbook.chapter6.Show;

/**
 * Observer quan tâm CẢ HAI loại sự kiện nên hiện thực cả hai interface:
 * in tổng thời lượng của chương trình sau mỗi thay đổi.
 */
public class ConcreteObserver3 implements ProgramAdditionObserver, ProgramRemovalObserver {

	@Override
	public void showAdded(Iterable<Show> pProgram) {
		printTotal(pProgram);
	}

	@Override
	public void showRemoved(Iterable<Show> pProgram) {
		printTotal(pProgram);
	}

	@Override
	public void programCleared() {
		System.out.println("[Observer3] Tong thoi luong: 0 phut");
	}

	private static void printTotal(Iterable<Show> pProgram) {
		int total = 0;
		for (Show show : pProgram) {
			total += show.runningTime(); // null show co thoi luong 0
		}
		System.out.println("[Observer3] Tong thoi luong: " + total + " phut");
	}
}
