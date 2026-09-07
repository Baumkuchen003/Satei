import java.io.IOException;
import java.util.ArrayList;

public class Version2Main {
    public static void main(String[] args) throws IOException {
        /*int[] transactionPrices = {76000, 74000, 65000, 61000, 56000};
        int[] propertySizes = {155, 85, 155, 135, 110};
        int[] propertyAges = {2001, 2018, 2009, 2007, 2009};*/

        String targetSizeInput = IO.readln("あなたのお部屋のサイズは何㎡？ ");
        int targetSize = Integer.parseInt(targetSizeInput);

        String targetAgeInput = IO.readln("あなたのお部屋の築年数は何年？ ");
        int targetAge = Integer.parseInt(targetAgeInput);
        int targetBuiltYear = 2026 - targetAge;
        
        
        ArrayList<Property> properties = CsvReader.getProperties();
        for (int i = 0; i < properties.size(); i++) {
        
        double sizeDifferentScore = Math.abs(targetSize - properties.get(i).propertySizes) / (double) targetSize * 100 * 3;
        double ageDifferentScore = Math.abs(targetBuiltYear - properties.get(i).propertyAges);
        double calculatedSimilarity = 100 - (sizeDifferentScore + ageDifferentScore);
        
        properties.get(i).similarityScores = calculatedSimilarity;
        
        /*Property property = new Property();
        property.transactionPrices = transactionPrices[i];
        property.propertySizes = propertySizes[i];
        property.propertyAges = propertyAges[i];

        if (calculatedSimilarity >= 0){
            property.similarityScores = calculatedSimilarity;
            properties.add(property);
            }
        }*/
        }

        for (int t = 0; t < properties.size(); t++) {
            for (int j = 0; j < properties.size() - 1 - t; j++) {
            if (properties.get(j).similarityScores < properties.get(j + 1).similarityScores){
            
                Property temp = properties.get(j); //temp は temporary の訳
                properties.set(j, properties.get(j + 1));
                properties.set(j + 1, temp);
                }
            }
        }
        
        double sumSimilarProperties = 0.0;
        double sumSimilarityScores = 0.0;
        int numberOfSimilarProperties = 10;
        int similarPropertyCount = 0;
        for (int k = 0; k < Math.min(numberOfSimilarProperties, properties.size()); k++) {
            System.out.println(properties.get(k).propertyInformation());
            sumSimilarProperties += properties.get(k).transactionPrices * properties.get(k).similarityScores;
            sumSimilarityScores += properties.get(k).similarityScores;
            similarPropertyCount++;
        }
        
        if (similarPropertyCount == 0){
            System.out.println("類似物件がありません");
        }else {
            double weightedAveragePrice = sumSimilarProperties / sumSimilarityScores;
        
            System.out.println("類似物件を" + similarPropertyCount + "件使用しました");
            System.out.println("推定価格：" + PriceFormatter.formatEstimatedPrice(weightedAveragePrice));
        }
        System.out.println("読み込んだ物件数：" + properties.size());
    }
}
