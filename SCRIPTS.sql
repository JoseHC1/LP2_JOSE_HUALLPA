CREATE DATABASE BD1_HUALLPA;

CREATE TABLE proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL
);

CREATE TABLE insumos (
    id_insumo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock_actual INT NOT NULL,
    frecuencia_compra VARCHAR(50) NOT NULL
);


CREATE TABLE compra_ins (
    nro_compra INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_proveedor INT,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

CREATE TABLE detalleins (
    nro_compra INT,
    id_insumo INT,
    cantidad INT NOT NULL,
    PRIMARY KEY (nro_compra, id_insumo),
    FOREIGN KEY (nro_compra) REFERENCES compra_ins(nro_compra),
    FOREIGN KEY (id_insumo) REFERENCES insumos(id_insumo)
);




DELIMITER $$

CREATE PROCEDURE insertarCompra(
    IN p_id_proveedor INT,
    IN p_total DECIMAL(10, 2),
    IN p_id_insumo INT,
    IN p_cantidad INT
)
BEGIN
    DECLARE nuevo_nro_compra INT;

    INSERT INTO compra_ins (id_proveedor, total)
    VALUES (p_id_proveedor, p_total);
    
    SET nuevo_nro_compra = LAST_INSERT_ID();

    INSERT INTO detalleins (nro_compra, id_insumo, cantidad)
    VALUES (nuevo_nro_compra, p_id_insumo, p_cantidad);
END

DELIMITER ;



DELIMITER $$

CREATE PROCEDURE listarCompras()
BEGIN
    SELECT c.nro_compra , c.fecha, i.descripcion AS producto, p.razon_social AS proveedor, c.total
    FROM compra_ins c
    JOIN proveedores p ON p.id_proveedor = c.id_proveedor
    JOIN detalleins d ON c.nro_compra = d.nro_compra
    JOIN insumos i ON d.id_insumo = i.id_insumo;
END

DELIMITER ;

