package Tools;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScripts extends MyData{
	
	@Test(priority=5,enabled=true)
	public void SignUpTest() throws InterruptedException
	{
		//Elements
		driver.navigate().to(SignUpPage);
		WebElement FirstName=driver.findElement(By.id("first_name"));
		WebElement LastName=driver.findElement(By.id("last_name"));
		WebElement DateBirthday=driver.findElement(By.id("dob"));
		WebElement Street=driver.findElement(By.id("street"));
		WebElement ZipCode=driver.findElement(By.id("postal_code"));
		WebElement Country=driver.findElement(By.id("country"));
		WebElement State=driver.findElement(By.id("state"));
		WebElement PhoneNumber=driver.findElement(By.id("phone"));
		Select MyCountrySelection=new Select(Country);
		MyCountrySelection.selectByContainsVisibleText(CustomerCountryInDataBase);
		Thread.sleep(3000);
		WebElement City=driver.findElement(By.id("city"));
		WebElement email=driver.findElement(By.id("email"));
		WebElement password=driver.findElement(By.id("password"));
		WebElement RegisterButton=driver.findElement(By.xpath("//button[normalize-space()='Register']"));
		
		
		//Actions
		
		FirstName.sendKeys(CustomerFirstName);
		LastName.sendKeys(CustomerLastName);
		DateBirthday.sendKeys(DateOfBirth);
		Street.sendKeys(AddLine1);
		ZipCode.sendKeys(PostCode);
		City.sendKeys(cityInDatabase);
		State.sendKeys(StateInDatabase);
		PhoneNumber.sendKeys(PhoneNumberInDatabase);
		email.sendKeys(Email);
		password.sendKeys(pass);
		RegisterButton.click();	
	}
	
	@Test(priority=6)
	public void LoginTest() throws InterruptedException
	{
		driver.navigate().to(LoginPage);
		WebElement email_login=driver.findElement(By.id("email"));
		WebElement pass_login=driver.findElement(By.id("password"));
		WebElement LoginButton=driver.findElement(By.xpath("//input[@value='Login']"));
		
		email_login.sendKeys(Email);
		pass_login.sendKeys(pass);
		Thread.sleep(2000);
		LoginButton.click();
		
		Thread.sleep(3000);
		boolean actualmessage=driver.getPageSource().contains("My account");
		Assert.assertEquals(actualmessage, true);
		
	}
	

}
