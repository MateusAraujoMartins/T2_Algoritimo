import java.util.function.Consumer;

public class Fila {
    Documento[] dados;
    int primeiro, ultimo, ocupacao;
    public Fila (int capacidade) {
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
        dados = new Documento[capacidade];
    }
    public Fila () {
        this(10);
    }
    public boolean filaVazia() {
        return ocupacao == 0;
    }
    public boolean filaCheia() {
        return ocupacao == dados.length;
    }
    private int proximaPosicao (int posicao) {
        return (posicao + 1) % dados.length;
    }
    public void enfileira (Documento e) {
        if (filaCheia()) throw new RuntimeException("falha na insercao");
        dados[ultimo] = e;
        ultimo = proximaPosicao(ultimo);
        ocupacao++;
    }
    public Documento desenfileira () {
        if (filaVazia()) throw new RuntimeException("falha na remocao");
        Documento aux = dados[primeiro];
        primeiro = proximaPosicao(primeiro);
        ocupacao--;
        return aux;
    }

    public void paraCadaDocumento(Consumer<Documento> acao) {
        for (int i = primeiro, cont = 0; cont < ocupacao; i = proximaPosicao(i), cont++) {
            acao.accept(dados[i]);
        }
    }
    
    @Override
    public String toString() {
        if (filaVazia()) return "fila vazia";
        String s = "";
        for (int i=primeiro, cont=0; cont<ocupacao; i=proximaPosicao(i), cont++) {
            s = s + dados[i] + " ";
        }
        return s;
    }
    public String stringVetor () {
        int i=0;
        String s = "";
        if (filaVazia())
            for (i=0; i<dados.length; i++)
                s = s + "_ ";
        else if (filaCheia())
            for (i=0; i<dados.length; i++)
                s = s + dados[i] + " ";
        else if (primeiro < ultimo) {
            for (i=0; i < primeiro; i++) 
                s = s + "_ ";
            for (i=primeiro; i<ultimo; i++)
                s = s + dados[i] + " ";
            for (i=ultimo; i<dados.length; i++)
                s = s + "_ ";
        }
        else {
            for (i=0; i < ultimo; i++) 
                s = s + dados[i] + " ";
            for (i=ultimo; i<primeiro; i++)
                s = s + "_ ";
            for (i=primeiro; i<dados.length; i++)
                s = s + dados[i] + " ";
        }
        return s;
    } 
}