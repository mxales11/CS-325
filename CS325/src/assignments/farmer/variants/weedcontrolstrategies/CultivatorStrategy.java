package assignments.farmer.variants.weedcontrolstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.*;
public class CultivatorStrategy implements WeedControlStrategy {

	@Override
	public void weedControl() {
		System.out.println("walking cultivator in garden");	
	}

}
