package Controladora;

import Modelo.*;
import Vista.Zeus;

/**
 * Created by rodri on 19/04/17.
 */
public class Main {

    public static void main(String args[])
    {
        CampoBatalla cB = new CampoBatalla();

        Zeus zs = new Zeus();

        cB.addObserver(zs);

        Dao dao = Dao.getInstance();

        Ejercito nazi = new Ejercito("Nazis",cB);
        Ejercito urss = new Ejercito("Urss",cB);

        cB.setNazi(nazi);
        cB.setUrss(urss);

        //Soldados Alemanes
            Soldado s1 = new Soldado("HuasoAleman",25,nazi.getId());
            Soldado s3 = new Soldado("PilotoAleman",25,nazi.getId());
            Soldado s5 = new Soldado("PomoAleman",25,nazi.getId());

                //Defensas Alemanas
                s1.setComportamientoDefensa(new Blindaje("Acero Tanque"));
                s3.setComportamientoDefensa(new Trinchera("Casco"));
                s5.setComportamientoDefensa(new Trinchera("Trinchera de 2mts"));
                //Armamento Aleman
                s1.setComportamientoAtaque(new Tanque("Tigre"));
                s3.setComportamientoAtaque(new Avion("Mirage"));
                s5.setComportamientoAtaque(new Cañon("50mm"));

            nazi.agregarSoldado(s1);
            nazi.agregarSoldado(s3);
            nazi.agregarSoldado(s5);

        //Soldados Sovieticos
            Soldado s2 = new Soldado("PomoRuso",30,urss.getId());
            Soldado s4 = new Soldado("InfanteRuso",30,urss.getId());
            Soldado s6 = new Soldado("ZapadorRuso",30,urss.getId());

                //Ataque Sovietico
                s2.setComportamientoAtaque(new Cañon("50mm"));
                s4.setComportamientoAtaque(new Fusil("MP-40"));
                s6.setComportamientoAtaque(new Fusil("Mauser"));
                //Defensas Sovieticas
                s2.setComportamientoDefensa(new Trinchera("Trinchera de 1 metro"));
                s4.setComportamientoDefensa(new Trinchera("Trinchera power"));
                s6.setComportamientoDefensa(new Casco("Casco pedorro"));

            urss.agregarSoldado(s6);
            urss.agregarSoldado(s4);
            urss.agregarSoldado(s2);

        System.out.println("Las 5 últimas partidas terminaron así: ");
        for(Partida p : dao.devolverPartidasAnteriores())
        {
            System.out.println("Ejercito "+p.getEjercito1()+" vs "+p.getEjercito2()+" ganaron los "+p.getGanador());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(nazi).start();
        new Thread(urss).start();

    }
}
