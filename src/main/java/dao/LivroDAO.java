package dao;

import domain.Livro;
import persistence.JPAUtil;

import java.util.List;

public class LivroDAO {
    private JPAUtil jpaUtil;

    public LivroDAO(){
        jpaUtil = new JPAUtil();
    }

    public void save(Livro livro){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().merge(livro);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public Livro livroById(Long id){
      jpaUtil.getEntityManager().getTransaction().begin();
        var livro = jpaUtil.getEntityManager().find(Livro.class, id);
        return livro;
    };

    public String delete (Long id){
        var livroParaDeletar = livroById(id);
        jpaUtil.getEntityManager().remove(livroParaDeletar);
        jpaUtil.getEntityManager().getTransaction().commit();
        return livroParaDeletar.getTitulo().concat(" exclido com sucesso!");
    }

    public List<Livro> retornarLivros(){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("livro.getAll");
        return query.getResultList();
    }
}
