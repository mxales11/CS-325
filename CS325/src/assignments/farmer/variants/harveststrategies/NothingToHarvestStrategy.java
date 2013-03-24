package assignments.farmer.variants.harveststrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;

public class NothingToHarvestStrategy implements HarvestStrategy {

	@Override
	public void harvest() {
		System.out.println("nothing to harvest");
	}

}
