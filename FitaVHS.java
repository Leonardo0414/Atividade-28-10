public class FitaVHS extends Midia {
    private boolean rebobinada;
    private static final double taxaRebobinagem = 5.0;

    public FitaVHS(String titulo, double precoBase) {
        super(titulo, precoBase);
        this.rebobinada = true;
    }

    @Override
    public double calcularPreco(int dias) {
        double preco = getPrecoBase() * dias;
        if (!rebobinada) {
            preco += taxaRebobinagem;
        }
        return preco;
    }

    public boolean isRebobinada() {
        return rebobinada;
    }

    public void setRebobinada(boolean rebobinada) {
        this.rebobinada = rebobinada;
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: FITA VHS | Rebobinada: %s",
            super.toString(), (rebobinada ? "Sim" : "Não (Taxa Pendente)"));
    }
}