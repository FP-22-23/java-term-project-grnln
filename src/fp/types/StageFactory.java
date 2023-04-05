package fp.types;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.common.Rider;
import fp.common.RiderCountry;
import fp.utils.Checkers;

/**
 * Factory class for the Stage datatype.
 * @author Guillermo R.N.
 */
public class StageFactory {
	/**
	 * Read stages from a .csv file.
	 * @param path File path of the .csv file.
	 * @return A Stages object containing all stages in the file.
	 */
	public static Stages readStages(String path) {
		Stages result = null;
		
		try {
			Stream<Stage> stages = Files.lines(Paths.get(path))
									.skip(1)
									.map(StageFactory::parseLine);
			result = new Stages(stages.collect(Collectors.toList()));
		} catch (IOException e) {
			System.out.println("Error reading .csv file.");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Parse a .csv line.
	 * @param line The line to parse.
	 * @return A Stage object resulting from parsing the line.
	 */
	private static Stage parseLine(String line) {
		String[] chunks = line.split(",");
		Checkers.check("Number of cells per line must be equal to 10.",
					chunks.length == 10);
		Stage result = null;
		
		Integer stageNo = Integer.valueOf(chunks[0].trim());
		LocalDate date = LocalDate.parse(chunks[1].trim(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Float distance = Float.valueOf(chunks[2].trim());
		
		String origin = chunks[3].trim();
		String destination = chunks[4].trim();
		
		StageType type = StageType.valueOf(chunks[5].trim());
		Rider winner = new Rider(chunks[6].trim(), chunks[7].trim(),
								RiderCountry.valueOf(chunks[8].trim()));
		
		List<String> podium = parsePodium(chunks[9].trim());
		
		result = new Stage(stageNo, date, distance, origin,
						destination, type, podium, winner);
		
		return result;
	}
	
	/**
	 * Parse a stage's podium from a string.
	 * @param podium The string to parse.
	 * @return A list of Strings containing the parsed podium.
	 */
	private static List<String> parsePodium(String podium) {
		String[] chunks = podium.split("; ");
		Checkers.check("Number of components of the podium must be equal to 3.",
					chunks.length == 3);
		
		List<String> result = new ArrayList<>();
		
		for (String s: chunks) {
			result.add(s);
		}
				
		return result;
	}
}
