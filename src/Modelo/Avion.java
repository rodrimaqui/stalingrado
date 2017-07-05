package Modelo;

import java.util.Random;

/**
 * Created by rodri on 19/04/17.
 */
public class Avion extends Armamento {

    Random azar = new Random();

    public Avion(){super();}

    public Avion(String nombre)
    {
        super(nombre,50);
       setMuniciones(generarMunicion());
    }

    private int generarMunicion()
    {
        int aux;
        do {
            //Le pongo que el avión puede llevar hasta 5 bombas
             aux = azar.nextInt(5);
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
            //Hago un random para que el arma sea recargada de vez en cuando.
            int municionExtra = azar.nextInt(4);
            if(municionExtra == 2)
            {
                municiones = generarMunicion();
            }
            return false;
        }
    }
}
