package assignments.farmer.seasons;

import assignments.farmer.FarmerImpl;
import assignments.farmer.Season;

public class Spring implements Season {

	private FarmerImpl farmerImpl;

	public Spring(FarmerImpl farmerImpl) {
		this.farmerImpl = farmerImpl;
	}

	public void plow() {

		farmerImpl.getPlowStrategy().plow();
	}

	public void plant() {

		farmerImpl.getPlantStrategy().plant();
	}

	public void weedControl() {

		farmerImpl.getWeedStrategy().weedControl();
	}

	public void harvest() {

		farmerImpl.getHarvestStrategy().harvest();
	}

	public void market() {

		farmerImpl.getMarketStrategy().market();
	}

	public String toString() {

		return "Spring";
	}
}
