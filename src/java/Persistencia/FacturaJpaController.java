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
import DTO.Factura;
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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getAtencionServicioList() == null) {
            factura.setAtencionServicioList(new ArrayList<AtencionServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AtencionServicio> attachedAtencionServicioList = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListAtencionServicioToAttach : factura.getAtencionServicioList()) {
                atencionServicioListAtencionServicioToAttach = em.getReference(atencionServicioListAtencionServicioToAttach.getClass(), atencionServicioListAtencionServicioToAttach.getId());
                attachedAtencionServicioList.add(atencionServicioListAtencionServicioToAttach);
            }
            factura.setAtencionServicioList(attachedAtencionServicioList);
            em.persist(factura);
            for (AtencionServicio atencionServicioListAtencionServicio : factura.getAtencionServicioList()) {
                Factura oldIdFacturaOfAtencionServicioListAtencionServicio = atencionServicioListAtencionServicio.getIdFactura();
                atencionServicioListAtencionServicio.setIdFactura(factura);
                atencionServicioListAtencionServicio = em.merge(atencionServicioListAtencionServicio);
                if (oldIdFacturaOfAtencionServicioListAtencionServicio != null) {
                    oldIdFacturaOfAtencionServicioListAtencionServicio.getAtencionServicioList().remove(atencionServicioListAtencionServicio);
                    oldIdFacturaOfAtencionServicioListAtencionServicio = em.merge(oldIdFacturaOfAtencionServicioListAtencionServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getId());
            List<AtencionServicio> atencionServicioListOld = persistentFactura.getAtencionServicioList();
            List<AtencionServicio> atencionServicioListNew = factura.getAtencionServicioList();
            List<String> illegalOrphanMessages = null;
            for (AtencionServicio atencionServicioListOldAtencionServicio : atencionServicioListOld) {
                if (!atencionServicioListNew.contains(atencionServicioListOldAtencionServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtencionServicio " + atencionServicioListOldAtencionServicio + " since its idFactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AtencionServicio> attachedAtencionServicioListNew = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListNewAtencionServicioToAttach : atencionServicioListNew) {
                atencionServicioListNewAtencionServicioToAttach = em.getReference(atencionServicioListNewAtencionServicioToAttach.getClass(), atencionServicioListNewAtencionServicioToAttach.getId());
                attachedAtencionServicioListNew.add(atencionServicioListNewAtencionServicioToAttach);
            }
            atencionServicioListNew = attachedAtencionServicioListNew;
            factura.setAtencionServicioList(atencionServicioListNew);
            factura = em.merge(factura);
            for (AtencionServicio atencionServicioListNewAtencionServicio : atencionServicioListNew) {
                if (!atencionServicioListOld.contains(atencionServicioListNewAtencionServicio)) {
                    Factura oldIdFacturaOfAtencionServicioListNewAtencionServicio = atencionServicioListNewAtencionServicio.getIdFactura();
                    atencionServicioListNewAtencionServicio.setIdFactura(factura);
                    atencionServicioListNewAtencionServicio = em.merge(atencionServicioListNewAtencionServicio);
                    if (oldIdFacturaOfAtencionServicioListNewAtencionServicio != null && !oldIdFacturaOfAtencionServicioListNewAtencionServicio.equals(factura)) {
                        oldIdFacturaOfAtencionServicioListNewAtencionServicio.getAtencionServicioList().remove(atencionServicioListNewAtencionServicio);
                        oldIdFacturaOfAtencionServicioListNewAtencionServicio = em.merge(oldIdFacturaOfAtencionServicioListNewAtencionServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getId();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AtencionServicio> atencionServicioListOrphanCheck = factura.getAtencionServicioList();
            for (AtencionServicio atencionServicioListOrphanCheckAtencionServicio : atencionServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the AtencionServicio " + atencionServicioListOrphanCheckAtencionServicio + " in its atencionServicioList field has a non-nullable idFactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
