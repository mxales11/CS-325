package assignments.farmer.variants.plowstrategies;

import assignments.farmer.interfacesoffarmingtasksstrategies.*;

public class PlowFallowFieldsStrategy implements PlowStrategy {

	@Override
	public void plow() {
		System.out.println("plow fallow fields to prepare for fall cover crop");		
	}

}
