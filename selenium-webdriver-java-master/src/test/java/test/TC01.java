package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TC01 {
    public static void main(String[] args) {

        // Create an instance of the ChromeDrive
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Navigate to the specified URL
        driver.get("http://live.techpanda.org/");

        // Step 2: Verify the title of the page
        String expectedTitle = "Home page";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verification successful " + actualTitle);
        } else {
            System.out.println("Title verification failed " + actualTitle);
        }

        // Step 3: Click on the "MOBILE" menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 4: Select SORT BY -> dropdown as name
        WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText("Name");

        // Step 5: Verify all products are sorted by name
        WebElement productList = driver.findElement(By.className("products-grid"));
        if (isSorted(productList, "h2")) {
            System.out.println("Products are sorted by name");
        } else {
            System.out.println("Products are not sorted by name");
        }

        // Close the browser
        driver.quit();
    }

    private static boolean isSorted(WebElement element, String tag) {
        java.util.List<WebElement> elements = element.findElements(By.tagName(tag));
        String[] arr = new String[elements.size()];

        for (int i = 0; i < elements.size(); i++) {
            arr[i] = elements.get(i).getText();
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }

        return true;
    }
}

