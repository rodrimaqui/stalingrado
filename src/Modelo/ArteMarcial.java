package Modelo;

/**
 * Created by root on 23/04/17.
 */
public class ArteMarcial extends Defensa{


    public ArteMarcial(){super();}
    public ArteMarcial(String nombre)
    {
        super(nombre,5);
    }
    @Override
    public int defensa() {
        return resistencia;
    }
}
