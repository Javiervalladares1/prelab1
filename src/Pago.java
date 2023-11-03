class Pago {
    String usuario;
    int id;
    String public_key;
    double monto;
    int cuotas;
    long numero_de_tarjeta;
    int fecha_vencimiento;
    int codigo_CVV;
    Tarjeta tipoTarjeta;

    Pago(String usuario, int id, String public_key, double monto, int cuotas, long numero_de_tarjeta, int fecha_vencimiento, int codigo_CVV, Tarjeta tipoTarjeta) {
        // Inicializa los campos con los valores proporcionados
        this.usuario = usuario;
        this.id = id;
        this.public_key = public_key;
        this.monto = monto;
        this.cuotas = cuotas;
        this.numero_de_tarjeta = numero_de_tarjeta;
        this.fecha_vencimiento = fecha_vencimiento;
        this.codigo_CVV = codigo_CVV;
        this.tipoTarjeta = tipoTarjeta;
    }
    

    public String getUsuario() {
        return usuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getPublic_key() {
        return public_key;
    }


    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }


    public double getMonto() {
        return monto;
    }


    public void setMonto(double monto) {
        this.monto = monto;
    }


    public int getCuotas() {
        return cuotas;
    }


    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }


    public long getNumero_de_tarjeta() {
        return numero_de_tarjeta;
    }


    public void setNumero_de_tarjeta(long numero_de_tarjeta) {
        this.numero_de_tarjeta = numero_de_tarjeta;
    }


    public int getFecha_vencimiento() {
        return fecha_vencimiento;
    }


    public void setFecha_vencimiento(int fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }


    public int getCodigo_CVV() {
        return codigo_CVV;
    }


    public void setCodigo_CVV(int codigo_CVV) {
        this.codigo_CVV = codigo_CVV;
    }


    public Tarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }


    public void setTipoTarjeta(Tarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }


    double calcularComision() {
        return tipoTarjeta.calcularComision(monto, cuotas);
    }
}
