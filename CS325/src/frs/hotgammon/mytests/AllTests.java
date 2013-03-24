package frs.hotgammon.mytests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import frs.hotgammon.mytests.AlphaMonTests;
import frs.hotgammon.mytests.BetaMonTests;
import frs.hotgammon.mytests.DeltaMonTests;
import frs.hotgammon.mytests.GammaMonTests;
import frs.hotgammon.mytests.SemiMonTests;


@RunWith(Suite.class)
@SuiteClasses({
	BoardTest.class, LocationTest.class,
	CoreTests.class,
	AlphaMonTests.class,
	BetaMonTests.class,
	GammaMonTests.class,
	DeltaMonTests.class,
	EpsilonMonTests.class,
	HandiCapTests.class,
	ZetaMonTests.class,
	SemiMonTests.class
	})


public class AllTests {

	
}
