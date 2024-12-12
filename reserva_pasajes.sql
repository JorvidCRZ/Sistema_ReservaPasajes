create database Reserva_Pasajes;
---------------------------------------------
use Reserva_Pasajes;
---------------------------------------------
create table usuario(
id_usuario int primary key auto_increment,
nombres varchar(40) not null,
apellidos varchar(40) not null,
direccion varchar(40) not null,
nacionalidad varchar(40),
email varchar(40) not null,
telefono char(9) not null,
contraseña varchar(40) unique not null, 
rol varchar(40) not null
);
---------------------------------------------

create table piloto(
piloto_id int primary key auto_increment,
nombres_pilot varchar(40) not null,
apellidos_pilot varchar(40) not null,
fech_nac_pilot date,
nacionalidad_pilot varchar(20),
nro_licencia_pilot varchar(20) not null,
tipo_licencia_pilot varchar(20) not null,
fecha_contratacion_pilot date not null,
estado_pilot char(1) not null
);
---------------------------------------------
ALTER TABLE piloto AUTO_INCREMENT = 100;
ALTER TABLE piloto
add column dni char(8) not null ;
---------------------------------------------

create table avion(
avion_id int primary key auto_increment,
capacidad_total  numeric not null,
estado_avion char(1)not null
);
---------------------------------------------
ALTER TABLE avion AUTO_INCREMENT = 1000;
---------------------------------------------

create table pago(
pago_id int primary key auto_increment,
reserva_id int,
monto_pago decimal(8,2),
fecha_pago date not null,
metodo_pago varchar(20) not null,
igv decimal(8,2) not null
);
---------------------------------------------

create table detalle_reserva(
detalle_reserva_id int primary key auto_increment,
reserva_id int ,
monto_total decimal(8,2) not null,
cantidad_asientos numeric not null
);
---------------------------------------------

create table pasajero(
pasajero_id int primary key auto_increment,
reserva_id int,
vuelo_id int,
nombres_pasajero varchar(40) not null,
apellido_pasajero varchar(40) not null,
nro_asiento varchar(10) not null,
nacionalidad_pasajero varchar(20),
fech_nacimiento_pasajero date not null,
clase varchar(20) not null
);
alter table pasajero
add column dni char(8) not null ;
---
alter table pasajero
add column pasaporte varchar(20) not null ;
---------------------------------------------

create table vuelo(
vuelo_id int primary key auto_increment,
avion_id int,
piloto_id int,
origen varchar(30) not null,
destino varchar(30),
fecha_salida date not null,
fecha_llegada date not null,
duracion datetime not null,
precio decimal(8,2)
);
---------------------------------------------

create table reserva(
reserva_id int primary key auto_increment,
usuario_id int,
vuelo_id int,
fecha_reserva date not null,
numero_pasajeros numeric,
estado_reserva char(1)not null
);
---------------------------------------------

alter table pago
add constraint conexion1 foreign key(reserva_id)
references reserva(reserva_id);
---------------------------------------------

alter table detalle_reserva
add constraint conexion2 foreign key(reserva_id)
references reserva(reserva_id);
---------------------------------------------

alter table pasajero
add constraint conexion3 foreign key(reserva_id)
references reserva(reserva_id);
---------------------------------------------

alter table pasajero
add constraint conexion4 foreign key(vuelo_id)
references vuelo(vuelo_id);
---------------------------------------------

alter table vuelo
add constraint conexion5 foreign key(avion_id)
references avion(avion_id);
---------------------------------------------

alter table vuelo
add constraint conexion6 foreign key(piloto_id)
references piloto(piloto_id);
---------------------------------------------

alter table reserva
add constraint conexion7 foreign key(usuario_id)
references usuario(id_usuario) ;

---------------------------------------------

alter table reserva 
add constraint conexion8 foreign key(vuelo_id)
references vuelo(vuelo_id);

DELIMITER $

