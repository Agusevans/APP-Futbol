package persistencia.memoData;

import domain.Jugador;
import persistencia.EntidadPersistente;
import java.util.ArrayList;
import java.util.List;

public class DataJugador {

    private static List<Jugador> jugadores = new ArrayList<>();

    static {
        int id = 1;

        Jugador jugador1 = new Jugador("jugador1",id++);
        Jugador jugador2 = new Jugador("jugador2",id++);
        Jugador jugador3 = new Jugador("jugador3",id++);
        Jugador jugador4 = new Jugador("jugador4",id++);
        Jugador jugador5 = new Jugador("jugador5",id++);
        Jugador jugador6 = new Jugador("jugador6",id++);
        Jugador jugador7 = new Jugador("jugador7",id++);
        Jugador jugador8 = new Jugador("jugador8",id++);
        Jugador jugador9 = new Jugador("jugador9",id++);
        Jugador jugador10 = new Jugador("jugador10",id++);
        Jugador jugador11 = new Jugador("jugador11",id++);
        Jugador jugador12 = new Jugador("jugador12",id++);
        Jugador jugador13 = new Jugador("jugador13",id++);
        Jugador jugador14 = new Jugador("jugador14",id++);
        Jugador jugador15 = new Jugador("jugador15",id++);
        Jugador jugador16 = new Jugador("jugador16",id++);

        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);
        jugadores.add(jugador4);
        jugadores.add(jugador5);
        jugadores.add(jugador6);
        jugadores.add(jugador7);
        jugadores.add(jugador8);
        jugadores.add(jugador9);
        jugadores.add(jugador10);
        jugadores.add(jugador11);
        jugadores.add(jugador12);
        jugadores.add(jugador13);
        jugadores.add(jugador14);
        jugadores.add(jugador15);
        jugadores.add(jugador16);
    }

    public static List<EntidadPersistente> getDataJugadores(){
        return (List<EntidadPersistente>)(List<?>) jugadores;
    }

    public static List<Jugador> getJugadores(){
        return jugadores;
    }

}
