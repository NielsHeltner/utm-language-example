package utm.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import utm.domain.datatypes.Drone;
import utm.domain.datatypes.Time;
import utm.domain.datatypes.TimeInterval;

public class DroneManager {
	
	private Multimap<Drone, TimeInterval> reservations;
	
	public DroneManager() {
		this.reservations = ArrayListMultimap.<Drone, TimeInterval>create();
	}
	
	public List<Drone> getAvailableDrones(List<Drone> drones, Time time, int size) {
		return drones.stream()
				.filter(drone -> isAvailable(drone, time))
				.limit(size)
				.collect(Collectors.toList());
	}
	
	public List<Drone> getAvailableDrones(List<Drone> drones, Time time) {
		return getAvailableDrones(drones, time, drones.size());
	}
	
	private boolean isAvailable(Drone drone, Time time) {
		return !reservations.get(drone).stream()
				.anyMatch(timeInterval -> timeInterval.isWithin(time));
	}
	
}
