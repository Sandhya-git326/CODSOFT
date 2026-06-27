import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;

public class CurrencyConverter {
    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency;
            URI uri = URI.create(apiUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            Pattern pattern = Pattern.compile("\"" + targetCurrency + "\":([0-9.]+)");
            Matcher matcher = pattern.matcher(response.toString());
            if (matcher.find()) {
                return Double.parseDouble(matcher.group(1));
            }
        } catch (Exception e) {
            System.out.println("Error fetching exchange rate.");
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static String getCurrencySymbol(String currency) {
        switch (currency) {
            case "USD":
                return "$";
            case "INR":
                return "₹";
            case "EUR":
                return "€";
            case "GBP":
                return "£";
            case "JPY":
                return "¥";
            case "AUD":
                return "A$";
            case "CAD":
                return "C$";
            default:
                return currency + " ";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("       CURRENCY CONVERTER");
        System.out.println("---------------------------------");
        System.out.println("Available Currencies:");
        System.out.println("USD, INR, EUR, GBP, JPY, AUD, CAD");
        System.out.print("\nEnter Base Currency: ");
        String baseCurrency = scanner.next().toUpperCase();
        System.out.print("Enter Target Currency: ");
        String targetCurrency = scanner.next().toUpperCase();
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println(
                    "Invalid amount. Amount must be greater than zero.");
            scanner.close();
            return;
        }
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Invalid currency code or unable to fetch data.");
        } else {
            double convertedAmount = amount * exchangeRate;
            System.out.println("\n-------- RESULT -----------");
            System.out.println("Base Currency   : " + baseCurrency);
            System.out.println("Target Currency : " + targetCurrency);
            System.out.println("Exchange Rate   : " + exchangeRate);
            System.out.printf(
                    "\n%s%.2f = %s%.2f\n",
                    getCurrencySymbol(baseCurrency),
                    amount,
                    getCurrencySymbol(targetCurrency),
                    convertedAmount);
            System.out.println("-----------------------");
        }
        scanner.close();
    }
}
