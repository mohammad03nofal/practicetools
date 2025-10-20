package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyData {
	
	WebDriver driver=new EdgeDriver();
	Connection conn;
	Statement stmt;
	ResultSet rs;
	String HomePage="https://practicesoftwaretesting.com/";
	String SignUpPage="https://practicesoftwaretesting.com/auth/register";
	String LoginPage="https://practicesoftwaretesting.com/auth/login";
	String CustomerFirstName;
	String CustomerLastName;
	String DateOfBirth="2003-12-31";
	String Email;
	String PhoneNumberInDatabase;
	String AddLine1;
	String CustomerCountryInDataBase;
	String PostCode;
	String loginnam;
	String pass="P@asswor1D";
	String StateInDatabase;
	String cityInDatabase;
	Random rand=new Random();
	int RandomNumberForTheEmail1=rand.nextInt(4658);
	int RandomNumberForTheEmail2=rand.nextInt(4652);
	int RandomNumberForTheEmail=RandomNumberForTheEmail1*RandomNumberForTheEmail2-6000;
	
	
	
	@BeforeTest
	public void SetUp() throws SQLException
	{
		driver.navigate().to(HomePage);
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
		
		while(rs.next())
		{
			CustomerFirstName=rs.getString("contactFirstName").toString().trim();
			CustomerLastName=rs.getString("contactLastName").toString().trim();
			Email=CustomerFirstName+CustomerLastName+RandomNumberForTheEmail+"@gmail.com";
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
		String query="update customers set contactFirstName='Hala' where customerNumber=507";
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
	
	
	
	

}
