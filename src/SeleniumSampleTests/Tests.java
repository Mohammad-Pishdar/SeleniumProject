package SeleniumSampleTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
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

        //chapter 1 link tests----------------------------------------------------------------------------
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

        //5: Test ajax button output
        driver.switchTo().window(parentWindowId);
        driver.findElement(By.cssSelector("#loadajax")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[9]/p[1]")));
        WebElement ajaxText = driver.findElement(By.xpath("/html[1]/body[1]/div[9]/p[1]"));
        if (ajaxText.isDisplayed()){
            System.out.println("Ajax text is displayed which reads: " + ajaxText.getText());
        } else {
            System.out.println("Ajax text is not displayed!");
        }

        //Chapter 2 link tests-----------------------------------------------------------------------------------
        driver.findElement(By.linkText("Home Page")).click();
        driver.findElement(By.linkText("Chapter2")).click();

        //1: Identify an element with a dynamically created id upon each refresh
        for (int i = 0; i < 2; i++) {
            WebElement dynamicElement = driver.findElement(By.xpath("//div[contains(@id, 'time')]"));
            if(dynamicElement.isDisplayed()){
                System.out.println("Found element with dynamic id on page");
            } else {
                System.out.println("Cannot find element with dynamic id on page");
            }
            driver.navigate().refresh();
        }

        //2: Find an element with name (a button in this case)
        driver.findElement(By.name("but2"));
        System.out.println("Button with a name attribute was found on Chapter2 page");

        //3: Find an element using its preceding sibling
        WebElement sibling = driver.findElement(By.xpath("//input[@id=\"but1\"]/following-sibling::input"));
        System.out.println("The value attribute of sibling button reads: " + sibling.getAttribute("value"));

        //Chapter 4 link tests----------------------------------------------------------------------------------------
        driver.findElement(By.linkText("Index")).click();
        driver.findElement(By.linkText("Chapter4")).click();

        //1: Test a mouse hover element
        WebElement hoverElement = driver.findElement(By.cssSelector("#hoverOver"));
        Actions act = new Actions(driver);
        act.moveToElement(hoverElement).perform();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("The alert text was: " + alertText);
        alert.accept();

        //2: Test a button that sends an input field value to be alerted
        WebElement inputField = driver.findElement(By.cssSelector("#blurry"));
        inputField.sendKeys("Selenium test");
        driver.findElement(By.cssSelector("#selectLoad")).click();
        Alert alert2 = driver.switchTo().alert();
        String alert2Text = alert2.getText();
        if(alert2Text.equals("Selenium test")) {
            System.out.println("The input test was successfully shown on alert");
        } else {
            System.out.println("Alert text does not match the input text");
        }
        alert2.accept();

        //Chapter 8 Link Tests----------------------------------------------------------------------------
        driver.findElement(By.cssSelector("#index")).click();
        driver.findElement(By.linkText("Chapter8")).click();

        //1: Test cookies
        Set <Cookie> ck = driver.manage().getCookies();
        System.out.println("First cookie is: " + ck.toString());
        driver.findElement(By.cssSelector("#secondCookie")).click();
        driver.navigate().refresh();
        Set <Cookie> ck2 = driver.manage().getCookies();
        System.out.println("Cookie button is pressed: \n The first cookie is now updated:\n and the second cooke also added:\n " + ck2.toString());





        driver.quit();

    }
}
