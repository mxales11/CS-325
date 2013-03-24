package assignments.farmer.variants.weedcontrolstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;

public class AmishSummerWeedControl extends CultivatorStrategy implements WeedControlStrategy{

	
	public void weedControl() {
		
		super.weedControl();
		System.out.println(", two-row, horse-drawn cultivator in the fields");
	}
	
	
}
