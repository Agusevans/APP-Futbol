package persistencia.memoData;

import domain.Cancha;
import domain.Equipo;
import domain.Jugador;
import domain.Partido;
import persistencia.EntidadPersistente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataCancha {
    private static List<Cancha> canchas = new ArrayList<>();

    static {
        int id = 1;

        Cancha soccer6 = new Cancha("Soccer 6", 12,id++);
        Cancha lasheras5 = new Cancha("Las Heras 5", 10,id++);
        Cancha lasheras7 = new Cancha("Las Heras 7", 14,id++);

        canchas.add(soccer6);
        canchas.add(lasheras5);
        canchas.add(lasheras7);
    }

    public static List<EntidadPersistente> getDataCanchas(){
        return (List<EntidadPersistente>)(List<?>) canchas;
    }

    public static List<Cancha> getCanchas(){
        return canchas;
    }

}
