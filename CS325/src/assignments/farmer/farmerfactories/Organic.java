package assignments.farmer.farmerfactories;

import assignments.farmer.FarmerFactory;
import assignments.farmer.Season;
import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlantStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;
import assignments.farmer.seasons.Fall;
import assignments.farmer.variants.marketstrategies.*;
import assignments.farmer.seasons.Spring;
import assignments.farmer.seasons.Summer;
import assignments.farmer.variants.NoActionStrategy;
import assignments.farmer.variants.harveststrategies.EmployInternsAndVolunteersStrategy;
import assignments.farmer.variants.harveststrategies.EmployInternsAndVolunteersUPickForSquashStrategy;
import assignments.farmer.variants.marketstrategies.BeansSquashTomatoes;
import assignments.farmer.variants.marketstrategies.FallGarlicPeasLettuceStrategy;
import assignments.farmer.variants.marketstrategies.PeasCarrotsEarlyBeansRomaTomatoes;
import assignments.farmer.variants.plantstrategies.OrganicFallPlantStrategy;
import assignments.farmer.variants.plantstrategies.OrganicSpringPlantStrategy;
import assignments.farmer.variants.plantstrategies.OrganicSummerPlantStrategy;
import assignments.farmer.variants.plowstrategies.PlowFallowFieldsStrategy;
import assignments.farmer.variants.plowstrategies.PlowUnderGreenManureStrategy;
import assignments.farmer.variants.weedcontrolstrategies.EmployInternsWithHoesStrategy;

public class Organic implements FarmerFactory{
	
	private Season season;

	@Override
	public PlowStrategy createPlowStrategy() {
		if (season instanceof Spring) {
			return new PlowUnderGreenManureStrategy();
		}

		if (season instanceof Summer) {
			return new PlowFallowFieldsStrategy();
		}

		if (season instanceof Fall) {
			return new NoActionStrategy();
		}
		return null;

	}

	@Override
	public PlantStrategy createPlantStrategy() {
		if (season instanceof Spring) {

			return new OrganicSpringPlantStrategy();
		}
		if (season instanceof Summer) {

			return new OrganicSummerPlantStrategy();
		}
		if (season instanceof Fall) {

			return new OrganicFallPlantStrategy();
		}
		return null;
	}

	@Override
	public WeedControlStrategy createWeedControl() {
		if (season instanceof Spring) {
			return new EmployInternsWithHoesStrategy();
		}
		if (season instanceof Summer) {

			return new EmployInternsWithHoesStrategy();
		}
		if (season instanceof Fall) {

			return new NoActionStrategy();
		}
		return null;
	}

	@Override
	public HarvestStrategy createHarvestStrategy() {
		if (season instanceof Spring) {

			return new EmployInternsAndVolunteersStrategy();
		}
		if (season instanceof Summer) {

			return new EmployInternsAndVolunteersStrategy();
		}
		if (season instanceof Fall) {

			return new EmployInternsAndVolunteersUPickForSquashStrategy();
		}
		return null;
	}

	@Override
	public MarketStrategy createMarketStrategy() {
		if (season instanceof Spring) {

			return new FallGarlicPeasLettuceStrategy();
		}
		if (season instanceof Summer) {

			return new PeasCarrotsEarlyBeansRomaTomatoes("to farmer's market");
		}
		if (season instanceof Fall) {

			return new BeansSquashTomatoesBigHarvestPartyOnFarm("to farmer's market");
		}
		return null;
	}

	@Override
	public void setSeason(Season season) {
		this.season = season;
	}
	
	public String toString() {
		return "Organic";
	}
	
}
