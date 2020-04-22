package utm.webservice.objectmapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import utm.domain.datatypes.Location;
import utm.domain.datatypes.Area;
import utm.domain.datatypes.NavigationPoint;

public class LocationObjectMapper extends JsonDeserializer<Location> {

	@Override
	public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectNode node = jp.readValueAsTree();
		Class<? extends Location> concreteClass = determineConcreteClass(node);
		return jp.getCodec().treeToValue(node, concreteClass);
	}

	private Class<? extends Location> determineConcreteClass(ObjectNode node) {
		if (node.has("boundingBox")) {
			return Area.class;
		}
		return NavigationPoint.class;
	}
	
}
