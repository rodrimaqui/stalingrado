package Modelo;

import java.util.Random;

/**
 * Created by rodri on 19/04/17.
 */
public class Tanque extends Armamento {

    Random azar = new Random();

    public Tanque(){super();}

    public Tanque(String nombre)
    {
        super(nombre,15);
        setMuniciones(generarMunicion());
    }

    private int generarMunicion()
    {
        int aux;
        do {
            //Le pongo que el Tanque puede llevar hasta 8 munisiones
            aux = azar.nextInt(8);
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
            int municionExtra = azar.nextInt(4);
            if(municionExtra == 2)
            {
                municiones = generarMunicion();
            }
            return false;
        }
    }
}
