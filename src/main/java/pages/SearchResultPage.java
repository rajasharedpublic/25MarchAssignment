package pages;

import helper.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.shared.Page;

import java.util.List;

public class SearchResultPage extends Page {
    public SearchResultPage(Browser browser) {
        super(browser);
    }

    @FindBy(id = "search-content")
    private List<WebElement> searchResultList;

    public int getSearchResultSize() {
        return searchResultList.size();
    }
}
