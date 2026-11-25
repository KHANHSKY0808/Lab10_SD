package designbook.solutionsChapter8.exercise06;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import designbook.chapter6.Show;

/**
 * Chương trình biểu diễn trong một tuần  được làm
 * cho có thể quan sát (subject của Observer, chiến lược PUSH).
 * Mỗi ngày luôn gắn với đúng một Show; ngày không có suất diễn dùng
 * "null show".
 */
public final class ObservableProgram {

	public enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	private static final Show NULL_SHOW = createNullShow();

	private final EnumMap<Day, Show> aShows = new EnumMap<>(Day.class);
	private final List<ProgramObserver> aObservers = new ArrayList<>();

	/**
	 * Tạo chương trình rỗng: mọi ngày đều là null show.
	 * (Lúc này chưa có observer nào nên clear() không thông báo cho ai.)
	 */
	public ObservableProgram() {
		clear();
	}

	/**
	 * @param pObserver Observer cần đăng ký.
	 * @pre pObserver != null
	 */
	public void addObserver(ProgramObserver pObserver) {
		assert pObserver != null;
		aObservers.add(pObserver);
	}

	/**
	 * Xóa toàn bộ chương trình, rồi thông báo programCleared().
	 */
	public void clear() {
		for (Day day : Day.values()) {
			aShows.put(day, NULL_SHOW);
		}
		for (ProgramObserver observer : aObservers) {
			observer.programCleared();
		}
	}

	/**
	 * Thêm một show vào chương trình (ghi đè show cũ cùng ngày), rồi đẩy
	 * show và ngày tới observer qua showAdded.
	 *
	 * @param pShow Show cần thêm.
	 * @param pDay  Ngày diễn ra show.
	 */
	public void add(Show pShow, Day pDay) {
		assert pShow != null && pDay != null;
		aShows.put(pDay, pShow);
		for (ProgramObserver observer : aObservers) {
			observer.showAdded(pShow.copy(), pDay);
		}
	}

	/**
	 * Gỡ show của một ngày (thay bằng null show), rồi đẩy show vừa gỡ tới
	 * observer. Nếu ngày đó vốn không có show thì trạng thái không đổi,
	 * nên không thông báo.
	 *
	 * @param pDay Ngày cần gỡ show.
	 */
	public void remove(Day pDay) {
		assert pDay != null;
		Show removed = aShows.put(pDay, NULL_SHOW);
		if (removed.isNull()) {
			return;
		}
		for (ProgramObserver observer : aObservers) {
			observer.showRemoved(removed.copy(), pDay);
		}
	}

	/**
	 * @param pDay Ngày cần lấy show.
	 * @return Bản sao của show trong ngày đó (null show nếu không có).
	 */
	public Show get(Day pDay) {
		assert pDay != null;
		return aShows.get(pDay).copy();
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

	/*
	 * Null show : một lớp ẩn danh không có
	 * nội dung, thời lượng 0, sao chép được, và các null show bằng nhau.
	 */
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
