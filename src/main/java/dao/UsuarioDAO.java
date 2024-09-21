package dao;

import domain.Usuario;
import persistence.JPAUtil;

import java.util.List;

public class UsuarioDAO {
    private final JPAUtil jpaUtil;

    public UsuarioDAO() {
        this.jpaUtil = new JPAUtil();
    }

    public void save(Usuario usuario) {
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().merge(usuario);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public  Usuario getUserById(Long id) {
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Usuario.class, id);
    }

    public List<Usuario> getAllUsers() {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("usuario.getAll");

        return query.getResultList();
    }

    public String deleteUser(Long id) {
        var user = getUserById(id);
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().remove(user);
        return "Usuário: " + user.getNome() + "excluído com sucesso!";
    }
}
