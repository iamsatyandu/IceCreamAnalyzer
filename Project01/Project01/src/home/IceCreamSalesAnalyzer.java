package home;


public class IceCreamSalesAnalyzer {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\satye\\Desktop/dataSet.txt"; // Adjust the file path

        DataProcessor dataProcessor = new DataProcessor(filePath);
        SalesStatistics salesStatistics = new SalesStatistics(dataProcessor);

        salesStatistics.printTotalSales();
        salesStatistics.printMonthWiseSalesTotals();
        salesStatistics.printMostPopularItemByMonth();
        salesStatistics.printTopRevenueItemByMonth();
        salesStatistics.printOrderStatisticsForMostPopularItem();
    }
}
