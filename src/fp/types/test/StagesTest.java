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
	
	public static void main(String[] args) {
		System.out.println("-----------");
		System.out.println("Stages Test");
		System.out.println("-----------\n");
		
		Stages stages1 = c1Test();
		Stages stages2 = c2Test();
		
		addDelTest(stages1);
		equalityTest(stages2);
		existsTest(stages2);
		averageTest(stages2);
		filterTest(stages2);
		stagesByNumberTest(stages2);
		stagesByWinnerTest(stages2);
		
		System.out.println("End of the Stages test.");
	}
}
