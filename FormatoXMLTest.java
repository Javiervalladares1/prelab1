import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import static org.junit.Assert.assertTrue;

public class FormatoXMLTest {

    private String nombreArchivo;

    @Before
    public void setUp() {
        nombreArchivo = "archivo_prueba";
    }

    @After
    public void tearDown() {
        // Eliminar el archivo de prueba después de cada test
        File file = new File(nombreArchivo + ".xml");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testEscribirArchivoXML() {
        FormatoXML formatoXML = new FormatoXML();
        Pago pago = new Pago("Usuario1", 1, "public_key_1", 100.0, 0, 4111111111111111L, 1225, 123, null);

        formatoXML.escribirArchivo(pago, nombreArchivo);

        // Verificar si el archivo XML existe
        File file = new File(nombreArchivo + ".xml");
        assertTrue(file.exists());

        // Leer el archivo XML y comprobar su contenido
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            String xmlContent = content.toString();
            assertTrue(xmlContent.contains("<usuario>Usuario1</usuario>"));
            assertTrue(xmlContent.contains("<id>1</id>"));
            assertTrue(xmlContent.contains("<public_key>public_key_1</public_key>"));
            assertTrue(xmlContent.contains("<monto>100.0</monto>"));
            assertTrue(xmlContent.contains("<cuotas>0</cuotas>"));
            assertTrue(xmlContent.contains("<numero_de_tarjeta>4111111111111111</numero_de_tarjeta>"));
            assertTrue(xmlContent.contains("<fecha_vencimiento>1225</fecha_vencimiento>"));
            assertTrue(xmlContent.contains("<codigo_CVV>123</codigo_CVV>"));
            assertTrue(xmlContent.contains("<monto_con_comision>107.0</monto_con_comision>"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
