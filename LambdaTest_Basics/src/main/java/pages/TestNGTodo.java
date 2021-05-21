package pages;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGTodo{
	public String username = "ENTER YOUR USER NAME";
	public String accesskey = "	ENTER ACCESS KEY";
	public static RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@BeforeClass
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "70.0");
		capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
		capabilities.setCapability("build", "TestBuild1");
		capabilities.setCapability("name", "MySampleTest");

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	@Test
	public void testLambdaTestTodoPage() throws Exception {
		driver.get("https://lambdatest.github.io/sample-todo-app/");
		driver.findElement(By.name("li5")).click();
		driver.findElement(By.name("li4")).click();
		driver.findElement(By.id("sampletodotext")).click();
		driver.findElement(By.id("sampletodotext")).clear();
		driver.findElement(By.id("sampletodotext")).sendKeys("Hello World");
		driver.findElement(By.id("addbutton")).click();
		driver.findElement(By.id("sampletodotext")).click();
		driver.findElement(By.id("sampletodotext")).clear();
		driver.findElement(By.id("sampletodotext")).sendKeys("wow");
		driver.findElement(By.id("addbutton")).click();
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}
}