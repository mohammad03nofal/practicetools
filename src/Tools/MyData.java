
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
	String forgotPassword="https://practicesoftwaretesting.com/auth/forgot-password";
	String handtoolsCattegory="https://practicesoftwaretesting.com/category/hand-tools";
	String powertoolscategory="https://practicesoftwaretesting.com/category/power-tools";
	String othertoolscategory="https://practicesoftwaretesting.com/category/other";
	String RentalsCategory="https://practicesoftwaretesting.com/rentals";
	String CustomerFirstName;
	String CustomerLastName;
	String Domain="@gmail.com";
	Random rand=new Random();
	int Year = rand.nextInt(1950,2008);
    int Month = rand.nextInt(1,13);
    int Day = rand.nextInt(1,31);
    String TheDateOfBirth = String.format("%04d-%02d-%02d", Year, Month, Day);
	String Email;
	String PhoneNumberInDatabase;
	String AddLine1;
	String CustomerCountryInDataBase;
	String PostCode;
	String loginnam;
	String pass="Mah11@1962mo";
	String newpassword="Ma@h10116969";
	String StateInDatabase;
	String cityInDatabase;
	
	

}
	
	
	
	