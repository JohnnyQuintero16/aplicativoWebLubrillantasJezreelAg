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
import DTO.Rol;
import DTO.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import DTO.Calificacion;
import DTO.Cita;
import DTO.AtencionServicio;
import DTO.Persona;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author johnny
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getVehiculoList() == null) {
            persona.setVehiculoList(new ArrayList<Vehiculo>());
        }
        if (persona.getCalificacionList() == null) {
            persona.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (persona.getCitaList() == null) {
            persona.setCitaList(new ArrayList<Cita>());
        }
        if (persona.getAtencionServicioList() == null) {
            persona.setAtencionServicioList(new ArrayList<AtencionServicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol idRol = persona.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getId());
                persona.setIdRol(idRol);
            }
            List<Vehiculo> attachedVehiculoList = new ArrayList<Vehiculo>();
            for (Vehiculo vehiculoListVehiculoToAttach : persona.getVehiculoList()) {
                vehiculoListVehiculoToAttach = em.getReference(vehiculoListVehiculoToAttach.getClass(), vehiculoListVehiculoToAttach.getPlaca());
                attachedVehiculoList.add(vehiculoListVehiculoToAttach);
            }
            persona.setVehiculoList(attachedVehiculoList);
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : persona.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            persona.setCalificacionList(attachedCalificacionList);
            List<Cita> attachedCitaList = new ArrayList<Cita>();
            for (Cita citaListCitaToAttach : persona.getCitaList()) {
                citaListCitaToAttach = em.getReference(citaListCitaToAttach.getClass(), citaListCitaToAttach.getId());
                attachedCitaList.add(citaListCitaToAttach);
            }
            persona.setCitaList(attachedCitaList);
            List<AtencionServicio> attachedAtencionServicioList = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListAtencionServicioToAttach : persona.getAtencionServicioList()) {
                atencionServicioListAtencionServicioToAttach = em.getReference(atencionServicioListAtencionServicioToAttach.getClass(), atencionServicioListAtencionServicioToAttach.getId());
                attachedAtencionServicioList.add(atencionServicioListAtencionServicioToAttach);
            }
            persona.setAtencionServicioList(attachedAtencionServicioList);
            em.persist(persona);
            if (idRol != null) {
                idRol.getPersonaList().add(persona);
                idRol = em.merge(idRol);
            }
            for (Vehiculo vehiculoListVehiculo : persona.getVehiculoList()) {
                Persona oldIdPersonaOfVehiculoListVehiculo = vehiculoListVehiculo.getIdPersona();
                vehiculoListVehiculo.setIdPersona(persona);
                vehiculoListVehiculo = em.merge(vehiculoListVehiculo);
                if (oldIdPersonaOfVehiculoListVehiculo != null) {
                    oldIdPersonaOfVehiculoListVehiculo.getVehiculoList().remove(vehiculoListVehiculo);
                    oldIdPersonaOfVehiculoListVehiculo = em.merge(oldIdPersonaOfVehiculoListVehiculo);
                }
            }
            for (Calificacion calificacionListCalificacion : persona.getCalificacionList()) {
                Persona oldPersonaOfCalificacionListCalificacion = calificacionListCalificacion.getPersona();
                calificacionListCalificacion.setPersona(persona);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldPersonaOfCalificacionListCalificacion != null) {
                    oldPersonaOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldPersonaOfCalificacionListCalificacion = em.merge(oldPersonaOfCalificacionListCalificacion);
                }
            }
            for (Cita citaListCita : persona.getCitaList()) {
                Persona oldIdPersonaOfCitaListCita = citaListCita.getIdPersona();
                citaListCita.setIdPersona(persona);
                citaListCita = em.merge(citaListCita);
                if (oldIdPersonaOfCitaListCita != null) {
                    oldIdPersonaOfCitaListCita.getCitaList().remove(citaListCita);
                    oldIdPersonaOfCitaListCita = em.merge(oldIdPersonaOfCitaListCita);
                }
            }
            for (AtencionServicio atencionServicioListAtencionServicio : persona.getAtencionServicioList()) {
                Persona oldIdPersonaOfAtencionServicioListAtencionServicio = atencionServicioListAtencionServicio.getIdPersona();
                atencionServicioListAtencionServicio.setIdPersona(persona);
                atencionServicioListAtencionServicio = em.merge(atencionServicioListAtencionServicio);
                if (oldIdPersonaOfAtencionServicioListAtencionServicio != null) {
                    oldIdPersonaOfAtencionServicioListAtencionServicio.getAtencionServicioList().remove(atencionServicioListAtencionServicio);
                    oldIdPersonaOfAtencionServicioListAtencionServicio = em.merge(oldIdPersonaOfAtencionServicioListAtencionServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getCedula()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getCedula());
            Rol idRolOld = persistentPersona.getIdRol();
            Rol idRolNew = persona.getIdRol();
            List<Vehiculo> vehiculoListOld = persistentPersona.getVehiculoList();
            List<Vehiculo> vehiculoListNew = persona.getVehiculoList();
            List<Calificacion> calificacionListOld = persistentPersona.getCalificacionList();
            List<Calificacion> calificacionListNew = persona.getCalificacionList();
            List<Cita> citaListOld = persistentPersona.getCitaList();
            List<Cita> citaListNew = persona.getCitaList();
            List<AtencionServicio> atencionServicioListOld = persistentPersona.getAtencionServicioList();
            List<AtencionServicio> atencionServicioListNew = persona.getAtencionServicioList();
            List<String> illegalOrphanMessages = null;
            for (Vehiculo vehiculoListOldVehiculo : vehiculoListOld) {
                if (!vehiculoListNew.contains(vehiculoListOldVehiculo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehiculo " + vehiculoListOldVehiculo + " since its idPersona field is not nullable.");
                }
            }
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its persona field is not nullable.");
                }
            }
            for (Cita citaListOldCita : citaListOld) {
                if (!citaListNew.contains(citaListOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaListOldCita + " since its idPersona field is not nullable.");
                }
            }
            for (AtencionServicio atencionServicioListOldAtencionServicio : atencionServicioListOld) {
                if (!atencionServicioListNew.contains(atencionServicioListOldAtencionServicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtencionServicio " + atencionServicioListOldAtencionServicio + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getId());
                persona.setIdRol(idRolNew);
            }
            List<Vehiculo> attachedVehiculoListNew = new ArrayList<Vehiculo>();
            for (Vehiculo vehiculoListNewVehiculoToAttach : vehiculoListNew) {
                vehiculoListNewVehiculoToAttach = em.getReference(vehiculoListNewVehiculoToAttach.getClass(), vehiculoListNewVehiculoToAttach.getPlaca());
                attachedVehiculoListNew.add(vehiculoListNewVehiculoToAttach);
            }
            vehiculoListNew = attachedVehiculoListNew;
            persona.setVehiculoList(vehiculoListNew);
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            persona.setCalificacionList(calificacionListNew);
            List<Cita> attachedCitaListNew = new ArrayList<Cita>();
            for (Cita citaListNewCitaToAttach : citaListNew) {
                citaListNewCitaToAttach = em.getReference(citaListNewCitaToAttach.getClass(), citaListNewCitaToAttach.getId());
                attachedCitaListNew.add(citaListNewCitaToAttach);
            }
            citaListNew = attachedCitaListNew;
            persona.setCitaList(citaListNew);
            List<AtencionServicio> attachedAtencionServicioListNew = new ArrayList<AtencionServicio>();
            for (AtencionServicio atencionServicioListNewAtencionServicioToAttach : atencionServicioListNew) {
                atencionServicioListNewAtencionServicioToAttach = em.getReference(atencionServicioListNewAtencionServicioToAttach.getClass(), atencionServicioListNewAtencionServicioToAttach.getId());
                attachedAtencionServicioListNew.add(atencionServicioListNewAtencionServicioToAttach);
            }
            atencionServicioListNew = attachedAtencionServicioListNew;
            persona.setAtencionServicioList(atencionServicioListNew);
            persona = em.merge(persona);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getPersonaList().remove(persona);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getPersonaList().add(persona);
                idRolNew = em.merge(idRolNew);
            }
            for (Vehiculo vehiculoListNewVehiculo : vehiculoListNew) {
                if (!vehiculoListOld.contains(vehiculoListNewVehiculo)) {
                    Persona oldIdPersonaOfVehiculoListNewVehiculo = vehiculoListNewVehiculo.getIdPersona();
                    vehiculoListNewVehiculo.setIdPersona(persona);
                    vehiculoListNewVehiculo = em.merge(vehiculoListNewVehiculo);
                    if (oldIdPersonaOfVehiculoListNewVehiculo != null && !oldIdPersonaOfVehiculoListNewVehiculo.equals(persona)) {
                        oldIdPersonaOfVehiculoListNewVehiculo.getVehiculoList().remove(vehiculoListNewVehiculo);
                        oldIdPersonaOfVehiculoListNewVehiculo = em.merge(oldIdPersonaOfVehiculoListNewVehiculo);
                    }
                }
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    Persona oldPersonaOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getPersona();
                    calificacionListNewCalificacion.setPersona(persona);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldPersonaOfCalificacionListNewCalificacion != null && !oldPersonaOfCalificacionListNewCalificacion.equals(persona)) {
                        oldPersonaOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldPersonaOfCalificacionListNewCalificacion = em.merge(oldPersonaOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (Cita citaListNewCita : citaListNew) {
                if (!citaListOld.contains(citaListNewCita)) {
                    Persona oldIdPersonaOfCitaListNewCita = citaListNewCita.getIdPersona();
                    citaListNewCita.setIdPersona(persona);
                    citaListNewCita = em.merge(citaListNewCita);
                    if (oldIdPersonaOfCitaListNewCita != null && !oldIdPersonaOfCitaListNewCita.equals(persona)) {
                        oldIdPersonaOfCitaListNewCita.getCitaList().remove(citaListNewCita);
                        oldIdPersonaOfCitaListNewCita = em.merge(oldIdPersonaOfCitaListNewCita);
                    }
                }
            }
            for (AtencionServicio atencionServicioListNewAtencionServicio : atencionServicioListNew) {
                if (!atencionServicioListOld.contains(atencionServicioListNewAtencionServicio)) {
                    Persona oldIdPersonaOfAtencionServicioListNewAtencionServicio = atencionServicioListNewAtencionServicio.getIdPersona();
                    atencionServicioListNewAtencionServicio.setIdPersona(persona);
                    atencionServicioListNewAtencionServicio = em.merge(atencionServicioListNewAtencionServicio);
                    if (oldIdPersonaOfAtencionServicioListNewAtencionServicio != null && !oldIdPersonaOfAtencionServicioListNewAtencionServicio.equals(persona)) {
                        oldIdPersonaOfAtencionServicioListNewAtencionServicio.getAtencionServicioList().remove(atencionServicioListNewAtencionServicio);
                        oldIdPersonaOfAtencionServicioListNewAtencionServicio = em.merge(oldIdPersonaOfAtencionServicioListNewAtencionServicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = persona.getCedula();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vehiculo> vehiculoListOrphanCheck = persona.getVehiculoList();
            for (Vehiculo vehiculoListOrphanCheckVehiculo : vehiculoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Vehiculo " + vehiculoListOrphanCheckVehiculo + " in its vehiculoList field has a non-nullable idPersona field.");
            }
            List<Calificacion> calificacionListOrphanCheck = persona.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable persona field.");
            }
            List<Cita> citaListOrphanCheck = persona.getCitaList();
            for (Cita citaListOrphanCheckCita : citaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Cita " + citaListOrphanCheckCita + " in its citaList field has a non-nullable idPersona field.");
            }
            List<AtencionServicio> atencionServicioListOrphanCheck = persona.getAtencionServicioList();
            for (AtencionServicio atencionServicioListOrphanCheckAtencionServicio : atencionServicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the AtencionServicio " + atencionServicioListOrphanCheckAtencionServicio + " in its atencionServicioList field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol idRol = persona.getIdRol();
            if (idRol != null) {
                idRol.getPersonaList().remove(persona);
                idRol = em.merge(idRol);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
