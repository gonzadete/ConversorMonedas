package Conversion;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Map;

import static java.net.http.HttpClient.newHttpClient;

public class ConvierteMoneda {
    private String moneda2;

    public String getMoneda2() {
        return moneda2;
    }

    public ConvierteMoneda(String moneda1, String moneda2, double valorAConvertir) throws IOException, InterruptedException {
        Object Locale = null;
        NumberFormat nf = NumberFormat.getInstance(java.util.Locale.ITALIAN);
        String LLAVE = "d3754b165c8522c30d5a830a";
        String direccion = "https://v6.exchangerate-api.com/v6/" + LLAVE + "/pair/" + moneda1 + "/" + moneda2;

        try {
            HttpClient client = newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            String json = response.body();
            Gson gson = new Gson();
            Map<?, ?> userMap = gson.fromJson(json, Map.class);
            Object valorConvertido = userMap.get("conversion_rate");
            double valorFinal = Double.parseDouble(valorConvertido.toString());
            double valorTotal = valorAConvertir * valorFinal;
            System.out.println("El valor de " + nf.format(valorAConvertir) + " [" + moneda1 + "]  " +
                    "corresponde al valor final de =>>> " + nf.format(valorTotal) + " [" + moneda2 + "]");
            System.out.println("\n*************************************************************************");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocurrió un error inesperado. Verifique su conexión a internet.");
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException");
        }
    }
}