CREATE PROCEDURE InsertarPiloto (IN p_nombres_pilot VARCHAR(40),IN p_apellidos_pilot VARCHAR(40),IN p_fech_nac_pilot DATE,IN p_nacionalidad_pilot VARCHAR(20),
    IN p_nro_licencia_pilot VARCHAR(20),IN p_tipo_licencia_pilot VARCHAR(20),IN p_fecha_contratacion_pilot DATE,IN p_estado_pilot CHAR(1),IN p_dni CHAR(8)
)
BEGIN
    INSERT INTO piloto (nombres_pilot,apellidos_pilot,fech_nac_pilot,nacionalidad_pilot,nro_licencia_pilot,tipo_licencia_pilot,fecha_contratacion_pilot, estado_pilot, dni) 
    VALUES (p_nombres_pilot,p_apellidos_pilot,p_fech_nac_pilot,p_nacionalidad_pilot,p_nro_licencia_pilot,p_tipo_licencia_pilot,p_fecha_contratacion_pilot,p_estado_pilot,p_dni);
END $

DELIMITER ;
CALL InsertarPiloto('Juan', 'Pérez', '1980-05-12', 'Peruano', 'LIC12345', 'Comercial', '2020-01-15', 'A', '12345678');
CALL InsertarPiloto('Carlos', 'Gómez', '1975-08-23', 'Argentino', 'LIC54321', 'Privado', '2018-06-10', 'A', '87654321');
CALL InsertarPiloto('Lucía', 'Martínez', '1982-03-15', 'Chilena', 'LIC98765', 'Comercial', '2017-09-25', 'A', '11223344');
CALL InsertarPiloto('Miguel', 'Rodríguez', '1990-11-30', 'Colombiano', 'LIC67890', 'Carga', '2019-11-12', 'I', '55667788');
CALL InsertarPiloto('Ana', 'Fernández', '1985-07-04', 'Mexicana', 'LIC13579', 'Comercial', '2016-02-28', 'A', '44332211');
CALL InsertarPiloto('Pedro', 'López', '1991-12-22', 'Peruano', 'LIC24680', 'Carga', '2021-03-18', 'A', '99887766');
CALL InsertarPiloto('María', 'Ramírez', '1987-04-17', 'Española', 'LIC15937', 'Privado', '2015-08-05', 'I', '33445566');
CALL InsertarPiloto('Roberto', 'García', '1978-09-19', 'Brasileño', 'LIC86420', 'Comercial', '2013-12-30', 'A', '66778899');
CALL InsertarPiloto('Laura', 'Torres', '1983-02-28', 'Colombiana', 'LIC75319', 'Carga', '2014-04-22', 'A', '22334455');
CALL InsertarPiloto('Jorge', 'Suárez', '1976-06-11', 'Venezolano', 'LIC95163', 'Comercial', '2012-10-16', 'I', '88990011');
DELIMITER $

CREATE PROCEDURE InsertarAvion ( IN p_capacidad_total NUMERIC, IN p_estado_avion CHAR(1))
BEGIN
    INSERT INTO avion (capacidad_total,estado_avion) 
    VALUES (p_capacidad_total,p_estado_avion);
END $

DELIMITER ;
CALL InsertarAvion(180, 'A');
CALL InsertarAvion(220, 'A');
CALL InsertarAvion(150, 'I');
CALL InsertarAvion(300, 'A');
CALL InsertarAvion(250, 'I');
CALL InsertarAvion(200, 'A');
CALL InsertarAvion(100, 'I');
/*'A' para activo, 'C' para cancelado, y 'I' para inactivo.*/
DELIMITER $

CREATE PROCEDURE InsertarVuelo(IN p_avion_id INT,IN p_piloto_id INT,IN p_origen VARCHAR(30),IN p_destino VARCHAR(30), IN p_fecha_salida DATE, IN p_fecha_llegada DATE,IN p_duracion TIME,IN p_precio DECIMAL(8,2))
BEGIN
    INSERT INTO vuelo (avion_id, piloto_id, origen, destino, fecha_salida, fecha_llegada, duracion, precio) 
    VALUES (p_avion_id, p_piloto_id, p_origen, p_destino, p_fecha_salida, p_fecha_llegada, p_duracion, p_precio );
END $

