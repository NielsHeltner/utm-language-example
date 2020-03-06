package utm.idsl.metamodel;

import java.util.List;
import utm.idsl.PathDescriptionVisitor;

public class Cover extends AbstractPathDescription {
	
	public Cover(List<ILocation> locations) {
		super(locations);
	}
	
	@Override
	public void accept(PathDescriptionVisitor pathDescriptionVisitor) {
		pathDescriptionVisitor.visit(this);
	}
	
}
