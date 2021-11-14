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
import DTO.Calificacion;
import DTO.Cita;
import DTO.Factura;
import DTO.Persona;
import DTO.DetallesProducto;
import java.util.ArrayList;
import java.util.List;
import DTO.DetallesServicio;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cristian
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
        if (atencionServicio.getDetallesProductoList() == null) {
            atencionServicio.setDetallesProductoList(new ArrayList<DetallesProducto>());
        }
        if (atencionServicio.getDetallesServicioList() == null) {
            atencionServicio.setDetallesServicioList(new ArrayList<DetallesServicio>());
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
            Calificacion idCalificacion = atencionServicio.getIdCalificacion();
            if (idCalificacion != null) {
                idCalificacion = em.getReference(idCalificacion.getClass(), idCalificacion.getId());
                atencionServicio.setIdCalificacion(idCalificacion);
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
            List<DetallesProducto> attachedDetallesProductoList = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListDetallesProductoToAttach : atencionServicio.getDetallesProductoList()) {
                detallesProductoListDetallesProductoToAttach = em.getReference(detallesProductoListDetallesProductoToAttach.getClass(), detallesProductoListDetallesProductoToAttach.getId());
                attachedDetallesProductoList.add(detallesProductoListDetallesProductoToAttach);
            }
            atencionServicio.setDetallesProductoList(attachedDetallesProductoList);
            List<DetallesServicio> attachedDetallesServicioList = new ArrayList<DetallesServicio>();
            for (DetallesServicio detallesServicioListDetallesServicioToAttach : atencionServicio.getDetallesServicioList()) {
                detallesServicioListDetallesServicioToAttach = em.getReference(detallesServicioListDetallesServicioToAttach.getClass(), detallesServicioListDetallesServicioToAttach.getId());
                attachedDetallesServicioList.add(detallesServicioListDetallesServicioToAttach);
            }
            atencionServicio.setDetallesServicioList(attachedDetallesServicioList);
            em.persist(atencionServicio);
            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().add(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            if (idCalificacion != null) {
                idCalificacion.getAtencionServicioList().add(atencionServicio);
                idCalificacion = em.merge(idCalificacion);
            }
            if (idCita != null) {
                idCita.getAtencionServicioList().add(atencionServicio);
                idCita = em.merge(idCita);
            }
            if (idFactura != null) {
                idFactura.getAtencionServicioList().add(atencionServicio);
                idFactura = em.merge(idFactura);
            }
            if (idPersona != null) {
                idPersona.getAtencionServicioList().add(atencionServicio);
                idPersona = em.merge(idPersona);
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
            for (DetallesServicio detallesServicioListDetallesServicio : atencionServicio.getDetallesServicioList()) {
                AtencionServicio oldIdAntencionServicioOfDetallesServicioListDetallesServicio = detallesServicioListDetallesServicio.getIdAntencionServicio();
                detallesServicioListDetallesServicio.setIdAntencionServicio(atencionServicio);
                detallesServicioListDetallesServicio = em.merge(detallesServicioListDetallesServicio);
                if (oldIdAntencionServicioOfDetallesServicioListDetallesServicio != null) {
                    oldIdAntencionServicioOfDetallesServicioListDetallesServicio.getDetallesServicioList().remove(detallesServicioListDetallesServicio);
                    oldIdAntencionServicioOfDetallesServicioListDetallesServicio = em.merge(oldIdAntencionServicioOfDetallesServicioListDetallesServicio);
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
            Calificacion idCalificacionOld = persistentAtencionServicio.getIdCalificacion();
            Calificacion idCalificacionNew = atencionServicio.getIdCalificacion();
            Cita idCitaOld = persistentAtencionServicio.getIdCita();
            Cita idCitaNew = atencionServicio.getIdCita();
            Factura idFacturaOld = persistentAtencionServicio.getIdFactura();
            Factura idFacturaNew = atencionServicio.getIdFactura();
            Persona idPersonaOld = persistentAtencionServicio.getIdPersona();
            Persona idPersonaNew = atencionServicio.getIdPersona();
            List<DetallesProducto> detallesProductoListOld = persistentAtencionServicio.getDetallesProductoList();
            List<DetallesProducto> detallesProductoListNew = atencionServicio.getDetallesProductoList();
            List<DetallesServicio> detallesServicioListOld = persistentAtencionServicio.getDetallesServicioList();
            List<DetallesServicio> detallesServicioListNew = atencionServicio.getDetallesServicioList();
            List<String> illegalOrphanMessages = null;
            for (DetallesProducto detallesProductoListOldDetallesProducto : detallesProductoListOld) {
                if (!detallesProductoListNew.contains(detallesProductoListOldDetallesProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesProducto " + detallesProductoListOldDetallesProducto + " since its idAtencionServicio field is not nullable.");
                }
            }
            for (DetallesServicio detallesServicioListOldDetallesServicio : detallesServicioListOld) {
                if (!detallesServicioListNew.contains(detallesServicioListOldDetallesServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesServicio " + detallesServicioListOldDetallesServicio + " since its idAntencionServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idFichaTecnicaNew != null) {
                idFichaTecnicaNew = em.getReference(idFichaTecnicaNew.getClass(), idFichaTecnicaNew.getId());
                atencionServicio.setIdFichaTecnica(idFichaTecnicaNew);
            }
            if (idCalificacionNew != null) {
                idCalificacionNew = em.getReference(idCalificacionNew.getClass(), idCalificacionNew.getId());
                atencionServicio.setIdCalificacion(idCalificacionNew);
            }
            if (idCitaNew != null) {
                idCitaNew = em.getReference(idCitaNew.getClass(), idCitaNew.getId());
                atencionServicio.setIdCita(idCitaNew);
            }
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getId());
                atencionServicio.setIdFactura(idFacturaNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getCedula());
                atencionServicio.setIdPersona(idPersonaNew);
            }
            List<DetallesProducto> attachedDetallesProductoListNew = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListNewDetallesProductoToAttach : detallesProductoListNew) {
                detallesProductoListNewDetallesProductoToAttach = em.getReference(detallesProductoListNewDetallesProductoToAttach.getClass(), detallesProductoListNewDetallesProductoToAttach.getId());
                attachedDetallesProductoListNew.add(detallesProductoListNewDetallesProductoToAttach);
            }
            detallesProductoListNew = attachedDetallesProductoListNew;
            atencionServicio.setDetallesProductoList(detallesProductoListNew);
            List<DetallesServicio> attachedDetallesServicioListNew = new ArrayList<DetallesServicio>();
            for (DetallesServicio detallesServicioListNewDetallesServicioToAttach : detallesServicioListNew) {
                detallesServicioListNewDetallesServicioToAttach = em.getReference(detallesServicioListNewDetallesServicioToAttach.getClass(), detallesServicioListNewDetallesServicioToAttach.getId());
                attachedDetallesServicioListNew.add(detallesServicioListNewDetallesServicioToAttach);
            }
            detallesServicioListNew = attachedDetallesServicioListNew;
            atencionServicio.setDetallesServicioList(detallesServicioListNew);
            atencionServicio = em.merge(atencionServicio);
            if (idFichaTecnicaOld != null && !idFichaTecnicaOld.equals(idFichaTecnicaNew)) {
                idFichaTecnicaOld.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnicaOld = em.merge(idFichaTecnicaOld);
            }
            if (idFichaTecnicaNew != null && !idFichaTecnicaNew.equals(idFichaTecnicaOld)) {
                idFichaTecnicaNew.getAtencionServicioList().add(atencionServicio);
                idFichaTecnicaNew = em.merge(idFichaTecnicaNew);
            }
            if (idCalificacionOld != null && !idCalificacionOld.equals(idCalificacionNew)) {
                idCalificacionOld.getAtencionServicioList().remove(atencionServicio);
                idCalificacionOld = em.merge(idCalificacionOld);
            }
            if (idCalificacionNew != null && !idCalificacionNew.equals(idCalificacionOld)) {
                idCalificacionNew.getAtencionServicioList().add(atencionServicio);
                idCalificacionNew = em.merge(idCalificacionNew);
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
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getAtencionServicioList().remove(atencionServicio);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getAtencionServicioList().add(atencionServicio);
                idPersonaNew = em.merge(idPersonaNew);
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
            for (DetallesServicio detallesServicioListNewDetallesServicio : detallesServicioListNew) {
                if (!detallesServicioListOld.contains(detallesServicioListNewDetallesServicio)) {
                    AtencionServicio oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio = detallesServicioListNewDetallesServicio.getIdAntencionServicio();
                    detallesServicioListNewDetallesServicio.setIdAntencionServicio(atencionServicio);
                    detallesServicioListNewDetallesServicio = em.merge(detallesServicioListNewDetallesServicio);
                    if (oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio != null && !oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio.equals(atencionServicio)) {
                        oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio.getDetallesServicioList().remove(detallesServicioListNewDetallesServicio);
                        oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio = em.merge(oldIdAntencionServicioOfDetallesServicioListNewDetallesServicio);
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
            List<DetallesProducto> detallesProductoListOrphanCheck = atencionServicio.getDetallesProductoList();
            for (DetallesProducto detallesProductoListOrphanCheckDetallesProducto : detallesProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AtencionServicio (" + atencionServicio + ") cannot be destroyed since the DetallesProducto " + detallesProductoListOrphanCheckDetallesProducto + " in its detallesProductoList field has a non-nullable idAtencionServicio field.");
            }
            List<DetallesServicio> detallesServicioListOrphanCheck = atencionServicio.getDetallesServicioList();
            for (DetallesServicio detallesServicioListOrphanCheckDetallesServicio : detallesServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AtencionServicio (" + atencionServicio + ") cannot be destroyed since the DetallesServicio " + detallesServicioListOrphanCheckDetallesServicio + " in its detallesServicioList field has a non-nullable idAntencionServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FichaTecnica idFichaTecnica = atencionServicio.getIdFichaTecnica();
            if (idFichaTecnica != null) {
                idFichaTecnica.getAtencionServicioList().remove(atencionServicio);
                idFichaTecnica = em.merge(idFichaTecnica);
            }
            Calificacion idCalificacion = atencionServicio.getIdCalificacion();
            if (idCalificacion != null) {
                idCalificacion.getAtencionServicioList().remove(atencionServicio);
                idCalificacion = em.merge(idCalificacion);
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
