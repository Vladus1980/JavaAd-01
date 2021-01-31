package Ex01;

import java.util.Scanner;

public enum Days {
	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	int serialNumber;

	Days(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public String toLiteral(boolean fullType) {
		String dayToLiteralFull;
		String dayToLiteralShort;

		switch (Days.this) {
		case MONDAY:
			dayToLiteralFull = "Понеділок";
			dayToLiteralShort = "Пн.";
			break;
		case TUESDAY:
			dayToLiteralFull = "Вівторок";
			dayToLiteralShort = "Вів.";
			break;
		case WEDNESDAY:
			dayToLiteralFull = "Середа";
			dayToLiteralShort = "Сер.";
			break;
		case THURSDAY:
			dayToLiteralFull = "Четвер";
			dayToLiteralShort = "Чет.";
			break;
		case FRIDAY:
			dayToLiteralFull = "Пятниця";
			dayToLiteralShort = "Пт.";
			break;
		case SATURDAY:
			dayToLiteralFull = "Субота";
			dayToLiteralShort = "Сб.";
			break;
		case SUNDAY:
			dayToLiteralFull = "Неділя";
			dayToLiteralShort = "Нд.";
			break;
		default:
			dayToLiteralFull = dayToLiteralShort = "";
			break;
		}

		if (fullType)
			return dayToLiteralFull;
		else
			return dayToLiteralShort;
	}

	public static Days inputDay() {
		
		Scanner scanner = new Scanner(System.in);
		Integer returnedNumber = 0;
		Days foundDay = null;

		System.out.print("Введіть номер дня тижня: ");
		if (scanner.hasNextInt()) {
			int nextInt = scanner.nextInt();

			if (nextInt > 0 && nextInt <= Days.values().length) {
				returnedNumber = nextInt;
			} else
				System.out.println("НОмер дня тижня від 1 до 7");
		} else
			System.out.println("Невірно введений номер");

		for (Days day : Days.values()) {
			if (day.getSerialNumber() == returnedNumber) {
				foundDay = day;
			}
		}

		return foundDay;
	}
}
