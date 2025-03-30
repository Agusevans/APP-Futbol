package persistencia;

import javax.persistence.*;

@MappedSuperclass
public class EntidadPersistente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}