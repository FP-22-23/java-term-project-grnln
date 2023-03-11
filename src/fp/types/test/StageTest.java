package fp.types.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fp.common.Rider;
import fp.common.RiderCountry;
import fp.types.Stage;
import fp.types.StageType;

public class StageTest {
	public static void showStage(Stage s) {
		System.out.println("Stage with hashcode = " + s.hashCode());
		
		System.out.println("Stage no.: " + s.stageNo());
		System.out.println("Date: " + s.date());
		System.out.println("Distance: " + s.distance());
		System.out.println("Origin - destination: " + s.origin() + " - " + s.destination());
		System.out.println("Type: " + s.type());
		System.out.println("Podium: " + s.podium());
		System.out.println("Winner: " + s.winner());
		System.out.println("Season: " + s.season());
		System.out.println("Is time trial? " + s.isTimeTrial());
		
		System.out.println("String representation:\n" + s.toString() + "\n");
	}
	
	public static Stage c1Test(Integer stageNo, LocalDate date, Float distance, 
							  String origin, String destination, StageType type,
							  List<String> podium, Rider winner) {
		
		System.out.println("------------------");
		System.out.println("Constructor 1 Test");
		System.out.println("------------------");
		
		try {
			Stage s = new Stage(stageNo, date, distance, origin, destination, type, podium, winner);
			showStage(s);
			return s;
		} catch (Exception e) {
			System.out.println("Exception raised: " + e);
			return null;
		}
	}
	
	public static Stage c2Test(String origin, String destination, StageType type) {
		System.out.println("------------------");
		System.out.println("Constructor 2 Test");
		System.out.println("------------------");
		
		try {
			Stage s = new Stage(origin, destination, type);
			showStage(s);
			return s;
		} catch (Exception e) {
			System.out.println("Exception raised: " + e);
			return null;
		}
	}
	
	public static void orderTest(Stage a, Stage b) {
		System.out.println("------------------");
		System.out.println("Natural order test");
		System.out.println("------------------");
		
		System.out.println("Is\n" + a.toString() + "\nequal to\n" + b.toString() + "?\n" + a.equals(b));
		System.out.println("\nStage: " + a.toString());
		
		if (a.compareTo(b) < 0) {
			System.out.println("Precedes");
			
		} else if (a.compareTo(b) > 0) {
			System.out.println("Succeeds");
		} else {
			System.out.println("Equals");
		}
		
		System.out.println("Stage:" + b.toString() + "\n");
	}
	
	public static void firstTest() {
		/*
		 * First test scenario:
		 * -Two different constructors
		 * -Two different objects
		 */
		
		System.out.println("-------------------");
		System.out.println("First test scenario");
		System.out.println("-------------------");
		
		List<String> testPodium = new ArrayList<String>();
		testPodium.add("Geraint Thomas");
		testPodium.add("Marcel Kittel");
		testPodium.add("Peter Sagan");
		
		Stage a = c1Test(1, LocalDate.of(2017, 7, 1), 14.0f, "Dusseldorf", "Dusseldorf", StageType.TIME_TRIAL,
						 testPodium, new Rider("Geraint Thomas", "Sky", RiderCountry.GBR));
		
		Stage b = c2Test("Dusseldorf", "Dusseldorf", StageType.TIME_TRIAL);
		
		orderTest(a, b);
		orderTest(b, a);
		
		System.out.println("---------------------");
		System.out.println("End of the first test");
		System.out.println("---------------------\n");
	}
	
	public static void secondTest() {
		/*
		 * Second test scenario:
		 * -First constructor
		 * -Two equal objects
		 */
		
		System.out.println("--------------------");
		System.out.println("Second test scenario");
		System.out.println("--------------------");
		
		List<String> testPodium = new ArrayList<String>();
		testPodium.add("Geraint Thomas");
		testPodium.add("Marcel Kittel");
		testPodium.add("Peter Sagan");
		
		Stage a = c1Test(1, LocalDate.of(2017, 7, 1), 14.0f, "Dusseldorf", "Dusseldorf", StageType.TIME_TRIAL,
						 testPodium, new Rider("Geraint Thomas", "Sky", RiderCountry.GBR));
		
		Stage b = c1Test(1, LocalDate.of(2017, 7, 1), 14.0f, "Dusseldorf", "Dusseldorf", StageType.TIME_TRIAL,
						 testPodium, new Rider("Geraint Thomas", "Sky", RiderCountry.GBR));
		
		orderTest(a, b);
		orderTest(b, a);
		
		System.out.println("----------------------");
		System.out.println("End of the second test");
		System.out.println("----------------------\n");
	}
	
	public static void thirdTest() {
		/*
		 * Third test scenario:
		 * -Second constructor
		 * -Two different objects
		 */
		
		System.out.println("-------------------");
		System.out.println("Third test scenario");
		System.out.println("-------------------");
		
		Stage a = c2Test("Dusseldorf", "Liege", StageType.FLAT);
		Stage b = c2Test("Dusseldorf", "Dusseldorf", StageType.TIME_TRIAL);
		
		orderTest(a, b);
		orderTest(b, a);
		
		System.out.println("---------------------");
		System.out.println("End of the third test");
		System.out.println("---------------------\n");
	}
	
	public static void fourthTest() {
		/*
		 * Third test scenario:
		 * -Second constructor
		 * -Two equal objects
		 */
		
		System.out.println("--------------------");
		System.out.println("Fourth test scenario");
		System.out.println("--------------------");
		
		Stage a = c2Test("Dusseldorf", "Liege", StageType.FLAT);
		Stage b = c2Test("Dusseldorf", "Liege", StageType.FLAT);
		
		orderTest(a, b);
		orderTest(b, a);
		
		System.out.println("----------------------");
		System.out.println("End of the fourth test");
		System.out.println("----------------------\n");
	}
	
	public static void main(String[] args) {
		firstTest();
		secondTest();
		thirdTest();
		fourthTest();
	}
}