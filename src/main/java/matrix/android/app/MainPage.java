package matrix.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    WebElement status;

    public MainPage(AppiumSession session) {
        this.driver = session.getDriver();
    }

    public void fillData(String size, String module, boolean print, String httpEndpoint) {
        this.driver.findElement(By.id("matrix_size")).sendKeys(size);
        this.driver.findElement(By.id("matrix_module")).sendKeys(module);
        if (print){
            WebElement printButton = this.driver.findElement(By.id("matrix_print"));
            if ("false".equals(printButton.getAttribute("checked"))){
                printButton.click();
            }
        } else {
            WebElement printButton = this.driver.findElement(By.id("matrix_print"));
            if ("true".equals(printButton.getAttribute("checked"))){
                printButton.click();
            }
        }
        this.driver.findElement(By.id("http_endpoint")).sendKeys(httpEndpoint);
    }

    public void compute() {
        this.driver.findElement(By.id("compute")).click();
    }

    public boolean isFinished(){
        if (this.status == null) {
            WebDriverWait wait = new WebDriverWait(this.driver, 5);

            this.status = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.id("status")
                    )
            );
        }


        if (this.status.getText().equals("Done.")) {
            return true;
        }
        return false;
    }

    public String getData() {
        return this.driver.findElement(By.id("matrix_timing")).getText();
    }

    public void quit() {
        this.driver.quit();
    }

}
