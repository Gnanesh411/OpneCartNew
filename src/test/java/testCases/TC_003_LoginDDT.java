package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven") // getting dataprovider from different
																				// class.
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {

		logger.info("******Starting TC_003_LoginDDT******");

		try {
			// Home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); // Login link under MyAccount

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin(); // Login button

			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExists();

			/*
			 * Data is valid - login success - test pass - logout login failed - test fail
			 * 
			 * Data is invalid - login success - test fail - logout login failed - test pass
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					myacc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					myacc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		Thread.sleep(3000);
		logger.info("******Finished TC_003_LoginDDT******");
	}
}
