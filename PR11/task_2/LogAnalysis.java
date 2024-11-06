import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogAnalysis {
    public static void main(String[] args) {
        String logFile = "src/log.txt";
        String analyticFile = "analytic.results.txt";

        Map<String, Integer> logTypeCount = new HashMap<>();
        Map<String, Integer> userSuccessCount = new HashMap<>();
        Map<String, Integer> userTotalCount = new HashMap<>();

        int charCount = 0, wordCount = 0, lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(logFile));
             FileWriter fw = new FileWriter(analyticFile)) {

            String line;
            while ((line = br.readLine()) != null) {
                charCount += line.length();
                wordCount += line.split("\\s+").length;
                lineCount++;

                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String logType = parts[1];
                    String user = parts[0];

                    logTypeCount.put(logType, logTypeCount.getOrDefault(logType, 0) + 1);
                    userTotalCount.put(user, userTotalCount.getOrDefault(user, 0) + 1);

                    if ("SUCCESS".equals(logType)) {
                        userSuccessCount.put(user, userSuccessCount.getOrDefault(user, 0) + 1);
                    }
                }
            }

            System.out.println("Кількість символів: " + charCount);
            System.out.println("Кількість слів: " + wordCount);
            System.out.println("Кількість рядків: " + lineCount);
            System.out.println("Кількість логів кожного виду: " + logTypeCount);

            fw.write("Статистика успішності:\n");
            for (String user : userTotalCount.keySet()) {
                int total = userTotalCount.get(user);
                int success = userSuccessCount.getOrDefault(user, 0);
                double successRate = (total > 0) ? (success * 100.0 / total) : 0;
                fw.write(String.format("Користувач: %s, Успішних: %d, Всього: %d, Успішність: %.2f%%\n",
                        user, success, total, successRate));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
