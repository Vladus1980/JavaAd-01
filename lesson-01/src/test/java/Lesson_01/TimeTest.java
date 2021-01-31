package Lesson_01;

import java.sql.Time;
import java.util.zip.DataFormatException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;



public class TimeTest {

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... SUCCEED!");
		};

		protected void failed(Throwable exception, org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... FAILED!");
		};
	};

	@Test
	public void timeTest1() throws IllegalTimeFormatException {
		new Lesson_01.Time(10, 15);
	}

	@Test(expected = IllegalTimeFormatException.class)
	public void timeTest2() throws IllegalTimeFormatException {
		new Lesson_01.Time(10, 75);
	}

	@Test(expected = IllegalTimeFormatException.class)
	public void timeTest3() throws IllegalTimeFormatException {
		new Lesson_01.Time(25, 15);
	}

	@Test
	public void toLiteralTest1() throws IllegalTimeFormatException {
		Lesson_01.Time time = new Lesson_01.Time(2, 15);
		String actualString = time.toLiteral();
		String expectedString = "2 год. 15 хв.";
		Assert.assertEquals(expectedString, actualString);
	}

	@Test
	public void toLiteralTest2() throws IllegalTimeFormatException {
		Lesson_01.Time time = new Lesson_01.Time(0, 15);
		String actualString = ((Lesson_01.Time) time).toLiteral();
		String expectedString = "15 хв.";
		Assert.assertEquals(expectedString, actualString);
	}



	@Test
	public void sumTimeTest1() throws IllegalTimeFormatException {
		Lesson_01.Time actualSum = Lesson_01.Time.sumTime(new Lesson_01.Time(10, 15), new Lesson_01.Time(2, 3));
		Lesson_01.Time expectedSum = new Lesson_01.Time(12, 18);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest2() throws IllegalTimeFormatException {
		Lesson_01.Time actualSum = Lesson_01.Time.sumTime(new Lesson_01.Time(12, 45), new Lesson_01.Time(2, 25));
		Lesson_01.Time expectedSum = new Lesson_01.Time(15, 10);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest3() throws IllegalTimeFormatException {
		Lesson_01.Time actualSum = Lesson_01.Time.sumTime(new Lesson_01.Time(22, 10), new Lesson_01.Time(2, 15));
		Lesson_01.Time expectedSum = new Lesson_01.Time(0, 25);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void timeToMinutesTest() throws IllegalTimeFormatException {
		Integer actual = Lesson_01.Time.timeToMinutes(new Lesson_01.Time(10, 10));
		Assert.assertTrue(actual == 610);
	}

	@Test
	public void minutesToTimeTest() throws IllegalTimeFormatException {
		Lesson_01.Time actual = Lesson_01.Time.minutesToTime(610);
		Assert.assertEquals(actual, new Lesson_01.Time(10, 10));
	}
}