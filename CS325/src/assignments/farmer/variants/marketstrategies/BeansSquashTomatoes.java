package assignments.farmer.variants.marketstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;

public class BeansSquashTomatoes implements MarketStrategy {
	
	private String where;
	public BeansSquashTomatoes(String where){
		
		this.where = where;
			
	}

	@Override
	public void market() {
		System.out.println("beans, squash, tomatos " + where);
		
	}
	
	
	

}
