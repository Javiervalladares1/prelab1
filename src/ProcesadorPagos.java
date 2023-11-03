import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class ProcesadorPagos {
    FormatoPago formato;

    ProcesadorPagos(FormatoPago formato) {
        this.formato = formato;
    }

    void procesarPago(Pago pago, String nombreArchivo) {
        // Determinar el tipo de tarjeta a partir del primer dígito
        Tarjeta tipoTarjeta;
        String numeroTarjetaStr = Long.toString(pago.numero_de_tarjeta);
        int primerDigito = Character.getNumericValue(numeroTarjetaStr.charAt(0));

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
        double montoConComision = tipoTarjeta.calcularComision(pago.monto, pago.cuotas);

        // Crear un nuevo objeto Pago con el monto actualizado
        Pago pagoConComision = new Pago(pago.usuario, pago.id, pago.public_key, montoConComision, pago.cuotas, pago.numero_de_tarjeta, pago.fecha_vencimiento, pago.codigo_CVV, tipoTarjeta);

        // Escribir el archivo en el formato especificado
        formato.escribirArchivo(pagoConComision, nombreArchivo);
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
