package designbook.chapter6;

/**
 * Represents a performance that can take place in a theater. All
 * implementations of this interface should be immutable.
 */
public interface Show {

	/**
	 * @return A description of the show.
	 */
	String description();

	/**
	 * @return The running time of the show, in minutes.
	 */
	int runningTime();

	Show copy();

	/**
	 * BỔ SUNG (Exercise 6.13): cho phép client biết một show có phải là
	 * "null show" (ngày không có suất diễn) hay không. Mặc định là false;
	 * chỉ null show ghi đè để trả về true.
	 *
	 * @return true nếu đây là null show.
	 */
	default boolean isNull() {
		return false;
	}
}
