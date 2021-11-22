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
import DTO.DetallesServicio;
import DTO.Servicio;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USUARIO
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getDetallesServicioList() == null) {
            servicio.setDetallesServicioList(new ArrayList<DetallesServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetallesServicio> attachedDetallesServicioList = new ArrayList<DetallesServicio>();
            for (DetallesServicio detallesServicioListDetallesServicioToAttach : servicio.getDetallesServicioList()) {
                detallesServicioListDetallesServicioToAttach = em.getReference(detallesServicioListDetallesServicioToAttach.getClass(), detallesServicioListDetallesServicioToAttach.getId());
                attachedDetallesServicioList.add(detallesServicioListDetallesServicioToAttach);
            }
            servicio.setDetallesServicioList(attachedDetallesServicioList);
            em.persist(servicio);
            for (DetallesServicio detallesServicioListDetallesServicio : servicio.getDetallesServicioList()) {
                Servicio oldIdServicioOfDetallesServicioListDetallesServicio = detallesServicioListDetallesServicio.getIdServicio();
                detallesServicioListDetallesServicio.setIdServicio(servicio);
                detallesServicioListDetallesServicio = em.merge(detallesServicioListDetallesServicio);
                if (oldIdServicioOfDetallesServicioListDetallesServicio != null) {
                    oldIdServicioOfDetallesServicioListDetallesServicio.getDetallesServicioList().remove(detallesServicioListDetallesServicio);
                    oldIdServicioOfDetallesServicioListDetallesServicio = em.merge(oldIdServicioOfDetallesServicioListDetallesServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
            List<DetallesServicio> detallesServicioListOld = persistentServicio.getDetallesServicioList();
            List<DetallesServicio> detallesServicioListNew = servicio.getDetallesServicioList();
            List<String> illegalOrphanMessages = null;
            for (DetallesServicio detallesServicioListOldDetallesServicio : detallesServicioListOld) {
                if (!detallesServicioListNew.contains(detallesServicioListOldDetallesServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesServicio " + detallesServicioListOldDetallesServicio + " since its idServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetallesServicio> attachedDetallesServicioListNew = new ArrayList<DetallesServicio>();
            for (DetallesServicio detallesServicioListNewDetallesServicioToAttach : detallesServicioListNew) {
                detallesServicioListNewDetallesServicioToAttach = em.getReference(detallesServicioListNewDetallesServicioToAttach.getClass(), detallesServicioListNewDetallesServicioToAttach.getId());
                attachedDetallesServicioListNew.add(detallesServicioListNewDetallesServicioToAttach);
            }
            detallesServicioListNew = attachedDetallesServicioListNew;
            servicio.setDetallesServicioList(detallesServicioListNew);
            servicio = em.merge(servicio);
            for (DetallesServicio detallesServicioListNewDetallesServicio : detallesServicioListNew) {
                if (!detallesServicioListOld.contains(detallesServicioListNewDetallesServicio)) {
                    Servicio oldIdServicioOfDetallesServicioListNewDetallesServicio = detallesServicioListNewDetallesServicio.getIdServicio();
                    detallesServicioListNewDetallesServicio.setIdServicio(servicio);
                    detallesServicioListNewDetallesServicio = em.merge(detallesServicioListNewDetallesServicio);
                    if (oldIdServicioOfDetallesServicioListNewDetallesServicio != null && !oldIdServicioOfDetallesServicioListNewDetallesServicio.equals(servicio)) {
                        oldIdServicioOfDetallesServicioListNewDetallesServicio.getDetallesServicioList().remove(detallesServicioListNewDetallesServicio);
                        oldIdServicioOfDetallesServicioListNewDetallesServicio = em.merge(oldIdServicioOfDetallesServicioListNewDetallesServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getId();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetallesServicio> detallesServicioListOrphanCheck = servicio.getDetallesServicioList();
            for (DetallesServicio detallesServicioListOrphanCheckDetallesServicio : detallesServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicio (" + servicio + ") cannot be destroyed since the DetallesServicio " + detallesServicioListOrphanCheckDetallesServicio + " in its detallesServicioList field has a non-nullable idServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
