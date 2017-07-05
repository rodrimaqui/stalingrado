package Modelo;

/**
 * Created by rodri on 19/04/17.
 */
public class Casco extends Defensa{

    public Casco(){super();}
    public Casco(String nombre)
    {
        super(nombre,15);
    }
    @Override
    public int defensa() {
        return resistencia;
    }
}
