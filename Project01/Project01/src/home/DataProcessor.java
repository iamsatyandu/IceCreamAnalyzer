package home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    private Map<String, Double> totalSalesByMonth = new HashMap<>();
    private Map<String, Map<String, Integer>> quantitySoldByMonth = new HashMap<>();
    private Map<String, Map<String, Double>> revenueByMonth = new HashMap<>();
    private Map<String, Map<String, Integer>> itemOrdersByMonth = new HashMap<>();

    public DataProcessor(String filePath) {
        processFile(filePath);
    }

    private void processFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); 

                String date = values[0]; 
                String month = date.substring(0, 7); 
                String item = values[1]; 
                int quantity = Integer.parseInt(values[2]);
                double price = Double.parseDouble(values[3]);

                double saleAmount = quantity * price;

              
                totalSalesByMonth.put(month, totalSalesByMonth.getOrDefault(month, 0.0) + saleAmount);

               
                quantitySoldByMonth.putIfAbsent(month, new HashMap<>());
                Map<String, Integer> quantityByItem = quantitySoldByMonth.get(month);
                quantityByItem.put(item, quantityByItem.getOrDefault(item, 0) + quantity);

               
                revenueByMonth.putIfAbsent(month, new HashMap<>());
                Map<String, Double> revenueByItem = revenueByMonth.get(month);
                revenueByItem.put(item, revenueByItem.getOrDefault(item, 0.0) + saleAmount);

               
                itemOrdersByMonth.putIfAbsent(month, new HashMap<>());
                Map<String, Integer> ordersByItem = itemOrdersByMonth.get(month);
                ordersByItem.put(item, ordersByItem.getOrDefault(item, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> getTotalSalesByMonth() {
        return totalSalesByMonth;
    }

    public Map<String, Map<String, Integer>> getQuantitySoldByMonth() {
        return quantitySoldByMonth;
    }

    public Map<String, Map<String, Double>> getRevenueByMonth() {
        return revenueByMonth;
    }

    public Map<String, Map<String, Integer>> getItemOrdersByMonth() {
        return itemOrdersByMonth;
    }
}
