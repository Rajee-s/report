package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.LoginPage;

public class VerifyLogin extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setup() {
		excelFileName = "Login";
		testName = "VerifyLogin";
		testDesc = "Login with Positive Data";
		testAuthor = "Hari";
	}
	
	
	@Test(dataProvider = "fetch")
	public void runLogin(String userName, String password) {
		
		LoginPage lp = new LoginPage();
		
		lp.typeUserName(userName).typePassword(password).clickLogin().verifyHomePage();
	}

}
