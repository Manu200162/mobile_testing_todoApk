package ejercicio1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class CreateTareas {

    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Galaxy S5 Neo");
        capabilities.setCapability("platformVersion","6.0.1");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        //implicit
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterEach
    public void closeApplication(){
        appiumDriver.quit();
    }

    @Test
    public void verifyCalculator() throws InterruptedException {
        String proyecto="Manuel"+new Date().getSeconds()+new Date().getMinutes();
        String descripcion = "Descripcion"+new Date().getSeconds();
        //click +
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // titulo textBox
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(proyecto);
        // notas textBox
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(descripcion);
        // save item
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        Thread.sleep(2000);
        //verify that the project is displayed
        Assertions.assertTrue(appiumDriver.findElement(By.xpath("//android.widget.ListView//android.widget.TextView[@text='"+proyecto+"']")).isDisplayed(),"Error no se muestra el control");
    }
}
