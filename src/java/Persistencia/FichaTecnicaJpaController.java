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
import DTO.Vehiculo;
import DTO.AtencionServicio;
import DTO.FichaTecnica;
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
public class FichaTecnicaJpaController implements Serializable {

    public FichaTecnicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FichaTecnica fichaTecnica) {
        if (fichaTecnica.getAtencionServicioList() == null) {
            fichaTecnica.setAtencionServicioList(new ArrayList<AtencionServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo idVehiculo = fichaTecnica.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo = em.getReference(idVehiculo.getClass(), idVehiculo.getPlaca());
                fichaTecnica.setIdVehiculo(idVehiculo);
            }
            List<AtencionServicio> attachedAtencionServicioList = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListAtencionServicioToAttach : fichaTecnica.getAtencionServicioList()) {
                atencionServicioListAtencionServicioToAttach = em.getReference(atencionServicioListAtencionServicioToAttach.getClass(), atencionServicioListAtencionServicioToAttach.getId());
                attachedAtencionServicioList.add(atencionServicioListAtencionServicioToAttach);
            }
            fichaTecnica.setAtencionServicioList(attachedAtencionServicioList);
            em.persist(fichaTecnica);
            if (idVehiculo != null) {
                idVehiculo.getFichaTecnicaList().add(fichaTecnica);
                idVehiculo = em.merge(idVehiculo);
            }
            for (AtencionServicio atencionServicioListAtencionServicio : fichaTecnica.getAtencionServicioList()) {
                FichaTecnica oldIdFichaTecnicaOfAtencionServicioListAtencionServicio = atencionServicioListAtencionServicio.getIdFichaTecnica();
                atencionServicioListAtencionServicio.setIdFichaTecnica(fichaTecnica);
                atencionServicioListAtencionServicio = em.merge(atencionServicioListAtencionServicio);
                if (oldIdFichaTecnicaOfAtencionServicioListAtencionServicio != null) {
                    oldIdFichaTecnicaOfAtencionServicioListAtencionServicio.getAtencionServicioList().remove(atencionServicioListAtencionServicio);
                    oldIdFichaTecnicaOfAtencionServicioListAtencionServicio = em.merge(oldIdFichaTecnicaOfAtencionServicioListAtencionServicio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FichaTecnica fichaTecnica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FichaTecnica persistentFichaTecnica = em.find(FichaTecnica.class, fichaTecnica.getId());
            Vehiculo idVehiculoOld = persistentFichaTecnica.getIdVehiculo();
            Vehiculo idVehiculoNew = fichaTecnica.getIdVehiculo();
            List<AtencionServicio> atencionServicioListOld = persistentFichaTecnica.getAtencionServicioList();
            List<AtencionServicio> atencionServicioListNew = fichaTecnica.getAtencionServicioList();
            List<String> illegalOrphanMessages = null;
            for (AtencionServicio atencionServicioListOldAtencionServicio : atencionServicioListOld) {
                if (!atencionServicioListNew.contains(atencionServicioListOldAtencionServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtencionServicio " + atencionServicioListOldAtencionServicio + " since its idFichaTecnica field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idVehiculoNew != null) {
                idVehiculoNew = em.getReference(idVehiculoNew.getClass(), idVehiculoNew.getPlaca());
                fichaTecnica.setIdVehiculo(idVehiculoNew);
            }
            List<AtencionServicio> attachedAtencionServicioListNew = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListNewAtencionServicioToAttach : atencionServicioListNew) {
                atencionServicioListNewAtencionServicioToAttach = em.getReference(atencionServicioListNewAtencionServicioToAttach.getClass(), atencionServicioListNewAtencionServicioToAttach.getId());
                attachedAtencionServicioListNew.add(atencionServicioListNewAtencionServicioToAttach);
            }
            atencionServicioListNew = attachedAtencionServicioListNew;
            fichaTecnica.setAtencionServicioList(atencionServicioListNew);
            fichaTecnica = em.merge(fichaTecnica);
            if (idVehiculoOld != null && !idVehiculoOld.equals(idVehiculoNew)) {
                idVehiculoOld.getFichaTecnicaList().remove(fichaTecnica);
                idVehiculoOld = em.merge(idVehiculoOld);
            }
            if (idVehiculoNew != null && !idVehiculoNew.equals(idVehiculoOld)) {
                idVehiculoNew.getFichaTecnicaList().add(fichaTecnica);
                idVehiculoNew = em.merge(idVehiculoNew);
            }
            for (AtencionServicio atencionServicioListNewAtencionServicio : atencionServicioListNew) {
                if (!atencionServicioListOld.contains(atencionServicioListNewAtencionServicio)) {
                    FichaTecnica oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio = atencionServicioListNewAtencionServicio.getIdFichaTecnica();
                    atencionServicioListNewAtencionServicio.setIdFichaTecnica(fichaTecnica);
                    atencionServicioListNewAtencionServicio = em.merge(atencionServicioListNewAtencionServicio);
                    if (oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio != null && !oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio.equals(fichaTecnica)) {
                        oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio.getAtencionServicioList().remove(atencionServicioListNewAtencionServicio);
                        oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio = em.merge(oldIdFichaTecnicaOfAtencionServicioListNewAtencionServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fichaTecnica.getId();
                if (findFichaTecnica(id) == null) {
                    throw new NonexistentEntityException("The fichaTecnica with id " + id + " no longer exists.");
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
            FichaTecnica fichaTecnica;
            try {
                fichaTecnica = em.getReference(FichaTecnica.class, id);
                fichaTecnica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fichaTecnica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AtencionServicio> atencionServicioListOrphanCheck = fichaTecnica.getAtencionServicioList();
            for (AtencionServicio atencionServicioListOrphanCheckAtencionServicio : atencionServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FichaTecnica (" + fichaTecnica + ") cannot be destroyed since the AtencionServicio " + atencionServicioListOrphanCheckAtencionServicio + " in its atencionServicioList field has a non-nullable idFichaTecnica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Vehiculo idVehiculo = fichaTecnica.getIdVehiculo();
            if (idVehiculo != null) {
                idVehiculo.getFichaTecnicaList().remove(fichaTecnica);
                idVehiculo = em.merge(idVehiculo);
            }
            em.remove(fichaTecnica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FichaTecnica> findFichaTecnicaEntities() {
        return findFichaTecnicaEntities(true, -1, -1);
    }

    public List<FichaTecnica> findFichaTecnicaEntities(int maxResults, int firstResult) {
        return findFichaTecnicaEntities(false, maxResults, firstResult);
    }

    private List<FichaTecnica> findFichaTecnicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FichaTecnica.class));
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

    public FichaTecnica findFichaTecnica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FichaTecnica.class, id);
        } finally {
            em.close();
        }
    }

    public int getFichaTecnicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FichaTecnica> rt = cq.from(FichaTecnica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
