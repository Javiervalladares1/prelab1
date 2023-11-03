import java.util.Formatter;
class AmericanExpress implements Tarjeta {
    @Override
    public double calcularComision(double monto, int cuotas) {
        double comision = monto * 0.07;
        if (cuotas > 0) {
            comision += monto * 0.15;
        }

        // Formatea el archivo en formato CSV con el hash MD5 como último campo
        return monto + comision;
    }
}