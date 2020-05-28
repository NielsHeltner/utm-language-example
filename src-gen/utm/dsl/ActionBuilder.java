package utm.dsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import utm.domain.datatypes.Drone;
import utm.domain.datatypes.Location;
import utm.dsl.metamodel.AbstractAction;
import utm.dsl.metamodel.OperationTemplate;
import utm.dsl.metamodel.ActionCollection;
import utm.dsl.metamodel.Straight;
import utm.dsl.metamodel.UnresolvedDrone;
import utm.dsl.metamodel.ForLoopIteration;
import utm.dsl.metamodel.SequentialForLoop;
import utm.dsl.metamodel.PrioritizedForLoop;
import utm.dsl.metamodel.ParallelForLoop;

public abstract class ActionBuilder {
	
	protected List<AbstractAction> actions;
	protected List<Drone> currentDrones;
	
	public ActionBuilder() {
		this.actions = new ArrayList<>();
	}
	
	protected abstract void buildActions();
	
	public OperationTemplate getOperationTemplate() {
		buildActions();
		return new OperationTemplate(new ActionCollection(actions));
	}
	
	public <T> List<T> array(T... arguments) {
		return Arrays.asList(arguments);
	}
	
	public ActionBuilder drone(Drone drone) {
		this.currentDrones = array(drone);
		return this;
	}
	
	public ActionBuilder drone(List<Drone> drones) {
		this.currentDrones = drones;
		return this;
	}
	
	public ActionBuilder straight(Location locations) {
		if (currentDrones == null) {
			throw new IllegalStateException("Cannot add action before assigning drone.");
		}
		actions.add(new Straight(currentDrones, array(locations)));
		return this;
	}
	
	public ActionBuilder straight(List<? extends Location> locations) {
		if (currentDrones == null) {
			throw new IllegalStateException("Cannot add action before assigning drone.");
		}
		actions.add(new Straight(currentDrones, locations));
		return this;
	}
	
	public ActionBuilder straight(Location... locations) {
		if (currentDrones == null) {
			throw new IllegalStateException("Cannot add action before assigning drone.");
		}
		actions.add(new Straight(currentDrones, array(locations)));
		return this;
	}
	
	private <T> List<ForLoopIteration> buildIterations(List<T> arguments, BiConsumer<ActionBuilder, T> body) {
		return arguments.stream().map(argument -> {
			ActionBuilder iterationBuilder = new ActionBuilder() {
				@Override
				protected void buildActions() {
					body.accept(this, argument);
				}
			};
			iterationBuilder.buildActions();
			return new ForLoopIteration(new ActionCollection(iterationBuilder.actions));
		}).collect(Collectors.toList());
	}
	
	private <T> List<ForLoopIteration> buildIterations(List<T> arguments, TriConsumer<ActionBuilder, T, Drone> body) {
		return arguments.stream().map(argument -> {
			UnresolvedDrone unresolvedDrone = new UnresolvedDrone();
			ActionBuilder iterationBuilder = new ActionBuilder() {
				@Override
				protected void buildActions() {
					body.accept(this, argument, unresolvedDrone);
				}
			};
			iterationBuilder.buildActions();
			return new ForLoopIteration(unresolvedDrone, new ActionCollection(iterationBuilder.actions));
		}).collect(Collectors.toList());
	}
	
	public <T> ActionBuilder sequentialForLoop(List<T> arguments, BiConsumer<ActionBuilder, T> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new SequentialForLoop<>(arguments, iterations));
		return this;
	}
	
	public <T> ActionBuilder sequentialForLoop(List<T> arguments, List<Drone> drones, TriConsumer<ActionBuilder, T, Drone> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new SequentialForLoop<>(arguments, drones, iterations));
		return this;
	}
	
	public <T> ActionBuilder prioritizedForLoop(List<T> arguments, BiConsumer<ActionBuilder, T> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new PrioritizedForLoop<>(arguments, iterations));
		return this;
	}
	
	public <T> ActionBuilder prioritizedForLoop(List<T> arguments, List<Drone> drones, TriConsumer<ActionBuilder, T, Drone> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new PrioritizedForLoop<>(arguments, drones, iterations));
		return this;
	}
	
	public <T> ActionBuilder parallelForLoop(List<T> arguments, BiConsumer<ActionBuilder, T> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new ParallelForLoop<>(arguments, iterations));
		return this;
	}
	
	public <T> ActionBuilder parallelForLoop(List<T> arguments, List<Drone> drones, TriConsumer<ActionBuilder, T, Drone> body) {
		List<ForLoopIteration> iterations = buildIterations(arguments, body);
		actions.add(new ParallelForLoop<>(arguments, drones, iterations));
		return this;
	}
	
	@FunctionalInterface
	public interface TriConsumer<T, U, V> {
		void accept(T t, U u, V v);
	}
	
}
