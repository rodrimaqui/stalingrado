package Modelo;

import java.util.Random;

/**
 * Created by root on 23/04/17.
 */
public class Punio extends Armamento{

    Random azar = new Random();

    public Punio(){super();}



    public Punio(String nombre)
    {
        super(nombre,5);

        setMuniciones(generarMunicion());
    }

    private int generarMunicion()
    {
        int aux;

        //Le pongo que el soldado puede tirar 500 piñas antes de que se canse
        do {
            aux = azar.nextInt(500);
        }while(aux == 0);

        return aux;
    }

    @Override
    public int ataque() {
        reducirMunicion();
        return poder;
    }

    @Override
    public boolean municion() {
        if(municiones > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }


}
