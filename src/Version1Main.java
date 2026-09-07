import java.util.ArrayList;
import java.util.List;

public class Version1Main {
    public static void main(String[] args) {
        List<Integer> transactionPrices = new ArrayList<Integer>();
        transactionPrices.add(76000);
        transactionPrices.add(74000);
        transactionPrices.add(65000);
        transactionPrices.add(61000);
        transactionPrices.add(56000);

        List<Integer> propertySizes = new ArrayList<Integer>();
        propertySizes.add(155);
        propertySizes.add(85);
        propertySizes.add(155);
        propertySizes.add(135);
        propertySizes.add(110);

        List<Integer> propertyAges = new ArrayList<Integer>();
        propertyAges.add(2001);
        propertyAges.add(2018);
        propertyAges.add(2009);
        propertyAges.add(2007);
        propertyAges.add(2009);

        List<Double> similarityScores = new ArrayList<Double>();
        List<Integer> similarityPropertyIndexes = new ArrayList<Integer>();

        String targetSizeInput = IO.readln("あなたのお部屋のサイズは何㎡？ ");
        int targetSize = Integer.parseInt(targetSizeInput);

        String targetAgeInput = IO.readln("あなたのお部屋の築年数は何年？ ");
        int targetAge = Integer.parseInt(targetAgeInput);
        int targetBuiltYear = 2026 - targetAge;

        double totalPerSize = 0;
        int similarPropertyCount = 0;
        for (int i = 0; i < propertyAges.size(); i++){
            double sizeDifferentScore = Math.abs(targetSize - propertySizes.get(i)) / (double) targetSize * 100 * 3;
            double ageDifferentScore = Math.abs(targetBuiltYear - propertyAges.get(i));
            double totalDifferentScore = sizeDifferentScore + ageDifferentScore;
            similarityScores.add(totalDifferentScore);

            System.out.println(totalDifferentScore);

            if (Math.abs(targetBuiltYear - propertyAges.get(i)) <= 2 
                && Math.abs(targetSize - propertySizes.get(i)) <= 30){
                double perSize = transactionPrices.get(i) / (double)propertySizes.get(i);
                totalPerSize += perSize;
                similarPropertyCount++;
                similarityPropertyIndexes.add(i);
            }
        }


        if (similarPropertyCount == 0){
            System.out.println("類似物件がありません");
        }else {
            double averagePerSize = totalPerSize / similarPropertyCount;
            System.out.println("あなたの条件");
            System.out.println("広さ：" + targetSize + "㎡");
            System.out.println("建築年：" + targetAge + "年");
            System.out.println("参考物件：" + similarPropertyCount + "件");
            System.out.printf("平均㎡単価：%.2f円%n", averagePerSize);

            double estimatedPrice = averagePerSize * targetSize;
            int oku = (int) estimatedPrice / 10000;
            int remainingMan = (int) estimatedPrice % 10000;
            int hundredManUnit = remainingMan / 100;
            int hundredMan = hundredManUnit * 100;


            System.out.print("推定価格：");
            if (estimatedPrice < 10000){
                System.out.printf("約%d万円%n", hundredMan);
            }else{
                System.out.printf("約%d億%d万円%n", oku, hundredMan);
            }

            for (int j = 0; j < similarityPropertyIndexes.size(); j++){
                System.out.println("参考物件" + (j+1));
                System.out.println("元データ番号：" + similarityPropertyIndexes.get(j));
                System.out.println(transactionPrices.get(j));
                System.out.println(propertySizes.get(j));
                System.out.println(propertyAges.get(j));
            }
            
            double mostSimilarProperty = similarityScores.get(0);
            int mostSimilarPropertyIndex = 0;
            for (int i = 0; i < similarityScores.size(); i++){

                if (similarityScores.get(i) < mostSimilarProperty){
                mostSimilarProperty = similarityScores.get(i);
            
                mostSimilarPropertyIndex = i;
                }
            }
            System.out.println("最少スコア" + mostSimilarProperty);
            System.out.println("物件番号" + mostSimilarPropertyIndex);
            
        }
    }        
}
