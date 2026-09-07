import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    public static ArrayList<Property> getProperties() throws IOException {

        Path path = Path.of("港区赤坂　マンション価格　20260814.csv");

        var lines = Files.readAllLines(path, Charset.forName("MS932"));

        ArrayList<Property> properties = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++){
            String line = lines.get(i);
            
            String[] data = line.split(",");
            
            Property property = new Property();

            property.transactionPrices = Integer.parseInt(data[9]);
            property.propertySizes = Integer.parseInt(data[11]);
            property.propertyAges = Integer.parseInt(data[12].replace("年", ""));

            properties.add(property);
        }
        return properties;
    }
}
