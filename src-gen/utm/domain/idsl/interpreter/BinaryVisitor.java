package utm.domain.idsl.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import utm.domain.idsl.model.LeafAreaPath;
import utm.domain.idsl.model.LeafNavigationPointPath;
import utm.domain.idsl.model.PathComponent;

public interface BinaryVisitor {
	
	CompositePath plan(LeafNavigationPointPath start, LeafNavigationPointPath end);
	
	CompositePath plan(LeafNavigationPointPath start, LeafAreaPath end);
	
	CompositePath plan(LeafNavigationPointPath start, CompositePath end);
	
	CompositePath plan(LeafAreaPath start, LeafNavigationPointPath end);
	
	CompositePath plan(LeafAreaPath start, LeafAreaPath end);
	
	CompositePath plan(LeafAreaPath start, CompositePath end);
	
	CompositePath plan(CompositePath start, CompositePath end);
	
	CompositePath plan(CompositePath start, LeafNavigationPointPath end);
	
	CompositePath plan(CompositePath start, LeafAreaPath end);
	
	
	
	default <T extends PathComponent, F extends PathComponent> CompositePath plan(PathComponent comp1, PathComponent comp2) {
		Map<Pair<Class<T>, Class<F>>, BiFunction<T, F, PathComponent>> map = new HashMap<>();
		
		map.put(new ImmutablePair<Class<LeafNavigationPointPath>, Class<LeafNavigationPointPath>>(LeafNavigationPointPath.class, LeafNavigationPointPath.class), (c1, c2) -> {
			return plan(c1, c2);
		});
		
		
		if (comp1 instanceof LeafNavigationPointPath && comp2 instanceof LeafNavigationPointPath) {
			return plan((LeafNavigationPointPath) comp1, (LeafNavigationPointPath) comp2);
		}
		else if (comp1 instanceof LeafNavigationPointPath && comp2 instanceof LeafAreaPath) {
			return plan((LeafNavigationPointPath) comp1, (LeafAreaPath) comp2);
		}
		else if (comp1 instanceof LeafNavigationPointPath && comp2 instanceof CompositePath) {
			return plan((LeafNavigationPointPath) comp1, (CompositePath) comp2);
		}
		else if (comp1 instanceof LeafAreaPath && comp2 instanceof LeafAreaPath) {
			return plan((LeafAreaPath) comp1, (LeafAreaPath) comp2);
		}
		else if (comp1 instanceof LeafAreaPath && comp2 instanceof LeafNavigationPointPath) {
			return plan((LeafAreaPath) comp1, (LeafNavigationPointPath) comp2);
		}
		else if (comp1 instanceof LeafAreaPath && comp2 instanceof CompositePath) {
			return plan((LeafAreaPath) comp1, (CompositePath) comp2);
		}
		
		else if (comp1 instanceof CompositePath && comp2 instanceof CompositePath) {
			return plan((CompositePath) comp1, (CompositePath) comp2);
		}
		else if (comp1 instanceof CompositePath && comp2 instanceof LeafNavigationPointPath) {
			return plan((CompositePath) comp1, (LeafNavigationPointPath) comp2);
		}
		else if (comp1 instanceof CompositePath && comp2 instanceof LeafAreaPath) {
			return plan((CompositePath) comp1, (LeafAreaPath) comp2);
		}
		return null;
	}
	

}