DELIMITER ;
CALL InsertarVuelo(1005,107,'Sao Paulo', 'Río de Janeiro', '2024-12-10', '2024-12-10', '01:10:00', 195.50);
CALL InsertarVuelo(1000,100 , 'Lima', 'Cusco', '2024-12-01', '2024-12-01', '01:20:00', 200.50);
CALL InsertarVuelo( 1001,101, 'Buenos Aires', 'Santiago', '2024-12-02', '2024-12-02', '02:10:00', 250.00);
CALL InsertarVuelo( 1005,102, 'Santiago', 'La Paz', '2024-12-03', '2024-12-03', '02:45:00', 300.00);
CALL InsertarVuelo( 1000,104, 'Bogotá', 'Caracas', '2024-12-04', '2024-12-04', '01:50:00', 180.75);
CALL InsertarVuelo( 1001,105, 'Ciudad de México', 'Guadalajara', '2024-12-05', '2024-12-05', '01:30:00', 230.25);
CALL InsertarVuelo( 1003,107, 'Madrid', 'Barcelona', '2024-12-06', '2024-12-06', '01:25:00', 210.60);
CALL InsertarVuelo( 1005,108, 'Río de Janeiro', 'Brasilia', '2024-12-07', '2024-12-07', '02:00:00', 275.40);
CALL InsertarVuelo( 1001,100, 'Lima', 'Quito', '2024-12-08', '2024-12-08', '03:10:00', 350.00);
CALL InsertarVuelo( 1000,104, 'Quito', 'Bogotá', '2024-12-09', '2024-12-09', '02:20:00', 240.80);
DELIMITER $
CREATE PROCEDURE InsertarUsuario(IN p_nombres VARCHAR(40),IN p_apellidos VARCHAR(40),IN p_direccion VARCHAR(40),IN p_nacionalidad VARCHAR(40),IN p_email VARCHAR(40),IN p_telefono CHAR(9),IN p_contraseña VARCHAR(40),IN user_name VARCHAR(40))
BEGIN
    INSERT INTO usuario (nombres, apellidos, direccion, nacionalidad, email, telefono, contraseña, user_name)
    VALUES (p_nombres, p_apellidos, p_direccion, p_nacionalidad, p_email, p_telefono, p_contraseña, user_name);
END $

DELIMITER ;
CALL InsertarUsuario('Jorvix', 'Cruz', 'Calle Falsa 123', 'Peruana', 'Jorvix.crz@email.com', '987654321','Admin', 'Admin');
CALL InsertarUsuario('Olinda', 'Huaripata', 'Calle Falsa 1223', 'Peruana', 'olimn@email.com', '123345456','Linda123', 'Linda123');
select * from vuelo

DELIMITER $
CREATE PROCEDURE InsertarReserva(IN p_usuario_id INT,IN p_vuelo_id INT,IN p_fecha_reserva DATE,IN p_numero_pasajeros NUMERIC,IN p_estado_reserva CHAR(1))
BEGIN
    INSERT INTO reserva (usuario_id,vuelo_id,fecha_reserva,numero_pasajeros,estado_reserva)
    VALUES (p_usuario_id,p_vuelo_id,p_fecha_reserva,p_numero_pasajeros,p_estado_reserva);
END $
DELIMITER ;
CALL InsertarReserva(4, 1, '2024-11-10', 2, 'A');
CALL InsertarReserva(4, 2, '2024-11-01', 2, 'A'); 
CALL InsertarReserva(4, 6, '2024-01-8', 5, 'A');
CALL InsertarReserva(4, 4, '2023-10-9', 4, 'A');
CALL InsertarReserva(4, 5, '2023-11-12', 6, 'A');
CALL InsertarReserva(4, 3, '2022-8-11', 10, 'A');
CALL InsertarReserva(4, 10, '2024-11-8', 12, 'A');
CALL InsertarReserva(4, 4 , '2024-10-12', 2, 'A');
select * from pago
DELIMITER $

CREATE PROCEDURE InsertarPago(IN p_reserva_id INT,IN p_fecha_pago DATE,IN p_metodo_pago VARCHAR(20))
BEGIN
    DECLARE v_precio DECIMAL(8,2);
    DECLARE v_numero_pasajeros INT;
    DECLARE v_monto_pago DECIMAL(8,2);
    DECLARE v_igv DECIMAL(8,2);
    SELECT v.precio, r.numero_pasajeros
    INTO v_precio, v_numero_pasajeros
    FROM vuelo v
    JOIN reserva r ON r.vuelo_id = v.vuelo_id
    WHERE r.reserva_id = p_reserva_id;
    SET v_monto_pago = v_precio * v_numero_pasajeros;
    SET v_igv = v_monto_pago * 0.18;
    INSERT INTO pago (reserva_id, monto_pago, fecha_pago, metodo_pago, igv)
    VALUES (p_reserva_id, v_monto_pago, p_fecha_pago, p_metodo_pago, v_igv);
