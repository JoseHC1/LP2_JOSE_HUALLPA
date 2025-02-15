package org.cibertec.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="compra_ins")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nro_compra")
	private int nroCompra;

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fecha;

	@Column(name="id_proveedor", nullable = false)
	private int idProveedor;
	
	@Column(name="total", nullable = false)
	private float total;
	
	private String proveedor;
	private String producto;
}
