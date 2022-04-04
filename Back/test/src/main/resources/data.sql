

INSERT INTO detalle_coordenadas(id_detalle_coordenadas,detalle_latitud, detalle_longitud) values (1,'detalle 1', 'sin comentarios');
INSERT INTO detalle_coordenadas(id_detalle_coordenadas,detalle_latitud, detalle_longitud) values (2,'detalle 2', 'perdido');

INSERT INTO usuarios (username,password) VALUES ('admin','$2a$10$wlGnXmAi7T/B3BU5epQUYuGI701Wlo9f4fPaGJVoF5xNIq9PojG9i');
INSERT INTO usuarios (username,password) VALUES ('operador','operador');

INSERT INTO coordenadas (id_coordenadas,latitud,longitud,id_detalle_coordenadas) VALUES (1,'-12.123','-21.321',1);
INSERT INTO coordenadas (id_coordenadas,latitud,longitud,id_detalle_coordenadas) VALUES (2,'-123.123','-321.321',2);

SELECT * FROM detalle_coordenadas;
SELECT * FROM coordenadas;
SELECT * FROM usuarios;


