package domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PartidoTest {

    Partido partido1;

    @BeforeEach
    public void init(){

        Cancha soccer = new Cancha("Soccer", 12);

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

        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();

        equipo1.agregarJugadores(jugador1, jugador2, jugador3, jugador4, jugador5, jugador6);
        equipo2.agregarJugadores(jugador7, jugador8, jugador9, jugador10, jugador11, jugador12);

        equipo1.setGoles(2);
        equipo2.setGoles(3);

        LocalDateTime fechap1 = LocalDateTime.of(2024,3,10,21,00);

        partido1 = new Partido(equipo1, equipo2, soccer, fechap1);

    }

    @Test
    void mostrarPartido(){

        partido1.mostrarPartido();

    }


}