END $
DELIMITER ;

CALL InsertarPago(9, '2024-11-12', 'Tarjeta de Debito'); 
CALL InsertarPago(7, '2024-11-9', 'Tarjeta de Debito'); 
CALL InsertarPago(6, '2022-8-12', 'Tarjeta de Crédito'); 
CALL InsertarPago(5, '2023-11-10', 'Tarjeta de Debito'); 
CALL InsertarPago(4, '2023-10-10', 'Tarjeta de Crédito'); 
CALL InsertarPago(3, '2024-01-9', 'Tarjeta de Debito'); 
CALL InsertarPago(1, '2024-12-01', 'Tarjeta de Crédito'); 
CALL InsertarPago(2, '2024-12-02', 'Tarjeta de Débito'); 
DELIMITER $

CREATE PROCEDURE InsertarPasajero(IN p_reserva_id INT,IN p_vuelo_id INT,IN p_nombres_pasajero VARCHAR(40),IN p_apellido_pasajero VARCHAR(40),
IN p_nro_asiento VARCHAR(10),IN p_nacionalidad_pasajero VARCHAR(20),IN p_fech_nacimiento_pasajero DATE,IN p_clase VARCHAR(20),IN p_dni CHAR(8),IN p_pasaporte VARCHAR(20))
BEGIN
    INSERT INTO pasajero (reserva_id,vuelo_id,nombres_pasajero,apellido_pasajero,nro_asiento,nacionalidad_pasajero,fech_nacimiento_pasajero,clase,dni,pasaporte) 
    VALUES (p_reserva_id,p_vuelo_id,p_nombres_pasajero,p_apellido_pasajero,p_nro_asiento,p_nacionalidad_pasajero,p_fech_nacimiento_pasajero,p_clase,p_dni,p_pasaporte);
