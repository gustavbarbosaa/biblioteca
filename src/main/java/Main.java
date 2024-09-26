import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;
import domain.Endereco;
import domain.Livro;
import domain.Usuario;
import services.MovimentacaoService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        var usuarioDAO = new UsuarioDAO();
        var livroDAO = new LivroDAO();
        var movimentacaoDAO = new MovimentacaoDAO();
        var movimentacaoService = new MovimentacaoService();

        var endereco = Endereco.builder()
                .rua("jardim america")
                .bairro("centro")
                .numero("12")
                .cidade("Cajazeiras")
                .cep("58900000")
                .estado("PB")
                .build();

        var usuario = Usuario.builder()
                .nome("Hector")
                .cpf("137711")
                .email("gustavoarha@gmail.com")
                .telefone("123233")
                .tipoUsuario("aluno")
                .endereco(endereco)
                .build();

//        usuarioDAO.save(usuario);

        var livro = Livro.builder()
                .titulo("Teste1")
                .genero("Teste1")
                .edicao("33")
                .escritor("Teste teste sa")
                .editora("teste teste sd")
                .categoria("teste")
                .dataPublicacao(LocalDateTime.now())
                .build();

//        livroDAO.save(livro);

//        movimentacaoService.saveMovimentacao(usuario, livro);

        movimentacaoService.verificaMulta(2L);
        System.out.println(livroDAO.getLivroByCategoria("teste"));
        System.out.println(movimentacaoDAO.getMaiorMulta());

        int mes = 9;
        int ano = 2024;
        Long quantidadeMovimentacoes = movimentacaoDAO.getQtdMovimentacaoMensal(mes, ano);
        System.out.println("Quantidade de movimentações no mês " + mes + "/" + ano + ": " + quantidadeMovimentacoes);
    }

}
