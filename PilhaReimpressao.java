public class PilhaReimpressao {
    private Documento[] documentos;
    private int topo;
    private int capacidade;

    public PilhaReimpressao(int capacidade) {
        this.capacidade = capacidade;
        this.documentos = new Documento[capacidade];
        this.topo = -1;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public boolean estaCheia() {
        return topo == capacidade - 1;
    }

    public void empilhar(Documento doc) {
        if (estaCheia()) {
            throw new RuntimeException("pilha cheia");
        }
        documentos[++topo] = doc;
    }

    public Documento desempilhar() {
        if (estaVazia()) {
            throw new RuntimeException("pilha vazia");
        }
        return documentos[topo--];
    }

    public Documento consultarTopo() {
        if (estaVazia()) {
            return null;
        }
        return documentos[topo];
    }

    public int getQuantidadeDocumentos() {
        return topo + 1;
    }

    public int buscarPosicaoDocumento(String nomeArquivo) {
        for (int i = topo; i >= 0; i--) {
            if (documentos[i].getNomeArquivo().equals(nomeArquivo)) {
                return topo - i + 1; 
            }
        }
        return -1; 
    }

    public String listarDocumentos() {
        if (estaVazia()) return "pilha vazia";
        
        StringBuilder sb = new StringBuilder();
        for (int i = topo; i >= 0; i--) {
            sb.append((topo - i + 1) + ". " + documentos[i] + "\n");
        }
        return sb.toString();
    }
}