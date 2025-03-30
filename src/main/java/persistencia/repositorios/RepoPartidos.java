package persistencia.repositorios;

import domain.Cancha;
import domain.Equipo;
import domain.Jugador;
import domain.Partido;
import persistencia.daos.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepoPartidos extends Repositorio<Partido>{

    public RepoPartidos(DAO<Partido> dao) {
        super(dao);
    }

    public void load(){

        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        Jugador jugador3 = new Jugador("jugador3");
        Jugador jugador4 = new Jugador("jugador4");
        Jugador jugador5 = new Jugador("jugador5");
        Jugador jugador6 = new Jugador("jugador6");
        Jugador jugador7 = new Jugador("jugador7");
        Jugador jugador8 = new Jugador("jugador8");
        Jugador jugador9 = new Jugador("jugador9");
        Jugador jugador10 = new Jugador("jugador10");
        Jugador jugador11 = new Jugador("jugador11");
        Jugador jugador12 = new Jugador("jugador12");
        Jugador jugador13 = new Jugador("jugador13");
        Jugador jugador14 = new Jugador("jugador14");
        Jugador jugador15 = new Jugador("jugador15");
        Jugador jugador16 = new Jugador("jugador16");

        List<Jugador> jugadores1 = new ArrayList<>();
        jugadores1.add(jugador1);
        jugadores1.add(jugador2);
        jugadores1.add(jugador3);
        jugadores1.add(jugador4);
        jugadores1.add(jugador5);
        jugadores1.add(jugador6);
        List<Jugador> jugadores2 = new ArrayList<>();
        jugadores2.add(jugador7);
        jugadores2.add(jugador8);
        jugadores2.add(jugador9);
        jugadores2.add(jugador10);
        jugadores2.add(jugador11);
        jugadores2.add(jugador12);

        Equipo equipo1 = new Equipo(jugadores1,2);
        Equipo equipo2 = new Equipo(jugadores2,3);

        Cancha soccer6 = new Cancha("Soccer 6", 12);

        LocalDateTime fechap1 = LocalDateTime.of(2024,3,10,21,00);

        Partido partido1 = new Partido(equipo1,equipo2,soccer6,fechap1);

        this.dao.agregar(partido1);

        List<Jugador> jugadores3 = new ArrayList<>();
        jugadores3.add(jugador13);
        jugadores3.add(jugador14);
        jugadores3.add(jugador15);
        jugadores3.add(jugador16);
        jugadores3.add(jugador1);
        List<Jugador> jugadores4 = new ArrayList<>();
        jugadores4.add(jugador2);
        jugadores4.add(jugador3);
        jugadores4.add(jugador4);
        jugadores4.add(jugador5);
        jugadores4.add(jugador6);

        Equipo equipo3 = new Equipo(jugadores3,2);
        Equipo equipo4 = new Equipo(jugadores4,2);

        Cancha lasheras5 = new Cancha("Las Heras 5", 10);

        LocalDateTime fechap2 = LocalDateTime.of(2024,4,10,21,00);

        Partido partido2 = new Partido(equipo3,equipo4,lasheras5,fechap2);

        this.dao.agregar(partido2);

    }

}
