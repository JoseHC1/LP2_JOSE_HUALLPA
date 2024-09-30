package org.cibertec.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.cibertec.model.Compra;
import java.util.Date;

public class CompraJpaController implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa_sesion02");
	
	public CompraJpaController(EntityManagerFactory emf) {
		this.emf=emf;
	}
		
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public CompraJpaController() {		
	}
	
	public List<Compra> findAllCompras() {
		em = getEntityManager();
	    em.getTransaction().begin();
	    
	    StoredProcedureQuery query = em.createStoredProcedureQuery("listarCompras");
	    List<Object[]> results = query.getResultList();
	    List<Compra> compras = new ArrayList<>();
	    
	    for (Object[] row : results) {
	        Compra compra = new Compra();
	        compra.setNroCompra((Integer) row[0]);
	        compra.setFecha((Date) row[1]);
	        compra.setProducto((String) row[2]);
	        compra.setProveedor((String) row[3]);
	        compra.setTotal((float) row[4]);

	        compras.add(compra);
	    }

	    em.getTransaction().commit();
	    em.close();
	    
	    return compras;
    }
	
	public void insertarCompra(int idProveedor, double total, int idInsumo, int cantidad) {
        em = getEntityManager();
        em.getTransaction().begin();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("insertarCompra");
            query.registerStoredProcedureParameter("p_id_proveedor", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_total", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_id_insumo", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_cantidad", Integer.class, ParameterMode.IN);

            query.setParameter("p_id_proveedor", idProveedor);
            query.setParameter("p_total", total);
            query.setParameter("p_id_insumo", idInsumo);
            query.setParameter("p_cantidad", cantidad);

            query.execute();
            em.getTransaction().commit();
            System.out.println("Compra insertada exitosamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            System.out.println("Error al insertar la compra: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
