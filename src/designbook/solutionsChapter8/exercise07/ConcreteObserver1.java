package designbook.solutionsChapter8.exercise07;

import java.util.Iterator;

import designbook.chapter6.Show;
import designbook.solutionsChapter8.exercise07.ObservableProgram.Day;

/**
 * Addition observer: mỗi khi có show mới, kéo dữ liệu và in lại lịch
 * các ngày đã có show.
 */
public class ConcreteObserver1 implements ProgramAdditionObserver {

	@Override
	public void showAdded(Iterable<Show> pProgram) {
		System.out.println("[Observer1] Lich hien tai:");
		Iterator<Show> shows = pProgram.iterator();
		for (Day day : Day.values()) { // thứ tự duyệt khớp thứ tự Day
			Show show = shows.next();
			if (!show.isNull()) {
				System.out.println(String.format("   %9s: %s", day, show.description()));
			}
		}
		// pProgram.add(...);  // KHONG bien dich duoc: Iterable<Show> khong co add()
	}
}
