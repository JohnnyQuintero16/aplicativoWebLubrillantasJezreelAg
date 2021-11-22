/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import DTO.AtencionServicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.FichaTecnica;
import DTO.Cita;
import DTO.Factura;
import DTO.FichaTecnica;
import DTO.Persona;
import DTO.Calificacion;
import java.util.ArrayList;
import java.util.List;
import DTO.DetallesProducto;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USUARIO
 */
public class AtencionServicioJpaController implements Serializable {

    public AtencionServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AtencionServicio atencionServicio) {
        if (atencionServicio.getCalificacionList() == null) {
            atencionServicio.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (atencionServicio.getDetallesProductoList() == null) {
            atencionServicio.setDetallesProductoList(new ArrayList<DetallesProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FichaTecnica idFichaTecnica = atencionServicio.getIdFichaTecnica();
            if (idFichaTecnica != null) {
                idFichaTecnica = em.getReference(idFichaTecnica.getClass(), idFichaTecnica.getId());
                atencionServicio.setIdFichaTecnica(idFichaTecnica);
            }
            Cita idCita = atencionServicio.getIdCita();
            if (idCita != null) {
                idCita = em.getReference(idCita.getClass(), idCita.getId());
                atencionServicio.setIdCita(idCita);
            }
            Factura idFactura = atencionServicio.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getId());
                atencionServicio.setIdFactura(idFactura);
            }
            
            Persona idPersona = atencionServicio.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getCedula());
                atencionServicio.setIdPersona(idPersona);
            }
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : atencionServicio.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            atencionServicio.setCalificacionList(attachedCalificacionList);
            List<DetallesProducto> attachedDetallesProductoList = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListDetallesProductoToAttach : atencionServicio.getDetallesProductoList()) {
                detallesProductoListDetallesProductoToAttach = em.getReference(detallesProductoListDetallesProductoToAttach.getClass(), detallesProductoListDetallesProductoToAttach.getId());
                attachedDetallesProductoList.add(detallesProductoListDetallesProductoToAttach);
            }
            atencionServicio.setDetallesProductoList(attachedDetallesProductoList);
            em.persist(atencionServicio);
            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().add(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            if (idCita != null) {
                idCita.getAtencionServicioList().add(atencionServicio);
                idCita = em.merge(idCita);
            }
            if (idFactura != null) {
                idFactura.getAtencionServicioList().add(atencionServicio);
                idFactura = em.merge(idFactura);
            }
            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().add(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            if (idPersona != null) {
                idPersona.getAtencionServicioList().add(atencionServicio);
                idPersona = em.merge(idPersona);
            }
            for (Calificacion calificacionListCalificacion : atencionServicio.getCalificacionList()) {
                AtencionServicio oldAtencionServicioOfCalificacionListCalificacion = calificacionListCalificacion.getAtencionServicio();
                calificacionListCalificacion.setAtencionServicio(atencionServicio);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldAtencionServicioOfCalificacionListCalificacion != null) {
                    oldAtencionServicioOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldAtencionServicioOfCalificacionListCalificacion = em.merge(oldAtencionServicioOfCalificacionListCalificacion);
                }
            }
            for (DetallesProducto detallesProductoListDetallesProducto : atencionServicio.getDetallesProductoList()) {
                AtencionServicio oldIdAtencionServicioOfDetallesProductoListDetallesProducto = detallesProductoListDetallesProducto.getIdAtencionServicio();
                detallesProductoListDetallesProducto.setIdAtencionServicio(atencionServicio);
                detallesProductoListDetallesProducto = em.merge(detallesProductoListDetallesProducto);
                if (oldIdAtencionServicioOfDetallesProductoListDetallesProducto != null) {
                    oldIdAtencionServicioOfDetallesProductoListDetallesProducto.getDetallesProductoList().remove(detallesProductoListDetallesProducto);
                    oldIdAtencionServicioOfDetallesProductoListDetallesProducto = em.merge(oldIdAtencionServicioOfDetallesProductoListDetallesProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AtencionServicio atencionServicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AtencionServicio persistentAtencionServicio = em.find(AtencionServicio.class, atencionServicio.getId());
            FichaTecnica idFichaTecnicaOld = persistentAtencionServicio.getIdFichaTecnica();
            FichaTecnica idFichaTecnicaNew = atencionServicio.getIdFichaTecnica();
            Cita idCitaOld = persistentAtencionServicio.getIdCita();
            Cita idCitaNew = atencionServicio.getIdCita();
            Factura idFacturaOld = persistentAtencionServicio.getIdFactura();
            Factura idFacturaNew = atencionServicio.getIdFactura();

            Persona idPersonaOld = persistentAtencionServicio.getIdPersona();
            Persona idPersonaNew = atencionServicio.getIdPersona();
            List<Calificacion> calificacionListOld = persistentAtencionServicio.getCalificacionList();
            List<Calificacion> calificacionListNew = atencionServicio.getCalificacionList();
            List<DetallesProducto> detallesProductoListOld = persistentAtencionServicio.getDetallesProductoList();
            List<DetallesProducto> detallesProductoListNew = atencionServicio.getDetallesProductoList();
            List<String> illegalOrphanMessages = null;
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its atencionServicio field is not nullable.");
                }
            }
            for (DetallesProducto detallesProductoListOldDetallesProducto : detallesProductoListOld) {
                if (!detallesProductoListNew.contains(detallesProductoListOldDetallesProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesProducto " + detallesProductoListOldDetallesProducto + " since its idAtencionServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idFichaTecnicaNew != null) {
                idFichaTecnicaNew = em.getReference(idFichaTecnicaNew.getClass(), idFichaTecnicaNew.getId());
                atencionServicio.setIdFichaTecnica(idFichaTecnicaNew);
            }
            if (idCitaNew != null) {
                idCitaNew = em.getReference(idCitaNew.getClass(), idCitaNew.getId());
                atencionServicio.setIdCita(idCitaNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getId());
                atencionServicio.setIdFactura(idFacturaNew);
            }
            if (idFichaTecnicaNew != null) {
                idFichaTecnicaNew = em.getReference(idFichaTecnicaNew.getClass(), idFichaTecnicaNew.getId());
                atencionServicio.setIdFichaTecnica(idFichaTecnicaNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getCedula());
                atencionServicio.setIdPersona(idPersonaNew);
            }
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            atencionServicio.setCalificacionList(calificacionListNew);
            List<DetallesProducto> attachedDetallesProductoListNew = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListNewDetallesProductoToAttach : detallesProductoListNew) {
                detallesProductoListNewDetallesProductoToAttach = em.getReference(detallesProductoListNewDetallesProductoToAttach.getClass(), detallesProductoListNewDetallesProductoToAttach.getId());
                attachedDetallesProductoListNew.add(detallesProductoListNewDetallesProductoToAttach);
            }
            detallesProductoListNew = attachedDetallesProductoListNew;
            atencionServicio.setDetallesProductoList(detallesProductoListNew);
            atencionServicio = em.merge(atencionServicio);
            if (idFichaTecnicaOld != null && !idFichaTecnicaOld.equals(idFichaTecnicaNew)) {
                idFichaTecnicaOld.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnicaOld = em.merge(idFichaTecnicaOld);
            }
            if (idFichaTecnicaNew != null && !idFichaTecnicaNew.equals(idFichaTecnicaOld)) {
                idFichaTecnicaNew.getAtencionServicioList().add(atencionServicio);
                idFichaTecnicaNew = em.merge(idFichaTecnicaNew);
            }
            if (idCitaOld != null && !idCitaOld.equals(idCitaNew)) {
                idCitaOld.getAtencionServicioList().remove(atencionServicio);
                idCitaOld = em.merge(idCitaOld);
            }
            if (idCitaNew != null && !idCitaNew.equals(idCitaOld)) {
                idCitaNew.getAtencionServicioList().add(atencionServicio);
                idCitaNew = em.merge(idCitaNew);
            }
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getAtencionServicioList().remove(atencionServicio);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getAtencionServicioList().add(atencionServicio);
                idFacturaNew = em.merge(idFacturaNew);
            }
            if (idFichaTecnicaOld != null && !idFichaTecnicaOld.equals(idFichaTecnicaNew)) {
                idFichaTecnicaOld.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnicaOld = em.merge(idFichaTecnicaOld);
            }
            if (idFichaTecnicaNew != null && !idFichaTecnicaNew.equals(idFichaTecnicaOld)) {
                idFichaTecnicaNew.getAtencionServicioList().add(atencionServicio);
                idFichaTecnicaNew = em.merge(idFichaTecnicaNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getAtencionServicioList().remove(atencionServicio);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getAtencionServicioList().add(atencionServicio);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    AtencionServicio oldAtencionServicioOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getAtencionServicio();
                    calificacionListNewCalificacion.setAtencionServicio(atencionServicio);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldAtencionServicioOfCalificacionListNewCalificacion != null && !oldAtencionServicioOfCalificacionListNewCalificacion.equals(atencionServicio)) {
                        oldAtencionServicioOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldAtencionServicioOfCalificacionListNewCalificacion = em.merge(oldAtencionServicioOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (DetallesProducto detallesProductoListNewDetallesProducto : detallesProductoListNew) {
                if (!detallesProductoListOld.contains(detallesProductoListNewDetallesProducto)) {
                    AtencionServicio oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto = detallesProductoListNewDetallesProducto.getIdAtencionServicio();
                    detallesProductoListNewDetallesProducto.setIdAtencionServicio(atencionServicio);
                    detallesProductoListNewDetallesProducto = em.merge(detallesProductoListNewDetallesProducto);
                    if (oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto != null && !oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto.equals(atencionServicio)) {
                        oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto.getDetallesProductoList().remove(detallesProductoListNewDetallesProducto);
                        oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto = em.merge(oldIdAtencionServicioOfDetallesProductoListNewDetallesProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = atencionServicio.getId();
                if (findAtencionServicio(id) == null) {
                    throw new NonexistentEntityException("The atencionServicio with id " + id + " no longer exists.");
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
            AtencionServicio atencionServicio;
            try {
                atencionServicio = em.getReference(AtencionServicio.class, id);
                atencionServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atencionServicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calificacion> calificacionListOrphanCheck = atencionServicio.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AtencionServicio (" + atencionServicio + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable atencionServicio field.");
            }
            List<DetallesProducto> detallesProductoListOrphanCheck = atencionServicio.getDetallesProductoList();
            for (DetallesProducto detallesProductoListOrphanCheckDetallesProducto : detallesProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AtencionServicio (" + atencionServicio + ") cannot be destroyed since the DetallesProducto " + detallesProductoListOrphanCheckDetallesProducto + " in its detallesProductoList field has a non-nullable idAtencionServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FichaTecnica idFichaTecnica = atencionServicio.getIdFichaTecnica();
            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            Cita idCita = atencionServicio.getIdCita();
            if (idCita != null) {
                idCita.getAtencionServicioList().remove(atencionServicio);
                idCita = em.merge(idCita);
            }
            Factura idFactura = atencionServicio.getIdFactura();
            if (idFactura != null) {
                idFactura.getAtencionServicioList().remove(atencionServicio);
                idFactura = em.merge(idFactura);
            }

            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            Persona idPersona = atencionServicio.getIdPersona();
            if (idPersona != null) {
                idPersona.getAtencionServicioList().remove(atencionServicio);
                idPersona = em.merge(idPersona);
            }
            em.remove(atencionServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AtencionServicio> findAtencionServicioEntities() {
        return findAtencionServicioEntities(true, -1, -1);
    }

    public List<AtencionServicio> findAtencionServicioEntities(int maxResults, int firstResult) {
        return findAtencionServicioEntities(false, maxResults, firstResult);
    }

    private List<AtencionServicio> findAtencionServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AtencionServicio.class));
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

    public AtencionServicio findAtencionServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AtencionServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtencionServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AtencionServicio> rt = cq.from(AtencionServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
