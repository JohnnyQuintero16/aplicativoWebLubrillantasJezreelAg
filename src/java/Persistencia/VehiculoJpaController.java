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
import DTO.Marca;
import DTO.Persona;
import DTO.Tipo;
import DTO.FichaTecnica;
import DTO.Vehiculo;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USUARIO
 */
public class VehiculoJpaController implements Serializable {

    public VehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehiculo vehiculo) throws PreexistingEntityException, Exception {
        if (vehiculo.getFichaTecnicaList() == null) {
            vehiculo.setFichaTecnicaList(new ArrayList<FichaTecnica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca idMarca = vehiculo.getIdMarca();
            if (idMarca != null) {
                idMarca = em.getReference(idMarca.getClass(), idMarca.getId());
                vehiculo.setIdMarca(idMarca);
            }
            Persona idPersona = vehiculo.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getCedula());
                vehiculo.setIdPersona(idPersona);
            }
            Tipo idTipo = vehiculo.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getId());
                vehiculo.setIdTipo(idTipo);
            }
            List<FichaTecnica> attachedFichaTecnicaList = new ArrayList<FichaTecnica>();
            for (FichaTecnica fichaTecnicaListFichaTecnicaToAttach : vehiculo.getFichaTecnicaList()) {
                fichaTecnicaListFichaTecnicaToAttach = em.getReference(fichaTecnicaListFichaTecnicaToAttach.getClass(), fichaTecnicaListFichaTecnicaToAttach.getId());
                attachedFichaTecnicaList.add(fichaTecnicaListFichaTecnicaToAttach);
            }
            vehiculo.setFichaTecnicaList(attachedFichaTecnicaList);
            em.persist(vehiculo);
            if (idMarca != null) {
                idMarca.getVehiculoList().add(vehiculo);
                idMarca = em.merge(idMarca);
            }
            if (idPersona != null) {
                idPersona.getVehiculoList().add(vehiculo);
                idPersona = em.merge(idPersona);
            }
            if (idTipo != null) {
                idTipo.getVehiculoList().add(vehiculo);
                idTipo = em.merge(idTipo);
            }
            for (FichaTecnica fichaTecnicaListFichaTecnica : vehiculo.getFichaTecnicaList()) {
                Vehiculo oldIdVehiculoOfFichaTecnicaListFichaTecnica = fichaTecnicaListFichaTecnica.getIdVehiculo();
                fichaTecnicaListFichaTecnica.setIdVehiculo(vehiculo);
                fichaTecnicaListFichaTecnica = em.merge(fichaTecnicaListFichaTecnica);
                if (oldIdVehiculoOfFichaTecnicaListFichaTecnica != null) {
                    oldIdVehiculoOfFichaTecnicaListFichaTecnica.getFichaTecnicaList().remove(fichaTecnicaListFichaTecnica);
                    oldIdVehiculoOfFichaTecnicaListFichaTecnica = em.merge(oldIdVehiculoOfFichaTecnicaListFichaTecnica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVehiculo(vehiculo.getPlaca()) != null) {
                throw new PreexistingEntityException("Vehiculo " + vehiculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehiculo vehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo persistentVehiculo = em.find(Vehiculo.class, vehiculo.getPlaca());
            Marca idMarcaOld = persistentVehiculo.getIdMarca();
            Marca idMarcaNew = vehiculo.getIdMarca();
            Persona idPersonaOld = persistentVehiculo.getIdPersona();
            Persona idPersonaNew = vehiculo.getIdPersona();
            Tipo idTipoOld = persistentVehiculo.getIdTipo();
            Tipo idTipoNew = vehiculo.getIdTipo();
            List<FichaTecnica> fichaTecnicaListOld = persistentVehiculo.getFichaTecnicaList();
            List<FichaTecnica> fichaTecnicaListNew = vehiculo.getFichaTecnicaList();
            List<String> illegalOrphanMessages = null;
            for (FichaTecnica fichaTecnicaListOldFichaTecnica : fichaTecnicaListOld) {
                if (!fichaTecnicaListNew.contains(fichaTecnicaListOldFichaTecnica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FichaTecnica " + fichaTecnicaListOldFichaTecnica + " since its idVehiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMarcaNew != null) {
                idMarcaNew = em.getReference(idMarcaNew.getClass(), idMarcaNew.getId());
                vehiculo.setIdMarca(idMarcaNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getCedula());
                vehiculo.setIdPersona(idPersonaNew);
            }
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getId());
                vehiculo.setIdTipo(idTipoNew);
            }
            List<FichaTecnica> attachedFichaTecnicaListNew = new ArrayList<FichaTecnica>();
            for (FichaTecnica fichaTecnicaListNewFichaTecnicaToAttach : fichaTecnicaListNew) {
                fichaTecnicaListNewFichaTecnicaToAttach = em.getReference(fichaTecnicaListNewFichaTecnicaToAttach.getClass(), fichaTecnicaListNewFichaTecnicaToAttach.getId());
                attachedFichaTecnicaListNew.add(fichaTecnicaListNewFichaTecnicaToAttach);
            }
            fichaTecnicaListNew = attachedFichaTecnicaListNew;
            vehiculo.setFichaTecnicaList(fichaTecnicaListNew);
            vehiculo = em.merge(vehiculo);
            if (idMarcaOld != null && !idMarcaOld.equals(idMarcaNew)) {
                idMarcaOld.getVehiculoList().remove(vehiculo);
                idMarcaOld = em.merge(idMarcaOld);
            }
            if (idMarcaNew != null && !idMarcaNew.equals(idMarcaOld)) {
                idMarcaNew.getVehiculoList().add(vehiculo);
                idMarcaNew = em.merge(idMarcaNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getVehiculoList().remove(vehiculo);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getVehiculoList().add(vehiculo);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getVehiculoList().remove(vehiculo);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getVehiculoList().add(vehiculo);
                idTipoNew = em.merge(idTipoNew);
            }
            for (FichaTecnica fichaTecnicaListNewFichaTecnica : fichaTecnicaListNew) {
                if (!fichaTecnicaListOld.contains(fichaTecnicaListNewFichaTecnica)) {
                    Vehiculo oldIdVehiculoOfFichaTecnicaListNewFichaTecnica = fichaTecnicaListNewFichaTecnica.getIdVehiculo();
                    fichaTecnicaListNewFichaTecnica.setIdVehiculo(vehiculo);
                    fichaTecnicaListNewFichaTecnica = em.merge(fichaTecnicaListNewFichaTecnica);
                    if (oldIdVehiculoOfFichaTecnicaListNewFichaTecnica != null && !oldIdVehiculoOfFichaTecnicaListNewFichaTecnica.equals(vehiculo)) {
                        oldIdVehiculoOfFichaTecnicaListNewFichaTecnica.getFichaTecnicaList().remove(fichaTecnicaListNewFichaTecnica);
                        oldIdVehiculoOfFichaTecnicaListNewFichaTecnica = em.merge(oldIdVehiculoOfFichaTecnicaListNewFichaTecnica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vehiculo.getPlaca();
                if (findVehiculo(id) == null) {
                    throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo vehiculo;
            try {
                vehiculo = em.getReference(Vehiculo.class, id);
                vehiculo.getPlaca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FichaTecnica> fichaTecnicaListOrphanCheck = vehiculo.getFichaTecnicaList();
            for (FichaTecnica fichaTecnicaListOrphanCheckFichaTecnica : fichaTecnicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the FichaTecnica " + fichaTecnicaListOrphanCheckFichaTecnica + " in its fichaTecnicaList field has a non-nullable idVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Marca idMarca = vehiculo.getIdMarca();
            if (idMarca != null) {
                idMarca.getVehiculoList().remove(vehiculo);
                idMarca = em.merge(idMarca);
            }
            Persona idPersona = vehiculo.getIdPersona();
            if (idPersona != null) {
                idPersona.getVehiculoList().remove(vehiculo);
                idPersona = em.merge(idPersona);
            }
            Tipo idTipo = vehiculo.getIdTipo();
            if (idTipo != null) {
                idTipo.getVehiculoList().remove(vehiculo);
                idTipo = em.merge(idTipo);
            }
            em.remove(vehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehiculo> findVehiculoEntities() {
        return findVehiculoEntities(true, -1, -1);
    }

    public List<Vehiculo> findVehiculoEntities(int maxResults, int firstResult) {
        return findVehiculoEntities(false, maxResults, firstResult);
    }

    private List<Vehiculo> findVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehiculo.class));
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

    public Vehiculo findVehiculo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehiculo> rt = cq.from(Vehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
