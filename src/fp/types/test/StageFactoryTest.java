package fp.types.test;

import fp.types.*;

/**
 * Class to test the StageFactory datatype.
 * @author Guillermo R.N.
 */
public class StageFactoryTest {
	/**
	 * Tests the readStages method.
	 * @param path Path of the .csv file to read.
	 */
	public static void readStagesTest(String path) {
		System.out.println("---------------");
		System.out.println("readStages Test");
		System.out.println("---------------\n");
		
		Stages stages = StageFactory.readStages(path);
		
		System.out.println("First register:");
		System.out.println(stages.getStages().get(0) + "\n");
		System.out.println();
		
		System.out.println("Second register:");
		System.out.println(stages.getStages().get(1) + "\n");
		
		System.out.println("Third register:");
		System.out.println(stages.getStages().get(2) + "\n");
		
		System.out.println("No problems during parsing.\n");
	}
	
	/**
	 * Tests the readStagesToStream method.
	 * @param path Path of the .csv file to read.
	 */
	public static void readStagesToStreamTest(String path) {
		System.out.println("-----------------------");
		System.out.println("readStagesToStream Test");
		System.out.println("-----------------------\n");
		
		Stages stages = StageFactory.readStagesToStream(path);
		
		System.out.println("First register:");
		System.out.println(stages.getStages().get(0) + "\n");
		System.out.println();
		
		System.out.println("Second register:");
		System.out.println(stages.getStages().get(1) + "\n");
		
		System.out.println("Third register:");
		System.out.println(stages.getStages().get(2) + "\n");
		
		System.out.println("No problems during parsing.\n");
	}
	
	public static void main(String[] args) {
		System.out.println("-----------------");
		System.out.println("StageFactory Test");
		System.out.println("-----------------\n");
		
		readStagesTest("data/stages_TDF.csv");
		readStagesToStreamTest("data/stages_TDF.csv");
		
		System.out.println("End of the StageFactory test.");
	}
}
