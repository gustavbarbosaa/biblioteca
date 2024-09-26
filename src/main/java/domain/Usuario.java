package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name = "usuario.getAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "usuario.getByCpf", query = "SELECT u FROM Usuario u where u.cpf = :cpf"),
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String telefone;
    @Embedded
    @Column(nullable = false)
    private Endereco endereco;
    @Column(nullable = false)
    private String tipoUsuario;
}
