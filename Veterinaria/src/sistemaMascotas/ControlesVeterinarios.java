package sistemaMascotas;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlesVeterinarios {
    private Date fecha;
    private TipoControl tipoControl;
    private String observaciones;

    public ControlesVeterinarios(Date fecha, TipoControl tipoControl, String observaciones) {
        this.fecha = fecha;
        this.tipoControl = tipoControl;
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoControl getTipoControl() {
        return tipoControl;
    }

    public void setTipoControl(TipoControl tipoControl) {
        this.tipoControl = tipoControl;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String consultarHistorial() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Fecha: " + sdf.format(fecha) + " | Tipo: " + tipoControl + " | Observaciones: " + observaciones;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Control [Fecha: " + sdf.format(fecha) + ", Tipo: " + tipoControl + ", Obs: " + observaciones + "]";
    }
}

