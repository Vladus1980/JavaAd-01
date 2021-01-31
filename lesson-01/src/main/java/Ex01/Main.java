package Ex01;

import java.util.Scanner;

public class Main {
	public static int menu() {
		System.out.println("1 - створити кінотеатр");
		System.out.println("2 - створити графік на день");
		System.out.println("3 - видалити графік на день");
		System.out.println("4 - показати графік на тиждень");
		System.out.println("5 - створити зал");
		System.out.println("6 - видалити зал");
		System.out.println("7 - показати зали");
		System.out.println("8 - створити графік зала на день");
		System.out.println("9 - видалити графік зала на день");
		System.out.println("10 - показати графік сеансів зала на тиждень");
		System.out.println("11 - створити розклад зала на день");
		System.out.println("12 - видалити розклад зала на день");
		System.out.println("13 - вивести розклад зала на тиждень");
		System.out.println("14 - додати фільм");
		System.out.println("15 - видалити фільм");
		System.out.println("0 - вийти");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Виберіть дію: ");
		int menuChoise = scanner.nextInt();

		return menuChoise;
	}

	public static void main(String[] args) {
		Cinema cinema = null;

		while (true) {
			switch (menu()) {

			case 1: {
				cinema = Cinema.inputCinema();
				break;
			}

			case 2: {
				cinema.addTimeTableToCinema();
				break;
			}

			case 3: {
				cinema.removeTimeTableFromCinema();
				break;
			}

			case 4: {
				cinema.viewCinemaTimeTable();
				break;
			}

			case 5: {
				cinema.addHallToCinema();
				break;
			}

			case 6: {
				cinema.removeHallFromCinema();
				break;
			}

			case 7: {
				cinema.viewCinemaHalls();
				break;
			}

			case 8: {
				cinema.addTimeTableToHallInCinema();
				break;
			}

			case 9: {
				cinema.removeTimeTableFromHallInCinema();
				break;
			}

			case 10: {
				cinema.viewHallTimeTableInCinema();
				break;
			}

			case 11: {
				cinema.addScheduleToHallInCinema();
				break;
			}

			case 12: {
				cinema.removeScheduleFromHallInCinema();
				break;
			}

			case 13: {
				cinema.viewHallScheduleInCinema();
				break;
			}

			case 14: {
				cinema.addMovieToSeanceInScheduleInHallInCinema();
				break;
			}

			case 15: {
				cinema.removeMovieFromSeanceInScheduleInAllHallsInCinema();
				break;
			}

			case 0: {
				System.exit(0);
				break;
			}

			default: {
				System.err.println("помилка, зробіть свій вибір");
				break;
			}
			}
		}
	}
}
