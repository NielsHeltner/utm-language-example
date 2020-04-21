package utm.domain;

import java.util.List;

public class UtmDynamic {
	
	public static UtmDynamic instance;
	
	private UtmDynamic() {}
	
	public static UtmDynamic getInstance() {
		if (instance == null) {
			instance = new UtmDynamic();
		}
		return instance;
	}
	
}
