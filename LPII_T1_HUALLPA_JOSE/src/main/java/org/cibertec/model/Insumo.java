package org.cibertec.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="insumos")
public class Insumo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_insumo")
	private int idInsumo;

	@Column(name="descripcion", nullable = false)
	private String descripcion;

	@Column(name="precio", nullable = false)
	private float precio;
	
	@Column(name="stock_actual", nullable = false)
	private int stockActual;
	
	@Column(name="frecuencia_compra", nullable = false)
	private String frecuencia;
}
