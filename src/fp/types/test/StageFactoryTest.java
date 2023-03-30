package fp.types.test;

import fp.types.*;

/**
 * Class to test the StageFactory datatype.
 * @author Guillermo R.N.
 */
public class StageFactoryTest {
	public static void main(String[] args) {
		Stages stages = StageFactory.readStages("data/stages_TDF.csv");
	}
}
