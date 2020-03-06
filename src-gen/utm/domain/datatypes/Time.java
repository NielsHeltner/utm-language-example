package utm.domain.datatypes;

import java.time.LocalDateTime;

public class Time { 

	private LocalDateTime time;
	
	public Time(LocalDateTime time) {
		this.time = time;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
}

