    import java.util.Formatter;
    public class Visa implements Tarjeta {

        @Override
        public double calcularComision(double monto, int cuotas) {
            double comision = monto * 0.05;
            double iva = comision * 0.12;
            return monto + comision + iva;
        }
        
    } 