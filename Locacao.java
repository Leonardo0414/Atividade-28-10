import java.time.LocalDate;

public class Locacao {
    private Cliente cliente;
    private Midia midia;
    private int diasLocacao;
    private double valorTotal;
    private boolean statusAtiva;
    private LocalDate dataLocacao;

    public Locacao(Cliente cliente, Midia midia, int diasLocacao) {
        this.cliente = cliente;
        this.midia = midia;
        this.diasLocacao = diasLocacao;
        this.dataLocacao = LocalDate.now();
        this.statusAtiva = true;
        this.valorTotal = midia.calcularPreco(diasLocacao);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Midia getMidia() {
        return midia;
    }

    public int getDiasLocacao() {
        return diasLocacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isStatusAtiva() {
        return statusAtiva;
    }

    public void finalizarLocacao() {
        this.statusAtiva = false;
    }

    @Override
    public String toString() {
        return String.format("--- LOCAÇÃO ---\nData: %s | Dias: %d\n%s\n- Mídia: %s (ID %d)\nVALOR TOTAL: R$ %.2f | Status: %s",
            dataLocacao.toString(), diasLocacao, cliente.toString(), midia.getTitulo(), 
            midia.getId(), valorTotal, (statusAtiva ? "ATIVA" : "FINALIZADA"));
    }
}