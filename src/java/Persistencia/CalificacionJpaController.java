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
import DTO.Persona;
import DTO.AtencionServicio;
import DTO.Calificacion;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
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

    public void create(Calificacion calificacion) {
        if (calificacion.getAtencionServicioList() == null) {
            calificacion.setAtencionServicioList(new ArrayList<AtencionServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = calificacion.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getCedula());
                calificacion.setIdPersona(idPersona);
            }
            List<AtencionServicio> attachedAtencionServicioList = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListAtencionServicioToAttach : calificacion.getAtencionServicioList()) {
                atencionServicioListAtencionServicioToAttach = em.getReference(atencionServicioListAtencionServicioToAttach.getClass(), atencionServicioListAtencionServicioToAttach.getId());
                attachedAtencionServicioList.add(atencionServicioListAtencionServicioToAttach);
            }
            calificacion.setAtencionServicioList(attachedAtencionServicioList);
            em.persist(calificacion);
            if (idPersona != null) {
                idPersona.getCalificacionList().add(calificacion);
                idPersona = em.merge(idPersona);
            }
            for (AtencionServicio atencionServicioListAtencionServicio : calificacion.getAtencionServicioList()) {
                Calificacion oldIdCalificacionOfAtencionServicioListAtencionServicio = atencionServicioListAtencionServicio.getIdCalificacion();
                atencionServicioListAtencionServicio.setIdCalificacion(calificacion);
                atencionServicioListAtencionServicio = em.merge(atencionServicioListAtencionServicio);
                if (oldIdCalificacionOfAtencionServicioListAtencionServicio != null) {
                    oldIdCalificacionOfAtencionServicioListAtencionServicio.getAtencionServicioList().remove(atencionServicioListAtencionServicio);
                    oldIdCalificacionOfAtencionServicioListAtencionServicio = em.merge(oldIdCalificacionOfAtencionServicioListAtencionServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getId());
            Persona idPersonaOld = persistentCalificacion.getIdPersona();
            Persona idPersonaNew = calificacion.getIdPersona();
            List<AtencionServicio> atencionServicioListOld = persistentCalificacion.getAtencionServicioList();
            List<AtencionServicio> atencionServicioListNew = calificacion.getAtencionServicioList();
            List<String> illegalOrphanMessages = null;
            for (AtencionServicio atencionServicioListOldAtencionServicio : atencionServicioListOld) {
                if (!atencionServicioListNew.contains(atencionServicioListOldAtencionServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtencionServicio " + atencionServicioListOldAtencionServicio + " since its idCalificacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getCedula());
                calificacion.setIdPersona(idPersonaNew);
            }
            List<AtencionServicio> attachedAtencionServicioListNew = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListNewAtencionServicioToAttach : atencionServicioListNew) {
                atencionServicioListNewAtencionServicioToAttach = em.getReference(atencionServicioListNewAtencionServicioToAttach.getClass(), atencionServicioListNewAtencionServicioToAttach.getId());
                attachedAtencionServicioListNew.add(atencionServicioListNewAtencionServicioToAttach);
            }
            atencionServicioListNew = attachedAtencionServicioListNew;
            calificacion.setAtencionServicioList(atencionServicioListNew);
            calificacion = em.merge(calificacion);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getCalificacionList().remove(calificacion);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getCalificacionList().add(calificacion);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (AtencionServicio atencionServicioListNewAtencionServicio : atencionServicioListNew) {
                if (!atencionServicioListOld.contains(atencionServicioListNewAtencionServicio)) {
                    Calificacion oldIdCalificacionOfAtencionServicioListNewAtencionServicio = atencionServicioListNewAtencionServicio.getIdCalificacion();
                    atencionServicioListNewAtencionServicio.setIdCalificacion(calificacion);
                    atencionServicioListNewAtencionServicio = em.merge(atencionServicioListNewAtencionServicio);
                    if (oldIdCalificacionOfAtencionServicioListNewAtencionServicio != null && !oldIdCalificacionOfAtencionServicioListNewAtencionServicio.equals(calificacion)) {
                        oldIdCalificacionOfAtencionServicioListNewAtencionServicio.getAtencionServicioList().remove(atencionServicioListNewAtencionServicio);
                        oldIdCalificacionOfAtencionServicioListNewAtencionServicio = em.merge(oldIdCalificacionOfAtencionServicioListNewAtencionServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calificacion.getId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AtencionServicio> atencionServicioListOrphanCheck = calificacion.getAtencionServicioList();
            for (AtencionServicio atencionServicioListOrphanCheckAtencionServicio : atencionServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Calificacion (" + calificacion + ") cannot be destroyed since the AtencionServicio " + atencionServicioListOrphanCheckAtencionServicio + " in its atencionServicioList field has a non-nullable idCalificacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = calificacion.getIdPersona();
            if (idPersona != null) {
                idPersona.getCalificacionList().remove(calificacion);
                idPersona = em.merge(idPersona);
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

    public Calificacion findCalificacion(Integer id) {
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
