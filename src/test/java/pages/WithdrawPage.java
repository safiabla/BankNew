package pages;



import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WithdrawPage {

    WebDriver driver;

    public WithdrawPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//button[@class='btn btn-primary btn-sm']")
    public WebElement submitButton;


    public void handleAlert(){

    }


}
