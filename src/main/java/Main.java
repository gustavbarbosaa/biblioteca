import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;
import domain.Endereco;
import domain.Livro;
import domain.Movimentacao;
import domain.Usuario;
import services.MovimentacaoService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var usuarioDAO = new UsuarioDAO();
        var livroDAO = new LivroDAO();
        var movimentacaoDAO = new MovimentacaoDAO();
        var movimentacaoService = new MovimentacaoService();

        var endereco = Endereco.builder()
                .rua("opa")
                .bairro("opa2")
                .numero("1234")
                .cidade("cajazeiras")
                .cep("58900000")
                .estado("PB")
                .build();

        var usuario = Usuario.builder()
                .nome("gustavo")
                .cpf("1234")
                .email("gustavo@gmail.com")
                .telefone("12344356")
                .tipoUsuario("aluno")
                .endereco(endereco)
                .build();

//        usuarioDAO.save(usuario);

        var livro = Livro.builder()
                .titulo("testando")
                .genero("testando")
                .edicao("4")
                .escritor("testando")
                .editora("testando")
                .categoria("testando")
                .dataPublicacao(LocalDateTime.now())
                .build();

//        livroDAO.save(livro);

//        movimentacaoService.saveMovimentacao(usuario, livro);

        movimentacaoService.verificaMulta(1L);

        movimentacaoService.verificaMovimentacoesSemDevolucao();
    }
}
