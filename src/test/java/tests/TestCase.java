package tests;

import helper.*;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestCase extends TestHelper {

    Browser browser;
    helper.ReadFile ReadFile;
    APIResponse apiCompare;
    helper.compareUtil compareUtil;

    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        //Since this test does not involve selenium scripts, commenting the line
        //browser = new Browser(browserName, baseUrl);

    }


    @Test(groups = {"test"})
    public void test() throws InterruptedException {

        Map<Integer, List<String>> map = new HashMap<>();
        // todo: add assert
        //  Read the file, changes the content under resource folder
        map = ReadFile.getApiFromText("resource/file1.txt", "resource/file2.txt");
        // hit the hit and get the response
        // pass the json/XML response and compare the util



        compareUtil.JsonComparator(map.get(0).get(1),map.get(0).get(2));

        Object  test = compareUtil.XMLComparator(map.get(0).get(1), map.get(0).get(2));


    }



    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser._driver.quit();
    }

}
