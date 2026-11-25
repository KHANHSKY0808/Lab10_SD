package designbook.solutions_Chapter8.exercise07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

import designbook.chapter6.Show;

/**
 * Chương trình biểu diễn có thể quan sát, chiến lược PULL với HAI interface
 * observer: một cho sự kiện thêm, một cho sự kiện gỡ/xóa.
 *
 * Lớp hiện thực Iterable<Show> và truyền chính nó (this) cho observer dưới
 * kiểu Iterable<Show>. Duyệt chương trình luôn cho đúng 7 show theo thứ tự
 * MONDAY..SUNDAY (ngày trống là null show), nên observer suy ra được ngày
 * từ vị trí.
 */
public final class ObservableProgram implements Iterable<Show> {

	public enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	private static final Show NULL_SHOW = createNullShow();

	private final EnumMap<Day, Show> aShows = new EnumMap<>(Day.class);
	private final List<ProgramAdditionObserver> aAdditionObservers = new ArrayList<>();
	private final List<ProgramRemovalObserver> aRemovalObservers = new ArrayList<>();

	public ObservableProgram() {
		clear();
	}

	/**
	 * @pre pObserver != null
	 */
	public void addObserver(ProgramAdditionObserver pObserver) {
		assert pObserver != null;
		aAdditionObservers.add(pObserver);
	}

	/**
	 * @pre pObserver != null
	 */
	public void addObserver(ProgramRemovalObserver pObserver) {
		assert pObserver != null;
		aRemovalObservers.add(pObserver);
	}

	/**
	 * Xóa toàn bộ chương trình, rồi thông báo cho các removal observer.
	 */
	public void clear() {
		for (Day day : Day.values()) {
			aShows.put(day, NULL_SHOW);
		}
		for (ProgramRemovalObserver observer : aRemovalObservers) {
			observer.programCleared();
		}
	}

	/**
	 * Thêm một show (ghi đè show cũ cùng ngày), rồi thông báo cho các
	 * addition observer. Observer tự kéo dữ liệu qua Iterable<Show>.
	 */
	public void add(Show pShow, Day pDay) {
		assert pShow != null && pDay != null;
		aShows.put(pDay, pShow);
		for (ProgramAdditionObserver observer : aAdditionObservers) {
			observer.showAdded(this);
		}
	}

	/**
	 * Gỡ show của một ngày (thay bằng null show), rồi thông báo cho các
	 * removal observer. Ngày vốn trống thì không thông báo.
	 */
	public void remove(Day pDay) {
		assert pDay != null;
		Show removed = aShows.put(pDay, NULL_SHOW);
		if (removed.isNull()) {
			return;
		}
		for (ProgramRemovalObserver observer : aRemovalObservers) {
			observer.showRemoved(this);
		}
	}

	/**
	 * @return Bản sao của show trong ngày pDay (null show nếu không có).
	 */
	public Show get(Day pDay) {
		assert pDay != null;
		return aShows.get(pDay).copy();
	}

	/**
	 * Duyệt 7 show theo thứ tự MONDAY..SUNDAY. Iterator trả về BẢN SAO và
	 * không hỗ trợ remove(), nên không thể dùng nó để sửa chương trình.
	 */
	@Override
	public Iterator<Show> iterator() {
		List<Show> copies = new ArrayList<>();
		for (Show show : aShows.values()) { // EnumMap duyệt theo thứ tự khai báo Day
			copies.add(show.copy());
		}
		return Collections.unmodifiableList(copies).iterator();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Day day : aShows.keySet()) {
			result.append(String.format("%9s", day.name()))
				.append(": ")
				.append(aShows.get(day).description())
				.append("\n");
		}
		return result.toString();
	}

	private static Show createNullShow() {
		return new Show() {
			@Override
			public String description() {
				return "";
			}

			@Override
			public int runningTime() {
				return 0;
			}

			@Override
			public Show copy() {
				return createNullShow();
			}

			@Override
			public boolean isNull() {
				return true;
			}

			@Override
			public boolean equals(Object pObject) {
				return pObject != null && pObject.getClass() == getClass();
			}

			@Override
			public int hashCode() {
				return 0;
			}
		};
	}
}
