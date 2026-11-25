package designbook.solutionsChapter8.exercise07;

import designbook.chapter6.Show;

/**
 * Observer cho sự kiện GỠ show và XÓA chương trình, chiến lược PULL.
 */
public interface ProgramRemovalObserver {

	/**
	 * @param pProgram Các show của chương trình sau khi gỡ (7 phần tử,
	 *                 thứ tự MONDAY..SUNDAY).
	 */
	void showRemoved(Iterable<Show> pProgram);

	/**
	 * Được gọi sau khi toàn bộ chương trình bị xóa.
	 */
	void programCleared();
}
