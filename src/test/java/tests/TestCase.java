package tests;

import helper.*;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestCase extends TestHelper {

    Browser browser;
    helper.ReadFile ReadFile;
    APIResponse apiCompare;
    helper.compareUtil compareUtil = new compareUtil();
    APIResponse APIResponse;

    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        //Since this test does not involve selenium scripts, commenting the line
        //browser = new Browser(browserName, baseUrl);

    }


    @Test(groups = {"test"})
    public void test() throws InterruptedException {

        Map<Integer, List<String>> map = new HashMap<>();

        //  Read the file, changes the content under resource folder
        map = ReadFile.getApiFromText("resource/file1.txt", "resource/file2.txt");
        // hit the hit and get the response
        // pass the json/XML response and compare the util

        try {

            //To get the name
       compareUtil compareUtil = new compareUtil(APIResponse.getResponse(map.get(0).get(1)),APIResponse.getResponse(map.get(0).get(2)));

            //In case the response in Json
            //APIResponse.getResponse(map.get(0).get(1) - it will response after hitting Get API
        compareUtil.JsonComparator(APIResponse.getResponse(map.get(0).get(1)),APIResponse.getResponse(map.get(0).get(2)));


            //In case the response in XML
        //compareUtil.XMLComparator(APIResponse.getResponse(map.get(0).get(1)),APIResponse.getResponse(map.get(0).get(2)));


        } catch (FileSystemNotFoundException e) {
            e.printStackTrace();
        }


    }



    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser._driver.quit();
    }

}
