package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_Login() {
		logger.info("*********Starting TC_002_LoginTets*********");
		
		try {
		
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		//My Account Page
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();	
		Assert.assertEquals(targetPage,true);//Assert.assertEquals(targetPage, true,"Login Failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished TC_002_LoginTest******");
	}
	
}
