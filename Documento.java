import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Documento {
    private String nomeArquivo;
    private String nomeUsuario;
    private LocalDateTime horarioSolicitacao;
    private LocalDateTime horarioImpressao;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Documento(String nomeArquivo, String nomeUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.nomeUsuario = nomeUsuario;
        this.horarioSolicitacao = LocalDateTime.now();
        this.horarioImpressao = null;
    }

    public void imprimir() {
        this.horarioImpressao = LocalDateTime.now();
    }

    public long getTempoEspera() {
        if (horarioImpressao == null) {
            return Duration.between(horarioSolicitacao, LocalDateTime.now()).getSeconds();
        }
        return Duration.between(horarioSolicitacao, horarioImpressao).getSeconds();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getHorarioSolicitacaoFormatado() {
        return horarioSolicitacao.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "Documento [Arquivo=" + nomeArquivo + 
               ", Usuário=" + nomeUsuario + 
               ", Solicitado às " + getHorarioSolicitacaoFormatado() + 
               (horarioImpressao != null ? ", Impresso após " + getTempoEspera() + "s" : "") + "]";
    }
}