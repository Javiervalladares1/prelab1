import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
class FormatoCSV implements FormatoPago {
    @Override
    public void escribirArchivo(Pago pago, String nombreArchivo) {
        try {
            // Crear una línea CSV con la información del pago
            String csv = pago.usuario + "," + pago.id + "," + pago.public_key + "," + pago.monto + "," + pago.cuotas + "," + pago.numero_de_tarjeta + "," + pago.fecha_vencimiento + "," + pago.codigo_CVV;

            // Calcular comisión y monto total
            double comision = pago.calcularComision();
            csv += "," + comision;

            // Calcular el hash MD5 y agregarlo al final del archivo
            String hashMD5 = calcularHashMD5(csv);
            csv += "," + hashMD5;

            // Escribir el archivo CSV
            FileWriter writer = new FileWriter(nombreArchivo + ".csv");
            writer.write(csv);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String calcularHashMD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}