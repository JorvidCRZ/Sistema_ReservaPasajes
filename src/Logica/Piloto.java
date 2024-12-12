package Logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Piloto extends Conexion {
    private int pilotoId;
    private String nombresPilot;
    private String apellidosPilot;
    private String fecNaciPiloto;
    private String nacionalidadPilot;
    private String numeroLicenciaPilot;
    private String tipoLicenciaPilot;
    private String fecContratacionPilot;
    private char estadoPilot;
    private String dniPilot; 

    public String getDniPilot() {
        return dniPilot;
    }

    public void setDniPilot(String dniPilot) {
        this.dniPilot = dniPilot;
    }
  
    public int getPilotoId() {
        return pilotoId;
    }

    public void setPilotoId(int pilotoId) {
        this.pilotoId = pilotoId;
    }

    public String getNombresPilot() {
        return nombresPilot;
    }

    public void setNombresPilot(String nombresPilot) {
        this.nombresPilot = nombresPilot;
    }

    public String getApellidosPilot() {
        return apellidosPilot;
    }

    public void setApellidosPilot(String apellidosPilot) {
        this.apellidosPilot = apellidosPilot;
    }

    public String getFecNaciPiloto() {
        return fecNaciPiloto;
    }

    public void setFecNaciPiloto(String fecNaciPiloto) {
        this.fecNaciPiloto = fecNaciPiloto;
    }

    public String getNacionalidadPilot() {
        return nacionalidadPilot;
    }

    public void setNacionalidadPilot(String nacionalidadPilot) {
        this.nacionalidadPilot = nacionalidadPilot;
    }

    public String getNumeroLicenciaPilot() {
        return numeroLicenciaPilot;
    }

    public void setNumeroLicenciaPilot(String numeroLicenciaPilot) {
        this.numeroLicenciaPilot = numeroLicenciaPilot;
    }

    public String getTipoLicenciaPilot() {
        return tipoLicenciaPilot;
    }

    public void setTipoLicenciaPilot(String tipoLicenciaPilot) {
        this.tipoLicenciaPilot = tipoLicenciaPilot;
    }

    public String getFecContratacionPilot() {
        return fecContratacionPilot;
    }

    public void setFecContratacionPilot(String fecContratacionPilot) {
        this.fecContratacionPilot = fecContratacionPilot;
    }

    public char getEstadoPilot() {
        return estadoPilot;
    }

    public void setEstadoPilot(char estadoPilot) {
        this.estadoPilot = estadoPilot;
    }

    public static List<Piloto> mostrarPilotos() {
        List<Piloto> pilotos = new ArrayList<>();

        try (Connection con = Conexion.Conectar()) {
            String sql = "SELECT * FROM piloto";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Piloto piloto = new Piloto();
                        piloto.setPilotoId(rs.getInt("piloto_id"));
                        piloto.setNombresPilot(rs.getString("nombres_pilot"));
                        piloto.setApellidosPilot(rs.getString("apellidos_pilot"));
                        piloto.setFecNaciPiloto(rs.getString("fech_nac_pilot"));
                        piloto.setNacionalidadPilot(rs.getString("nacionalidad_pilot"));
                        piloto.setNumeroLicenciaPilot(rs.getString("nro_licencia_pilot"));
                        piloto.setTipoLicenciaPilot(rs.getString("tipo_licencia_pilot"));
                        piloto.setFecContratacionPilot(rs.getString("fecha_contratacion_pilot"));
                        piloto.setEstadoPilot(rs.getString("estado_pilot").charAt(0));
                        piloto.setDniPilot(rs.getString("dni")); 
                        pilotos.add(piloto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pilotos;
    }

    public static Piloto getPilotoById(int pilotoId) {
        Piloto piloto = null; 
        try (Connection con = Conexion.Conectar()) {
            String sql = "SELECT * FROM piloto WHERE piloto_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, pilotoId); 
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        piloto = new Piloto();
                        piloto.setPilotoId(rs.getInt("piloto_id"));
                        piloto.setNombresPilot(rs.getString("nombres_pilot"));
                        piloto.setApellidosPilot(rs.getString("apellidos_pilot"));
                        piloto.setFecNaciPiloto(rs.getString("fech_nac_pilot"));
                        piloto.setNacionalidadPilot(rs.getString("nacionalidad_pilot"));
                        piloto.setNumeroLicenciaPilot(rs.getString("nro_licencia_pilot"));
                        piloto.setTipoLicenciaPilot(rs.getString("tipo_licencia_pilot"));
                        piloto.setFecContratacionPilot(rs.getString("fecha_contratacion_pilot"));
                        piloto.setEstadoPilot(rs.getString("estado_pilot").charAt(0));
                        piloto.setDniPilot(rs.getString("dni")); 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piloto; 
    }
}
