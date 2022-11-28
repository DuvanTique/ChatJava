package viewModel;

import java.io.Serializable;

/**
 *
 * @author Duvan
 */
public class PaqueteEnvio implements Serializable {
    private String nombre;
    private String mensaje;
    private String ip;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
}
