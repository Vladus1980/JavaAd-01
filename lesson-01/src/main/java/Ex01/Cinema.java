package Ex01;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class Cinema{

	
	private String name;
	private TimeTable cinemaTimeTable;
	private TreeSet<Hall> cinemaHalls;

	public Cinema(String name) {
		this.name = name;
		this.cinemaTimeTable = new TimeTable();
		this.cinemaHalls = new TreeSet<Hall>();
	}

	public String getName() {
		return name;
	}

	public static Cinema inputCinema() {
	
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введіть назву Кінотеатру: ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.out.println("Невірно введено");
			name = "Технічний";
		}
		System.out.println("Кинотеатр \"" + name.toString() + "\" успешно создан!\n");
		return new Cinema(name);
	}

	public void addHallToCinema() {
		Hall cinemaHall = Hall.inputHall();
		cinemaHalls.add(cinemaHall);
		System.out.println("Зал \"" + cinemaHall.getName() + "\" додано \"" + name + "\"!\n");
	}

	public Optional<Hall> getHallFromSet(Hall cinemaHall) {
		Optional<Hall> hallFound = cinemaHalls.stream().filter(entry -> entry.getName().equals(cinemaHall.getName()))
				.findFirst();

		return hallFound;
	}

	public boolean removeHallFromCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			cinemaHalls.remove(hallFound.get());
			System.out.println(
					"Зал \"" + hallFound.get().getName() + "\" видалено \"" + name + "\"!\n");
			return true;
		} else {
			System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addTimeTableToCinema(){
		boolean isDone = cinemaTimeTable.addTimeTableEntry();
		if (isDone) {
			System.out.println("Графік \"" + name + "\" змінено\n");
			return true;
		} else {
			System.out.println("Графік " + name + "\" не змінено\n");
			return false;
		}
	}

	public boolean removeTimeTableFromCinema() {
		boolean isDone = cinemaTimeTable.removeTimeTableEntry();
		if (isDone) {
			System.out.println("Графік \"" + name + "\" змінено\n");
			return true;
		} else {
			System.out.println("Графік \"" + name + "\" не змінено\n");
			return false;
		}
	}

	public boolean addTimeTableToHallInCinema(){
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addTimeTableToHall();

			if (isDone) {
				System.out.println("Графік \"" + hallFound.get() + "\" додано \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Зміни в графік \"" + name + "\" не внесено\n");
				return false;
			}
		} else {
			System.err.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeTimeTableFromHall();

			if (isDone) {
				System.out.println("Графік \"" + hallFound.get() + "\" видалено \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Зміни в графік \"" + name + "\" не внесено\n");
				return false;
			}
		} else {
			System.err.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addScheduleToHallInCinema(){
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addScheduleToHall();

			if (isDone) {
				System.out.println("Розклад \"" + hallFound.get() + "\" додано \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Зміни в розклад \"" + hallFound.get() + "\" кінотеатру \""
						+ name + "\" не внесено!\n");
				return false;
			}
		} else {
			System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeScheduleFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeScheduleFromHall();

			if (isDone) {
				System.out.println("Розклад \"" + hallFound.get() + "\" видалено \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Розклад для \"" + hallFound.get() + "\" кінотеатру \""
						+ name + "\" не змінено\n");
				return false;
			}
		} else {
			System.err.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addSeanceToScheduleInHallInCinema(){
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				boolean isDone = hallScheduleEntryFound.get().getValue().addSeance(movie);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.out.println(day.toLiteral(true) + " відсутній для \"" + hallFound.get()
						+ "\" кінотеатру \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeSeanceFromScheduleInHallInCinema(){
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				Seance removingSeance = Seance.inputSeance(movie);
				boolean isDone = hallScheduleEntryFound.get().getValue().removeSeance(removingSeance);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " відсутній в розкладі для  \"" + hallFound.get()
						+ "\" кінотеатру \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public void addMovieToSeanceInScheduleInHallInCinema(){
		Movie movie = Movie.inputMovie();

		boolean addOneMoreSeance;

		do {
			Hall cinemaHall = Hall.inputHall();

			Optional<Hall> hallFound = getHallFromSet(cinemaHall);

			if (hallFound.isPresent()) {
				Days day = Days.inputDay();
				if (day == null)
					return;

				Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
						.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

				if (hallScheduleEntryFound.isPresent()) {
					hallScheduleEntryFound.get().getValue().addSeance(movie);
				} else {
					System.out.println(day.toLiteral(true) + " відсутній в розкладі сеансів для \""
							+ hallFound.get() + "\" кінотеатру \"" + name + "\"!\n");
				}
			} else {
				System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			}

			
			Scanner scanner = new Scanner(System.in);

			System.out.print("Додати ще сеанс? (true/false) ");
			addOneMoreSeance = scanner.nextBoolean();

			
		} while (addOneMoreSeance);
	}

	public void removeMovieFromSeanceInScheduleInAllHallsInCinema(){
		Movie movie = Movie.inputMovie();
		boolean isDone = false;

		for (Hall hall : cinemaHalls) {
			for (Days day : Days.values()) {
				Optional<Seance> seance = hall.getHallSchedule().entrySet().stream()
						.filter(entry -> entry.getKey().equals(day)).findFirst().get().getValue()
						.getMovieSeanceFromSet(movie);

				if (seance.isPresent()) {
					hall.getHallSchedule().entrySet().stream().filter(entry -> entry.getKey().equals(day)).findFirst()
							.get().getValue().removeSeance(seance.get());
					isDone = true;
				} else
					break;
			}
		}

		if (isDone) {
			System.out.println(movie.toString() + " видалено\n");
		} else {
			System.err.println("Видалити " + movie.toString()
					+ " не вдалося\n");
		}
	}

	public void viewCinemaTimeTable() {
		System.out.print("Кінотеатр \"" + name + "\"\n");
		cinemaTimeTable.viewTimeTable();
		System.out.println();
	}

	public void viewCinemaHalls() {
		System.out.println("Зали \"" + name + "\":");
		cinemaHalls.forEach(System.out::println);
		System.out.println();
	}

	public boolean viewHallTimeTableInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallTimeTable();
			return true;
		} else {
			System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean viewHallScheduleInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallSchedule();
			return true;
		} else {
			System.out.println("Зал \"" + cinemaHall.getName() + "\" відсутній \"" + name + "\"!\n");
			return false;
		}
	}

	@Override
	public String toString() {
		if (name == "Технічний") {
			return (String) name;
		} else
			return "Кінотеатр \"" + name + "\"";
	}
}

