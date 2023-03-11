package fp.types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.common.Rider;
import fp.utils.Checkers;

public record Stage(Integer stageNo, LocalDate date, Float distance, 
					String origin, String destination, StageType type,
					List<String> podium, Rider winner) implements Comparable<Stage> {
	public Stage {
		Checkers.check("Stage number must be greater than 0.", stageNo > 0);
		Checkers.check("Distance must be greater or equal to 0.", distance >= 0.0f);
		Checkers.check("Date must be before current date.", date.isBefore(LocalDate.now()));
		Checkers.check("Podium must contain a maximum of three elements.", podium.size() <= 3);
	}
	
	public Stage(String origin, String destination, StageType type) {
		this(1, LocalDate.of(2000, 1, 1), 0.0f, origin, destination, type, null, null);
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
	
	public String season() {
		return date().getYear() + "-" + (date().getYear() - 1);
	}
	
	public Boolean isTimeTrial() {
		return (type().equals(StageType.TIME_TRIAL));
	}
	
	public int hashCode() {
		return Objects.hash(date, destination, distance, origin, podium, stageNo, type);
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
				&& Objects.equals(podium, other.podium) && Objects.equals(stageNo, other.stageNo) && type == other.type;
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
				+ ", destination=" + destination + ", type=" + type + ", podium=" + podium + "]";
	}
}