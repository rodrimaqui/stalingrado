package Vista;

import Modelo.CampoBatalla;
import Modelo.Dao;
import Modelo.Soldado;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by rodri on 19/04/17.
 */

public class Zeus implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("|============================|");
        System.out.println(arg);
        System.out.println("|============================|");

        CampoBatalla cp = (CampoBatalla)o;

        if(cp.isGanador())
        {
            Dao d = Dao.getInstance();

            d.agregarPartida(cp);
            d.agregarSoldados(cp);

            System.out.println("\nLos Muertos en batalla fueron: ");
            System.out.println("\nSoldados     Ejercito");
            System.out.println("|============================|");
            for(Soldado s : d.devolverSoldados()) {

                if(s.getIdEjercito() == 1)
                {
                    System.out.println(s.getNombre() +" Nazi");
                }
                else {
                    System.out.println(s.getNombre() + " Sovietico");
                }
            }
            System.out.println("|============================|");
        }
    }
}
