package pages.shared;

import helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderSection extends Element {

    public HeaderSection(Browser browser) {
        super(browser);
    }

    @FindBy(name = "q")
    private WebElement searchField;

    // This search button is actually useless - as it just opens another search box
    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(name = "btnK")
    private WebElement searchSubmitButton;

    public void setSearchField(String value) {
        searchField.sendKeys(value);
    }

    public void clickOnSearchButton() {
        // this element only appears when something is input into search field.
        searchSubmitButton.click();
    }

}
