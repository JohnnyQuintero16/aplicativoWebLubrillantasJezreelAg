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
import DTO.DetallesProducto;
import DTO.Producto;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USUARIO
 */
public class DetallesProductoJpaController implements Serializable {

    public DetallesProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetallesProducto detallesProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AtencionServicio idAtencionServicio = detallesProducto.getIdAtencionServicio();
            if (idAtencionServicio != null) {
                idAtencionServicio = em.getReference(idAtencionServicio.getClass(), idAtencionServicio.getId());
                detallesProducto.setIdAtencionServicio(idAtencionServicio);
            }
            Producto idProducto = detallesProducto.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getCodigo());
                detallesProducto.setIdProducto(idProducto);
            }
            em.persist(detallesProducto);
            if (idAtencionServicio != null) {
                idAtencionServicio.getDetallesProductoList().add(detallesProducto);
                idAtencionServicio = em.merge(idAtencionServicio);
            }
            if (idProducto != null) {
                idProducto.getDetallesProductoList().add(detallesProducto);
                idProducto = em.merge(idProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallesProducto detallesProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesProducto persistentDetallesProducto = em.find(DetallesProducto.class, detallesProducto.getId());
            AtencionServicio idAtencionServicioOld = persistentDetallesProducto.getIdAtencionServicio();
            AtencionServicio idAtencionServicioNew = detallesProducto.getIdAtencionServicio();
            Producto idProductoOld = persistentDetallesProducto.getIdProducto();
            Producto idProductoNew = detallesProducto.getIdProducto();
            if (idAtencionServicioNew != null) {
                idAtencionServicioNew = em.getReference(idAtencionServicioNew.getClass(), idAtencionServicioNew.getId());
                detallesProducto.setIdAtencionServicio(idAtencionServicioNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getCodigo());
                detallesProducto.setIdProducto(idProductoNew);
            }
            detallesProducto = em.merge(detallesProducto);
            if (idAtencionServicioOld != null && !idAtencionServicioOld.equals(idAtencionServicioNew)) {
                idAtencionServicioOld.getDetallesProductoList().remove(detallesProducto);
                idAtencionServicioOld = em.merge(idAtencionServicioOld);
            }
            if (idAtencionServicioNew != null && !idAtencionServicioNew.equals(idAtencionServicioOld)) {
                idAtencionServicioNew.getDetallesProductoList().add(detallesProducto);
                idAtencionServicioNew = em.merge(idAtencionServicioNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getDetallesProductoList().remove(detallesProducto);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getDetallesProductoList().add(detallesProducto);
                idProductoNew = em.merge(idProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallesProducto.getId();
                if (findDetallesProducto(id) == null) {
                    throw new NonexistentEntityException("The detallesProducto with id " + id + " no longer exists.");
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
            DetallesProducto detallesProducto;
            try {
                detallesProducto = em.getReference(DetallesProducto.class, id);
                detallesProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallesProducto with id " + id + " no longer exists.", enfe);
            }
            AtencionServicio idAtencionServicio = detallesProducto.getIdAtencionServicio();
            if (idAtencionServicio != null) {
                idAtencionServicio.getDetallesProductoList().remove(detallesProducto);
                idAtencionServicio = em.merge(idAtencionServicio);
            }
            Producto idProducto = detallesProducto.getIdProducto();
            if (idProducto != null) {
                idProducto.getDetallesProductoList().remove(detallesProducto);
                idProducto = em.merge(idProducto);
            }
            em.remove(detallesProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallesProducto> findDetallesProductoEntities() {
        return findDetallesProductoEntities(true, -1, -1);
    }

    public List<DetallesProducto> findDetallesProductoEntities(int maxResults, int firstResult) {
        return findDetallesProductoEntities(false, maxResults, firstResult);
    }

    private List<DetallesProducto> findDetallesProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallesProducto.class));
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

    public DetallesProducto findDetallesProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallesProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallesProducto> rt = cq.from(DetallesProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
