package services;

import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;

import domain.Livro;
import domain.Movimentacao;
import domain.Usuario;
import net.bytebuddy.implementation.bytecode.Throw;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

public class MovimentacaoService {
    private MovimentacaoDAO movimentacaoDAO;
    private UsuarioDAO usuarioDAO;
    private LivroDAO livroDAO;

    public MovimentacaoService() {
        this.movimentacaoDAO = new MovimentacaoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.livroDAO = new LivroDAO();
    }

    public void saveMovimentacao(Usuario usuario, Livro livro) {
        var movimentacao = new Movimentacao();

        var verificaUsuario = this.usuarioDAO.getUsuarioByCpf(usuario.getCpf());
        var verificaLivro = this.livroDAO.getLivroByTitulo(livro.getTitulo());

        System.out.println(verificaUsuario);
        System.out.println(verificaLivro);

        if (verificaUsuario == null || verificaLivro == null) {
            throw new IllegalArgumentException("Usuário ou livro não existem no banco de dados");
        }

        movimentacao.setUsuario(verificaUsuario);
        movimentacao.setLivro(verificaLivro);

        movimentacao.setDataEmprestimo(LocalDateTime.now());
        movimentacao.setDataDevolucao(null);
        movimentacao.setValorMulta(BigDecimal.ZERO);

        movimentacaoDAO.save(movimentacao);
    }

    public void verificaMulta(Long id) throws Exception {
        var movimentacao = movimentacaoDAO.getMovimentacaoById(id);

        if (movimentacao == null) {
            throw new Exception("Movimentacao nao foi encontrada");
        }

        LocalDateTime prazo = movimentacao.getDataEmprestimo().plusDays(3);
        LocalDateTime dataAtual = LocalDateTime.now();


        if (prazo.isBefore(LocalDateTime.now())) {
            Period diferenca = Period.between(prazo.toLocalDate(), dataAtual.toLocalDate());
            int totalDias = diferenca.getYears() * 365 + diferenca.getMonths() * 30 + diferenca.getDays();
            double valorMulta = 1.00 * totalDias;
            movimentacao.setValorMulta(BigDecimal.valueOf(valorMulta));
            movimentacao.setDataDevolucao(LocalDateTime.now());
            movimentacaoDAO.save(movimentacao);
            System.out.println(
                movimentacao.getUsuario().getNome() +
                " está com o livro: " +
                movimentacao.getLivro().getTitulo() +
                " com " + totalDias + " dias de atraso"
            );
        } else {
            System.out.println("O livro ainda está no prazo de 3 dias!");
            movimentacao.setDataEmprestimo(LocalDateTime.now());
            movimentacaoDAO.save(movimentacao);
        }
    }

    public void movimentacoesComAtraso() {
        var movimentacoesSemDevolucao = movimentacaoDAO.getMovimentacoesSemDevolucao();

        if (movimentacoesSemDevolucao.size() <= 0) {
            System.out.println("Não existem movimentacoes com atraso!");
        } else {
            System.out.println("Movimentações com atraso:");
        }

        movimentacoesSemDevolucao.forEach(m ->
                System.out.println(
                    m.getId() +
                    m.getUsuario().getNome() + m.getUsuario().getCpf() +
                    m.getLivro().getTitulo()
                )
        );
    }

    public void verificaMovimentacoesSemDevolucao() {
        var movimentacoesSemDevolucao = movimentacaoDAO.getMovimentacoesSemDevolucao();

        if (movimentacoesSemDevolucao.isEmpty()) {
            System.out.println("Não tem movimentacoes com atraso");
        } else {
            movimentacoesSemDevolucao.forEach(m -> {
                if (m.getDataEmprestimo().plusDays(3).isBefore(LocalDateTime.now())) {
                    System.out.println(m.getUsuario().getNome() + " ---->" + " Data emprestimo: " + m.getDataEmprestimo().toLocalDate());
                } else {
                    System.out.println("Periodo ainda é valido");
                }
            });
        }
    }
}
