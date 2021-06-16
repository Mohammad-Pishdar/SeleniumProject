package SeleniumSampleTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class Tests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohpi\\OneDrive\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://book.theautomatedtester.co.uk/");


        //check that the website is up and running by searching for a specific text
        WebElement header = driver.findElement(By.cssSelector("body:nth-child(2) > div.mainheading"));
        String headerText = header.getText();
        String expectedHeaderText = "Selenium: Beginners Guide";
        Assert.assertEquals(headerText, expectedHeaderText);

        //chapter 1 link tests
        driver.findElement(By.linkText("Chapter1")).click();

        //1: Asserting that chapter1 specific text is on the page per instructions
        WebElement chapter1PageHeader = driver.findElement(By.cssSelector("#divontheleft"));
        String chapter1PageHeaderText = chapter1PageHeader.getText();
        String expectedChapter1PageHeaderText = "Assert that this text is on the page";
        Assert.assertEquals(chapter1PageHeaderText, expectedChapter1PageHeaderText);

        //2: test dropdown menu by selecting an item from it
        Select dropdown = new Select(driver.findElement(By.cssSelector("#selecttype")));
        dropdown.selectByVisibleText("Selenium Grid");
        dropdown.selectByVisibleText("Selenium Core");

        //3: Verifying that the button is there on page
        WebElement verifyButton = driver.findElement(By.cssSelector("#verifybutton"));
        if(verifyButton.isDisplayed()) {
            System.out.println("Verify button is there");
        } else {
            System.out.println("Verify button is not displayed");
        }

        //4: Check to see that popup window works
        driver.findElement(By.xpath("/html[1]/body[1]/div[5]")).click();
        Set<String> handler = driver.getWindowHandles();
        Iterator <String> it = handler.iterator();
        String parentWindowId = it.next();
        System.out.println("Parent window id: " + parentWindowId);
        String childWindowId = it.next();
        System.out.println("Child window id: " + childWindowId);
        driver.switchTo().window(childWindowId);
        WebElement popupWindowText = driver.findElement(By.cssSelector("#popuptext"));
        if(popupWindowText.isDisplayed()){
            System.out.println(popupWindowText.getText());
        } else {
            System.out.println("Popup window text is not displayed");
        }
        driver.findElement(By.cssSelector("#closepopup")).click();



        driver.quit();

    }
}
