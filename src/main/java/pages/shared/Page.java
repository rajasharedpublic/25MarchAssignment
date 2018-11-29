package pages.shared;

import helper.Browser;

public class Page extends Element {
    public Page(Browser browser) {
        super(browser);
    }

    public HeaderSection HeaderSection(){
        if(headerSection == null){
            headerSection = new HeaderSection(browser);
        }
        return headerSection;
    }

    private HeaderSection headerSection;





}
