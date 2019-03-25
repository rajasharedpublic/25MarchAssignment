package helper;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.custommonkey.xmlunit.XMLUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader; import java.util.List;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

//Read more: https://javarevisited.blogspot.com/2017/04/how-to-compare-two-xml-files-in-java.html#ixzz5j9zTa5cR

public class compareUtil {


    public static Object JsonComparator(Object obj1, Object obj2) throws JSONException {

        JSONObject diff = new JSONObject();

        if (!obj1.getClass().equals(obj2.getClass())) {
            return diff;
        }

        if (obj1 instanceof JSONObject && obj2 instanceof JSONObject) {
            JSONObject jsonObj1 = (JSONObject) obj1;

            JSONObject jsonObj2 = (JSONObject) obj2;

            List<String> names = new ArrayList(Arrays.asList(JSONObject.getNames(jsonObj1)));
            List<String> names2 = new ArrayList(Arrays.asList(JSONObject.getNames(jsonObj2)));
            if (!names.containsAll(names2) && names2.removeAll(names)) {
                for (String fieldName : names2) {
                    if(jsonObj1.has(fieldName))
                        diff.put(fieldName, jsonObj1.get(fieldName));
                    else if(jsonObj2.has(fieldName))
                        diff.put(fieldName, jsonObj2.get(fieldName));
                }
                names2 = Arrays.asList(JSONObject.getNames(jsonObj2));
            }

            if (names.containsAll(names2)) {
                for (String fieldName : names) {
                    Object obj1FieldValue = jsonObj1.get(fieldName);
                    Object obj2FieldValue = jsonObj2.get(fieldName);
                    Object obj = jsonsEqual(obj1FieldValue, obj2FieldValue);
                    if (obj != null && !checkObjectIsEmpty(obj))
                        diff.put(fieldName, obj);
                }
            }
            return diff;
        } else if (obj1 instanceof JSONArray && obj2 instanceof JSONArray) {

            JSONArray obj1Array = (JSONArray) obj1;
            JSONArray obj2Array = (JSONArray) obj2;
            if (!obj1Array.toString().equals(obj2Array.toString())) {
                JSONArray diffArray = new JSONArray();
                for (int i = 0; i < obj1Array.length(); i++) {
                    Object obj = null;
                    matchFound: for (int j = 0; j < obj2Array.length(); j++) {
                        obj = jsonsEqual(obj1Array.get(i), obj2Array.get(j));
                        if (obj == null) {
                            break matchFound;
                        }
                    }
                    if (obj != null)
                        diffArray.put(obj);
                }
                if (diffArray.length() > 0)
                    return diffArray;
            }
        } else {
            if (!obj1.equals(obj2)) {
                return obj2;
            }
        }

        return null;
    }

    private static boolean checkObjectIsEmpty(Object obj) {
        if (obj == null)
            return true;
        String objData = obj.toString();
        if (objData.length() == 0)
            return true;
        if (objData.equalsIgnoreCase("{}"))
            return true;
        return false;
    }

    public static Object XMLComparator( String filepath1, String filepath2) throws FileNotFoundException {

        // reading two xml file to compare in Java program
        FileInputStream fis1 = new FileInputStream(filepath1);
        FileInputStream fis2 = new FileInputStream(filepath2);

        // using BufferedReader for improved performance
    BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
    BufferedReader target = new BufferedReader(new InputStreamReader(fis2));
    //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);
        //comparing two XML using XMLUnit in Java
        List differences = null;
        try {
            differences = compareXML(source, target);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //showing differences found in two xml files
        printDifferences(differences);


        return differences;
    }

    public static List compareXML(Reader source, Reader target) throws SAXException, IOException{
        //creating Diff instance to compare two XML files

        Diff xmlDiff = new Diff(source, target);

        // for getting detailed differences between two xml files
        DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
        return detailXmlDiff.getAllDifferences();

    }
        //
        public static void printDifferences(List differences){
            int totalDifferences = differences.size();
            System.out.println("===============================");
            System.out.println("Total differences : " + totalDifferences);
            System.out.println("================================");

            for( Object difference : differences)
            {
                System.out.println(difference);
            }

        }

}

