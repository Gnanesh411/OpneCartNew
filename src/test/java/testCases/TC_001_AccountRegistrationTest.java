package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("***** Starting TC_001_AccountRegistrationTest *****");
		
		try {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister(); 
		logger.info("Clicked on Clicked on Register Link");

		AccountRegistrationPage rp = new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details");
		rp.setFirstName(randomeString().toUpperCase());
		rp.setLastName(randomeString().toUpperCase());
		rp.setEmail(randomeString().concat("@gmail.com"));// randomly generated the email
		rp.setTelephone(randomeNumber());

		String password = randomeAlphaNumberic();

		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.setPrivacyPolicy();
		rp.clickContinue();

		logger.info("Validating expected message....");
		String confmsg = rp.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed.....");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("***** Finished TC_001_AccountRegistrationTest *****");

	}

}