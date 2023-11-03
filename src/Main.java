import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            // Cargar datos desde un archivo JSON
            JSONParser parser = new JSONParser();
            JSONArray jsonDataArray = (JSONArray) parser.parse(new FileReader("datos_pago.json"));

            for (Object obj : jsonDataArray) {
                JSONObject jsonData = (JSONObject) obj;

                String usuario = (String) jsonData.get("user");
                int id = ((Long) jsonData.get("id")).intValue();
                String public_key = (String) jsonData.get("public_key");
                double monto = (double) jsonData.get("amount");
                int cuotas = ((Long) jsonData.get("installments")).intValue();
                long numero_de_tarjeta = (long) jsonData.get("cardNumber");
                int fecha_vencimiento = ((Long) jsonData.get("expirationDate")).intValue();
                int codigo_CVV = ((Long) jsonData.get("cvv")).intValue();

                // Determinar el tipo de tarjeta a partir del primer dígito
                Tarjeta tipoTarjeta;
                int primerDigito = Integer.parseInt(Long.toString(numero_de_tarjeta).substring(0, 1));

                if (primerDigito == 4) {
                    tipoTarjeta = new Visa();
                } else if (primerDigito == 5) {
                    tipoTarjeta = new Mastercard();
                } else if (primerDigito == 3) {
                    tipoTarjeta = new AmericanExpress();
                } else {
                    throw new IllegalArgumentException("Tipo de tarjeta no compatible.");
                }

                // Calcular la comisión según el tipo de tarjeta
                double montoConComision = tipoTarjeta.calcularComision(monto, cuotas);

                // Crea una instancia del formato deseado (XML, JSON o CSV)
                FormatoPago formato;
                if (primerDigito == 4) {
                    formato = new FormatoXML();
                } else if (primerDigito == 5) {
                    formato = new FormatoJSON();
                } else {
                    formato = new FormatoCSV();
                }

                // Crea una instancia del procesador de pagos con el formato elegido
                ProcesadorPagos procesador = new ProcesadorPagos(formato);

                // Procesa el pago
                Pago pago = new Pago(usuario, id, public_key, montoConComision, cuotas, numero_de_tarjeta, fecha_vencimiento, codigo_CVV, tipoTarjeta);
                procesador.procesarPago(pago, "archivo_pago");

                System.out.println("Pago procesado y archivo generado con éxito.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
