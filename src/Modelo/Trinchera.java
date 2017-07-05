package Modelo;

/**
 * Created by rodri on 19/04/17.
 */
public class Trinchera extends Defensa {

    public Trinchera(){super();}
    public Trinchera(String nombre)
    {
        super(nombre,5);
    }
    @Override
    public int defensa() {
        return resistencia;
    }


}
