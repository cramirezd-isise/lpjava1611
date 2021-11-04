/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Carlos
 * Created: 04/11/2021
 */


DELIMITER //
#LISTAR
CREATE PROCEDURE pa_listar_provincias()
BEGIN
	SELECT * FROM Provincias;
END;

#INSERTAR
CREATE PROCEDURE pa_insertar_provincias(
p_nombre varchar(50)
)
BEGIN
	INSERT INTO Provincias(nombre) VALUES(p_nombre);
END;

#MODIFICAR
CREATE PROCEDURE pa_modificar_provincias(
p_nombre varchar(50),
p_id_prov int
)
BEGIN
	UPDATE Provincias SET nombre = p_nombre 
    WHERE id_prov = p_id_prov;
END;
#ELIMINAR

CREATE PROCEDURE pa_eliminar_provincias(
p_id_prov int
)
BEGIN
	DELETE FROM Provincias WHERE ip_prov = p_id_prov;
END;

// DELIMITER ;

