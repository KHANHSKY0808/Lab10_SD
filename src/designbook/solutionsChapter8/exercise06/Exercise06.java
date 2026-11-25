package designbook.solutionsChapter8.exercise06;

import designbook.chapter6.Concert;
import designbook.chapter6.Movie;
import designbook.solutionsChapter8.exercise06.ObservableProgram.Day;

/**
 * Exercise 8.6: Observer cho Program, chiến lược PUSH, một interface observer.
 */
public class Exercise06 {

	public static void main(String[] args) {
		ObservableProgram program = new ObservableProgram();
		program.addObserver(new ConcreteProgramObserver());

		System.out.println("== Them 3 show ==");
		program.add(new Movie("Metropolis", 1927, 153), Day.MONDAY);
		program.add(new Concert("Symphonie fantastique", "OSM", 50), Day.WEDNESDAY);
		program.add(new Movie("Nosferatu", 1922, 94), Day.FRIDAY);

		System.out.println("== Go show thu Hai ==");
		program.remove(Day.MONDAY);

		System.out.println("== Go show thu Ba (ngay trong, khong co thong bao) ==");
		program.remove(Day.TUESDAY);

		System.out.println("== Chuong trinh hien tai ==");
		System.out.print(program);
		System.out.println("TUESDAY la null show? " + program.get(Day.TUESDAY).isNull());

		System.out.println("== Xoa toan bo ==");
		program.clear();
	}
}
