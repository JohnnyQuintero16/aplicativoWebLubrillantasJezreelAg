/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import DTO.MarcaProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Producto;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author johnny
 */
public class MarcaProductoJpaController implements Serializable {

    public MarcaProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MarcaProducto marcaProducto) {
        if (marcaProducto.getProductoList() == null) {
            marcaProducto.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : marcaProducto.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getCodigo());
                attachedProductoList.add(productoListProductoToAttach);
            }
            marcaProducto.setProductoList(attachedProductoList);
            em.persist(marcaProducto);
            for (Producto productoListProducto : marcaProducto.getProductoList()) {
                MarcaProducto oldIdMarcaOfProductoListProducto = productoListProducto.getIdMarca();
                productoListProducto.setIdMarca(marcaProducto);
                productoListProducto = em.merge(productoListProducto);
                if (oldIdMarcaOfProductoListProducto != null) {
                    oldIdMarcaOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldIdMarcaOfProductoListProducto = em.merge(oldIdMarcaOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MarcaProducto marcaProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MarcaProducto persistentMarcaProducto = em.find(MarcaProducto.class, marcaProducto.getId());
            List<Producto> productoListOld = persistentMarcaProducto.getProductoList();
            List<Producto> productoListNew = marcaProducto.getProductoList();
            List<String> illegalOrphanMessages = null;
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its idMarca field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getCodigo());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            marcaProducto.setProductoList(productoListNew);
            marcaProducto = em.merge(marcaProducto);
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    MarcaProducto oldIdMarcaOfProductoListNewProducto = productoListNewProducto.getIdMarca();
                    productoListNewProducto.setIdMarca(marcaProducto);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldIdMarcaOfProductoListNewProducto != null && !oldIdMarcaOfProductoListNewProducto.equals(marcaProducto)) {
                        oldIdMarcaOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldIdMarcaOfProductoListNewProducto = em.merge(oldIdMarcaOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marcaProducto.getId();
                if (findMarcaProducto(id) == null) {
                    throw new NonexistentEntityException("The marcaProducto with id " + id + " no longer exists.");
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
            MarcaProducto marcaProducto;
            try {
                marcaProducto = em.getReference(MarcaProducto.class, id);
                marcaProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marcaProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = marcaProducto.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MarcaProducto (" + marcaProducto + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable idMarca field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(marcaProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MarcaProducto> findMarcaProductoEntities() {
        return findMarcaProductoEntities(true, -1, -1);
    }

    public List<MarcaProducto> findMarcaProductoEntities(int maxResults, int firstResult) {
        return findMarcaProductoEntities(false, maxResults, firstResult);
    }

    private List<MarcaProducto> findMarcaProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MarcaProducto.class));
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

    public MarcaProducto findMarcaProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MarcaProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MarcaProducto> rt = cq.from(MarcaProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
