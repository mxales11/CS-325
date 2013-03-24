package assignments.farmer.variants.harveststrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.HelpStrategy;

public class WifeAndKidsHelpOutInTheGardenNeighborsHelpStrategy extends WifeAndKidsHelpStrategy {
	
	private HelpStrategy helpStrategy;
	
	public WifeAndKidsHelpOutInTheGardenNeighborsHelpStrategy(HelpStrategy helpStrategy) {
		
		this.helpStrategy = helpStrategy;
	}

	@Override
	public void harvest() {
		super.harvest();
		System.out.println("in the garden, neighbours help " + helpStrategy.help());
	}

}
