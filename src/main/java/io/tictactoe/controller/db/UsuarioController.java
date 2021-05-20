package io.tictactoe.controller.db;

import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.UserNotFoundException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioController {
    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    public UsuarioController() {}

    public Usuario getUsuarioByUsername(String username) throws UserNotFoundException {
        Query q = em.createQuery("select u from Usuario u where u.nome = :username");
        q.setParameter("username", username);
        List<Usuario> l = q.getResultList();
        if (l.size() == 0) {
            throw new UserNotFoundException();
        }
        return l.get(0);
    }

    public Usuario createUsuario(String usuario, String senha) {
        Usuario u = new Usuario(usuario, senha);
        em.persist(u);
        logger.info("Criado usuário: " + usuario);
        return u;
    }
}
