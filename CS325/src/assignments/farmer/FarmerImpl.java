package assignments.farmer;

import assignments.farmer.farmerfactories.Amish;
import assignments.farmer.farmerfactories.Conventional;
import assignments.farmer.farmerfactories.Organic;
import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlantStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;
import assignments.farmer.seasons.Fall;
import assignments.farmer.seasons.Spring;
import assignments.farmer.seasons.Summer;

public class FarmerImpl implements Farmer {

	private FarmerFactory farmerFactory;

	private PlowStrategy plowStrategy;
	private PlantStrategy plantStrategy;
	private WeedControlStrategy weedControlStrategy;
	private HarvestStrategy harvestStrategy;
	private MarketStrategy marketStrategy;
	private Season currentSeason;

	public void createFarmingTasksStrategies() {

		plowStrategy = farmerFactory.createPlowStrategy();
		plantStrategy = farmerFactory.createPlantStrategy();
		weedControlStrategy = farmerFactory.createWeedControl();
		harvestStrategy = farmerFactory.createHarvestStrategy();
		marketStrategy = farmerFactory.createMarketStrategy();

	}

	public FarmerImpl(FarmerFactory farmerFactory) {
		
		this.currentSeason = new Spring(this);
		farmerFactory.setSeason(currentSeason);
		this.farmerFactory = farmerFactory;
		createFarmingTasksStrategies();
	
	}

	@Override
	public void plow() {
		currentSeason.plow();
	}

	@Override
	public void plant() {
		currentSeason.plant();
	}

	@Override
	public void weedControl() {
		currentSeason.weedControl();
	}

	@Override
	public void harvest() {
		currentSeason.harvest();
	}

	@Override
	public void market() {
		currentSeason.market();
	}

	@Override
	public void changeSeason() {
		
		if (currentSeason instanceof Spring) {
			currentSeason = new Summer(this);
		}
		else if (currentSeason instanceof Summer) {
			currentSeason = new Fall(this);
		}

		else if (currentSeason instanceof Fall) {
			currentSeason = new Spring(this);
		}
		else if(currentSeason == null){
			currentSeason = new Spring(this);
		}
			farmerFactory.setSeason(currentSeason);
			createFarmingTasksStrategies();
	}

	public PlowStrategy getPlowStrategy() {
		return plowStrategy;
	}

	public PlantStrategy getPlantStrategy() {
		return plantStrategy;
	}

	public WeedControlStrategy getWeedStrategy() {
		return weedControlStrategy;
	}

	public HarvestStrategy getHarvestStrategy() {
		return harvestStrategy;
	}

	public MarketStrategy getMarketStrategy() {
		return marketStrategy;
	}

	public static void main(String[] args) {

		final int NUMBER_OF_INTERATIONS_OVER_SEASONS = 2;
		final int NUMBER_OF_SEASONS = 3;

		FarmerFactory[] farmerFactories = { new Amish(), new Organic(),
				new Conventional() };

		for (int k = 0; k < farmerFactories.length; k++) {

			FarmerImpl farmerImpl = new FarmerImpl(farmerFactories[k]);
			
			System.out.println("Farmer is "+ farmerFactories[k]);
			System.out.println();
			

			for (int i = 0; i < NUMBER_OF_INTERATIONS_OVER_SEASONS; i++) {

				for (int m = 0; m < NUMBER_OF_SEASONS; m++) {
					
					System.out.println("It is " + farmerImpl.currentSeason);
					System.out.println();
					farmerImpl.plow();
					farmerImpl.plant();
					farmerImpl.weedControl();
					farmerImpl.harvest();
					farmerImpl.market();
					System.out.println();
					farmerImpl.changeSeason();
				}
			}
			System.out.println();
		}
	}

}
