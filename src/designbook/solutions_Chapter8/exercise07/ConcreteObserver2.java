package designbook.solutions_Chapter8.exercise07;

import designbook.chapter6.Show;

/**
 * Removal observer: sau mỗi lần gỡ, kéo dữ liệu và in số ngày còn show.
 */
public class ConcreteObserver2 implements ProgramRemovalObserver {

	@Override
	public void showRemoved(Iterable<Show> pProgram) {
		int count = 0;
		for (Show show : pProgram) {
			if (!show.isNull()) {
				count++;
			}
		}
		System.out.println("[Observer2] Da go mot show, con lai " + count + " ngay co show");
	}

	@Override
	public void programCleared() {
		System.out.println("[Observer2] Chuong trinh da bi xoa toan bo");
	}
}
