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
}

