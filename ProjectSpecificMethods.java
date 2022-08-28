package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadData;

public class ProjectSpecificMethods {

	public static String leadId;
	public String excelFileName = "";
	private static final ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	public static ExtentReports extent;
	
	public String testName, testDesc, testAuthor, testCategory;
	
	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);				// to keep the report history
		extent = new ExtentReports();		// Step2: Create object for ExtentReports
		extent.attachReporter(reporter);				// Step3: attach the data with physical file
	}
	
	@BeforeClass
	public void testCaseDetails() {
		ExtentTest test = extent.createTest(testName, testDesc);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	
	}
	
	@AfterSuite
	public void stopReport() {
		extent.flush();
	}

	//setter method 
	public void setDriver() {
		remoteWebDriver.set(new ChromeDriver());
	}

	public RemoteWebDriver getDriver() {
		return remoteWebDriver.get();
	}

	//public static ChromeDriver driver;


	@DataProvider(name="fetch", indices = {0})

	public String[][] fetchData() throws IOException
	{
		String[][] data=ReadData.readData(excelFileName);
		return data;
	}



	@BeforeMethod
	public void init() {
		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		setDriver(); //to initialize driver
		getDriver().get("http://leaftaps.com/opentaps");
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@AfterMethod
	public void closeBrowser() {
		getDriver().close();
	}


}
