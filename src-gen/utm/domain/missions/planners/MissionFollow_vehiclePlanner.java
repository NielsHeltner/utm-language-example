package utm.domain.missions.planners;

import java.util.List;

import utm.domain.datatypes.NavigationPoint;
import utm.domain.missions.MissionFollow_vehicle;
import utm.domain.planners.Straight;
import utm.domain.planners.Cover;

public class MissionFollow_vehiclePlanner extends AbstractPlanner {

/*
 * public class MissionManager {
 * 
 * 		public Map<MetaModel, List<NavigationPoint>> routes = new HashMap<>();
 * 		public List<Area> noFlyZones = new ArrayList<>();
 * 
 * 		public onPostMission(PlannerBuilder planner, Interpreter interpreter) {
 * 			MetaModel mm = planner.getPlan();
 * 			List<NavigationPoint> plan = interpreter.interpret(mm, noFlyZones);
 * 			routes.put(mm, plan);
 * 		}
 * 
 * 		public onUpdateNoFlyZones(List<Area> newNoFlyZones) {
 * 			noFlyZones.addAll(newNoFlyZones);
 * 
 * 			
 * 			for (Map.Entry<MetaModel, List<NavigationPoint>> entry : routes.getEntries() {
 * 				List<NavigationPoint> plan = new Interpreter().interpret(entry.getKey(), noFlyZones);
 * 				routes.put(mm, plan);
 * 			}
 * 		}
 * 
 * }
 * 
 * 
 */
	
	
/*
 * public class Interpreter {
 * 
 * 		public List<NavigationPoint> interpret/plan(MetaModel model, List<Area> noFlyZones) {
 * 			return evaluate(model);
 *  	}
 *  
 *  	public Object evaluate(Object object) {
 *  		if (object instanceof Straight) {
 *  			return _evaluate((Straight) object)
 *  		}
 *  		else if (object instanceof Cover) {
 *  			return _evaluate((Cover) object)
 *  		}
 *  		else if (object instanceof NavigationPoint) {
 *  			return _evaluate((NavigationPoint) object)
 *  		}
 *  	}
 *  
 *  	_evaluate(Straight straight) {
 *  		Object from = evaluate(straight.from)
 *  		Object to = evaluate(straight.to)
 *  		List<NavigationPoint> plan = straight.plan(from, to)
 *  
 *  		return plan
 *  	}
 *  
 *  	_evaluate(Cover cover) {
 *  		Object expression = evaluate(cover.expression)
 *  		List<NavigationPoint> plan = cover.plan(expression)
 *  	}
 *  
 *  	_evaluate(NavigationPoint np) {
 *  		return np
 *  	}
 *  
 *  	_evaluate(Area a) {
 *  		return a
 *  	}
 *  
 * }
 */
	
	
/*
 * public abstract class PlannerBuilder {
 * 
 * 		public MetaModel getPlan() {
 *			return build(); 
 * 		}
 * 
 * }
 */
	
	
/*public class MissionFollow_vehiclePlanner extends PlannerBuilder {
 * 
 * @Override
 * public MetaModel build() {
 * 		return straight(straight(start, point1), slut);
 * }
 * 
 * 
 * 
 * 
 */
	
	private MissionFollow_vehicle mission;
	private Straight planner = new Straight();
	private Cover cover = new Cover(planner);
	
	public MissionFollow_vehiclePlanner(MissionFollow_vehicle mission) {
		this.mission = mission;
	}
	
	@Override
	public boolean verifyConstraints() {
		return true;
	}
	
	@Override
	public List<NavigationPoint> createPlan() {
		return planner.plan(planner.plan(planner.plan(planner.plan(mission.getStart(), mission.getPoint1()), cover.plan(mission.getZone())), mission.getPoint2()), mission.getEnd());
	}
	
	
	/*
	public List<NavigationPoint> createPlanBuilder(MissionFollow_vehicle mission) {
		straight(start).straight(point1).cover(zone).straight(point2).
		straight(end);
	}
	*/
	
}
