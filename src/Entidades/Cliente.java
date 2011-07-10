package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    
    @Column(length=30,nullable=false)
    private String nombre;
    
    @Column(length=30,nullable=false)
    private String apellido;
    
    @Column(length=30,nullable=false)
    private long limiteCredito;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(long limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    
    
}
