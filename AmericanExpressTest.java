import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AmericanExpressTest {

    @Test
    public void testCalcularComisionSinCuotas() {
        AmericanExpress americanExpress = new AmericanExpress();
        double monto = 100.0;
        int cuotas = 0;
        double comisionEsperada = 100.0 * 0.07;
        double montoTotalEsperado = monto + comisionEsperada;

        double resultado = americanExpress.calcularComision(monto, cuotas);

        assertEquals(comisionEsperada, resultado - monto, 0.001); // Comprueba la comisión
        assertEquals(montoTotalEsperado, resultado, 0.001); // Comprueba el monto total
    }

    @Test
    public void testCalcularComisionConCuotas() {
        AmericanExpress americanExpress = new AmericanExpress();
        double monto = 200.0;
        int cuotas = 3;
        double comisionEsperada = monto * 0.07 + monto * 0.15;
        double montoTotalEsperado = monto + comisionEsperada;

        double resultado = americanExpress.calcularComision(monto, cuotas);

        assertEquals(comisionEsperada, resultado - monto, 0.001); // Comprueba la comisión
        assertEquals(montoTotalEsperado, resultado, 0.001); // Comprueba el monto total
    }
}
