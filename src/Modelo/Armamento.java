package Modelo;

/**
 * Created by rodri on 19/04/17.
 */
public abstract class Armamento implements IAtaque {


    protected String nombre;
    protected int poder;
    protected int municiones;

    public Armamento(){}

    public Armamento(String nombre,int poder)
    {
        this.nombre = nombre;
        this.poder = poder;
        this.municiones = 0;
    }

    protected void reducirMunicion()
    {
        if(municiones > 0)
        {
            municiones --;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getMuniciones() {
        return municiones;
    }

    public void setMuniciones(int municiones) {
        this.municiones = municiones;
    }

    @Override
    public int ataque() {
        return 0;
    }

    public abstract boolean municion();


}
