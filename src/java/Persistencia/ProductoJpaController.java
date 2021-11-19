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
import DTO.DetallesProducto;
import DTO.Producto;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cristian
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, Exception {
        if (producto.getDetallesProductoList() == null) {
            producto.setDetallesProductoList(new ArrayList<DetallesProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetallesProducto> attachedDetallesProductoList = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListDetallesProductoToAttach : producto.getDetallesProductoList()) {
                detallesProductoListDetallesProductoToAttach = em.getReference(detallesProductoListDetallesProductoToAttach.getClass(), detallesProductoListDetallesProductoToAttach.getId());
                attachedDetallesProductoList.add(detallesProductoListDetallesProductoToAttach);
            }
            producto.setDetallesProductoList(attachedDetallesProductoList);
            em.persist(producto);
            for (DetallesProducto detallesProductoListDetallesProducto : producto.getDetallesProductoList()) {
                Producto oldIdProductoOfDetallesProductoListDetallesProducto = detallesProductoListDetallesProducto.getIdProducto();
                detallesProductoListDetallesProducto.setIdProducto(producto);
                detallesProductoListDetallesProducto = em.merge(detallesProductoListDetallesProducto);
                if (oldIdProductoOfDetallesProductoListDetallesProducto != null) {
                    oldIdProductoOfDetallesProductoListDetallesProducto.getDetallesProductoList().remove(detallesProductoListDetallesProducto);
                    oldIdProductoOfDetallesProductoListDetallesProducto = em.merge(oldIdProductoOfDetallesProductoListDetallesProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProducto(producto.getCodigo()) != null) {
                throw new PreexistingEntityException("Producto " + producto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getCodigo());
            List<DetallesProducto> detallesProductoListOld = persistentProducto.getDetallesProductoList();
            List<DetallesProducto> detallesProductoListNew = producto.getDetallesProductoList();
            List<String> illegalOrphanMessages = null;
            for (DetallesProducto detallesProductoListOldDetallesProducto : detallesProductoListOld) {
                if (!detallesProductoListNew.contains(detallesProductoListOldDetallesProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesProducto " + detallesProductoListOldDetallesProducto + " since its idProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetallesProducto> attachedDetallesProductoListNew = new ArrayList<DetallesProducto>();
            for (DetallesProducto detallesProductoListNewDetallesProductoToAttach : detallesProductoListNew) {
                detallesProductoListNewDetallesProductoToAttach = em.getReference(detallesProductoListNewDetallesProductoToAttach.getClass(), detallesProductoListNewDetallesProductoToAttach.getId());
                attachedDetallesProductoListNew.add(detallesProductoListNewDetallesProductoToAttach);
            }
            detallesProductoListNew = attachedDetallesProductoListNew;
            producto.setDetallesProductoList(detallesProductoListNew);
            producto = em.merge(producto);
            for (DetallesProducto detallesProductoListNewDetallesProducto : detallesProductoListNew) {
                if (!detallesProductoListOld.contains(detallesProductoListNewDetallesProducto)) {
                    Producto oldIdProductoOfDetallesProductoListNewDetallesProducto = detallesProductoListNewDetallesProducto.getIdProducto();
                    detallesProductoListNewDetallesProducto.setIdProducto(producto);
                    detallesProductoListNewDetallesProducto = em.merge(detallesProductoListNewDetallesProducto);
                    if (oldIdProductoOfDetallesProductoListNewDetallesProducto != null && !oldIdProductoOfDetallesProductoListNewDetallesProducto.equals(producto)) {
                        oldIdProductoOfDetallesProductoListNewDetallesProducto.getDetallesProductoList().remove(detallesProductoListNewDetallesProducto);
                        oldIdProductoOfDetallesProductoListNewDetallesProducto = em.merge(oldIdProductoOfDetallesProductoListNewDetallesProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = producto.getCodigo();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetallesProducto> detallesProductoListOrphanCheck = producto.getDetallesProductoList();
            for (DetallesProducto detallesProductoListOrphanCheckDetallesProducto : detallesProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetallesProducto " + detallesProductoListOrphanCheckDetallesProducto + " in its detallesProductoList field has a non-nullable idProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Producto> findProductoTipo(String tipo) {
        List<Producto> allproduct = findProductoEntities();
        List<Producto> busqueda =new ArrayList<Producto>();
        
      
        for(Producto pro: allproduct){
            
            if(pro.getTipo().equals(tipo)){
            busqueda.add(pro);
                    }
        }
        
        return busqueda;
    }

   

    

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
