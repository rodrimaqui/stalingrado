package Modelo;

/**
 * Created by root on 06/05/17.
 */
public class Partida {

    private String ejercito1;
    private String ejercito2;
    private String ganador;

    public Partida(){}

    public Partida(String ejercito1,String ejercito2, String ganador){
        this.ejercito1 = ejercito1;
        this.ejercito2 = ejercito2;
        this.ganador = ganador;

    }

    public String getEjercito1() {
        return ejercito1;
    }

    public void setEjercito1(String ejercito1) {
        this.ejercito1 = ejercito1;
    }

    public String getEjercito2() {
        return ejercito2;
    }

    public void setEjercito2(String ejercito2) {
        this.ejercito2 = ejercito2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
}
