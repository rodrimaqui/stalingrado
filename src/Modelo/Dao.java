package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by rodri on 19/04/17.
 */
public class Dao {

    private Connection cn;

    private static Dao instance;

    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        return instance;
    }

    public Dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stalingrado", "root", "rodri123456");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void agregarPartida(CampoBatalla cb) {
        try {
            PreparedStatement st = cn.prepareStatement("INSERT INTO partidas(ejercito1_id,ejercito2_id,ganador) VALUES(?,?,?)");
            st.setInt(1, cb.getNazi().getId());
            st.setInt(2, cb.getUrss().getId());
            if (cb.getNazi().isGanador()) {
                st.setInt(3, cb.getNazi().getId());
            } else {
                st.setInt(3, cb.getUrss().getId());
            }
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void agregarSoldados(CampoBatalla cB) {
        try {
            Statement st1 = cn.createStatement();
            ResultSet rs = st1.executeQuery("SELECT buscarUltimoId()");

            int i = 0;

            while(rs.next())
            {
                i = rs.getInt("buscarUltimoId()");
            }

            PreparedStatement st = cn.prepareStatement("INSERT INTO soldados(nombre,ejercito_id,partida_id) VALUES(?,?,?)");

            for (Soldado one : cB.getNazi().getListaSoldados()) {
                if (!one.isVivo()) {
                    st.setString(1, one.getNombre());
                    st.setInt(2, one.getIdEjercito());
                    st.setInt(3,i);
                    st.execute();
                }
            }
            for (Soldado two : cB.getUrss().getListaSoldados()) {
                if (!two.isVivo()) {
                    st.setString(1, two.getNombre());
                    st.setInt(2, two.getIdEjercito());
                    st.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Soldado> devolverSoldados() {

        List<Soldado> listSoldier = new ArrayList<Soldado>();
        try {
            Statement st = cn.createStatement();

            ResultSet rs1 = st.executeQuery("SELECT buscarUltimoId()");

            int ultimoId = 0;

            while(rs1.next())
            {
                ultimoId = rs1.getInt("buscarUltimoId()");
            }

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM soldados WHERE partida_id = ?");
            ps.setInt(1,ultimoId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Soldado aux = new Soldado(rs.getString("nombre"), rs.getInt("ejercito_id"));
                listSoldier.add(aux);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSoldier;
    }

    public List<Partida>  devolverPartidasAnteriores()
    {
        List<Partida> lista = new ArrayList<Partida>();
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT e1.nombre AS e1, e2.nombre AS e2, g.nombre AS ganador " +
                    "FROM partidas AS p " +
                    "INNER JOIN ejercitos AS e1 ON p.ejercito1_id = e1.id " +
                    "INNER JOIN ejercitos AS e2 ON p.ejercito2_id = e2.id " +
                    "INNER JOIN ejercitos AS g  ON p.ganador = g.id " +
                    "ORDER BY p.id DESC " +
                    "LIMIT 0,5;");

            while(rs.next())
            {
                Partida ps = new Partida(rs.getString("e1"),rs.getString("e2"),rs.getString("ganador"));
                lista.add(ps);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lista;
    }
}
