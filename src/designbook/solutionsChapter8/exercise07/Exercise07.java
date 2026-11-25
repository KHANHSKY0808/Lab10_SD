package designbook.solutionsChapter8.exercise07;

import designbook.chapter6.Concert;
import designbook.chapter6.Movie;
import designbook.solutionsChapter8.exercise07.ObservableProgram.Day;

/**
 * Exercise 8.7: Observer cho Program, chiến lược PULL, hai interface observer
 * và Interface Segregation (observer chỉ thấy Iterable<Show>).
 */
public class Exercise07 {

	public static void main(String[] args) {
		ObservableProgram program = new ObservableProgram();
		program.addObserver(new ConcreteObserver1());
		program.addObserver(new ConcreteObserver2());

		// ConcreteObserver3 hien thuc CA HAI interface, nen loi goi
		// program.addObserver(observer3) bi mo ho (ambiguous) giua hai phien ban
		// nap chong. Phai ep kieu de chon dung danh sach, va dang ky hai lan.
		ConcreteObserver3 observer3 = new ConcreteObserver3();
		program.addObserver((ProgramAdditionObserver) observer3);
		program.addObserver((ProgramRemovalObserver) observer3);

		System.out.println("== Them 2 show ==");
		program.add(new Movie("Metropolis", 1927, 153), Day.MONDAY);
		program.add(new Concert("Symphonie fantastique", "OSM", 50), Day.WEDNESDAY);

		System.out.println("== Go show thu Hai ==");
		program.remove(Day.MONDAY);

		System.out.println("== Xoa toan bo ==");
		program.clear();
	}
}
