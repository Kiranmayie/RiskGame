package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class RiskTestsSuite.
 */
@RunWith(Suite.class)
@SuiteClasses({ ContestantTerritories.class, MapValidate.class, Reinforce.class, PlayerAssignmentTest.class, AttackPhase.class })
public class RiskTestsSuite {

}
