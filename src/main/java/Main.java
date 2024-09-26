import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;
import domain.Endereco;
import domain.Livro;
import domain.Usuario;
import services.MovimentacaoService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        var usuarioDAO = new UsuarioDAO();
        var livroDAO = new LivroDAO();
        var movimentacaoDAO = new MovimentacaoDAO();
        var movimentacaoService = new MovimentacaoService();

        var endereco = Endereco.builder()
                .rua("jardim europa")
                .bairro("soledade")
                .numero("333")
                .cidade("Cajazeiras")
                .cep("58900000")
                .estado("PB")
                .build();

        var usuario = Usuario.builder()
                .nome("gustavo")
                .cpf("777")
                .email("gugu@gmail.com")
                .telefone("12355")
                .tipoUsuario("aluno")
                .endereco(endereco)
                .build();

        var livro = Livro.builder()
                .titulo("Teste2")
                .genero("Teste2")
                .edicao("333")
                .escritor("hdfugihdpu")
                .editora("hdfugihdpu")
                .categoria("categoria")
                .dataPublicacao(LocalDateTime.now())
                .build();

//        usuarioDAO.save(usuario);
//        livroDAO.save(livro);
//        movimentacaoService.saveMovimentacao(usuario, livro);
//
//        // Query 1
//        movimentacaoService.verificaMulta(3L);
//        movimentacaoService.verificaMovimentacoesSemDevolucao();


//        // Query 2
//        var livrosPorCategoria = livroDAO.getLivroByCategoria("teste");
//        livrosPorCategoria.forEach(l -> System.out.println(l.getTitulo()));

//        // Query 3
//        System.out.println(movimentacaoDAO.getMaiorMulta());

//        // Query 4
//        movimentacaoService.movimentacoesComAtraso();
//
//        // Query 5
//        int mes = 8;
//        int ano = 2024;
//        Long quantidadeMovimentacoes = movimentacaoDAO.getQtdMovimentacaoMensal(mes, ano);
//        System.out.println("Quantidade de movimentações no mês " + mes + "/" + ano + ": " + quantidadeMovimentacoes);
    }

}
