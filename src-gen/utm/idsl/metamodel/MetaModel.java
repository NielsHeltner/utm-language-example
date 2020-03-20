package utm.idsl.metamodel;

import java.util.ArrayList;
import java.util.List;
import utm.idsl.PathDescriptionVisitor;

public class MetaModel {
	
	private List<AbstractPathDescription> pathDescriptions;
	
	public MetaModel() {
		this.pathDescriptions = new ArrayList<>();
	}
	
	public List<AbstractPathDescription> getPathDescriptions() {
		return this.pathDescriptions;
	}
	
	public void add(AbstractPathDescription abstractPathDescription) {
		this.pathDescriptions.add(abstractPathDescription);
	}
}
