package utm.domain.datatypes;

public class Drone {
	
	public String uuid;
	public NavigationPoint takeOffPosition;
	
	@Override
	public String toString() {
		return "Drone[" + uuid + "]";
	}
	
}
