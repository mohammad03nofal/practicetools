package Tools;

import static org.testng.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScripts extends MyData{
	
	
	@BeforeTest
	public void SetUp() throws SQLException
	{
		driver.get(HomePage);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","0000");
		
	}
	
	@Test(priority=1,enabled=true)
	public void AddRecord() throws SQLException
	{
	String query= "INSERT INTO customers (customerNumber, customerName, contactLastName,contactFirstName, phone, addressLine1, city , state , Postalcode , country , salesRepEmployeeNumber, creditLimit) VALUE (507, 'Mohammad Nofal', 'Nofal', 'Mohammad', '0798641234', '123 King Abdullah St', 'Amman', 'Amman','6529271' ,'Jordan', 1370, 50000.00)";
	
	stmt=conn.createStatement();
	int insertedRow=stmt.executeUpdate(query);			
	}
	
	@Test(priority=3)
    public void ReadData() throws SQLException
    {
		String query="select * from customers where customerNumber=507";
		stmt=conn.createStatement();
		rs=stmt.executeQuery(query);
		int RandomNumberForTheEmail=rand.nextInt(4622);
		
		while(rs.next())
		{
			CustomerFirstName=rs.getString("contactFirstName").toString().trim();
			CustomerLastName=rs.getString("contactLastName").toString().trim();
			Email=CustomerFirstName+CustomerLastName+RandomNumberForTheEmail+Domain;
			PhoneNumberInDatabase=rs.getString("phone");
			AddLine1=rs.getString("addressLine1");
			PostCode=rs.getString("Postalcode");
			CustomerCountryInDataBase=rs.getString("country");
			cityInDatabase=rs.getString("city");
			StateInDatabase=rs.getString("state");
			
			
			
		}
    }
	
	@Test(priority=2)
	public void UpdateRecord() throws SQLException
	{
		String query="update customers set contactFirstName='Kamal' , contactLastName='Nofal'  where customerNumber=507";
		stmt=conn.createStatement();
		int updatedrow=stmt.executeUpdate(query);
	}
	
	@Test(priority=4)
	public void DeleteRecord() throws SQLException
	{
		String query="delete  from customers where customerNumber=507 ";
		stmt=conn.createStatement();
		int deletedrow=stmt.executeUpdate(query);
	}
	
	
	
	



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
		DateBirthday.sendKeys(TheDateOfBirth);
		Street.sendKeys(AddLine1);
		ZipCode.sendKeys(PostCode);
		City.sendKeys(cityInDatabase);
		State.sendKeys(StateInDatabase);
		PhoneNumber.sendKeys(PhoneNumberInDatabase);
		email.sendKeys(Email);
		password.sendKeys(pass);
		RegisterButton.click();	
	}
	@Test(priority=6,enabled=true)
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
		
		//Thread.sleep(3000);
		//boolean actualmessage=driver.getPageSource().contains("My account");
		//Assert.assertEquals(actualmessage, true);
		
	}

	
	@Test(priority=7,enabled=false)
	public void setNewPassword() throws InterruptedException
	{
		String profilepage="https://practicesoftwaretesting.com/account/profile";
		driver.navigate().to(profilepage);
		WebElement currentpass=driver.findElement(By.id("current-password"));
		WebElement newpass=driver.findElement(By.id("new-password"));
		WebElement confirmNewPassword=driver.findElement(By.id("new-password-confirm"));
		WebElement changepassButton=driver.findElement(By.xpath("//button[@data-test='change-password-submit']"));
		currentpass.sendKeys(pass);
		newpass.sendKeys(newpassword);
		confirmNewPassword.sendKeys(newpassword);
		Thread.sleep(2000);
		changepassButton.click();
	}
	
	@Test(priority=8,enabled=false)
	public void LoginTestAfterChangePassword() throws InterruptedException
	{
		driver.navigate().to(LoginPage);
		WebElement email_login=driver.findElement(By.id("email"));
		WebElement pass_login=driver.findElement(By.id("password"));
		WebElement LoginButton=driver.findElement(By.xpath("//input[@value='Login']"));
		
		email_login.sendKeys(Email);
		pass_login.sendKeys(newpassword);
		Thread.sleep(2000);
		LoginButton.click();
		
		Thread.sleep(3000);
		//boolean actualmessage=driver.getPageSource().contains("My account");
		//Assert.assertEquals(actualmessage, true);
		
		
	}
	
	@Test(priority=9,enabled=true)
	public void AddTools() throws InterruptedException
	{
		
		driver.navigate().to(handtoolsCattegory);
		
		//add hand tools
		for(int i=1;i<=3;i++)
		{
			
			 List <WebElement> HandtoolsOptions=driver.findElements(By.name("category_id"));
			 int randomcheckbox=rand.nextInt(HandtoolsOptions.size());
			 HandtoolsOptions.get(randomcheckbox).click();
			 Thread.sleep(2000);
			 boolean notoolsfound =driver.getPageSource().contains("There are no products found.");
			 if(notoolsfound)
			 { 
				 driver.navigate().to(handtoolsCattegory);
				 List <WebElement> AltHandtoolsOptions=driver.findElements(By.name("category_id"));
				 int randomcheckboxOfHand=rand.nextInt(AltHandtoolsOptions.size());
				 AltHandtoolsOptions.get(randomcheckbox).click();
			 
			 }
			 List <WebElement> toolName=driver.findElements(By.className("card-title"));
			 int randomtool=rand.nextInt(toolName.size());
			 toolName.get(randomtool).click();
			 
		boolean outofstockmesssage =driver.getPageSource().contains("Out of stock");
		
		if(!outofstockmesssage)
		{
			WebElement AddToCartButton =driver.findElement(By.cssSelector(".btn-success.btn"));
			AddToCartButton.click();
			Thread.sleep(3000);
		}
		
			
		driver.navigate().back();
		}
		
		//add power tools 
	
		driver.navigate().to(powertoolscategory);
		for(int i=1;i<=2;i++)
		{
			
			 List <WebElement> PowertoolsOptions=driver.findElements(By.name("category_id"));
			 int randomcheckbox=rand.nextInt(PowertoolsOptions.size());
			 PowertoolsOptions.get(randomcheckbox).click();
			 Thread.sleep(2000);
			 boolean notoolsfound =driver.getPageSource().contains("There are no products found.");
			 if(notoolsfound)
			 { 
				 driver.navigate().to(powertoolscategory);
				 List <WebElement> AltPowertoolsOptions=driver.findElements(By.name("category_id"));
				 int randomcheckboxOfpower=rand.nextInt(AltPowertoolsOptions.size());
				 AltPowertoolsOptions.get(randomcheckbox).click();
			 
			 }
			 List <WebElement> toolName=driver.findElements(By.className("card-title"));
			 int randomtool=rand.nextInt(toolName.size());
			 toolName.get(randomtool).click();
			 
		boolean outofstockmesssage =driver.getPageSource().contains("Out of stock");
		
		if(!outofstockmesssage)
		{
			WebElement AddToCartButton =driver.findElement(By.cssSelector(".btn-success.btn"));
			AddToCartButton.click();
			Thread.sleep(3000);
		}
		
			
		driver.navigate().to(powertoolscategory);
		}
		
		//add other tools 
		
		
		
		driver.navigate().to(othertoolscategory);
				for(int i=1;i<=2;i++)
				{
					
					 List <WebElement> OthertoolsOptions=driver.findElements(By.name("category_id"));
					 int randomcheckbox=rand.nextInt(OthertoolsOptions.size());
					 OthertoolsOptions.get(randomcheckbox).click();
					 Thread.sleep(2000);
					 boolean notoolsfound =driver.getPageSource().contains("There are no products found.");
					 if(notoolsfound)
					 { 
						 driver.navigate().to(othertoolscategory);
						 List <WebElement> AltOthertoolsOptions=driver.findElements(By.name("category_id"));
						 int randomcheckboxOfpower=rand.nextInt(AltOthertoolsOptions.size());
						 AltOthertoolsOptions.get(randomcheckbox).click();
					 
					 }
					 List <WebElement> toolName=driver.findElements(By.className("card-title"));
					 int randomtool=rand.nextInt(toolName.size());
					 toolName.get(randomtool).click();
					 
				boolean outofstockmesssage =driver.getPageSource().contains("Out of stock");
				
				if(!outofstockmesssage)
				{
					WebElement AddToCartButton =driver.findElement(By.cssSelector(".btn-success.btn"));
					AddToCartButton.click();
					Thread.sleep(3000);
				}
				
					
				driver.navigate().to(othertoolscategory);
				}
				
				
				driver.navigate().to(RentalsCategory);
				for(int i=1;i<=1;i++)
				{
					 List <WebElement> toolName=driver.findElements(By.className("card-title"));
					 int randomtool=rand.nextInt(toolName.size());
					 toolName.get(randomtool).click();
					 
				boolean outofstockmesssage =driver.getPageSource().contains("Out of stock");
				
				if(!outofstockmesssage)
				{
					WebElement AddToCartButton =driver.findElement(By.cssSelector(".btn-success.btn"));
					AddToCartButton.click();
					Thread.sleep(3000);
				}
				
					
				driver.navigate().back();
				}
				
			
		
		
		 
		
	}
				
		@Test(priority=10,enabled=true)
		public void checkoutTest() throws InterruptedException
		{
			driver.navigate().to("https://practicesoftwaretesting.com/checkout");
			WebElement checkoutbutton=driver.findElement(By.cssSelector(".btn.btn-success"));
			checkoutbutton.click();
			WebElement email_checkout=driver.findElement(By.id("email"));
			WebElement pass_checkout=driver.findElement(By.id("password"));
			WebElement LoginButton_checkout=driver.findElement(By.xpath("//input[@data-test='login-submit']"));
		    email_checkout.clear();
		    email_checkout.sendKeys(Email);
		    pass_checkout.clear();
		    pass_checkout.sendKeys(pass);
			Thread.sleep(2000);
			LoginButton_checkout.click();
			Thread.sleep(2000);
			WebElement ProceedtwoToCheckOut=driver.findElement(By.xpath("//button[@data-test='proceed-2']"));
			ProceedtwoToCheckOut.click();
			Thread.sleep(2000);
			WebElement ProceedthreeToCheckOut=driver.findElement(By.xpath("//button[@data-test='proceed-3']"));
			ProceedthreeToCheckOut.click();
			Thread.sleep(2000);
			WebElement paymentmethod=driver.findElement(By.id("payment-method"));
			Select payment=new Select(paymentmethod);
			payment.selectByIndex(2);
			WebElement confirmpayment=driver.findElement(By.xpath("//button[normalize-space()='Confirm']"));
			confirmpayment.click();
			Thread.sleep(2000);
			confirmpayment.click();
			Thread.sleep(3000);
			boolean asctualresult=driver.getPageSource().contains("Thanks for your order! Your invoice number is");
			Assert.assertEquals(asctualresult, true);
			
			
			
			
			
			
			
			
		}
		
		
}


	       
			
         
		
		
	
	
	
	
	
	
	
	
	
	

