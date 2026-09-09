public class Property {
    int transactionPrices;
    int propertySizes;
    int propertyAges;
    double similarityScores;

    public String propertyInformation() {
    return "価格：" + transactionPrices + "万円,"
            + "サイズ：" + propertySizes+ "㎡," 
            + "築年：" + propertyAges + "年,"
            + "類似度：" + similarityScores + "ポイント";
    }

    public void calculatedSimilarity(int targetSize, int targetBuiltYear){
        double sizeDifferentScore = 
            Math.abs(targetSize - propertySizes) 
            / (double) targetSize * 100 * 3;
        double ageDifferentScore = 
            Math.abs(targetBuiltYear - propertyAges);
        double calculatedSimilarity = 
            100 - (sizeDifferentScore + ageDifferentScore);
        
        similarityScores = calculatedSimilarity;
    }
}

