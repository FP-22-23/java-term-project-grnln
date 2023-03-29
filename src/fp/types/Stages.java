package fp.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

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
	 * @param stages Set of stages that the container will bear.
	 */
	public Stages(SortedSet<Stage> stages) {
		this.stages = new ArrayList<Stage>(stages);
	}

	public List<Stage> getStages() {
		return this.stages;
	}
	
	/**
	 * Gets the number of stages.
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
