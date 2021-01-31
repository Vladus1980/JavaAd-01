package Ex01;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Schedule {

	private Set<Seance> schedule;
	private Map<Time, Boolean> isTimeFree;
	private final Time technicalBreakTime = new Time(0, 25);
	private final Movie technicalBreakMovie = new Movie("Перерва", technicalBreakTime);

	public Schedule() {
		this.schedule = new TreeSet<Seance>();
		this.isTimeFree = new TreeMap<Time, Boolean>();

		Time maxTime = Time.getMaxValue();
		for (int t = 0; t <= Time.timeToMinutes(maxTime); t++) {
			isTimeFree.put(Time.minutesToTime(t), true);
		}
	}

	public Map<Time, Boolean> getIsTimeFree() {
		return isTimeFree;
	}

	public Optional<Seance> getMovieSeanceFromSet(Movie movie) {
		Optional<Seance> seanceFound = schedule.stream().filter(entry -> entry.getMovie().equals(movie)).findFirst();

		return seanceFound;
	}

	public boolean isSeanceTimeFree(Seance seance) {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();
		boolean isSeanceTimeFree = true;

		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			if (!isTimeFree.get(Time.minutesToTime(t))) {
				isSeanceTimeFree = false;
				break;
			}
		}
		return isSeanceTimeFree;
	}

	public void registerSeanceInSchedule(Seance seance) {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();

		schedule.add(seance);
		schedule.add(new Seance(technicalBreakMovie, Time.sumTime(seanceEndTime, new Time(0, 1))));
		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			isTimeFree.replace(Time.minutesToTime(t), false);
		}
	}

	public boolean addSeance(Movie movie) {
		Seance seance = Seance.inputSeance(movie);

		if (isSeanceTimeFree(seance)) {
			registerSeanceInSchedule(seance);
			System.out.println("Додано в розклад");
			return true;
		} else {
			System.out.println("Цей час зайнято");
			return false;
		}
	}

	public Optional<Seance> findSeance(Seance seance2) {
		Predicate<Seance> isEqual = seance -> seance.getMovie().getTitle()
				.equalsIgnoreCase(seance2.getMovie().getTitle()) && seance.getStartTime().equals(seance2.getStartTime())
				&& seance.getEndTime().equals(seance2.getEndTime());
		Optional<Seance> seanceFound = schedule.stream().filter(isEqual).findFirst();

		return seanceFound;
	}

	public void unregisterSeanceInSchedule(Seance seance, Optional<Seance> seanceFound) {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();

		schedule.remove(seanceFound.get());
		schedule.remove(new Seance(technicalBreakMovie, Time.sumTime(seanceEndTime, new Time(0, 1))));
		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			isTimeFree.replace(Time.minutesToTime(t), true);
		}
	}

	public boolean removeSeance(Seance removingSeance) {
		Optional<Seance> removingSeanceFound = findSeance(removingSeance);

		if (removingSeanceFound.isPresent()) {
			unregisterSeanceInSchedule(removingSeance, removingSeanceFound);
			System.out.println("Сеанс видалено");
			return true;
		} else {
			System.out.println("Немає такого сеансу");
			return false;
		}
	}

	public void viewSchedule() {
		System.out.println("Розклад:");
		schedule.forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isTimeFree == null) ? 0 : isTimeFree.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (isTimeFree == null) {
			if (other.isTimeFree != null)
				return false;
		} else if (!isTimeFree.equals(other.isTimeFree))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Seance seance : schedule) {
			sb.append(seance.toString() + "\n");
		}
		return sb.toString();
	}
}
