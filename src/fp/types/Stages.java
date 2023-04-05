package fp.types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;

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

	public List<Stage> getStages() {
		return this.stages;
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
