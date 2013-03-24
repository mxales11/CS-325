package assignments.farmer.variants.marketstrategies;
 public class BeansSquashTomatoesBigHarvestPartyOnFarm extends BeansSquashTomatoes {

	public BeansSquashTomatoesBigHarvestPartyOnFarm(String where) {
		super(where);
	}

	@Override
	public void market() {
		super.market();
		System.out.println("big harvest party on farm");
	}

}
