package assignments.farmer.variants.marketstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;

public class NothingToMarketStrategy implements MarketStrategy{

	@Override
	public void market() {
		System.out.println("nothing to market");
		
	}

}
