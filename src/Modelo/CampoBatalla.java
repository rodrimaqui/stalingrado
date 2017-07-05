package Modelo;

import java.util.Observable;
import java.util.Random;

/**
 * Created by rodri on 19/04/17.
 */
public class CampoBatalla extends Observable {

    private Ejercito nazi;
    private Ejercito urss;
    Soldado soldadoDefensor;
    Soldado soldadoAtacante;
    private boolean estado;
    private boolean ganador;
    private int reducirVida;
    private int reducirDefensa;
    private Random azar;

    public CampoBatalla() {
        this.estado = true;
        this.ganador = false;
        this.azar = new Random();
    }

    public synchronized void bloquearCampoBatalla() {
        while (!isEstado()) {
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        estado = false;
    }

    public synchronized void enfrentamiento(int atacante) {

        if (nazi.saberCuantosHayVivos() >= 1 && urss.saberCuantosHayVivos() >= 1) {
            if (atacante == nazi.getId()) {
                soldadoDefensor = urss.darUnSoldado();
                soldadoAtacante = nazi.darUnSoldado();
            } else {
                soldadoDefensor = nazi.darUnSoldado();
                soldadoAtacante = urss.darUnSoldado();
            }
            bloquearCampoBatalla();

            setChanged();
            notifyObservers("Atacan los " + soldadoAtacante.getNombre());

            if (congelamiento(soldadoAtacante)) {
                setChanged();
                notifyObservers("EL soldado " + soldadoAtacante.getNombre()+" murió CONGELADO");
                nazi.eliminarSoldadoDelList(soldadoAtacante);
                if(nazi.saberCuantosHayVivos() == 0)
                {
                    this.ganador = true;
                    urss.setGanador(true);
                    setChanged();
                    notifyObservers("Los sovieticos ganaron, el frio intenso destruyó a tu enemigo");

                }
            } else {

                //Pregunto si hay municiones para poder atacar.
                if (soldadoAtacante.municion()) {

                    //Obtengo los puntos que voy a sacar al soldado defensor.
                    int resistenciaNueva = sacarDefensa(soldadoAtacante, soldadoDefensor);

                    //Si la defensa es mayor a los puntos a sacar, sólamente le saco esos puntos a esa defensa.
                    if (soldadoDefensor.saberDefensa() > resistenciaNueva) {
                        int aux = soldadoDefensor.saberDefensa() - resistenciaNueva;

                        soldadoDefensor.reducirDefensas(aux);
                    }
                    //De lo contrario le saco los puntos a la defensa y a la vida del soldado.
                    else {
                        //Acá consulto si la defensa es mayor o igual a 0, puesto a que si es mayor,le resto a la defensa y vida.
                        if (soldadoDefensor.saberDefensa() > 0) {
                            reducirDefensa = soldadoDefensor.saberDefensa() - resistenciaNueva;
                            int aux = resistenciaNueva - soldadoDefensor.saberDefensa();
                            reducirVida = soldadoDefensor.getResistencia() - aux;

                            if(reducirDefensa < 0)
                            {
                                reducirDefensa = 0;
                            }

                            soldadoDefensor.reducirDefensas(reducirDefensa);
                        }
                        //De lo contrario se la resto sólo a la vida.
                        else {
                            reducirVida = soldadoDefensor.getResistencia() - resistenciaNueva;
                        }

                        if(reducirVida < 0)
                        {
                            reducirVida = 0;
                        }

                        soldadoDefensor.setResistencia(reducirVida);
                    }

                    setChanged();
                    notifyObservers("El " + soldadoAtacante.getNombre() +
                            " Atacó y sacó " + resistenciaNueva + " puntos a los " +
                            soldadoDefensor.getNombre() + " le queda " +
                            soldadoDefensor.saberDefensa() + " de defensa y " + soldadoDefensor.getResistencia() + " de vida");


                    //Si está muerto, lo elimino de la lista de soldados
                    if (soldadoDefensor.getResistencia() <= 0) {
                        //Pregunto quien es el que debe morir para eliminarlo de la lista
                        if (atacante == nazi.getId()) {
                            urss.eliminarSoldadoDelList(soldadoDefensor);

                            setChanged();
                            notifyObservers("Un soldado " + urss.getNombre() + " ah muerto");

                            if (urss.saberCuantosHayVivos() == 0) {

                                ganador = true;
                                nazi.setGanador(true);
                                setChanged();
                                notifyObservers("Los Nazis ganaron");
                            }
                        } else {
                            nazi.eliminarSoldadoDelList(soldadoDefensor);

                            setChanged();
                            notifyObservers("Un soldado " + nazi.getNombre() + " ah muerto");

                            if (nazi.saberCuantosHayVivos() == 0) {
                                ganador = true;
                                urss.setGanador(true);
                                setChanged();
                                notifyObservers("Los sovieticos ganaron");
                            }
                        }
                    }
                }
                //Si el soldado no tiene municiones, aviso por pantalla que no hay municiones.
                else {
                    setChanged();
                    notifyObservers(soldadoAtacante.getNombre() + " no tiene municiones");
                }
            }
            liberarCampoDeBatalla();
        }
    }

    public synchronized void liberarCampoDeBatalla() {
        this.estado = true;
        notifyAll();
    }

    //Metodo donde desarrollo toda la lógica para sacar vidas de soldados.
    private synchronized int sacarDefensa(Soldado soldadoAtacante, Soldado soldadoDefensor) {

        int diferencia = azar.nextInt(10);
        //Pregunto si el armamento del Soldado atacante es mayor a la defensa del Soldado defensor
        if (soldadoAtacante.ataque() > soldadoDefensor.defensa()) {

            //Si el ataque es mayor a la defensa, saco la diferencia que hay y se la resto a la resistencia del Soldado
            diferencia = soldadoAtacante.ataque() - soldadoDefensor.defensa();

        }

        return diferencia;
    }

    // Método que hago para que el frio de la Urss mate soldados Nazis.
    private synchronized boolean congelamiento(Soldado soldadoAtacante) {
        if (soldadoAtacante.getIdEjercito() == nazi.getId()) {
            int hielo = azar.nextInt(5);
            if (hielo == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public Ejercito getNazi() {
        return nazi;
    }

    public void setNazi(Ejercito nazi) {
        this.nazi = nazi;
    }

    public Ejercito getUrss() {
        return urss;
    }

    public void setUrss(Ejercito urss) {
        this.urss = urss;
    }
}
