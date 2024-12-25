package issuemanagertestcases;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class GeneralClass {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public GeneralClass(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for element to be visible
    public WebElement waitForVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be clickable
    public WebElement waitForClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element to be invisible
    public void waitForInvisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // Wait for an element to be present in the DOM
    public WebElement waitForPresence(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Click on a WebElement
    public void clickElement(WebElement element) {
        element.click();
    }

    // Send keys to a WebElement
    public void sendKeysToElement(WebElement element, String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    // Clear the input field
    public void clearInput(WebElement element) {
        element.clear();
    }

    // Get the text of a WebElement
    public String getElementText(WebElement element) {
        return element.getText();
    }

    // Get the value of a WebElement
    public String getElementValue(WebElement element) {
        return element.getAttribute("value");
    }

    // Get the attribute of a WebElement
    public String getElementAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    // Scroll into view for an element
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Wait for a page title to contain specific text
    public boolean waitForPageTitleContains(String titleText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.titleContains(titleText));
    }

    // Check if an element is displayed
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    // Check if an element is enabled
    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    // Check if an element is selected (for checkboxes/radio buttons)
    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    // Handle JavaScript alert - Accept the alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    // Handle JavaScript alert - Dismiss the alert
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    // Send input to a prompt alert
    public void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // Switch to a frame by name or index
    public void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    public void switchToFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    // Switch back to the main content from a frame
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Handle dynamic elements - Check if element exists
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Take a screenshot
    public void takeScreenshot(String filePath) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        screenshot.renameTo(destFile);
    }

    // Upload a file using the file input element
    public void uploadFile(WebElement fileInputElement, String filePath) {
        fileInputElement.sendKeys(filePath);
    }

    // Handle double-click on an element
    public void doubleClickElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    // Handle right-click on an element
    public void rightClickElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    // Hover over an element
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Handle dragging and dropping
    public void dragAndDropElement(WebElement sourceElement, WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    // Handle drag and drop by specifying x and y coordinates
    public void dragAndDropByCoordinates(WebElement element, int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    // Wait for a specific condition to be true (e.g., an element is visible, the title contains text)
    public boolean waitForCondition(ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(condition);
    }

    // Handle dropdown select by visible text
    public void selectDropdownByText(WebElement dropdownElement, String visibleText) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }

    // Handle dropdown select by index
    public void selectDropdownByIndex(WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    // Handle dropdown select by value
    public void selectDropdownByValue(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }

    // Get all selected options from a dropdown
    public List<WebElement> getSelectedOptions(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getAllSelectedOptions();
    }

    // Get all options from a dropdown
    public List<WebElement> getAllOptions(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getOptions();
    }

    // Handle multiple windows - Switch to a new window by title
    public void switchToWindowByTitle(String windowTitle) {
        String currentWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(windowTitle)) {
                break;
            }
        }
    }

    // Switch back to the original window
    public void switchToOriginalWindow() {
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().window(originalWindow);
    }

    // Wait for an element to be clickable and then click it
    public void waitAndClick(WebElement element, int timeoutInSeconds) {
        waitForClickable(element, timeoutInSeconds);
        clickElement(element);
    }

    // Wait for an element to be visible and then send keys to it
    public void waitAndSendKeys(WebElement element, String keys, int timeoutInSeconds) {
        waitForVisibility(element, timeoutInSeconds);
        sendKeysToElement(element, keys);
    }

    // Wait for an element to be present in DOM and then perform action
    public void waitAndPerformAction(By locator, Runnable action, int timeoutInSeconds) {
        WebElement element = waitForPresence(locator, timeoutInSeconds);
        action.run();
    }
}
