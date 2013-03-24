package assignments.farmer.variants;

import assignments.farmer.interfacesoffarmingtasksstrategies.PlantStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;

public class NoActionStrategy implements PlantStrategy, PlowStrategy, WeedControlStrategy  {

	
	String noAction = new String("no action");
	@Override
	public void weedControl() {
		System.out.println(noAction);
		
	}

	@Override
	public void plant() {
		System.out.println(noAction);
		
		
	}

	@Override
	public void plow() {
		System.out.println(noAction);	
	}

}
