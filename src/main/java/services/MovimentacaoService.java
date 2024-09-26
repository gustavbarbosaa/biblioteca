package services;

import dao.LivroDAO;
import dao.MovimentacaoDAO;
import dao.UsuarioDAO;

import domain.Livro;
import domain.Movimentacao;
import domain.Usuario;

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

        if (verificaUsuario == null || verificaLivro == null) {
            throw new IllegalArgumentException("Usuário ou livro não existem no banco de dados");
        }

        movimentacao.setUsuario(usuario);
        movimentacao.setLivro(livro);

        movimentacao.setDataEmprestimo(LocalDateTime.now());
        movimentacao.setDataDevolucao(null);
        movimentacao.setValorMulta(BigDecimal.ZERO);
        movimentacaoDAO.save(movimentacao);
    }

    public void verificaMulta(Long id) {
        var movimentacao = movimentacaoDAO.getMovimentacaoById(id);

        if (movimentacao.getDataDevolucao() == null) {
            LocalDateTime prazo = movimentacao.getDataEmprestimo().plusDays(3);
            LocalDateTime dataAtual = LocalDateTime.now();

            if (prazo.isBefore(LocalDateTime.now())) {
                Period diferenca = Period.between(prazo.toLocalDate(), dataAtual.toLocalDate());

                int totalDias = diferenca.getYears() * 365 + diferenca.getMonths() * 30 + diferenca.getDays();

                System.out.println("Prazo já expirou!");
                System.out.println("Diferença total em dias: " + totalDias);
                double valorMulta = 1.00 * totalDias;
                movimentacao.setDataEmprestimo(LocalDateTime.now());
                movimentacao.setValorMulta(BigDecimal.valueOf(valorMulta));
                movimentacaoDAO.save(movimentacao);
            } else {
                System.out.println("O livro ainda está no prazo de 3 dias!");
            }
        } else {
            System.out.println("Livro já foi devolvido");
            System.out.println("Data de devolução: " + movimentacao.getDataDevolucao());
        }
    }

    public void verificaMovimentacoesSemDevolucao() {
        var movimentacoesSemDevolucao = movimentacaoDAO.getMovimentacoesSemDevolucao();

        movimentacoesSemDevolucao.forEach(m -> {
            if (m.getDataEmprestimo().plusDays(3).isBefore(LocalDateTime.now())) {
                System.out.println(m.getUsuario().getNome() + " tem uma movimentacao em atraso com o livro: " + m.getLivro().getTitulo());
            }
        });
    }
}
