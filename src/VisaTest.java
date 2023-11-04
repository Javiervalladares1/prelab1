import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VisaTest {

    @Test
    public void testCalcularComision() {
        Visa visa = new Visa();
        double monto = 100.0;
        int cuotas = 3;

        double comision = visa.calcularComision(monto, cuotas);

        // Cálculo de comisión e IVA esperado
        double comisionEsperada = monto * 0.05;
        double ivaEsperado = comisionEsperada * 0.12;
        double totalEsperado = monto + comisionEsperada + ivaEsperado;

        // Comprueba que el cálculo es correcto
        assertEquals(totalEsperado, comision, 0.001);
    }
}

