package io.tictactoe.controller.db;

import io.tictactoe.model.db.Usuario;
import io.tictactoe.model.errors.BadRequestException;
import io.tictactoe.model.errors.UserNotFoundException;
import io.tictactoe.utils.AppLogger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class UsuarioController {
    private static final Logger LOGGER = Logger.getLogger(UsuarioController.class.getName());
    @Inject
    EntityManager em;

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

    public Usuario createUsuario(String usuario, String senha) throws BadRequestException {
        try {
            Usuario u = new Usuario(usuario, senha);
            em.persist(u);
            LOGGER.info("Criado usuário: " + usuario);
            return u;
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            throw new BadRequestException();
        }
    }
}