END $
DELIMITER ;
CALL InsertarPasajero(1, 1, 'Luis Jesus', 'Pérez Quispe', '1A', 'Peruana', '1990-01-01', 'Económica', '12345678', 'AB123456');
CALL InsertarPasajero(1, 1, 'Ana Luisa', 'Lujan Gómez', '1B', 'Peruana', '1992-02-02', 'Económica', '87654321', 'CD987654');
CALL InsertarPasajero(2, 2, 'Carlos José', 'Torres Candamo', '2A', 'Chilena', '1985-03-03', 'Económica', '34567890', 'EF567890');
CALL InsertarPasajero(2, 2, 'María Isabel', 'López Diaz', '2B', 'Chilena', '1988-04-04', 'Económica', '90876543', 'GH432109');
CALL InsertarPasajero(3, 6, 'Juan Carlos', 'García Pérez', '1A', 'Mexicano', '1990-05-12', 'Primera', '75849302', 'MX123456');
CALL InsertarPasajero(3, 6, 'Ana Sofía', 'Rodríguez Martínez', '2C', 'Argentina', '1995-08-25', 'Económica', '65432109', 'AR654321');
CALL InsertarPasajero(3, 6, 'Pedro Luis', 'Hernández Gómez', '3D', 'Colombiano', '1987-03-15', 'Ejecutiva', '87654321', 'CO876543');
CALL InsertarPasajero(3, 6, 'Lucía Fernanda', 'Ramírez Torres', '4E', 'Peruana', '1992-11-30', 'Económica', '12345678', 'PE123456');
CALL InsertarPasajero(3, 6, 'Santiago Andrés', 'Ortiz Núñez', '5F', 'Chileno', '1985-07-20', 'Primera', '45678901', 'CL456789');
CALL InsertarPasajero(4, 4, 'Carlos Eduardo', 'Mendoza Villalobos', '6A', 'Ecuatoriano', '1993-02-18', 'Ejecutiva', '81234567', 'EC812345');
CALL InsertarPasajero(4, 4, 'María Fernanda', 'Soto Gutiérrez', '7B', 'Uruguaya', '1998-09-12', 'Económica', '92765432', 'UY927654');
CALL InsertarPasajero(4, 4, 'Diego Alejandro', 'Navarro Cruz', '8C', 'Venezolano', '1990-06-08', 'Primera', '63456789', 'VE634567');
CALL InsertarPasajero(4, 4, 'Isabella Victoria', 'López Ramírez', '9D', 'Boliviana', '1986-10-22', 'Económica', '72345698', 'BO723456');
CALL InsertarPasajero(5, 5, 'Andrea Paola', 'Martínez Soto', '10E', 'Mexicana', '1992-03-14', 'Económica', '85476321', 'MX854763');
CALL InsertarPasajero(5, 5, 'Gabriel Arturo', 'Ramírez Torres', '11F', 'Argentino', '1980-12-05', 'Primera', '71234567', 'AR712345');
CALL InsertarPasajero(5, 5, 'Valeria Carolina', 'Gómez Vidal', '12A', 'Colombiana', '1995-01-25', 'Ejecutiva', '63456789', 'CO634567');
CALL InsertarPasajero(5, 5, 'Matías Nicolás', 'Fernández Rojas', '13B', 'Peruano', '1989-07-11', 'Económica', '78901234', 'PE789012');
CALL InsertarPasajero(5, 5, 'Lucía Fernanda', 'Hernández López', '14C', 'Uruguaya', '1990-08-30', 'Primera', '56789012', 'UY567890');
CALL InsertarPasajero(5, 5, 'Diego Alejandro', 'Pérez Gómez', '15D', 'Venezolano', '1987-05-18', 'Económica', '67890123', 'VE678901');
CALL InsertarPasajero(6, 3, 'Sofía Emilia', 'García Méndez', '16A', 'Argentina', '1990-02-10', 'Primera', '45876321', 'AR458763');
CALL InsertarPasajero(6, 3, 'Tomás Ignacio', 'Ruiz Delgado', '17B', 'Chileno', '1993-09-21', 'Ejecutiva', '56723489', 'CL567234');
CALL InsertarPasajero(6, 3, 'Camila Isabel', 'Vargas Peña', '18C', 'Peruana', '1985-04-12', 'Económica', '78213467', 'PE782134');
CALL InsertarPasajero(6, 3, 'Javier Esteban', 'Fernández Rojas', '19D', 'Colombiano', '1988-11-30', 'Primera', '34678512', 'CO346785');
CALL InsertarPasajero(6, 3, 'Isabella María', 'López Ramírez', '20E', 'Boliviana', '1991-03-08', 'Ejecutiva', '91245678', 'BO912456');
CALL InsertarPasajero(6, 3, 'Lucas Gabriel', 'Torres González', '21F', 'Mexicano', '1983-06-15', 'Económica', '54367189', 'MX543671');
CALL InsertarPasajero(6, 3, 'Valentina Andrea', 'Rojas Campos', '22A', 'Uruguaya', '1987-12-25', 'Primera', '67382145', 'UY673821');
CALL InsertarPasajero(6, 3, 'Mateo Alejandro', 'Díaz Pérez', '23B', 'Venezolano', '1994-01-17', 'Ejecutiva', '89756123', 'VE897561');
CALL InsertarPasajero(6, 3, 'Antonella Carolina', 'Martínez López', '24C', 'Ecuatoriana', '1990-08-05', 'Primera', '78213467', 'EC782134');
CALL InsertarPasajero(6, 3, 'Emiliano David', 'Hernández Cruz', '25D', 'Paraguayo', '1989-07-22', 'Económica', '61234589', 'PY612345');
CALL InsertarPasajero(7, 10, 'Valeria Sofía', 'Martínez López', '16A', 'Argentina', '1992-06-15', 'Primera', '12345678', 'AR123456');
CALL InsertarPasajero(7, 10, 'Gabriel Esteban', 'Sánchez Rodríguez', '17B', 'Chileno', '1986-04-10', 'Económica', '23456789', 'CL234567');
CALL InsertarPasajero(7, 10, 'Mariana Paula', 'González Vargas', '18C', 'Peruana', '1989-02-20', 'Ejecutiva', '34567890', 'PE345678');
CALL InsertarPasajero(7, 10, 'Javier Esteban', 'Fernández Rojas', '19D', 'Colombiano', '1985-11-12', 'Primera', '45678901', 'CO456789');
CALL InsertarPasajero(7, 10, 'Juliana Andrea', 'López Ramírez', '20E', 'Boliviana', '1993-07-25', 'Económica', '56789012', 'BO567890');
CALL InsertarPasajero(7, 10, 'Tomás Gabriel', 'Torres González', '21F', 'Mexicano', '1990-09-30', 'Ejecutiva', '67890123', 'MX678901');
CALL InsertarPasajero(7, 10, 'Lucía Fernanda', 'Rojas Campos', '22A', 'Uruguaya', '1988-10-10', 'Primera', '78901234', 'UY789012');
CALL InsertarPasajero(7, 10, 'Lucas Gabriel', 'Díaz Pérez', '23B', 'Venezolano', '1987-05-18', 'Económica', '89012345', 'VE890123');
CALL InsertarPasajero(7, 10, 'Camila Isabel', 'Martínez López', '24C', 'Ecuatoriana', '1995-01-10', 'Primera', '90123456', 'EC901234');
CALL InsertarPasajero(7, 10, 'Emiliano Javier', 'Hernández Cruz', '25D', 'Paraguayo', '1987-03-15', 'Económica', '12345098', 'PY123450');
CALL InsertarPasajero(7, 10, 'Diego Sebastián', 'Pérez Gómez', '26E', 'Venezolano', '1991-08-21', 'Ejecutiva', '23456700', 'VE234567');
CALL InsertarPasajero(7, 10, 'Ana Carolina', 'Mendoza Soto', '27F', 'Chilena', '1992-12-30', 'Económica', '34567890', 'CL345678');
CALL InsertarPasajero(9, 4, 'Fernando Uriel', 'Quispe Quispe', '1A', 'Boliviano','1990-01-01', 'Económica', '12345678', 'AB123456');
CALL InsertarPasajero(9, 4, 'Fran Uriel', 'Quispe Mamani', '1A', 'Boliviano','1990-01-01', 'Económica', '12345678', 'AB123456');
 select * from pasajero;
