package fp.types.test;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.common.Rider;
import fp.common.RiderCountry;
import fp.types.*;
import fp.utils.Checkers;

/**
 * Class to test the Stages datatype.
 * @author Guillermo R.N.
 */
public class StagesTest {
	/**
	 * Tests the first constructor.
	 * @return The newly created container.
	 */
	public static Stages c1Test() {
		System.out.println("----------------------");
		System.out.println("First Constructor Test");
		System.out.println("----------------------\n");
		
		Stages stages = new Stages();
		Checkers.check("List of stages should be empty when using the first constructor.",
						stages.getStages().isEmpty());
		
		System.out.println(stages + "\n");
		return stages;
	}
	
	/**
	 * Tests the second constructor.
	 * @return The newly created container.
	 */
	public static Stages c2Test() {
		System.out.println("-----------------------");
		System.out.println("Second Constructor Test");
		System.out.println("-----------------------\n");
		
		SortedSet<Stage> ss = buildStageSet();
		Stages stages = new Stages(ss);
		Checkers.check("List of stages should not be empty when using second constructor.",
						!stages.getStages().isEmpty());
		
		System.out.println(stages);
		
		for (Stage s: stages.getStages())
			System.out.println("Is stages["
								+ stages.getStages().indexOf(s)
								+ "] contained in the set? "
								+ ss.contains(s));
		
		System.out.println("");
		return stages;
	}
	
	/**
	 * Constructs a SortedSet of stages like that formed by the first 3 registers of the .csv file.
	 * @return The newly created set.
	 */
	private static SortedSet<Stage> buildStageSet() {
		List<String> podium1 = new ArrayList<>();
		podium1.add("David Millar");
		podium1.add("Tom Steels");
		podium1.add("Abraham Olano");
		
		List<String> podium2 = new ArrayList<>();
		podium2.add("Tom Steels");
		podium2.add("Marcel Wust");
		podium2.add("Abraham Olano");
		
		List<String> podium3 = new ArrayList<>();
		podium3.add("Tom Steels");
		podium3.add("Abraham Olano");
		podium3.add("Marcel Wust");
		
		SortedSet<Stage> ss = new TreeSet<>();
		ss.add(new Stage(1, LocalDate.of(2000, 7, 1), 16.5f, "Futuroscope", "Futuroscope",
						StageType.TIME_TRIAL, podium1, new Rider("David Millar", "T-Mobile", RiderCountry.GBR)));
		
		ss.add(new Stage(2, LocalDate.of(2000, 7, 2), 194.0f, "Futuroscope", "Loudun",
				StageType.FLAT, podium2, new Rider("Tom Steels", "BMC Racing Team", RiderCountry.BEL)));

		ss.add(new Stage(3, LocalDate.of(2000, 7, 3), 161.5f, "Loudun", "Nantes",
				StageType.FLAT, podium3, new Rider("Tom Steels", "BMC Racing Team", RiderCountry.BEL)));
		
		return ss;
	}
	
	/**
	 * Tests the addStages and deleteStage methods.
	 * @param stages The container to analyze.
	 */
	public static void addDelTest(Stages stages) {
		System.out.println("-----------------------");
		System.out.println("Add / Delete Stage Test");
		System.out.println("-----------------------\n");
		
		System.out.println("Adding stages to the container's array...");
		SortedSet<Stage> ss = buildStageSet();
		stages.addStages(ss);
		
		System.out.println("Size of the container's array == Size of the set? "
							+ (stages.getStages().size() == ss.size()));
		
		System.out.println("Deleting stages from the container's array...");
		for (Stage s: ss)
			stages.deleteStage(s);
		
		System.out.println("Size of the container's array == 0? "
							+ (stages.getStages().size() == 0) + "\n");
	}
	
	/**
	 * Tests the equality criterion.
	 * @param stages The container to analyze.
	 */
	public static void equalityTest(Stages stages) {
		System.out.println("-------------");
		System.out.println("Equality Test");
		System.out.println("-------------\n");
		
		System.out.println("stages[0] != stages[1]? "
							+ !(stages.getStages().get(0).equals(stages.getStages().get(1))));
		
		System.out.println("stages[2] == stages[2]? "
							+ (stages.getStages().get(2).equals(stages.getStages().get(2))) + "\n");
	}
	
	/**
	 * Tests the stageWithRiderInPodium method.
	 * @param stages The container to analyze.
	 */
	public static void existsTest(Stages stages) {
		System.out.println("-------------");
		System.out.println("'Exists' Test");
		System.out.println("-------------\n");
		
		System.out.println("Does there exist a stage with Tom Steels in the podium? "
							+ stages.stageWithRiderInPodium("Tom Steels"));
		
		System.out.println("Does there exist a stage with John Doe in the podium? "
							+ stages.stageWithRiderInPodium("John Doe") + "\n");
	}
	
