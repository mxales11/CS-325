package assignments.farmer;

import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlantStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;

public interface FarmerFactory {

	public PlowStrategy createPlowStrategy();
	
	public PlantStrategy createPlantStrategy();
	
	public WeedControlStrategy createWeedControl();
	
	public HarvestStrategy createHarvestStrategy();

	public MarketStrategy createMarketStrategy();
	
	public void setSeason(Season season);
	
	public String toString();
	
}
