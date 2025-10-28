public abstract class Midia {
    private static int i = 1;
    private int id;
    private String titulo;
    private double precoBase;
    private boolean disponibilidade;

    public Midia(String titulo, double precoBase) {
        this.id = i++;
        this.titulo = titulo;
        this.precoBase = precoBase;
        this.disponibilidade = true;
    }

    public abstract double calcularPreco(int dias);

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public boolean isDisponivel() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Título: %s | Preço Base: R$ %.2f | Status: %s",
            id, titulo, precoBase, (disponibilidade ? "DISPONÍVEL" : "ALUGADO"));
    }
}