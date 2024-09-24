package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name= "livro.getAll", query= "select l from Livro l")

})
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String escritor;
    @Column(nullable = false)
    private LocalDateTime dataPublicacao;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private String editora;
    @Column(nullable = false)
    private String edicao;
}
