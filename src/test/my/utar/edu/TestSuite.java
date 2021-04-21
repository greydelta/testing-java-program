package my.utar.edu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddDeliveryChargesTest.class, AddItemTest.class, AddMemberTest.class, AddOrdersTest.class,
		CalculateAdditionalChargeTest.class, CalculateDeliveryChargesTest.class, CalculateGrandTotalTest.class,
		CalculateItemPriceTest.class, CalculatePromotionalPriceTest.class, CheckItemQuantityTest.class,
		IntInputValidationTest.class, MakingPaymentTest.class, OrderCalculationIntegrationTest.class,
		OrderIntegrationTest.class, StringInputValidationTest.class, ValidateAddressTest.class,
		ValidateMemberTest.class })
public class TestSuite {

}
