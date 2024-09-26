package dao;

import domain.Movimentacao;
import domain.Usuario;
import persistence.JPAUtil;

import java.util.List;

public class MovimentacaoDAO {
    private final JPAUtil jpaUtil;

    public MovimentacaoDAO() { this.jpaUtil = new JPAUtil(); }

    public void save(Movimentacao movimentacao) {
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(movimentacao);
        jpaUtil.getEntityManager().getTransaction().commit();
    }

    public Movimentacao getMovimentacaoById(Long id) {
        var movimentacao = jpaUtil.getEntityManager().find(Movimentacao.class, id);
        return movimentacao;
    }

    public List<Movimentacao> getAllMovimentacoes() {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("movimentacao.getAll");
        return query.getResultList();
    }

    public List<Movimentacao> getMovimentacoesSemDevolucao() {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("movimentacao.getMovimentacoesSemDevolucao");
        return query.getResultList();
    }

    public Usuario getMaiorMulta(){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("movimentacao.getMaiorMulta");
        return (Usuario) query.getSingleResult();
    }

    public Long getQtdMovimentacaoMensal(int mes, int ano){
        Long quantidade;
        var query = jpaUtil.getEntityManager().createNamedQuery("movimentacao.getQtdMovimentacaoMensal");
        query.setParameter("mes", mes);
        query.setParameter("ano", ano);
        quantidade = (Long) query.getSingleResult();
        return quantidade;
    }
}
