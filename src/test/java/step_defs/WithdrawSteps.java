package step_defs;

import org.junit.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.WithdrawPage;
import utilities.Driver;

public class WithdrawSteps {
    WebDriver driver = Driver.getDriver();
    MainPage mainPage = new MainPage();
    WithdrawPage withdrawPage = new WithdrawPage();


    @When("^user press on Withdraw button$")
    public void userPressOnWithdrawButton() {
        //withdrawPage.withDrawButton.click();

    }

    @And("^user press Submit button$")
    public void userPressSubmitButton() {
        withdrawPage.submitButton.click();
    }

    @Then("^user should be able to see error message \"([^\"]*)\"$")
    public void userShouldBeAbleToSeeErrorMessage(String expectedString) {
        Assert.assertTrue("Failed: Validation error message mismatch",
                driver.switchTo().activeElement().getAttribute("validationMessage").contains(expectedString));
    }


}
