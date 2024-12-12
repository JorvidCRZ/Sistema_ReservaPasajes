package Logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vuelo extends Conexion {

    private int id;
    private int avionId;
    private int pilotoId;
    private String origen;
    private String destino;
    private Date fecSalida;
    private Date fecLlegada;
    private Time duracion;
    private double precio;

    private String nomPiloto;
    private String apePiloto;

    public int getIdVuelo() {
        return id;
    }

    public void setIdVuelo(int id) {
        this.id = id;
    }

    public int getAvionId() {
        return avionId;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public int getPilotoId() {
        return pilotoId;
    }

    public void setPilotoId(int pilotoId) {
        this.pilotoId = pilotoId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecSalida() {
        return fecSalida;
    }

    public void setFecSalida(Date fecSalida) {
        this.fecSalida = fecSalida;
    }

    public Date getFecLlegada() {
        return fecLlegada;
    }

    public void setFecLlegada(Date fecLlegada) {
        this.fecLlegada = fecLlegada;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNomPiloto() {
        return nomPiloto;
    }

    public void setNomPiloto(String nomPiloto) {
        this.nomPiloto = nomPiloto;
    }

    public String getApePiloto() {
        return apePiloto;
    }

    public void setApePiloto(String apePiloto) {
        this.apePiloto = apePiloto;
    }

    public Vuelo getVueloById(int vueloId) throws Exception {
        Vuelo vuelo = null;
        String query = "SELECT vuelo.*, piloto.nombres_pilot, piloto.apellidos_pilot FROM vuelo " +
                       "JOIN piloto ON vuelo.piloto_id = piloto.piloto_id " +
                       "WHERE vuelo.vuelo_id = ? LIMIT 1;";

        try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, vueloId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    vuelo = new Vuelo();
                    vuelo.setIdVuelo(rs.getInt("vuelo_id"));
                    vuelo.setAvionId(rs.getInt("avion_id"));
                    vuelo.setPilotoId(rs.getInt("piloto_id"));
                    vuelo.setOrigen(rs.getString("origen"));
                    vuelo.setDestino(rs.getString("destino"));
                    vuelo.setFecSalida(rs.getDate("fecha_salida"));
                    vuelo.setFecLlegada(rs.getDate("fecha_llegada"));
                    vuelo.setDuracion(rs.getTime("duracion"));
                    vuelo.setPrecio(rs.getDouble("precio"));
                    vuelo.setNomPiloto(rs.getString("nombres_pilot"));
                    vuelo.setApePiloto(rs.getString("apellidos_pilot"));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el vuelo por ID: " + e.getMessage(), e);
        }
        return vuelo;
    }

    public List<Vuelo> mostrarVuelos() throws Exception {
        List<Vuelo> lista = new ArrayList<>();
        String query = "SELECT vuelo.*, piloto.nombres_pilot, piloto.apellidos_pilot FROM vuelo " +
                       "JOIN piloto ON vuelo.piloto_id = piloto.piloto_id;";

        try (Connection conn = Conexion.Conectar();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(rs.getInt("vuelo_id"));
                vuelo.setAvionId(rs.getInt("avion_id"));
                vuelo.setPilotoId(rs.getInt("piloto_id"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                vuelo.setFecSalida(rs.getDate("fecha_salida"));
                vuelo.setFecLlegada(rs.getDate("fecha_llegada"));
                vuelo.setDuracion(rs.getTime("duracion"));
                vuelo.setPrecio(rs.getDouble("precio"));
                vuelo.setNomPiloto(rs.getString("nombres_pilot"));
                vuelo.setApePiloto(rs.getString("apellidos_pilot"));
                lista.add(vuelo);
            }
        } catch (Exception e) {
            throw new Exception("Error al mostrar los vuelos: " + e.getMessage(), e);
        }
        return lista;
    }
}
