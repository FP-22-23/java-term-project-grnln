package fp.types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.common.Rider;
import fp.utils.Checkers;

/**
 * Container type for the Stage datatype.
 * @author Guillermo R.N.
 */
public class Stages {
	private List<Stage> stages;
	
	/**
	 * First constructor.
	 */
	public Stages() {
		this.stages = new ArrayList<Stage>();
	}
	
	/**
	 * Second constructor.
	 * @param stages Collection of stages that the container will bear.
	 */
	public Stages(Collection<Stage> stages) {
		this.stages = new ArrayList<Stage>(stages);
	}
	
	/**
	 * Third constructor.
	 * @param stages Stream of stages that the container will bear.
	 */
	public Stages(Stream<Stage> stages) {
		this.stages = stages.collect(Collectors.toList());
	}

	public List<Stage> getStages() {
		return new ArrayList<Stage>(stages);
	}
	
	/**
	 * Gets the number of stages (derived property).
	 * @return Number of stages in the container.
	 */
	public Integer getNumberStages() {
		return this.stages.size();
	}
	
	/**
	 * Adds a stage to the container.
	 * @param stage The stage to add.
	 */
	public void addStage(Stage stage) {
		stages.add(stage);
	}
	
	/**
	 * Adds a collection of stages to the container.
	 * @param stages Collection of stages to add.
	 */
	public void addStages(Collection<Stage> stages) {
		stages.addAll(stages);
	}
	
	/**
	 * Deletes a stage from the container.
	 * @param stage The stage to delete.
	 */
	public void deleteStage(Stage stage) {
		stages.remove(stage);
	}
	
