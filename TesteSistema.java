import java.util.Scanner;

public class TesteSistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("digite o tamanho da fila: ");
        int capacidadeFila = scanner.nextInt();
        System.out.print("digite o tamanho da pilha: ");
        int capacidadePilha = scanner.nextInt();
        scanner.nextLine();

        SistemaImpressao sistema = new SistemaImpressao(capacidadeFila, capacidadePilha);

        while (true) {
            System.out.println("\n=== menu===");
            System.out.println("1: impressão");
            System.out.println("2: reimpressão");
            System.out.println("3: sair");
            System.out.print("escolha uma opção: ");

            int opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1:
                    menuImpressao(scanner, sistema);
                    break;

                case 2:
                    menuReimpressao(scanner, sistema);
                    break;

                case 3:
                    System.out.println("saindo");
                    scanner.close();
                    return;

                default:
                    System.out.println("digite uma opção valida");
            }
        }
    }

    private static void menuImpressao(Scanner scanner, SistemaImpressao sistema) {
        while (true) {
            System.out.println("\n=== menu de impressão ===");
            System.out.println("1: adicionar documento");
            System.out.println("2: imprimir proximo documento");
            System.out.println("3: consultar documento");
            System.out.println("4: mostrar fila");
            System.out.println("5: volta");
            System.out.print("escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("nome do arquivo: ");
                    String nomeArquivo = scanner.nextLine();
                    System.out.print("nome do usuario: ");
                    String nomeUsuario = scanner.nextLine();
                    sistema.adicionarDocumento(nomeArquivo, nomeUsuario);
                    break;

                case 2:
                    sistema.imprimirProximo();
                    break;

                case 3:
                    System.out.print("nome do arquivo: ");
                    String arquivoConsulta = scanner.nextLine();
                    sistema.consultarDocumento(arquivoConsulta);
                    break;

                case 4:
                    sistema.mostrarStatusFila();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("digite uma opção valida");
            }
        }
    }

    private static void menuReimpressao(Scanner scanner, SistemaImpressao sistema) {
        while (true) {
            System.out.println("\n===menu de reimpressão===");
            System.out.println("1: solicitar reimpressão");
            System.out.println("2: reimprimir");
            System.out.println("3: consultar documento");
            System.out.println("4: ver doucmentos na pilha");
            System.out.println("5: voltar ao menu");
            System.out.print("escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("nome do arquivo: ");
                    String nomeArquivo = scanner.nextLine();
                    sistema.solicitarReimpressao(nomeArquivo);
                    break;

                case 2:
                    sistema.reimprimirDocumento();
                    break;

                case 3:
                    System.out.print("nome do arquivo: ");
                    String arquivoConsulta = scanner.nextLine();
                    sistema.consultarDocumentoReimpressao(arquivoConsulta);
                    break;

                case 4:
                    sistema.mostrarStatusReimpressao();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("digite uma opção valida");
            }
        }
    }
}