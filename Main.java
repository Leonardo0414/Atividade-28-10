import utils.Teclado;

public class Main {
    public static void main(String[] args) {
        GerenciadorLocadora gerenciador = new GerenciadorLocadora();

        gerenciador.cadastrarCliente(new Cliente("leonardo", "leo@email.com"));
        gerenciador.cadastrarMidia(new FitaVHS("Rocky III", 5.0));
        gerenciador.cadastrarMidia(new DVD("O Mágico de Oz", 7.5, true));
        gerenciador.cadastrarMidia(new Streaming("Duna (Digital)", 10.0, "PrimeFlix"));

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== RetroFlix: Menu Principal ===");
            System.out.println("1. Cadastrar Mídia");
            System.out.println("2. Listar Acervo e Clientes");
            System.out.println("3. Realizar Locação");
            System.out.println("4. Devolver Mídia");
            System.out.println("5. Listar Histórico de Locações");
            System.out.println("6. Mostrar Total Arrecadado");
            System.out.println("7. Sair do Sistema");

            int opcao = Teclado.readInt("Escolha uma opção (1-7): ");

            switch (opcao) {
                case 1:
                    cadastrarMidia(gerenciador);
                    break;
                case 2:
                    gerenciador.listarAcervo();
                    gerenciador.listarClientes();
                    break;
                case 3:
                    gerenciador.realizarAluguel();
                    break;
                case 4:
                    gerenciador.devolverMidia();
                    break;
                case 5:
                    gerenciador.listarHistoricoLocacoes();
                    break;
                case 6:
                    gerenciador.mostrarTotalArrecadado();
                    break;
                case 7:
                    System.out.println("Sistema encerrado.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void cadastrarMidia(GerenciadorLocadora gerenciador) {
        System.out.println("\nTipo de Mídia a cadastrar:");
        System.out.println("1. Fita VHS");
        System.out.println("2. DVD");
        System.out.println("3. Streaming");
        int tipo = Teclado.readInt("Escolha o tipo: ");

        String titulo = Teclado.readString("Título: ");
        double precoBase = Teclado.readDouble("Preço Base (por dia): ");

        Midia novaMidia = null;
        
        switch (tipo) {
            case 1:
                novaMidia = new FitaVHS(titulo, precoBase);
                break;
            case 2:
                String respostaExtras = Teclado.readString("Possui extras (S/N)? ").toUpperCase();
                boolean possuiExtras = respostaExtras.equals("S");
                novaMidia = new DVD(titulo, precoBase, possuiExtras);
                break;
            case 3:
                String plataforma = Teclado.readString("Plataforma (Netflix, Prime, etc.): ");
                novaMidia = new Streaming(titulo, precoBase, plataforma);
                break;
            default:
                System.out.println("Tipo de mídia inválido.");
                return;
        }

        if (novaMidia != null) {
            gerenciador.cadastrarMidia(novaMidia);
        }
    }
}