package designbook.solutions_Chapter8.exercise07;

import designbook.chapter6.Show;

/**
 * Observer cho sự kiện THÊM show, chiến lược PULL.
 *
 * Tham số chỉ có kiểu Iterable<Show> (Interface Segregation): observer
 * duyệt được các show của chương trình nhưng không tham chiếu được tới
 * add/remove/clear, nên không thể làm thay đổi chương trình.
 */
public interface ProgramAdditionObserver {

	/**
	 * @param pProgram Các show của chương trình sau khi thêm, đúng 7 phần tử
	 *                 theo thứ tự MONDAY..SUNDAY (ngày trống là null show).
	 */
	void showAdded(Iterable<Show> pProgram);
}
