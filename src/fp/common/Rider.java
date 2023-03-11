package fp.common;

import java.util.Objects;

/*
 * Rider: Record to hold Tour de France rider data.
 * 
 * Properties:
 * -name (String)
 * -team (String)
 * -country (RiderCountry)
 */

public record Rider(String name, String team, RiderCountry country) {
	public Rider(Rider rider) {
		this(rider.name, rider.team, rider.country);
	}
	
	public String name() {
		return name;
	}

	public String team() {
		return team;
	}

	public RiderCountry country() {
		return country;
	}

	public int hashCode() {
		return Objects.hash(country, name, team);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Rider other = (Rider) obj;
		
		return country == other.country && Objects.equals(name, other.name) && Objects.equals(team, other.team);
	}

	public String toString() {
		return "Rider [name=" + name + ", team=" + team + ", country=" + country + "]";
	}	
}
