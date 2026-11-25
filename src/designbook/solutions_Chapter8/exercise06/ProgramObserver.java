package designbook.solutions_Chapter8.exercise06;

import designbook.chapter6.Show;
import designbook.solutions_Chapter8.exercise06.ObservableProgram.Day;

/**
 * Observer trừu tượng DUY NHẤT của ObservableProgram, chiến lược PUSH:
 * chương trình đẩy sẵn show và ngày liên quan qua tham số callback.
 */
public interface ProgramObserver {

	/**
	 * Được gọi sau khi một show được thêm vào chương trình.
	 *
	 * @param pShow Show vừa thêm.
	 * @param pDay  Ngày diễn ra show.
	 */
	void showAdded(Show pShow, Day pDay);

	/**
	 * Được gọi sau khi một show bị gỡ khỏi chương trình.
	 *
	 * @param pShow Show vừa bị gỡ.
	 * @param pDay  Ngày của show bị gỡ.
	 */
	void showRemoved(Show pShow, Day pDay);

	/**
	 * Được gọi sau khi toàn bộ chương trình bị xóa.
	 */
	void programCleared();
}
