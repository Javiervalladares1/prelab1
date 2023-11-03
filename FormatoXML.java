import java.io.FileWriter;
import java.io.IOException;

class FormatoXML implements FormatoPago {
    @Override
    public void escribirArchivo(Pago pago, String nombreArchivo) {
        try {
            // Crear un archivo XML con la información del pago
            String xml = "<pago>";
            xml += "<usuario>" + pago.usuario + "</usuario>";
            xml += "<id>" + pago.id + "</id>";
            xml += "<public_key>" + pago.public_key + "</public_key>";
            xml += "<monto>" + pago.monto + "</monto>";
            xml += "<cuotas>" + pago.cuotas + "</cuotas>";
            xml += "<numero_de_tarjeta>" + pago.numero_de_tarjeta + "</numero_de_tarjeta>";
            xml += "<fecha_vencimiento>" + pago.fecha_vencimiento + "</fecha_vencimiento>";
            xml += "<codigo_CVV>" + pago.codigo_CVV + "</codigo_CVV>";
            xml += "</pago>";

            // Agregar comisión y IVA al monto
            double montoConComision = pago.calcularComision();
            xml += "<monto_con_comision>" + montoConComision + "</monto_con_comision>";

            // Escribir el archivo XML
            FileWriter writer = new FileWriter(nombreArchivo + ".xml");
            writer.write(xml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}