public class Property {
    int transactionPrices;
    int propertySizes;
    int propertyAges;
    double similarityScores;

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

