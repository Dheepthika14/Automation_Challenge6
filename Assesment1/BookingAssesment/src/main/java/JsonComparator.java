import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonComparator {

    public static void main(String[] args) {
       JsonElement JsonData1=getJson1();
        JsonElement JsonData2=getJson2();


        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> firstJMap = g.fromJson(JsonData1, mapType);
        Map<String, Object> secondJMap = g.fromJson(JsonData2, mapType);
        MapDifference<String, Object> difference = Maps.difference(firstJMap, secondJMap);

        System.out.println("\n\nData missing in Json2\n--------------------------\n");
        difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value)); 
        

        System.out.println("\n\nData missing in Json1\n--------------------------\n");
        difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries Mismatching:\n--------------------------\n");
        difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));

    }
    public static JsonElement getJson1() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir")+"/src/main/java/json1_fileName.json");//Json1 filePath
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }
    public static JsonElement getJson2() {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement=null;
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir")+"/src/main/java/json2_fileName.json");//Json2 filePath
            jsonElement = jsonParser.parse(fileReader);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return jsonElement;
    }
}