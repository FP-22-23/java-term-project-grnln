package fp.types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.common.Rider;
import fp.common.RiderCountry;
import fp.utils.Checkers;

/**
 * Record to hold Tour de France stage data.
 * 
 * @author Guillermo R.N.
 */

/* 
 * Properties:
 * -stageNo (Integer)
 * -date (LocalDate)
 * -distance (Float)
 * -origin (String)
 * -destination (String)
 * -type (StageType)
 * -podium (List<String>)
 * -winner (Rider)
 * 
 * Plus 2 derived properties:
 * -season (String)
 * -isTimeTrial (Boolean)
 */
public record Stage(Integer stageNo, LocalDate date, Float distance, 
					String origin, String destination, StageType type,
					List<String> podium, Rider winner) implements Comparable<Stage> {
	/**
	 * First constructor.
	 * @param stageNo Number of the stage to be created.
	 * @param date Date of the stage to be created.
	 * @param distance Distance of the stage to be created.
	 * @param origin Origin of the stage to be created.
	 * @param destination Destination of the stage to be created.
	 * @param type Type of the stage to be created.
	 * @param podium Podium of the stage to be created.
	 * @param winner Winner of the stage to be created.
	 */
	public Stage {
		Checkers.check("Stage number must be greater than 0.", stageNo > 0);
		Checkers.check("Distance must be greater than or equal to 0.0f.", distance >= 0.0f);
		Checkers.check("Date must be before current date.", date.isBefore(LocalDate.now()));
		Checkers.check("Podium must contain a maximum of three elements.", podium.size() <= 3);
	}
	
	/**
	 * Second constructor.
	 * @param origin Origin of the stage to be created.
	 * @param destination Destination of the stage to be created.
	 * @param type Type of the stage to be created.
	 */
	public Stage(String origin, String destination, StageType type) {
		this(1, LocalDate.of(2000, 1, 1), 0.0f, origin, destination, type,
			 new ArrayList<String>(), new Rider("", "", RiderCountry.FRA));
	}

	public Integer stageNo() {
		return stageNo;
	}

	public LocalDate date() {
		return date;
	}

	public Float distance() {
		return distance;
	}

	public String origin() {
		return origin;
	}

	public String destination() {
		return destination;
	}

	public StageType type() {
		return type;
	}

	public List<String> podium() {
		return new ArrayList<String>(this.podium);
	}

	public Rider winner() {
		return new Rider(this.winner);
	}
	
	/**
	 * Derived property.
	 * @return The season to which the stage belongs.
	 */
	public String season() {
		return (date().getYear() - 1) + "-" + date().getYear();
	}
	
	/**
	 * Derived property.
	 * @return Whether the stage was a time trial or not.
	 */
	public Boolean isTimeTrial() {
		return (type().equals(StageType.TIME_TRIAL));
	}
	
	public int hashCode() {
		return Objects.hash(date, destination, distance, origin, podium, stageNo, type, winner);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stage other = (Stage) obj;
		return Objects.equals(date, other.date) && Objects.equals(destination, other.destination)
				&& Objects.equals(distance, other.distance) && Objects.equals(origin, other.origin)
				&& Objects.equals(podium, other.podium) && Objects.equals(stageNo, other.stageNo) && type == other.type
				&& Objects.equals(winner, other.winner);
	}

	public int compareTo(Stage s) {
		int res;
		
		Checkers.checkNoNull(s);
		
		if (this.date.isBefore(s.date()))
			res = -1;
		else if (this.date.isAfter(s.date()))
			res = 1;
		else {
			res = this.stageNo.compareTo(s.stageNo());
		}
		
		return res;
	}
	
	public String toString() {
		return "Stage [stageNo=" + stageNo + ", date=" + date + ", distance=" + distance + ", origin=" + origin
				+ ", destination=" + destination + ", type=" + type + ", podium=" + podium + ", winner=" + winner + "]";
	}
}