	/**
	 * Tests the averageStageDistance method.
	 * @param stages The container to analyze.
	 */
	public static void averageTest(Stages stages) {
		System.out.println("--------------");
		System.out.println("'Average' Test");
		System.out.println("--------------\n");
		
		System.out.println("Average stage distance: "
							+ stages.averageStageDistance() + "\n");
	}
	
	/**
	 * Tests the stagesBefore method.
	 * @param stages The container to analyze.
	 */
	public static void filterTest(Stages stages) {
		System.out.println("--------------");
		System.out.println("Filtering Test");
		System.out.println("--------------\n");
		
		System.out.println("Stages before 17/3/2001: "
							+ stages.stagesBefore(LocalDate.of(2001, 3, 17))
							+ "\n");
		
		System.out.println("Stages before 2/7/2000: "
							+ stages.stagesBefore(LocalDate.of(2000, 7, 2))
							+ "\n");
		
		System.out.println("Stages before 1/7/2000: "
							+ stages.stagesBefore(LocalDate.of(2000, 7, 1))
							+ "\n");
	}
	
	/**
	 * Tests the stagesByNumber method.
	 * @param stages The container to analyze.
	 */
	public static void stagesByNumberTest(Stages stages) {
		System.out.println("---------------------");
		System.out.println("Stages By Number Test");
		System.out.println("---------------------\n");
		
		System.out.println("Stages (by stage number): "
							+ stages.stagesByNumber()
							+ "\n");
	}
	
	/**
	 * Tests the stagesByWinner method.
	 * @param stages The container to analyze.
	 */
	public static void stagesByWinnerTest(Stages stages) {
		System.out.println("---------------------");
		System.out.println("Stages By Winner Test");
		System.out.println("---------------------\n");
		
		System.out.println("Stages (by Winner): "
							+ stages.stagesByWinner()
							+ "\n");
	}
	
	/**
	 * Tests the third constructor.
	 * @return The newly created container.
	 */
	public static Stages c3Test() {
		System.out.println("----------------------");
		System.out.println("Third Constructor Test");
		System.out.println("----------------------\n");
		
		SortedSet<Stage> ss = buildStageSet();
		Stages stages = new Stages(ss.stream());
		Checkers.check("List of stages should not be empty when using third constructor.",
						!stages.getStages().isEmpty());
		
		System.out.println(stages);
		
		for (Stage s: stages.getStages())
			System.out.println("Is stages["
								+ stages.getStages().indexOf(s)
								+ "] contained in the set? "
								+ ss.contains(s));
		
		System.out.println("");
		return stages;
	}
	
	/**
	 * Tests the stageWithRiderInPodiumStream method.
	 * @param stages The container to analyze.
	 */
	public static void existsStreamTest(Stages stages) {
		System.out.println("--------------------");
		System.out.println("'Exists' Stream Test");
		System.out.println("--------------------\n");
		
		System.out.println("Does there exist a stage with Tom Steels in the podium? "
							+ stages.stageWithRiderInPodiumStream("Tom Steels"));
		
		System.out.println("Does there exist a stage with John Doe in the podium? "
							+ stages.stageWithRiderInPodiumStream("John Doe") + "\n");
	}
	
	/**
	 * Tests the averageStageDistanceStream method.
	 * @param stages The container to analyze.
	 */
	public static void averageStreamTest(Stages stages) {
		System.out.println("---------------------");
		System.out.println("'Average' Stream Test");
		System.out.println("---------------------\n");
		
		System.out.println("Average stage distance: "
							+ stages.averageStageDistanceStream() + "\n");
	}
	
	/**
	 * Tests the stagesBeforeStream method.
	 * @param stages The container to analyze.
	 */
	public static void filterStreamTest(Stages stages) {
		System.out.println("---------------------");
		System.out.println("Filtering Stream Test");
		System.out.println("---------------------\n");
		
		System.out.println("Stages before 17/3/2001: "
							+ stages.stagesBeforeStream(LocalDate.of(2001, 3, 17))
							+ "\n");
		
		System.out.println("Stages before 2/7/2000: "
							+ stages.stagesBeforeStream(LocalDate.of(2000, 7, 2))
							+ "\n");
		
		System.out.println("Stages before 1/7/2000: "
							+ stages.stagesBeforeStream(LocalDate.of(2000, 7, 1))
							+ "\n");
	}
	
