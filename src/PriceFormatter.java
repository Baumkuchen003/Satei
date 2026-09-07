public class PriceFormatter {
    public static String formatEstimatedPrice(double estimatedPrice) {
        int oku = (int) estimatedPrice / 10000;
        int remainingMan = (int) estimatedPrice % 10000;
        int hundredManUnit = remainingMan / 100;
        int hundredMan = hundredManUnit * 100;

        if (estimatedPrice < 10000){
                return String.format("約%d万円%n", hundredMan);
            }else{
                return String.format("約%d億%d万円", oku, hundredMan);
            }
    }
}
