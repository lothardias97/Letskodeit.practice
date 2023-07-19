package letskodeit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class Letskodeit {

    WebDriver driver;

    @Before
    public void openUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--disable-notifications");
        driver = new ChromeDriver(option);
        driver.get("https://www.letskodeit.com/practice");
    }

    @Test
    public void checkbox() {
        WebElement bmw = driver.findElement(By.id("bmwcheck"));
        bmw.click();
        WebElement benz = driver.findElement(By.id("benzcheck"));
        benz.click();
        WebElement Honda = driver.findElement(By.id("hondacheck"));
        Honda.click();
    }

    @Test
    public void radiobutton() {
        WebElement benz = driver.findElement(By.id("benzradio"));
        benz.click();
        WebElement bmw = driver.findElement(By.cssSelector("#bmwradio"));
        bmw.click();
        WebElement honda = driver.findElement(By.xpath("//input[@id='hondaradio']"));
        honda.click();
        if (honda.isSelected()) {
            System.out.println("Test will pass");
        } else {
            System.out.println("Test will fail");
        }
    }

    @Test
    public void Selectexample() throws InterruptedException {
        WebElement selectindex = driver.findElement(By.id("carselect"));
        Select sel = new Select(selectindex);
        sel.selectByIndex(2);
        sel.selectByVisibleText("Benz");
        sel.selectByValue("honda");
        System.out.println(sel.getOptions());
        List<WebElement> total = sel.getOptions();
        int boxsize = total.size();
        System.out.println("boxsize is" + boxsize);
        for (int i = 0; i < boxsize; i++) {
            String names = (total.get(i).getText());
            System.out.println(names);
        }
    }

    @Test
    public void multiselect() throws InterruptedException {
        WebElement multi = driver.findElement(By.id("multiple-select-example"));
        Select select = new Select(multi);
        select.selectByValue("apple");
        select.selectByVisibleText("Orange");
        select.selectByIndex(2);
        Thread.sleep(15000);
        List<WebElement> totalmulti = select.getOptions();
        int size = totalmulti.size();
        for (int i = 0; i < size; i++) {
            String fruits = totalmulti.get(i).getText();
            System.out.println(fruits);
        }
    }

    @Test
    public void Topclick() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        WebElement hover = driver.findElement(By.cssSelector("#mousehover"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hover).build().perform();
        WebElement top = driver.findElement(By.xpath("//div[@class='mouse-hover-content']/a[1]"));
        top.click();
    }

    @Test
    public void reload() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        WebElement hover = driver.findElement(By.cssSelector("#mousehover"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hover).build().perform();
        WebElement reloadclick = driver.findElement(By.xpath("//div[@class='mouse-hover-content']/a[2]"));
        reloadclick.click();
    }


    @Test

    public void alertAndconfirm() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        WebElement type = driver.findElement(By.cssSelector("[placeholder='Enter Your Name']"));
        type.sendKeys("Mitul patel");
        WebElement alert = driver.findElement(By.id("alertbtn"));
        alert.click();
        Alert alert1 = driver.switchTo().alert();
        alert1.accept();
        WebElement type1 = driver.findElement(By.cssSelector("[name='enter-name']"));
        type1.sendKeys("I need more practice");
        WebElement confirm = driver.findElement(By.id("confirmbtn"));
        confirm.click();
        Alert confirm1 = driver.switchTo().alert();
        confirm1.dismiss();
    }

    @Test
    public void CourseAndprice() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        WebElement course = driver.findElement(By.xpath("//table[@id='product']/tbody/tr[2]/td[2]"));
        String name = course.getText();
        Assert.assertThat(name, Matchers.equalTo("Selenium WebDriver With Java"));
        Assert.assertThat(name, Matchers.endsWith("Java"));
        WebElement price = driver.findElement(By.xpath("//table[@id='product']/tbody/tr[2]/td[3]"));
        String amount = price.getText();
        Assert.assertThat(amount, Matchers.equalTo("35"));
    }

    @Test
    public void showAndHide() throws InterruptedException {

        WebElement text = driver.findElement(By.cssSelector("#displayed-text"));
        text.sendKeys("Hello");
        WebElement hideBox = driver.findElement(By.id("hide-textbox"));
        hideBox.click();
        Thread.sleep(5000);
        WebElement showBox = driver.findElement(By.id("show-textbox"));
        showBox.click();
    }

    @Test
    public void iframe() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,900)");
        WebElement frame = driver.findElement(By.id("courses-iframe"));
        driver.switchTo().frame(frame);
        WebElement box = driver.findElement(By.cssSelector("[class='form-control find-input dynamic-text']"));
        box.sendKeys("Selenium");
        Thread.sleep(2500);
    }


    @After

    public void close() {
        driver.close();
    }


}







