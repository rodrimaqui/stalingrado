package Modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rodri on 19/04/17.
 */
public class Ejercito implements Runnable {

    private CampoBatalla cBatalla;
    private String nombre;
    private ArrayList<Soldado> listaSoldados;
    private int id;
    private static Random azar = new Random();
    private static int idStatic = 1;
    private int tiempoDormido;
    private boolean ganador;

    public Ejercito() {
    }

    public Ejercito(String nombre, CampoBatalla cBatalla) {
        this.nombre = nombre;
        this.cBatalla = cBatalla;
        this.id = idStatic++;
        this.listaSoldados = new ArrayList<Soldado>();
        this.ganador = false;
    }

    public CampoBatalla getcBatalla() {
        return cBatalla;
    }

    public void setcBatalla(CampoBatalla cBatalla) {
        this.cBatalla = cBatalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Soldado> getListaSoldados() {
        return listaSoldados;
    }

    public void setListaSoldados(ArrayList<Soldado> listaSoldados) {
        this.listaSoldados = listaSoldados;
    }

    public void agregarSoldado(Soldado oSoldado) {
        listaSoldados.add(oSoldado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public int saberQuienEstaVivo() {
        int aux = 0;
        for (int i = 0; i < listaSoldados.size(); i++) {
            if (listaSoldados.get(i).isVivo()) {
                aux = i;
            }
        }
        return aux;
    }

    //Doy un Soldado ya sea  para que pueda luchar.
    public Soldado darUnSoldado() {
        int aux;
        Soldado soldadoAux;
        //Si hay un soldado solo vivo, le paso el que está
        if (saberCuantosHayVivos() == 1) {
            //debería retornar el soldado vivo
            aux = saberQuienEstaVivo();
        } else {
            do {
                int auxx = listaSoldados.size() - 1;
                aux = azar.nextInt(auxx);
                soldadoAux = listaSoldados.get(aux);

            } while (aux < 0 || !soldadoAux.isVivo());
        }
        return listaSoldados.get(aux);
    }

    public int saberCuantosHayVivos() {
        int aux = 0;
        for (Soldado one : listaSoldados) {
            if (one.isVivo()) {
                aux++;
            }
        }
        return aux;
    }

    //Tiro un random para dormir un bando y que no entre siempre uno.
    public int tiempoDormidoEjercito() {
        do {
            tiempoDormido = azar.nextInt(1000);
        } while (tiempoDormido == 0);
        return tiempoDormido;
    }

    public void eliminarSoldadoDelList(Soldado muerto) {

        muerto.setVivo(false);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ejercito Ejercito = (Ejercito) o;

        if (cBatalla != null ? !cBatalla.equals(Ejercito.cBatalla) : Ejercito.cBatalla != null) return false;
        if (nombre != null ? !nombre.equals(Ejercito.nombre) : Ejercito.nombre != null) return false;
        return listaSoldados != null ? listaSoldados.equals(Ejercito.listaSoldados) : Ejercito.listaSoldados == null;
    }

    @Override
    public int hashCode() {
        int result = cBatalla != null ? cBatalla.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (listaSoldados != null ? listaSoldados.hashCode() : 0);
        return result;
    }

    @Override
    public void run() {

        while (!cBatalla.isGanador()) {
            try {
                Thread.sleep(tiempoDormidoEjercito());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cBatalla.enfrentamiento(this.id);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
