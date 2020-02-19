package utm.domain.datatypes;

import java.time.LocalDate;

public class Time { 

	private LocalDate time;
	
	public Time(LocalDate time) {
		this.time = time;
	}
	
	public LocalDate getTime() {
		return time;
	}
	
}

