public class SistemaImpressao {
    private Fila filaImpressao;
    private PilhaReimpressao pilhaReimpressao;

    public SistemaImpressao(int capacidadeFila, int capacidadePilha) {
        filaImpressao = new Fila(capacidadeFila);
        pilhaReimpressao = new PilhaReimpressao(capacidadePilha);
    }

    public void adicionarDocumento(String nomeArquivo, String nomeUsuario) {
        if (filaImpressao.filaCheia()) {
            System.out.println("fila cheia");
            return;
        }
        Documento novoDoc = new Documento(nomeArquivo, nomeUsuario);
        filaImpressao.enfileira(novoDoc);
        System.out.println(nomeArquivo + " adicioado a fila");
    }

    public void imprimirProximo() {
        if (filaImpressao.filaVazia()) {
            System.out.println("fila vazia");
            return;
        }

        Documento doc = filaImpressao.desenfileira();
        doc.imprimir();
        System.out.println("imprimindo " + doc);

        if (!pilhaReimpressao.estaCheia()) {
            pilhaReimpressao.empilhar(doc);
        } else {
            System.out.println("pilha cheia");
        }
    }

    public void solicitarReimpressao(String nomeArquivo) {
        PilhaReimpressao temp = new PilhaReimpressao(pilhaReimpressao.getCapacidade());
        Documento encontrado = null;

        while (!pilhaReimpressao.estaVazia()) {
            Documento doc = pilhaReimpressao.desempilhar();
            temp.empilhar(doc);

            if (doc.getNomeArquivo().equals(nomeArquivo)) {
                encontrado = doc;
                break;
            }
        }

        while (!temp.estaVazia()) {
            Documento doc = temp.desempilhar();
            if (doc != encontrado) {
                pilhaReimpressao.empilhar(doc);
            }
        }

        if (encontrado != null) {
            Documento copia = new Documento(encontrado.getNomeArquivo(), encontrado.getNomeUsuario());
            pilhaReimpressao.empilhar(copia);
            System.out.println("reimprimindo " + copia);
        } else {
            System.out.println(nomeArquivo + " não encontrado");
        }

        Documento doc = filaImpressao.desenfileira();
        doc.imprimir();
        System.out.println("imprimindo " + doc);
    }

    public void consultarDocumento(String nomeArquivo) {
        final int[] posicao = { 1 };
        final boolean[] encontrado = { false };

        filaImpressao.paraCadaDocumento(doc -> {
            if (doc.getNomeArquivo().equals(nomeArquivo)) {
                System.out.println("documento:");
                System.out.println("-posição na fila " + posicao[0]);
                System.out.println("-usuario: " + doc.getNomeUsuario());
                System.out.println("-horario de solicitação: " + doc.getHorarioSolicitacaoFormatado());
                System.out.println("-tempo de espera até agora: " + doc.getTempoEspera() + "s");
                encontrado[0] = true;
            }
            posicao[0]++;
        });

        if (!encontrado[0]) {
            System.out.println(nomeArquivo + " não encontrado");
        }
    }

    public void mostrarStatusFila() {
        System.out.println("\n=== status da fila===");
        System.out.println("documentos: " + filaImpressao.ocupacao + "/" + filaImpressao.dados.length);
        System.out.println("fila:");

        if (filaImpressao.filaVazia()) {
            System.out.println("vazia");
        } else {
            System.out.println(filaImpressao.toString());
        }

        System.out.println("vetor:");
        System.out.println(filaImpressao.stringVetor());
    }

    public void solicitarReimpressao(String nomeArquivo, String nomeUsuario) {
        if (pilhaReimpressao.estaCheia()) {
            System.out.println("pilha cheia");
            return;
        }
        Documento doc = new Documento(nomeArquivo, nomeUsuario);
        pilhaReimpressao.empilhar(doc);
        System.out.println(nomeArquivo + " adicionado a pilha");
    }

    public void reimprimirDocumento() {
        if (pilhaReimpressao.estaVazia()) {
            System.out.println("pilha vaiza");
            return;
        }

        Documento doc = pilhaReimpressao.desempilhar();
        doc.imprimir();
        System.out.println("reimprimindo: " + doc);
    }

    public void consultarDocumentoReimpressao(String nomeArquivo) {
        int posicao = pilhaReimpressao.buscarPosicaoDocumento(nomeArquivo);
        if (posicao == -1) {
            System.out.println(nomeArquivo + " não encontrado");
            return;
        }

        Documento doc = pilhaReimpressao.consultarTopo();
        System.out.println("documento: ");
        System.out.println("-posição (topo a base)" + posicao);
        System.out.println("-usuario: " + doc.getNomeUsuario());
        System.out.println("-horario de solicitação: " + doc.getHorarioSolicitacaoFormatado());
    }

    public void mostrarStatusReimpressao() {
        System.out.println("\n===status da pilha ===");
        System.out.println("documentos: " + pilhaReimpressao.getQuantidadeDocumentos());
        System.out.println("conteudo da pilha (topo a base):");
        System.out.println(pilhaReimpressao.listarDocumentos());
    }
}