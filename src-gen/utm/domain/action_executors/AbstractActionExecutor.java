package utm.domain.action_executors;

import java.util.List;

import utm.domain.ActionExecutorManager;
import utm.dsl.metamodel.AbstractAction;

public abstract class AbstractActionExecutor {
	
	protected ActionExecutorManager context;
	protected AbstractAction action;
	protected List<Path> paths;
	
	public AbstractActionExecutor(ActionExecutorManager context, AbstractAction action, List<Path> paths) {
		this.context = context;
		this.action = action;
		this.paths = paths;
	}
	
	public abstract void execute();
	
}
