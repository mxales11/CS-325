package assignments.farmer.farmerfactories;

import assignments.farmer.FarmerFactory;
import assignments.farmer.Season;
import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.MarketStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlantStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.PlowStrategy;
import assignments.farmer.interfacesoffarmingtasksstrategies.WeedControlStrategy;
import assignments.farmer.seasons.Fall;
import assignments.farmer.seasons.Spring;
import assignments.farmer.seasons.Summer;
import assignments.farmer.variants.NoActionStrategy;
import assignments.farmer.variants.harveststrategies.HireCombineStrategy;
import assignments.farmer.variants.harveststrategies.NothingToHarvestStrategy;
import assignments.farmer.variants.harveststrategies.WifeAndKidsHelpOutInTheGardenNeighborsHelpStrategy;
import assignments.farmer.variants.harveststrategies.WifeAndKidsHelpStrategy;
import assignments.farmer.variants.harveststrategies.helpstrategy.WithCornAndHay;
import assignments.farmer.variants.harveststrategies.helpstrategy.WithOatsAndHay;
import assignments.farmer.variants.marketstrategies.BeansSquashTomatoes;
import assignments.farmer.variants.marketstrategies.FeedCornToElevatorStrategy;
import assignments.farmer.variants.marketstrategies.JamsJelliesPeasLettuceStrategy;
import assignments.farmer.variants.marketstrategies.NothingToMarketStrategy;
import assignments.farmer.variants.marketstrategies.PeasCarrotsEarlyBeansRomaTomatoes;
import assignments.farmer.variants.plantstrategies.AmishFallPlantStrategy;
import assignments.farmer.variants.plantstrategies.AmishSpringPlantStrategy;
import assignments.farmer.variants.plantstrategies.AmishSummerPlantStrategy;
import assignments.farmer.variants.plantstrategies.ConventionalSummerPlantStrategy;
import assignments.farmer.variants.plowstrategies.PlowCornFields;
import assignments.farmer.variants.plowstrategies.UsingNoTill;
import assignments.farmer.variants.weedcontrolstrategies.AmishSummerWeedControl;
import assignments.farmer.variants.weedcontrolstrategies.CultivatorStrategy;
import assignments.farmer.variants.weedcontrolstrategies.SprayStrategy;

public class Conventional implements FarmerFactory {

	private Season season;

	@Override
	public PlowStrategy createPlowStrategy() {
		if (season instanceof Spring) {

			return new UsingNoTill();
		}

		if (season instanceof Summer) {

			return new NoActionStrategy();
		}

		if (season instanceof Fall) {
			return new NoActionStrategy();
		}
		return null;

	}

	@Override
	public PlantStrategy createPlantStrategy() {
		if (season instanceof Spring) {

			return new NoActionStrategy();
		}
		if (season instanceof Summer) {

			return new ConventionalSummerPlantStrategy();
		}
		if (season instanceof Fall) {

			return new NoActionStrategy();
		}
		return null;
	}

	@Override
	public WeedControlStrategy createWeedControl() {
		if (season instanceof Spring) {
			return new NoActionStrategy();
		}
		if (season instanceof Summer) {

			return new SprayStrategy();
		}
		if (season instanceof Fall) {

			return new NoActionStrategy();
		}
		return null;
	}

	@Override
	public HarvestStrategy createHarvestStrategy() {
		if (season instanceof Spring) {

			return new NothingToHarvestStrategy();
		}
		if (season instanceof Summer) {

			return new NothingToHarvestStrategy();
		}
		if (season instanceof Fall) {

			return new HireCombineStrategy();
		}
		return null;
	}

	@Override
	public MarketStrategy createMarketStrategy() {
		if (season instanceof Spring) {

			return new NothingToMarketStrategy();
		}
		if (season instanceof Summer) {

			return new NothingToMarketStrategy();
		}
		if (season instanceof Fall) {

			return new FeedCornToElevatorStrategy();
		}
		return null;
	}

	@Override
	public void setSeason(Season season) {
		this.season = season;
	}

	public String toString() {
		return "Conventional";
	}
}
