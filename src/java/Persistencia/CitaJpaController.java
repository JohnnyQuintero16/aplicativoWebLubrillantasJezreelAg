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
import DTO.Cita;
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
public class CitaJpaController implements Serializable {

    public CitaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cita cita) {
        if (cita.getAtencionServicioList() == null) {
            cita.setAtencionServicioList(new ArrayList<AtencionServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = cita.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getCedula());
                cita.setIdPersona(idPersona);
            }
            List<AtencionServicio> attachedAtencionServicioList = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListAtencionServicioToAttach : cita.getAtencionServicioList()) {
                atencionServicioListAtencionServicioToAttach = em.getReference(atencionServicioListAtencionServicioToAttach.getClass(), atencionServicioListAtencionServicioToAttach.getId());
                attachedAtencionServicioList.add(atencionServicioListAtencionServicioToAttach);
            }
            cita.setAtencionServicioList(attachedAtencionServicioList);
            em.persist(cita);
            if (idPersona != null) {
                idPersona.getCitaList().add(cita);
                idPersona = em.merge(idPersona);
            }
            for (AtencionServicio atencionServicioListAtencionServicio : cita.getAtencionServicioList()) {
                Cita oldIdCitaOfAtencionServicioListAtencionServicio = atencionServicioListAtencionServicio.getIdCita();
                atencionServicioListAtencionServicio.setIdCita(cita);
                atencionServicioListAtencionServicio = em.merge(atencionServicioListAtencionServicio);
                if (oldIdCitaOfAtencionServicioListAtencionServicio != null) {
                    oldIdCitaOfAtencionServicioListAtencionServicio.getAtencionServicioList().remove(atencionServicioListAtencionServicio);
                    oldIdCitaOfAtencionServicioListAtencionServicio = em.merge(oldIdCitaOfAtencionServicioListAtencionServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cita cita) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cita persistentCita = em.find(Cita.class, cita.getId());
            Persona idPersonaOld = persistentCita.getIdPersona();
            Persona idPersonaNew = cita.getIdPersona();
            List<AtencionServicio> atencionServicioListOld = persistentCita.getAtencionServicioList();
            List<AtencionServicio> atencionServicioListNew = cita.getAtencionServicioList();
            List<String> illegalOrphanMessages = null;
            for (AtencionServicio atencionServicioListOldAtencionServicio : atencionServicioListOld) {
                if (!atencionServicioListNew.contains(atencionServicioListOldAtencionServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtencionServicio " + atencionServicioListOldAtencionServicio + " since its idCita field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getCedula());
                cita.setIdPersona(idPersonaNew);
            }
            List<AtencionServicio> attachedAtencionServicioListNew = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListNewAtencionServicioToAttach : atencionServicioListNew) {
                atencionServicioListNewAtencionServicioToAttach = em.getReference(atencionServicioListNewAtencionServicioToAttach.getClass(), atencionServicioListNewAtencionServicioToAttach.getId());
                attachedAtencionServicioListNew.add(atencionServicioListNewAtencionServicioToAttach);
            }
            atencionServicioListNew = attachedAtencionServicioListNew;
            cita.setAtencionServicioList(atencionServicioListNew);
            cita = em.merge(cita);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getCitaList().remove(cita);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getCitaList().add(cita);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (AtencionServicio atencionServicioListNewAtencionServicio : atencionServicioListNew) {
                if (!atencionServicioListOld.contains(atencionServicioListNewAtencionServicio)) {
                    Cita oldIdCitaOfAtencionServicioListNewAtencionServicio = atencionServicioListNewAtencionServicio.getIdCita();
                    atencionServicioListNewAtencionServicio.setIdCita(cita);
                    atencionServicioListNewAtencionServicio = em.merge(atencionServicioListNewAtencionServicio);
                    if (oldIdCitaOfAtencionServicioListNewAtencionServicio != null && !oldIdCitaOfAtencionServicioListNewAtencionServicio.equals(cita)) {
                        oldIdCitaOfAtencionServicioListNewAtencionServicio.getAtencionServicioList().remove(atencionServicioListNewAtencionServicio);
                        oldIdCitaOfAtencionServicioListNewAtencionServicio = em.merge(oldIdCitaOfAtencionServicioListNewAtencionServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cita.getId();
                if (findCita(id) == null) {
                    throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
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
            Cita cita;
            try {
                cita = em.getReference(Cita.class, id);
                cita.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cita with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AtencionServicio> atencionServicioListOrphanCheck = cita.getAtencionServicioList();
            for (AtencionServicio atencionServicioListOrphanCheckAtencionServicio : atencionServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cita (" + cita + ") cannot be destroyed since the AtencionServicio " + atencionServicioListOrphanCheckAtencionServicio + " in its atencionServicioList field has a non-nullable idCita field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = cita.getIdPersona();
            if (idPersona != null) {
                idPersona.getCitaList().remove(cita);
                idPersona = em.merge(idPersona);
            }
            em.remove(cita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cita> findCitaEntities() {
        return findCitaEntities(true, -1, -1);
    }

    public List<Cita> findCitaEntities(int maxResults, int firstResult) {
        return findCitaEntities(false, maxResults, firstResult);
    }

    private List<Cita> findCitaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cita.class));
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

    public Cita findCita(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cita.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cita> rt = cq.from(Cita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
