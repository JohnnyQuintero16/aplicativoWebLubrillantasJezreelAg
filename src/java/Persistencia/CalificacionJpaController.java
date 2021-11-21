/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.AtencionServicio;
import DTO.Calificacion;
import DTO.CalificacionPK;
import DTO.Persona;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cristian
 */
public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacion calificacion) throws PreexistingEntityException, Exception {
        if (calificacion.getCalificacionPK() == null) {
            calificacion.setCalificacionPK(new CalificacionPK());
        }
        calificacion.getCalificacionPK().setIdAtencion(calificacion.getAtencionServicio().getId());
        calificacion.getCalificacionPK().setIdPersona(calificacion.getPersona().getCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AtencionServicio atencionServicio = calificacion.getAtencionServicio();
            if (atencionServicio != null) {
                atencionServicio = em.getReference(atencionServicio.getClass(), atencionServicio.getId());
                calificacion.setAtencionServicio(atencionServicio);
            }
            Persona persona = calificacion.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getCedula());
                calificacion.setPersona(persona);
            }
            em.persist(calificacion);
            if (atencionServicio != null) {
                atencionServicio.getCalificacionList().add(calificacion);
                atencionServicio = em.merge(atencionServicio);
            }
            if (persona != null) {
                persona.getCalificacionList().add(calificacion);
                persona = em.merge(persona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCalificacion(calificacion.getCalificacionPK()) != null) {
                throw new PreexistingEntityException("Calificacion " + calificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, Exception {
        calificacion.getCalificacionPK().setIdAtencion(calificacion.getAtencionServicio().getId());
        calificacion.getCalificacionPK().setIdPersona(calificacion.getPersona().getCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getCalificacionPK());
            AtencionServicio atencionServicioOld = persistentCalificacion.getAtencionServicio();
            AtencionServicio atencionServicioNew = calificacion.getAtencionServicio();
            Persona personaOld = persistentCalificacion.getPersona();
            Persona personaNew = calificacion.getPersona();
            if (atencionServicioNew != null) {
                atencionServicioNew = em.getReference(atencionServicioNew.getClass(), atencionServicioNew.getId());
                calificacion.setAtencionServicio(atencionServicioNew);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getCedula());
                calificacion.setPersona(personaNew);
            }
            calificacion = em.merge(calificacion);
            if (atencionServicioOld != null && !atencionServicioOld.equals(atencionServicioNew)) {
                atencionServicioOld.getCalificacionList().remove(calificacion);
                atencionServicioOld = em.merge(atencionServicioOld);
            }
            if (atencionServicioNew != null && !atencionServicioNew.equals(atencionServicioOld)) {
                atencionServicioNew.getCalificacionList().add(calificacion);
                atencionServicioNew = em.merge(atencionServicioNew);
            }
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.getCalificacionList().remove(calificacion);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.getCalificacionList().add(calificacion);
                personaNew = em.merge(personaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CalificacionPK id = calificacion.getCalificacionPK();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CalificacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getCalificacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            AtencionServicio atencionServicio = calificacion.getAtencionServicio();
            if (atencionServicio != null) {
                atencionServicio.getCalificacionList().remove(calificacion);
                atencionServicio = em.merge(atencionServicio);
            }
            Persona persona = calificacion.getPersona();
            if (persona != null) {
                persona.getCalificacionList().remove(calificacion);
                persona = em.merge(persona);
            }
            em.remove(calificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacion findCalificacion(CalificacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
