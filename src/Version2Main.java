import java.io.IOException;
import java.util.ArrayList;

public class Version2Main {
    public static void main(String[] args) throws IOException {

        String targetSizeInput = IO.readln("あなたのお部屋のサイズは何㎡？ ");
        int targetSize = Integer.parseInt(targetSizeInput);

        String targetAgeInput = IO.readln("あなたのお部屋の築年数は何年？ ");
        int targetAge = Integer.parseInt(targetAgeInput);
        int targetBuiltYear = 2026 - targetAge;
        
        
        ArrayList<Property> properties = CsvReader.getProperties();
        ArrayList<Property> suitableProperties = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            properties.get(i).calculatedSimilarity(targetSize, targetBuiltYear);
            if (properties.get(i).similarityScores >= 50){
            suitableProperties.add(properties.get(i));
        }   
    }

        for (int t = 0; t < suitableProperties.size(); t++) {
            for (int j = 0; j < suitableProperties.size() - 1 - t; j++) {
            if (suitableProperties.get(j).similarityScores < properties.get(j + 1).similarityScores){
            
                Property temp = suitableProperties.get(j); //temp は temporary の訳
                suitableProperties.set(j, suitableProperties.get(j + 1));
                suitableProperties.set(j + 1, temp);
                }
            }
        }
        
        double sumSimilarProperties = 0.0;
        double sumSimilarityScores = 0.0;
        int numberOfSimilarProperties = 10;
        int similarPropertyCount = 0;
        for (int k = 0; k < Math.min(numberOfSimilarProperties, suitableProperties.size()); k++) {
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
