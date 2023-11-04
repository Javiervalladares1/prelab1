import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FormatoCSVTest {

    private String nombreArchivo;

    @Before
    public void setUp() {
        nombreArchivo = "archivo_prueba";
    }

    @After
    public void tearDown() {
        // Eliminar el archivo de prueba después de cada test
        try {
            Files.delete(Paths.get(nombreArchivo + ".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEscribirArchivoCSV() {
        FormatoCSV formatoCSV = new FormatoCSV();
        Pago pago = new Pago("Usuario1", 1, "public_key_1", 100.0, 0, 4111111111111111L, 1225, 123, null);

        formatoCSV.escribirArchivo(pago, nombreArchivo);

        // Leer el archivo CSV y comprobar su contenido
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo + ".csv"))) {
            String linea = reader.readLine();
            assertEquals("Usuario1,1,public_key_1,100.0,0,4111111111111111,1225,123,7.0,hashMD5", linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
