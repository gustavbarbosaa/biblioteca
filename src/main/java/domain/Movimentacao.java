package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(nullable = false)
    private LocalDateTime dataEmprestimo;
    @Column(nullable = false)
    private LocalDateTime dataDevolucao;
    private BigDecimal valorMulta;
}
