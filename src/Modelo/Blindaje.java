package Modelo;

/**
 * Created by rodri on 19/04/17.
 */
public class Blindaje extends Defensa{


    public Blindaje(){super();}
    public Blindaje(String nombre)
    {
        super(nombre,25);
    }

    @Override
    public int defensa() {
        return resistencia;
    }


}
