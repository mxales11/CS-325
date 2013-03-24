package assignments.farmer.variants.harveststrategies;
import assignments.farmer.interfacesoffarmingtasksstrategies.HarvestStrategy;
import assignments.farmer.variants.EmployInternsStrategy;

public class EmployInternsAndVolunteersStrategy extends EmployInternsStrategy implements HarvestStrategy {

	public void harvest() {
		super.harvest();
		System.out.println("and volunteers");
	}
}
