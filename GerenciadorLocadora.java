import java.util.ArrayList;
import java.util.List;
import utils.Teclado;

public class GerenciadorLocadora {
    private List<Midia> acervo;
    private List<Locacao> historicoLocacoes;
    private List<Cliente> clientes;
    private double totalArrecadado;

    public GerenciadorLocadora() {
        this.acervo = new ArrayList<>();
        this.historicoLocacoes = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.totalArrecadado = 0.0;
    }

    public void cadastrarMidia(Midia midia) {
        acervo.add(midia);
        System.out.println("Mídia cadastrada com sucesso: " + midia.getTitulo());
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());
    }

    public void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }
        
        System.out.println("\n--- ACERVO RETROFLIX ---");
        for (Midia midia : acervo) {
            System.out.println(midia.toString());
        }
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public void listarHistoricoLocacoes() {
        if (historicoLocacoes.isEmpty()) {
            System.out.println("Nenhuma locação realizada até o momento.");
            return;
        }
        
        System.out.println("\n--- HISTÓRICO DE LOCAÇÕES ---");
        for (Locacao locacao : historicoLocacoes) {
            System.out.println(locacao.toString());
        }
    }

    public void realizarAluguel() {
        if (clientes.isEmpty() || acervo.isEmpty()) {
            System.out.println("É necessário ter clientes e mídias cadastradas para alugar.");
            return;
        }

        listarClientes();
        int idCliente = Teclado.readInt("Informe o ID do cliente: ");
        Cliente clienteSelecionado = buscarCliente(idCliente);
        
        if (clienteSelecionado == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        listarAcervo();
        int idMidia = Teclado.readInt("Informe o ID da mídia para alugar: ");
        Midia midiaSelecionada = buscarMidia(idMidia);

        if (midiaSelecionada == null || !midiaSelecionada.isDisponivel()) {
            System.out.println("Mídia não encontrada ou indisponível.");
            return;
        }

        int dias = Teclado.readInt("Quantos dias de locação? ");

        Locacao novaLocacao = new Locacao(clienteSelecionado, midiaSelecionada, dias);
        midiaSelecionada.setDisponibilidade(false);
        historicoLocacoes.add(novaLocacao);
        
        System.out.printf("\n--- LOCAÇÃO CONCLUÍDA ---\nValor a pagar: R$ %.2f\n", novaLocacao.getValorTotal());
    }

    public void devolverMidia() {
        if (historicoLocacoes.stream().noneMatch(Locacao::isStatusAtiva)) {
            System.out.println("Não há mídias ativas para devolução.");
            return;
        }

        System.out.println("\n--- MÍDIAS ALUGADAS ---");
        historicoLocacoes.stream()
            .filter(Locacao::isStatusAtiva)
            .forEach(locacao -> System.out.printf("Locação Mídia ID %d: %s\n", 
                locacao.getMidia().getId(), locacao.getMidia().getTitulo()));

        int idMidia = Teclado.readInt("Informe o ID da mídia que está sendo devolvida: ");
        Midia midiaDevolvida = buscarMidia(idMidia);

        if (midiaDevolvida == null || midiaDevolvida.isDisponivel()) {
            System.out.println("Mídia não encontrada ou já estava disponível.");
            return;
        }

        Locacao locacaoAtiva = historicoLocacoes.stream()
            .filter(locacao -> locacao.getMidia().getId() == idMidia && locacao.isStatusAtiva())
            .findFirst()
            .orElse(null);

        if (locacaoAtiva == null) {
            System.out.println("Erro: Locação ativa não encontrada para esta mídia.");
            return;
        }

        if (midiaDevolvida instanceof FitaVHS) {
            FitaVHS fitaVHS = (FitaVHS) midiaDevolvida;
            String resposta = Teclado.readString("A fita foi rebobinada? (S/N): ").toUpperCase();
            
            if (resposta.equals("N")) {
                fitaVHS.setRebobinada(false);
            } else {
                fitaVHS.setRebobinada(true);
            }
            
            locacaoAtiva.setValorTotal(fitaVHS.calcularPreco(locacaoAtiva.getDiasLocacao()));
        }
        
        midiaDevolvida.setDisponibilidade(true);
        locacaoAtiva.finalizarLocacao();
        totalArrecadado += locacaoAtiva.getValorTotal();

        System.out.printf("\nDevolução concluída. Valor final da locação: R$ %.2f\n", locacaoAtiva.getValorTotal());
    }

    public void mostrarTotalArrecadado() {
        System.out.printf("\nTOTAL ARRECADADO PELA RETROFLIX: R$ %.2f\n", totalArrecadado);
    }

    private Cliente buscarCliente(int id) {
        return clientes.stream()
            .filter(cliente -> cliente.getId() == id)
            .findFirst()
            .orElse(null);
    }

    private Midia buscarMidia(int id) {
        return acervo.stream()
            .filter(midia -> midia.getId() == id)
            .findFirst()
            .orElse(null);
    }
}