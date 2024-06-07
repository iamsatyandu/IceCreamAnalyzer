package home;

import java.util.Map;

public class SalesStatistics {
    private final DataProcessor dataProcessor;

    public SalesStatistics(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public void printTotalSales() {
        double totalSales = dataProcessor.getTotalSalesByMonth().values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("Total sales of the store: ₹" + totalSales);
    }

    public void printMonthWiseSalesTotals() {
        System.out.println("Month-wise totals sales:");
        for (Map.Entry<String, Double> entry : dataProcessor.getTotalSalesByMonth().entrySet()) {
            System.out.println(entry.getKey() + ": ₹" + entry.getValue());
        }
    }

    public void printMostPopularItemByMonth() {
        System.out.println("Most popular item (most quantity sold) in each month:");
        for (Map.Entry<String, Map<String, Integer>> entry : dataProcessor.getQuantitySoldByMonth().entrySet()) {
            String month = entry.getKey();
            Map<String, Integer> quantityByItem = entry.getValue();
            String mostPopularItem = quantityByItem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
            System.out.println(month + ": " + mostPopularItem);
        }
    }

    public void printTopRevenueItemByMonth() {
        System.out.println("Items generating most revenue in each month:");
        for (Map.Entry<String, Map<String, Double>> entry : dataProcessor.getRevenueByMonth().entrySet()) {
            String month = entry.getKey();
            Map<String, Double> revenueByItem = entry.getValue();
            String topRevenueItem = revenueByItem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
            System.out.println(month + ": " + topRevenueItem);
        }
    }

    public void printOrderStatisticsForMostPopularItem() {
        System.out.println("Min, max and average number of orders for the most popular item each month:");
        for (Map.Entry<String, Map<String, Integer>> entry : dataProcessor.getItemOrdersByMonth().entrySet()) {
            Map<String, Integer> ordersByItem = entry.getValue();
            int minOrders = ordersByItem.values().stream().min(Integer::compare).get();
            int maxOrders = ordersByItem.values().stream().max(Integer::compare).get();
            double avgOrders = ordersByItem.values().stream().mapToInt(Integer::intValue).average().getAsDouble();
            System.out.println(" Min=" + minOrders + ", Max=" + maxOrders + ", Avg=" + avgOrders);
        }
    }
}