	/**
	 * Checks if there is a stage whose podium contains a certain rider ("Exists" criterion).
	 * @param riderName The rider for whose existance to check.
	 * @return true/false depending on whether there is a stage with a rider in the podium or not.
	 */
	public Boolean stageWithRiderInPodium(String riderName) {
		Boolean result = false;
		
		for (Stage s: stages) {
			if (s.podium().contains(riderName)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Get the average stage distance ("Average" criterion).
	 * @return The average of the distances.
	 */
	public Float averageStageDistance() {
		Checkers.check("Stage number must be greater than 0.", stages.size() > 0);
		Float result = 0.0f;
	
		for (Stage s: stages) {
			result += s.distance();
		}
		
		return result / stages.size();
	}
	
	/**
	 * Gets a list with all stages before a certain date (selection with filtering).
	 * @param date The date used for the filtering.
	 * @return A list containing all the stages before that date.
	 */
	public List<Stage> stagesBefore(LocalDate date) {
		List<Stage> result = new ArrayList<>();
		
		for (Stage s: stages) {
			if (s.date().isBefore(date)) {
				result.add(s);
			}
		}
		
		return result;
	}

	/**
	 * Gets a map with all stages with the same number.
	 * @return The created map.
	 */
	public Map<Integer, List<Stage>> stagesByNumber() {
		Map<Integer, List<Stage>> result = new HashMap<>();
		
		for (Stage s: stages) {
			if (result.containsKey(s.stageNo())) {
				result.get(s.stageNo()).add(s);
			} else {
				result.put(s.stageNo(), new ArrayList<Stage>());
				result.get(s.stageNo()).add(s);
			}
		}
		
		return result;
	}
	
	/**
	 * Gets a map with the number of stages won by every stage winner.
	 * @return The created map.
	 */
	public Map<Rider, Integer> stagesByWinner() {
		Map<Rider, Integer> result = new HashMap<>();
		
		for (Stage s: stages) {
			if (result.containsKey(s.winner())) {
				result.put(s.winner(), result.get(s.winner()) + 1);
			} else {
				result.put(s.winner(), 1);
			}
		}
		
		return result;
	}
	
	/**
	 * Checks if there is a stage whose podium contains a certain rider using a Stream ("Exists" criterion).
	 * @param riderName The rider for whose existance to check.
	 * @return true/false depending on whether there is a stage with a rider in the podium or not.
	 */
	public Boolean stageWithRiderInPodiumStream(String riderName) {
		return stages.stream()
				.anyMatch(stage->stage.podium().contains(riderName));
	}
	
	/**
	 * Get the average stage distance using a stream ("Average" criterion).
	 * @return The average of the distances.
	 */
	public Double averageStageDistanceStream() {
		return stages.stream()
				.mapToDouble(stage->stage.distance())
				.average()
				.orElse(0.0);
	}
	
	/**
	 * Gets a list with all stages before a certain date using a stream (selection with filtering).
	 * @param date The date used for the filtering.
	 * @return A list containing all the stages before that date.
	 */
	public List<Stage> stagesBeforeStream(LocalDate date) {
		return stages.stream()
				.filter(stage->stage.date().isBefore(date))
				.toList();
	}
	
	/**
	 * Gets the longest stage a rider has ever won (maximum with filtering).
	 * @param riderName The name of the rider used for the filtering.
	 * @return The longest stage with said rider as its winner.
	 */
	public Stage longestStageWon(String riderName) {
		return stages.stream()
				.filter(stage->stage.winner().name().equals(riderName))
				.max(Comparator.comparing(stage->stage.distance()))
				.orElse(null);
	}
	
	/**
	 * Gets a list of stages with the same type, sorted by distance
	 * (selection with filtering and sorting).
	 * 
	 * @param type The stage type used for the filtering.
	 * @return The list of stages of that type, sorted by distance.
	 */
	public List<Stage> stagesByDistanceOfType(StageType type) {
		return stages.stream()
				.filter(stage->stage.type() == type)
				.sorted(Comparator.comparing(stage->stage.distance()))
				.toList();
	}
	
	/**
	 * Gets a map with the number of stages won by every stage winner using a stream.
	 * @return The created map.
	 */
	public Map<Rider, Long> stagesByWinnerStream() {
		return stages.stream()
				.collect(Collectors.groupingBy(stage->stage.winner(), Collectors.counting()));
	}
	
	/**
	 * Gets a map associating each stage type to a set of all the names of the riders who won
	 * stages of that type.
	 * @return The created map.
	 */
	public Map<StageType, Set<String>> winnersByType() {
		return stages.stream()
				.collect(Collectors.groupingBy(
						stage->stage.type(),
						Collectors.mapping(stage->stage.winner().name(), Collectors.toSet())));
	}
	
	/**
	 * Gets a map associating every stage winner to the earliest stage they won.
	 * @return The created map.
	 */
	public Map<Rider, Stage> firstStageByRider() {
		return stages.stream()
				.collect(Collectors.groupingBy(
						stage->stage.winner(),
						Collectors.collectingAndThen(
								Collectors.minBy(Comparator.comparing(stage->stage.date())),
								x->x.orElse(null))));
	}
	
	/**
	 * Gets a SortedMap with the n longest stages every stage winner has won.
	 * @param n Number of stages to associate to each winner.
	 * @return The created SortedMap.
	 */
	public SortedMap<String, List<Stage>> longestStagesByWinner(Integer n) {
		return stages.stream()
				.collect(Collectors.groupingBy(
						stage->stage.winner().name(),
						TreeMap::new,
						Collectors.collectingAndThen(
								Collectors.toList(),
								list->list.stream()
								.sorted(Comparator.comparing(Stage::distance).reversed())
								.limit(n)
								.toList())));
	}
	
	/**
	 * Creates a map with all stage winners and their number of wins
	 * and gets the one with the greatest number of wins.
	 * @return The key of the map with its largest value of wins.
	 */
	public Rider riderWithMostWins() {
		Map<Rider, Long> map = stages.stream()
				.collect(Collectors.groupingBy(
						stage->stage.winner(),
						Collectors.counting()));
		
		return map.entrySet().stream()
				.collect(Collectors.maxBy(Comparator.comparing(entry->entry.getValue())))
				.orElse(null)
				.getKey();
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Stages other = (Stages) obj;
		
		return Objects.equals(stages, other.stages);
	}

	public String toString() {
		return "Stages [stages=" + stages + "]";
	}	
}
