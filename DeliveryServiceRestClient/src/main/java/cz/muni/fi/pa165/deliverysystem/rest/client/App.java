package cz.muni.fi.pa165.deliverysystem.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class App {

    private static int post(URL url, Map<String, String> paramMap) throws UnsupportedEncodingException, IOException {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url.toString());
        HttpResponse httpResponse = client.execute(post);

        HttpParams params = new BasicHttpParams();
        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                params.setParameter(entry.getKey(), entry.getValue());
            }
            post.setParams(params);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            InputStream is = httpResponse.getEntity().getContent();
        }
        return statusCode;
    }

    private static int get(URL url) throws UnsupportedEncodingException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url.toString());
        HttpResponse httpResponse = client.execute(get);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            InputStream is = httpResponse.getEntity().getContent();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
        return statusCode;
    }

    private static int delete(URL url) throws UnsupportedEncodingException, IOException {
        HttpClient client = new DefaultHttpClient();

        HttpDelete delete = new HttpDelete(url.toString());
        HttpResponse httpResponse = client.execute(delete);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            InputStream is = httpResponse.getEntity().getContent();
        }
        return statusCode;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        HttpURLConnection.setFollowRedirects(false);
        String url = "http://localhost:4080/pa165/index.jsp/";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("HEAD");

        try {
            String result = String.valueOf(con.getResponseCode() == HttpURLConnection.HTTP_OK);
            if (result.contains("false")) {
                System.out.println("Server is offline");
            } else {
                System.out.println("Server is online");
                cliUiStart(url);
            }
        } catch (IOException ex) {
            System.out.println("Server is off");
        }
    }

    private static void cliUiStart(String url) throws UnsupportedEncodingException, IOException {
        String restUrl = url + "Rest/";
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome this is Delivery Service rest command line ui");
            System.out.println("1: Customer managment");
            System.out.println("2: Courier managment");
            System.out.println("0: Exit");
            int input = (new Scanner(System.in)).nextInt();
            switch (input) {
                default: {
                    break;
                }
                case 1: {
                    cliUiCustomer(restUrl);
                    break;
                }
                case 2: {
                    cliUiCourier(restUrl);
                    break;
                }
                case 0: {
                    exit = true;
                    break;
                }
            }
        }
    }

    private static void cliUiCustomer(String restUrl) throws UnsupportedEncodingException, IOException {
        String customerUrl = restUrl + "Customer/";
        System.out.println("Customer managment: Choose command:");
        System.out.println("0: Return");
        System.out.println("1: Print all customers");
        System.out.println("2: Create customer");
        System.out.println("3: Update customer");
        System.out.println("4: Delete customer");
        System.out.println("5: Find customer");
        int input = (new Scanner(System.in)).nextInt();
        switch (input) {
            default: {
                break;
            }
            case 0: {
                break;
            }
            case 1: {
                get(new URL(customerUrl + "?getAllCustomers"));
                break;
            }
            case 2: {
                HashMap<String, String> map = new HashMap<>();
                String entry;
                System.out.println("Enter first name:");
                entry = (new Scanner(System.in)).next();
                map.put("firstName", entry);
                System.out.println("Enter last name:");
                entry = (new Scanner(System.in)).next();
                map.put("lastName", entry);
                System.out.println("Enter email:");
                entry = (new Scanner(System.in)).next();
                map.put("email", entry);
                System.out.println("Enter city:");
                entry = (new Scanner(System.in)).next();
                map.put("city", entry);
                System.out.println("Enter street:");
                entry = (new Scanner(System.in)).next();
                map.put("street", entry);
                System.out.println("Enter zip code:");
                entry = (new Scanner(System.in)).next();
                map.put("zipCode", entry);
                System.out.println("Enter country:");
                entry = (new Scanner(System.in)).next();
                map.put("country", entry);
                System.out.println("Enter telephone number:");
                entry = (new Scanner(System.in)).next();
                map.put("telephoneNumber", entry);
                post(new URL(customerUrl + "?create"), map);
                break;
            }
            case 3: {
                HashMap<String, String> map = new HashMap<>();
                String entry;
                System.out.println("Enter first name:");
                entry = (new Scanner(System.in)).next();
                map.put("firstName", entry);
                System.out.println("Enter last name:");
                entry = (new Scanner(System.in)).next();
                map.put("lastName", entry);
                System.out.println("Enter email:");
                entry = (new Scanner(System.in)).next();
                map.put("email", entry);
                System.out.println("Enter city:");
                entry = (new Scanner(System.in)).next();
                map.put("city", entry);
                System.out.println("Enter street:");
                entry = (new Scanner(System.in)).next();
                map.put("street", entry);
                System.out.println("Enter zip code:");
                entry = (new Scanner(System.in)).next();
                map.put("zipCode", entry);
                System.out.println("Enter country:");
                entry = (new Scanner(System.in)).next();
                map.put("country", entry);
                System.out.println("Enter telephone number:");
                entry = (new Scanner(System.in)).next();
                map.put("telephoneNumber", entry);
                post(new URL(customerUrl + "?create"), map);
            }
            case 4: {
                System.out.println("Enter id of customer to delete:");
                int id = (new Scanner(System.in)).nextInt();
                delete(new URL(customerUrl + "?delete=" + id));
                break;
            }
            case 5: {
                System.out.println("Enter id:");
                int id = (new Scanner(System.in)).nextInt();
                get(new URL(customerUrl + "?find=" + id));
                break;
            }
        }
    }

    private static void cliUiCourier(String restUrl) throws IOException {
        String courierUrl = restUrl + "Courier/";
        System.out.println("Courier managment: Choose command:");
        System.out.println("0: Return");
        System.out.println("1: Print all couriers");
        System.out.println("2: Create courier");
        System.out.println("3: Update courier");
        System.out.println("4: Delete courier");
        System.out.println("5: Find courier");
        int input = (new Scanner(System.in)).nextInt();
        switch (input) {
            default: {
                break;
            }
            case 0: {
                break;
            }
            case 1: {
                get(new URL(courierUrl + "?getAllCouriers"));
                break;
            }
            case 2: {
                HashMap<String, String> map = new HashMap<>();
                String entry;
                System.out.println("Enter first name:");
                entry = (new Scanner(System.in)).next();
                map.put("firstName", entry);
                System.out.println("Enter last name:");
                entry = (new Scanner(System.in)).next();
                map.put("lastName", entry);
                System.out.println("Enter email:");
                entry = (new Scanner(System.in)).next();
                map.put("email", entry);
                post(new URL(courierUrl + "?create"), map);
                break;
            }
            case 3: {
                HashMap<String, String> map = new HashMap<>();
                String entry;
                System.out.println("Enter first name:");
                entry = (new Scanner(System.in)).next();
                map.put("firstName", entry);
                System.out.println("Enter last name:");
                entry = (new Scanner(System.in)).next();
                map.put("lastName", entry);
                System.out.println("Enter email:");
                entry = (new Scanner(System.in)).next();
                map.put("email", entry);
                post(new URL(courierUrl + "?create"), map);
                break;
            }
            case 4: {
                System.out.println("Enter id of courier to delete:");
                int id = (new Scanner(System.in)).nextInt();
                delete(new URL(courierUrl + "?delete=" + id));
                break;
            }
            case 5: {
                System.out.println("Enter id:");
                int id = (new Scanner(System.in)).nextInt();
                get(new URL(courierUrl + "?find=" + id));
                break;
            }
        }
    }
}