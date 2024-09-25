package dao;

import domain.Movimentacao;
import persistence.JPAUtil;

import java.util.List;

public class MovimentacaoDAO {
    private JPAUtil jpaUtil;

    public MovimentacaoDAO() { this.jpaUtil = new JPAUtil(); }

    public void save(Movimentacao movimentacao) {
        this.jpaUtil.getEntityManager().getTransaction().begin();
        this.jpaUtil.getEntityManager().merge(movimentacao);
        this.jpaUtil.getEntityManager().getTransaction().commit();
        this.jpaUtil.getEntityManager().close();
    }

    public Movimentacao getMovimentacaoById(Long id) {
//        this.jpaUtil.getEntityManager().getTransaction().begin();
        return this.jpaUtil.getEntityManager().find(Movimentacao.class, id);
    }

    public List<Movimentacao> getAllMovimentacoes() {
        this.jpaUtil.getEntityManager().getTransaction().begin();
        var query = this.jpaUtil.getEntityManager().createNamedQuery("movimentacao.getAll");
        return query.getResultList();
    }
}
