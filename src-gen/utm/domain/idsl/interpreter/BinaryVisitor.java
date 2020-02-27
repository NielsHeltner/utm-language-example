package utm.domain.idsl.interpreter;

import java.util.List;

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
	
	
	
	default CompositePath plan(PathComponent comp1, PathComponent comp2) {
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
