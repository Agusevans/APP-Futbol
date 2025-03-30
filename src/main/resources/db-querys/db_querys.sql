USE appfutbol;

SELECT * FROM usuario;

SELECT * FROM jugador;
SELECT * FROM cancha;
SELECT * FROM partido;
SELECT * FROM equipo;

SELECT equipo_id,goles,jugador_id,nombre FROM equipo_jugador
INNER JOIN equipo
INNER JOIN jugador;


DELETE FROM cancha WHERE nombre = 'La bombonera';

/*
DROP TABLE cancha,equipo,equipo_jugador,jugador,partido,usuario;
*/