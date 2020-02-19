package utm.domain.missions;

import utm.domain.datatypes.TimeInterval;
import utm.domain.datatypes.Time;
import utm.domain.datatypes.NavigationPoint;
import utm.domain.datatypes.Area;

public class MissionMark {
	
	private TimeInterval hvornaar;
	private Time starttime;
	private NavigationPoint start;
	private NavigationPoint punkt1;
	private NavigationPoint punkt2;
	private NavigationPoint punkt3;
	private NavigationPoint punkt4;
	private NavigationPoint punkt5;
	private NavigationPoint punkt6;
	private NavigationPoint punkt7;
	private NavigationPoint slut;
	private Area[] omraader;
	private Area omraader2;

	public MissionMark(TimeInterval hvornaar, Time starttime, NavigationPoint start, NavigationPoint punkt1, NavigationPoint punkt2, NavigationPoint punkt3, NavigationPoint punkt4, NavigationPoint punkt5, NavigationPoint punkt6, NavigationPoint punkt7, NavigationPoint slut, Area[] omraader, Area omraader2) {
		this.hvornaar = hvornaar;
		this.starttime = starttime;
		this.start = start;
		this.punkt1 = punkt1;
		this.punkt2 = punkt2;
		this.punkt3 = punkt3;
		this.punkt4 = punkt4;
		this.punkt5 = punkt5;
		this.punkt6 = punkt6;
		this.punkt7 = punkt7;
		this.slut = slut;
		this.omraader = omraader;
		this.omraader2 = omraader2;
	}
	
	public TimeInterval getHvornaar() {
		return this.hvornaar;
	}
	
	public Time getStarttime() {
		return this.starttime;
	}
	
	public NavigationPoint getStart() {
		return this.start;
	}
	
	public NavigationPoint getPunkt1() {
		return this.punkt1;
	}
	
	public NavigationPoint getPunkt2() {
		return this.punkt2;
	}
	
	public NavigationPoint getPunkt3() {
		return this.punkt3;
	}
	
	public NavigationPoint getPunkt4() {
		return this.punkt4;
	}
	
	public NavigationPoint getPunkt5() {
		return this.punkt5;
	}
	
	public NavigationPoint getPunkt6() {
		return this.punkt6;
	}
	
	public NavigationPoint getPunkt7() {
		return this.punkt7;
	}
	
	public NavigationPoint getSlut() {
		return this.slut;
	}
	
	public Area[] getOmraader() {
		return this.omraader;
	}
	
	public Area getOmraader2() {
		return this.omraader2;
	}
	
	
}

