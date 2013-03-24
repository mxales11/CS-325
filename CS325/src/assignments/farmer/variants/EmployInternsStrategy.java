package assignments.farmer.variants;

import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;

public class EmployInternsStrategy implements HarvestStrategy, WeedControlStrategy {
	
	
	private String employInterns = new String("employ lots of interns");
	
	public  void harvest() {
		System.out.println(employInterns);
	}

	@Override
	public void weedControl() {
		System.out.println(employInterns);	
	}
	
}
