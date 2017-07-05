package Modelo;

/**
 * Created by rodri on 19/04/17.
 */
public class Soldado implements IDefensa, IAtaque {

    private String nombre;
    private int resistencia;
    private IAtaque comportamientoAtaque;
    private IDefensa comportamientoDefensa;
    private int id;
    private int idEjercito;
    private boolean vivo;
    private static int idStatic = 0;

    public Soldado(){}

    //Le paso por parametro la resistencia porque voy a darle dif pts dependiendo el bando.
    public Soldado(String nombre, int resistencia,int idEjercito)
    {
        this.nombre = nombre;
        this.resistencia = resistencia;
        this.comportamientoAtaque = new Punio("Piña de Boxeo");
        this.comportamientoDefensa = new ArteMarcial(" krack maga");
        this.id =  idStatic++;
        this.idEjercito = idEjercito;
        this.vivo = true;
    }

    public Soldado(String nombre,int idEjercito)
    {
        this.nombre = nombre;
        this.resistencia = 0;
        this.comportamientoAtaque = new Punio("Piña de Boxeo");
        this.comportamientoDefensa = new ArteMarcial(" krack maga");
        this.idEjercito = idEjercito;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public IAtaque getComportamientoAtaque() {
        return comportamientoAtaque;
    }

    public void setComportamientoAtaque(IAtaque comportamientoAtaque) {
        this.comportamientoAtaque = comportamientoAtaque;
    }

    public IDefensa getComportamientoDefensa() {
        return comportamientoDefensa;
    }

    public void setComportamientoDefensa(IDefensa comportamientoDefensa) {
        this.comportamientoDefensa = comportamientoDefensa;
    }

    public int getId() {
        return id;
    }

    public int getIdEjercito() {
        return idEjercito;
    }

    public void setIdEjercito(int idEjercito) {
        this.idEjercito = idEjercito;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    @Override
    public int ataque() {
        return comportamientoAtaque.ataque();
    }

    @Override
    public boolean municion() {
        return getComportamientoAtaque().municion();
    }

    @Override
    public int defensa() {
        return comportamientoDefensa.defensa();
    }

    @Override
    public void reducirDefensas(int restarResistencia) {
        comportamientoDefensa.reducirDefensas(restarResistencia);
    }

    @Override
    public int saberDefensa() {
        return comportamientoDefensa.saberDefensa();
    }
}