	/**
	 * Tests the longestStageWon method.
	 * @param stages The container to analyze.
	 */
	public static void maxFilterTest(Stages stages) {
		System.out.println("---------------------------");
		System.out.println("Maximum with filtering Test");
		System.out.println("---------------------------\n");
		
		System.out.println("Longest stage won by Tom Steels: "
							+ stages.longestStageWon("Tom Steels")
							+ "\n");
		
		System.out.println("Longest stage won by John Doe: "
							+ stages.longestStageWon("John Doe")
							+ "\n");
	}
	
	/**
	 * Tests the stagesByDistanceOfType method.
	 * @param stages The container to analyze.
	 */
	public static void sortFilterTest(Stages stages) {
		System.out.println("-----------------------------------------");
		System.out.println("Selection with sorting and filtering Test");
		System.out.println("-----------------------------------------\n");
		
		System.out.println("Stages by distance of type: FLAT"
							+ stages.stagesByDistanceOfType(StageType.FLAT)
							+ "\n");
		
		System.out.println("Stages by distance of type: MOUNTAIN"
				+ stages.stagesByDistanceOfType(StageType.MOUNTAIN)
				+ "\n");
		
		System.out.println("Stages by distance of type: TIME_TRIAL"
				+ stages.stagesByDistanceOfType(StageType.TIME_TRIAL)
				+ "\n");
	}
	
	/**
	 * Tests the stagesByWinnerStream method.
	 * @param stages The container to analyze.
	 */
	public static void stagesByWinnerStreamTest(Stages stages) {
		System.out.println("----------------------------");
		System.out.println("Stages By Winner Stream Test");
		System.out.println("----------------------------\n");
		
		System.out.println("Stages (by Winner): "
							+ stages.stagesByWinnerStream()
							+ "\n");
	}
	
	/**
	 * Tests the winnersByType method.
	 * @param stages The container to analyze.
	 */
	public static void winnersByTypeTest(Stages stages) {
		System.out.println("--------------------");
		System.out.println("Winners By Type Test");
		System.out.println("--------------------\n");
		
		System.out.println("Winners by type: "
							+ stages.winnersByType()
							+ "\n");
	}
	
	/**
	 * Tests the firstStageByRider method.
	 * @param stages The container to analyze.
	 */
	public static void firstStageByRiderTest(Stages stages) {
		System.out.println("-------------------------");
		System.out.println("First Stage By Rider Test");
		System.out.println("-------------------------\n");
		
		System.out.println("First stage by rider: "
							+ stages.firstStageByRider()
							+ "\n");
	}
	
	/**
	 * Tests the longestStagesByWinner method.
	 * @param stages The container to analyze.
	 */
	public static void longestStagesByWinnerTest(Stages stages) {
		System.out.println("-----------------------------");
		System.out.println("Longest Stages By Winner Test");
		System.out.println("-----------------------------\n");
		
		System.out.println("Longest 5 stages by winner: "
							+ stages.longestStagesByWinner(5)
							+ "\n");
		
		System.out.println("Longest 2 stages by winner: "
							+ stages.longestStagesByWinner(2)
							+ "\n");
	}
	
	/**
	 * Tests the riderWithMostWins method.
	 * @param stages The container to analyze.
	 */
	public static void riderWithMostWinsTest(Stages stages) {
		System.out.println("-------------------------");
		System.out.println("Rider With Most Wins Test");
		System.out.println("-------------------------\n");
		
		System.out.println("Rider with most wins: "
							+ stages.riderWithMostWins()
							+ "\n");
	}
	
	public static void main(String[] args) {
		System.out.println("-----------");
		System.out.println("Stages Test");
		System.out.println("-----------\n");
		
		Stages stages1 = c1Test();
		Stages stages2 = c2Test();
		Stages stages3 = c3Test();
		Stages stages4 = StageFactory.readStages("data/stages_TDF.csv");
		
		// Second delivery tests
		addDelTest(stages1);
		equalityTest(stages2);
		
		existsTest(stages4);
		averageTest(stages4);
		filterTest(stages4);
		stagesByNumberTest(stages4);
		stagesByWinnerTest(stages4);
		
		// Third delivery tests - Block 1
		existsStreamTest(stages4);
		averageStreamTest(stages4);
		filterStreamTest(stages4);
		maxFilterTest(stages4);
		sortFilterTest(stages4);
		
		// Third delivery tests - Block 2
		stagesByWinnerStreamTest(stages4);
		winnersByTypeTest(stages4);
		firstStageByRiderTest(stages4);
		longestStagesByWinnerTest(stages4);
		riderWithMostWinsTest(stages4);
		
		System.out.println("End of the Stages test.");
	}
}
