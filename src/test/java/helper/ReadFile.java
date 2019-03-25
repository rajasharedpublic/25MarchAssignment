package helper;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {


    public  Map<Integer, List<String>> getApiFromText(String filepath1 , String filepath2) {

        // create map to store
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> values = new ArrayList<>();

        try {

            File f1 = new File(filepath1);
            File f2 = new File(filepath2);

            System.out.println("Reading files using Apache IO:");

            List<String> lines1 = FileUtils.readLines(f1, "UTF-8");
            List<String> lines2 = FileUtils.readLines(f2, "UTF-8");

            // get which file is lesser, we will iterate till less content file
            List<String> lines =lines1.size() > lines2.size()? lines2: lines1;

            int i=0;
            for (String line1 : lines1) {
                for (String line2: lines1) {
                    //System.out.println(line);
                    values.add(line1); values.add(line2); map.put(i,values);
                    i++;
                    return map;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
