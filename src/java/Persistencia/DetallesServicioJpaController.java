/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import DTO.DetallesServicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Servicio;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cristian
 */
public class DetallesServicioJpaController implements Serializable {

    public DetallesServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetallesServicio detallesServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio idServicio = detallesServicio.getIdServicio();
            if (idServicio != null) {
                idServicio = em.getReference(idServicio.getClass(), idServicio.getId());
                detallesServicio.setIdServicio(idServicio);
            }
            em.persist(detallesServicio);
            if (idServicio != null) {
                idServicio.getDetallesServicioList().add(detallesServicio);
                idServicio = em.merge(idServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallesServicio detallesServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesServicio persistentDetallesServicio = em.find(DetallesServicio.class, detallesServicio.getId());
            Servicio idServicioOld = persistentDetallesServicio.getIdServicio();
            Servicio idServicioNew = detallesServicio.getIdServicio();
            if (idServicioNew != null) {
                idServicioNew = em.getReference(idServicioNew.getClass(), idServicioNew.getId());
                detallesServicio.setIdServicio(idServicioNew);
            }
            detallesServicio = em.merge(detallesServicio);
            if (idServicioOld != null && !idServicioOld.equals(idServicioNew)) {
                idServicioOld.getDetallesServicioList().remove(detallesServicio);
                idServicioOld = em.merge(idServicioOld);
            }
            if (idServicioNew != null && !idServicioNew.equals(idServicioOld)) {
                idServicioNew.getDetallesServicioList().add(detallesServicio);
                idServicioNew = em.merge(idServicioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallesServicio.getId();
                if (findDetallesServicio(id) == null) {
                    throw new NonexistentEntityException("The detallesServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesServicio detallesServicio;
            try {
                detallesServicio = em.getReference(DetallesServicio.class, id);
                detallesServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallesServicio with id " + id + " no longer exists.", enfe);
            }
            Servicio idServicio = detallesServicio.getIdServicio();
            if (idServicio != null) {
                idServicio.getDetallesServicioList().remove(detallesServicio);
                idServicio = em.merge(idServicio);
            }
            em.remove(detallesServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallesServicio> findDetallesServicioEntities() {
        return findDetallesServicioEntities(true, -1, -1);
    }

    public List<DetallesServicio> findDetallesServicioEntities(int maxResults, int firstResult) {
        return findDetallesServicioEntities(false, maxResults, firstResult);
    }

    private List<DetallesServicio> findDetallesServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallesServicio.class));
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

    public DetallesServicio findDetallesServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallesServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallesServicio> rt = cq.from(DetallesServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
