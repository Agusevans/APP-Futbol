package persistencia.memoData;

import domain.Cancha;
import domain.Equipo;
import domain.Jugador;
import persistencia.EntidadPersistente;
import domain.Partido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataPartido {
    private static List<Partido> partidos = new ArrayList<>();

    static {
        int id = 1;

        Cancha cancha1 = DataCancha.getCanchas().get(0);
        int jugadoresxEquipo1 = cancha1.getCapacidad()/2;

        LocalDateTime fechap1 = LocalDateTime.of(2024,3,10,21,00);

        List<Jugador> jugadores1 = DataJugador.getJugadores().subList(0,cancha1.getCapacidad());
        Equipo equipo1 = new Equipo(jugadores1.subList(0,jugadoresxEquipo1),2);
        Equipo equipo2 = new Equipo(jugadores1.subList(jugadoresxEquipo1, cancha1.getCapacidad()),3);

        Partido partido1 = new Partido(equipo1,equipo2,cancha1,fechap1,id++);

        partidos.add(partido1);

        Cancha cancha2 = DataCancha.getCanchas().get(1);
        int jugadoresxEquipo2 = cancha2.getCapacidad()/2;

        LocalDateTime fechap2 = LocalDateTime.of(2024,3,10,21,00);

        List<Jugador> jugadores2 = DataJugador.getJugadores().subList(0,cancha2.getCapacidad());
        Equipo equipo3 = new Equipo(jugadores2.subList(0,jugadoresxEquipo2),2);
        Equipo equipo4 = new Equipo(jugadores2.subList(jugadoresxEquipo2, cancha2.getCapacidad()),3);

        Partido partido2 = new Partido(equipo3,equipo4,cancha2,fechap2,id++);

        partidos.add(partido2);
    }

    public static List<EntidadPersistente> getDataPartidos(){
        return (List<EntidadPersistente>)(List<?>) partidos;
    }

    public static List<Partido> getPartidos() {
        return partidos;
    }

}
