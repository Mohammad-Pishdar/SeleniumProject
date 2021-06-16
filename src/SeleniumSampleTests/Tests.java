package SeleniumSampleTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Tests {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohpi\\OneDrive\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://book.theautomatedtester.co.uk/");


        //check that the website is up and running by searching for a specific text
        WebElement header= driver.findElement(By.cssSelector("body:nth-child(2) > div.mainheading"));
        String headerText = header.getText();
        String expectedHeaderText = "Selenium: Beginners Guide";
        Assert.assertEquals(headerText, expectedHeaderText);

        driver.quit();

    }
}
