package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "movimentacao.getAll", query = "SELECT m FROM Movimentacao m"),
        @NamedQuery(name = "movimentacao.getMaiorMulta", query = "SELECT m.usuario FROM Movimentacao m WHERE m.valorMulta " +
                "= (SELECT MAX(m2.valorMulta) FROM Movimentacao m2)"),
        @NamedQuery(
                name = "movimentacao.getQtdMovimentacaoMensal",
                query = "SELECT COUNT(m) FROM Movimentacao m WHERE FUNCTION('MONTH', m.dataEmprestimo) = :mes AND FUNCTION('YEAR', m.dataEmprestimo) = :ano"
        ),
        @NamedQuery(name = "movimentacao.getMovimentacoesSemDevolucao", query = "SELECT m FROM Movimentacao m WHERE m.dataDevolucao is NULL")
})
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    private Livro livro;

    @Column(nullable = true)
    private LocalDateTime dataEmprestimo;
    @Column(nullable = true)
    private LocalDateTime dataDevolucao;
    private BigDecimal valorMulta;
}
