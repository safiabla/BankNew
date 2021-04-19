package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CheckingPages;
import pages.MainPage;
import utilities.Driver;

public class AccountDataBaseValidation {
    WebDriver driver = Driver.getDriver ();
    MainPage mainPage = new MainPage ();
    CheckingPages checking =  new CheckingPages ();

    @When("^user gets checking account number$")
    public void user_gets_checking_account_number() throws Throwable {
        mainPage.checkingMenu.click ();
        mainPage.viewChecking.click ();
        String actualAccountName = checking.checkingAccountName.getText ();
        Assert.assertTrue (actualAccountName.contains ("Hobbit"));
    }

    @When("^user gets account number from data base$")
    public void user_gets_account_number_from_data_base() throws Throwable {
        
    }

    @Then("^account numbers have to match$")
    public void account_numbers_have_to_match() throws Throwable {

    }
}
