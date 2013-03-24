package assignments.farmer.variants.plowstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;

public class UsingNoTill implements PlowStrategy {

	@Override
	public void plow() {
		System.out.println("using no-till; no plowing");
		
	}

}
