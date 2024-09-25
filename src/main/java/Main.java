import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;
import domain.Endereco;
import domain.Livro;
import domain.Movimentacao;
import domain.Usuario;
import services.MovimentacaoService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        var usuarioDAO = new UsuarioDAO();
        var livroDAO = new LivroDAO();
        var movimentacaoService = new MovimentacaoService();

        var endereco = Endereco.builder()
                .rua("Galdino vilante santos")
                .bairro("Casas populares")
                .numero("123")
                .cidade("Cajazeiras")
                .cep("58900000")
                .estado("PB")
                .build();

        var usuario = Usuario.builder()
                .nome("Gustavo Henrique")
                .cpf("12332112312")
                .email("gustavoaraujohab@gmail.com")
                .telefone("12345")
                .tipoUsuario("aluno")
                .endereco(endereco)
                .build();

//        usuarioDAO.save(usuario);

        var livro = Livro.builder()
                .titulo("Teste")
                .genero("Teste")
                .edicao("3")
                .escritor("Teste teste")
                .editora("teste teste")
                .categoria("teste")
                .dataPublicacao(LocalDateTime.now())
                .build();

//        livroDAO.save(livro);

//        movimentacaoService.saveMovimentacao(usuario, livro);

        movimentacaoService.verificaMulta(1L);
    }

}
