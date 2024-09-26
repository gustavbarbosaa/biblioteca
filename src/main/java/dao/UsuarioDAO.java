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
        jpaUtil.getEntityManager().persist(usuario);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public Usuario getUserById(Long id) {
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Usuario.class, id);
    }

    public Usuario getUsuarioByCpf(String cpf) {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("usuario.getByCpf");
        query.setParameter("cpf", cpf);
        return (Usuario) query.getSingleResult();
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
        jpaUtil.getEntityManager().getTransaction().commit();
        return "Usuário: " + user.getNome() + " excluído com sucesso!";
    }

    public List<Usuario> usuariosComEmprestimoEmAberto() {
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager().createNamedQuery("usuario.getEmprestimoPendente");
        return query.getResultList();
    }
}
