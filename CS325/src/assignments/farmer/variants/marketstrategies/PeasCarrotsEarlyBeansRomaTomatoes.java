package assignments.farmer.variants.marketstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;

public class PeasCarrotsEarlyBeansRomaTomatoes implements MarketStrategy {

	private String where;
	
	public PeasCarrotsEarlyBeansRomaTomatoes(String where) {
		
		this.where = where;	
	}
	
	@Override
	public void market() {
		System.out.println("peas, carrots, early beans, roma tomatoes " + where);
	}
	
	

}
