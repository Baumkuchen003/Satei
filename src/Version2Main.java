import java.io.IOException;
import java.util.ArrayList;

public class Version2Main {
    public static void main(String[] args) throws IOException {

        int targetSize;
        while(true){
            String targetSizeInput = IO.readln("あなたのお部屋のサイズは何㎡？ ");
            try {
                targetSize = Integer.parseInt(targetSizeInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください");
            }
        }

        int targetAge;
        while(true){
            String targetAgeInput = IO.readln("あなたのお部屋の築年数は何年？ ");
            try{
                targetAge = Integer.parseInt(targetAgeInput);
                break;
            } catch (NumberFormatException e){
                System.out.println("数字を入力してください");
            }
        }
        int targetBuiltYear = 2026 - targetAge;
        
        ArrayList<Property> properties;
        try{
            properties = CsvReader.getProperties();
        } catch (IOException e){
            System.out.println("ファイルが正常に読み込みませんでした");
            return;
        }
        
        ArrayList<Property> suitableProperties = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            properties.get(i).calculatedSimilarity(targetSize, targetBuiltYear);
            if (properties.get(i).similarityScores >= 50){
            suitableProperties.add(properties.get(i));
            }   
        }

        for (int t = 0; t < suitableProperties.size(); t++) {
            for (int j = 0; j < suitableProperties.size() - 1 - t; j++) {
            if (suitableProperties.get(j).similarityScores < suitableProperties.get(j + 1).similarityScores){
            
                Property temp = suitableProperties.get(j); //temp は temporary の訳
                suitableProperties.set(j, suitableProperties.get(j + 1));
                suitableProperties.set(j + 1, temp);
                }
            }
        }
        
        double sumSimilarProperties = 0.0;
        double sumSimilarityScores = 0.0;
        int numberOfSimilarProperties = 5;
        int similarPropertyCount = 0;
        for (int k = 0; k < Math.min(numberOfSimilarProperties, suitableProperties.size()); k++) {
            sumSimilarProperties += suitableProperties.get(k).transactionPrices * suitableProperties.get(k).similarityScores;
            sumSimilarityScores += suitableProperties.get(k).similarityScores;
            similarPropertyCount++;
        }
        
        if (similarPropertyCount == 0){
            System.out.println("類似物件がありません");
        }else {
            double weightedAveragePrice = sumSimilarProperties / sumSimilarityScores;
        
            System.out.println("推定価格：約" + PriceFormatter.formatEstimatedPrice(weightedAveragePrice));
            System.out.println("類似物件を" + similarPropertyCount + "件使用しました");
            displaySimilarProperties(suitableProperties, numberOfSimilarProperties);
        }
        System.out.println();
        System.out.println("読み込んだ物件数：" + properties.size());
    
    }

    static void displaySimilarProperties(
            ArrayList<Property> suitableProperties, int numberOfSimilarProperties){
            for(int i = 0; i < Math.min(numberOfSimilarProperties, suitableProperties.size()); i++){
                System.out.println();
                System.out.println("参考物件" + (i+1));
                System.out.println("価格：" + PriceFormatter.formatEstimatedPrice(suitableProperties.get(i).transactionPrices));
                System.out.println("サイズ：" + suitableProperties.get(i).propertySizes+ "㎡");
                System.out.println("築年：" + suitableProperties.get(i).propertyAges + "年");
                System.out.println("類似度：" + String.format("%.2f", suitableProperties.get(i).similarityScores) + "pt");
            }
    }
}
