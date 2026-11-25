package designbook.solutionsChapter8.exercise06;

import designbook.chapter6.Show;
import designbook.solutionsChapter8.exercise06.ObservableProgram.Day;

/**
 * Observer cụ thể "giả" (dummy) để kiểm tra việc áp dụng mẫu: chỉ in ra
 * dữ liệu mà chương trình đẩy tới.
 */
public class ConcreteProgramObserver implements ProgramObserver {

	@Override
	public void showAdded(Show pShow, Day pDay) {
		System.out.println("[Observer] Them vao " + pDay + ": " + pShow.description()
				+ " (" + pShow.runningTime() + " phut)");
	}

	@Override
	public void showRemoved(Show pShow, Day pDay) {
		System.out.println("[Observer] Go khoi " + pDay + ": " + pShow.description());
	}

	@Override
	public void programCleared() {
		System.out.println("[Observer] Chuong trinh da bi xoa toan bo");
	}
}
