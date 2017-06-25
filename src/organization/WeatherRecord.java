package organization;

/*
*Created by: Kyle Loomis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

/*
*References:
*  - Info on how the Scanner class works: https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
*  - Splitting lines of data into an array: http://stackoverflow.com/questions/6086381/split-string-into-an-array-of-string
 */

// imported for the sake of being able to read files and scan their contents
import java.util.Scanner;
import java.io.File;

// used to store data from weather_10000.txt file
public class WeatherRecord {

    // all member variables here are info taken from API file
    private String city;
    private String country;
    private double longitude;
    private double latitude;
    private double temperature;
    private double minimum;
    private double maximum;
    private String description;

    // constructor
    public WeatherRecord(String city, String country, double longitude, double latitude,
                         double temperature, double minimum, double maximum, String description) {
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.minimum = minimum;
        this.maximum = maximum;
        this.description = description;
    }

    public static WeatherRecord parse(HashMap map, String fileName) {
        Scanner reader = null;
        // try-catch block avoid any issues with locating file
        try {
            reader = new Scanner(new File(fileName));
        } catch (Exception e) {
            System.out.println("File " + fileName + " not found");
        }
        WeatherRecord wr = null;
        // loops through file until reader doesn't have any more lines to read
        while (reader != null && reader.hasNext()) {
            // splits up line into string array
            String[] arr = reader.nextLine().split("\\;");
            // string array values casted to doubles in the case of longitude,
            // latitude, temperature, minimum, and maximum values
            wr = new WeatherRecord(arr[0],arr[1],Double.valueOf(arr[2]),Double.valueOf(arr[3]),
                    Double.valueOf(arr[4]),Double.valueOf(arr[5]),Double.valueOf(arr[6]),arr[7]);
            // add WeatherRecord object to HashMap
            // hashes with key of city name
            map.put(arr[0],wr);
        }
        // returns wr so we can use it to test containsValue's efficiency
        return wr;
    }

    public static void main(String[] args) {
        HashMap<String, WeatherRecord> map = new HashMap<>();
        // replace filename destination with directory to weather_10000.txt file
        WeatherRecord test = parse(map, "/Users/kloomis/Google Drive/spring2017/csci1933/projects/project5/src/weather_10000.txt");

        System.out.println(map.toString() + "\n");
        System.out.println("Ede".hashCode()%128);
        //These print statements were used as tests to determine runtime efficiency
        System.out.println("containsKey test 1: " + map.containsKey("Meissen"));
        System.out.println("containsKey test 2: " + map.containsKey("shoes"));
        System.out.println("containsKey test 3: " + map.containsKey("Nilokheri"));
        System.out.println("containsKey test 4: " + map.containsKey("Bani Walid"));
        System.out.println("containsKey test 5: " + map.containsKey("this can't exist") + "\n");

//        System.out.println("containsValue test 1: " + map.containsValue(new WeatherRecord("Bani Walid", "LY", 13.994220, 31.756620, 297.164000, 297.164000, 297.164000, "Sky is Clear")));
//        System.out.println("containsValue test 2: " + map.containsValue(test) + "\n");
//
//        System.out.println("get test 1: " + map.get("Meissen"));
        System.out.println("get test 2: " + map.get("shoes"));
        System.out.println("get test 3: " + map.get("Nilokheri"));
        System.out.println("get test 4: " + map.get("Bani Walid"));
        System.out.println("get test 5: " + map.get("this can't exist") + "\n");

        System.out.println("put test 1: " + map.put("a", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("put test 2: " + map.put("a", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("put test 3: " + map.put("bc", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("put test 4: " + map.put("qwert", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("put test 5: " + map.put("stuff", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("Current size is: " + map.size() + "\n");

        System.out.println("remove test 1: " + map.remove("Meissen"));
        System.out.println("remove test 2: " + map.remove("shoes"));
        System.out.println("remove test 3: " + map.remove("Nilokheri"));
        System.out.println("remove test 4: " + map.remove("Bani Walid"));
        System.out.println("remove test 5: " + map.remove("this can't exist") + "\n");

        System.out.println("replace test 1: " + map.replace("Leduc", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("replace test 2: " + map.replace("shoes", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("replace test 3: " + map.replace("Auerbach",new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("replace test 4: " + map.replace("Gennep", new WeatherRecord("a","b",0,0,0,0,0,"c")));
        System.out.println("replace test 5: " + map.replace("this can't exist", new WeatherRecord("a","b",0,0,0,0,0,"c")) + "\n");

        System.out.println();
        String dicks = "dicks(1)";
        System.out.println("Splitting: " + dicks.split("\\(")[1]);
    }
}