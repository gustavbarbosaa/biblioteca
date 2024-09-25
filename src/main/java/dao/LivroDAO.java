package dao;

import domain.Livro;
import persistence.JPAUtil;

import java.util.List;

public class LivroDAO {
    private JPAUtil jpaUtil;

    public LivroDAO() { this.jpaUtil = new JPAUtil(); }

    public void save(Livro livro) {
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(livro);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public Livro getLivroById(Long id) {
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Livro.class, id);
    }

    public Livro getLivroByTitulo(String titulo) {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("livro.getByTitulo");
        query.setParameter("titulo", titulo);
        return (Livro) query.getSingleResult();
    }

    public List<Livro> getAllLivros() {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("livro.getAll");
        return query.getResultList();
    }
}
