package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.LoginPage;


public class CreateLead extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setup() {
		excelFileName = "CreateLead";
		testName = "CreateLead";
		testDesc = "Create Lead with mandatory fields";
		testAuthor = "Babu";
	}
	
	@Test(dataProvider = "fetch")
	public void runLogin(String userName, String password, String firstName, String lastName, String companyName) {
		
		LoginPage lp = new LoginPage();
		
		lp.typeUserName(userName).typePassword(password).clickLogin().clickCrmsfaLink().clickLeadsLink().clickCreateLeadsLink().typeFirstName(firstName).typeLastName(lastName)
		.typeCompanyName(companyName).clickCreateLeadsButton().verifyFirstName(firstName);
		
		
	}

}
