package fp.types.test;

import fp.types.*;

public class StageFactoryTest {
	public static void main(String[] args) {
		Stages stages = StageFactory.readStages("data/stages_TDF.csv");
		System.out.println(stages.toString());
	}
}
