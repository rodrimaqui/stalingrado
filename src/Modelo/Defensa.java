package Modelo;

/**
 * Created by root on 24/04/17.
 */

public abstract class Defensa  implements IDefensa {

    protected String nombre;
    protected int resistencia;

    public Defensa(){}
    public Defensa(String nombre,int resistencia)
    {
        this.nombre = nombre;
        this.resistencia = resistencia;
    }

    @Override
    public int defensa() {
        return 0;
    }


    public void reducirDefensas(int restarResistencia)
    {
        this.resistencia = restarResistencia;
    }

    public int saberDefensa()
    {
        return resistencia;
    }


}
