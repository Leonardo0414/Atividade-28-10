public class Streaming extends Midia {
    private String plataforma;
    private static final double descontoDigital = 0.15;

    public Streaming(String titulo, double precoBase, String plataforma) {
        super(titulo, precoBase);
        this.plataforma = plataforma;
    }

    @Override
    public double calcularPreco(int dias) {
        double preco = getPrecoBase() * dias;
        preco -= preco * descontoDigital;
        return preco;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: STREAMING | Plataforma: %s",
            super.toString(), plataforma);
    }
}