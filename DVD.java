public class DVD extends Midia {
    private boolean possuiExtras;
    private static final double acrescimoExtras = 0.10;

    public DVD(String titulo, double precoBase, boolean possuiExtras) {
        super(titulo, precoBase);
        this.possuiExtras = possuiExtras;
    }

    @Override
    public double calcularPreco(int dias) {
        double preco = getPrecoBase() * dias;
        
        if (possuiExtras) {
            preco += preco * acrescimoExtras;
        }
        
        return preco;
    }

    public boolean isPossuiExtras() {
        return possuiExtras;
    }

    public void setPossuiExtras(boolean possuiExtras) {
        this.possuiExtras = possuiExtras;
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: DVD | Extras: %s",
            super.toString(), (possuiExtras ? "Sim" : "Não"));
    }
}