DELIMITER $

CREATE PROCEDURE InsertarDetalleReserva(IN p_reserva_id INT)
BEGIN
    DECLARE v_monto_pago DECIMAL(8,2);
    DECLARE v_igv DECIMAL(8,2);
    DECLARE v_monto_total DECIMAL(8,2);
    DECLARE v_cantidad_asientos INT;
    SELECT monto_pago, igv 
    INTO v_monto_pago, v_igv
    FROM pago
    WHERE reserva_id = p_reserva_id
    LIMIT 1;
    SET v_monto_total = v_monto_pago + v_igv;
    SELECT numero_pasajeros
    INTO v_cantidad_asientos
    FROM reserva
    WHERE reserva_id = p_reserva_id;
    INSERT INTO detalle_reserva (reserva_id, monto_total, cantidad_asientos)
    VALUES (p_reserva_id, v_monto_total, v_cantidad_asientos)
    ON DUPLICATE KEY UPDATE monto_total = v_monto_total, cantidad_asientos = v_cantidad_asientos;
END $
DELIMITER ;
CALL InsertarDetalleReserva(1);
CALL InsertarDetalleReserva(2);
CALL InsertarDetalleReserva(3);
CALL InsertarDetalleReserva(4);
CALL InsertarDetalleReserva(5);
CALL InsertarDetalleReserva(6);
CALL InsertarDetalleReserva(7);
CALL InsertarDetalleReserva(9);
 
select*from detalle_reserva ;
select * from pago;


SELECT 
    r.reserva_id, 
    psg.apellido_pasajero, 
    psg.nombres_pasajero, 
    pg.fecha_pago, 
    pg.metodo_pago, 
    pg.monto_pago, 
    pg.igv, 
    dr.monto_total
FROM 
    pago pg
JOIN 
    reserva r ON r.reserva_id = pg.reserva_id
JOIN 
    pasajero psg ON psg.reserva_id = r.reserva_id
JOIN 
    detalle_reserva dr ON dr.reserva_id = r.reserva_id;


UPDATE pago SET fecha_pago = ?, metodo_pago = ?, monto_pago = ?, igv = ? 
WHERE reserva_id = ? AND reserva_id IN (SELECT reserva_id FROM pasajero 
WHERE nombres_pasajero LIKE ? OR apellido_pasajero LIKE ?);





















