package Ex01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

public class TimeTable {
	
	
	private Map<Days, ArrayList<Time>> timeTable;

	public TimeTable() {
		this.timeTable = new TreeMap<Days, ArrayList<Time>>();
	}

	public Optional<Entry<Days, ArrayList<Time>>> findDayInTimeTable(Days day) {
		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = timeTable.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();
		return timeTableEntryFound;
	}

	public Time getOpeningTime(Days day) {
		return timeTable.get(day).get(0);
	}

	public Time getClosingTime(Days day) {
		return timeTable.get(day).get(1);
	}

	public boolean addTimeTableEntry(){
		Days day = Days.inputDay();
		if (day == null)
			return false;

		System.out.print("Час відкриття - ");
		Time openingTime = Time.inputTime();

		System.out.print("Час закриття - ");
		Time closingTime = Time.inputTime();

		if (Time.timeToMinutes(closingTime) <= Time.timeToMinutes(openingTime)) {
			System.out.println("Невірний час!\n");
			return false;
		}

		timeTable.put(day, new ArrayList<Time>(Arrays.asList(openingTime, closingTime)));
		System.out.println("Час роботи " + day.toLiteral(true) + " додано\n");
		return true;
	}

	public boolean removeTimeTableEntry() {
		Days day = Days.inputDay();
		if (day == null)
			return false;

		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = findDayInTimeTable(day);

		if (timeTableEntryFound.isPresent()) {
			timeTable.remove(timeTableEntryFound.get().getKey());
			System.out.println("Час роботи " + day.toLiteral(true) + " видалено\n");
			return true;
		} else {
			System.out.println("Час роботи " + day.toLiteral(true) + " відсутній\n");
			return false;
		}
	}
	
	public Function<Entry<Days, ArrayList<Time>>, String> timeTableToString () {
		return entry -> entry.getKey().toLiteral(false) + " " + entry.getValue().get(0).toString()
				+ " - " + entry.getValue().get(1).toString();
	}
		
	public void viewTimeTable() {
		System.out.println("Графік роботи:");
		timeTable.entrySet().stream().map(timeTableToString()).forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeTable == null) ? 0 : timeTable.hashCode());
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
		TimeTable other = (TimeTable) obj;
		if (timeTable == null) {
			if (other.timeTable != null)
				return false;
		} else if (!timeTable.equals(other.timeTable))
			return false;
		return true;
	}
}