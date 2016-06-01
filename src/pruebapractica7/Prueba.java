package Pruebapractica7;

import java.io.Serializable;

/**
 *
 * @author Jose Luis
 * @version 1.0
 */
public class Prueba implements Serializable{
    public static final long serialUID = 1L;
    private String dni;
    private String nombre2;
    private long telefono2;
    private String direccion2;
    private float deuda2;
    
    public Prueba(String nif, String nombre, long telefono, String direccion, float deuda){
        this.dni=nif;
        this.nombre2=nombre;
        this.telefono2=telefono;
        this.direccion2=direccion;
        this.deuda2=deuda;
    }

    public float getDeuda() {
        return deuda2;
    }

    public void setDeuda(float deuda) {
        this.deuda2 = deuda;
    }

    public String getDireccion() {
        return direccion2;
    }

    public void setDireccion(String direccion) {
        this.direccion2 = direccion;
    }

    public String getNif() {
        return dni;
    }

    public void setNif(String nif) {
        this.dni = nif;
    }

    public String getNombre() {
        return nombre2;
    }

    public void setNombre(String nombre) {
        this.nombre2 = nombre;
    }

    public long getTelefono() {
        return telefono2;
    }

    public void setTelefono(long telefono) {
        this.telefono2 = telefono;
    }
    @Override
    /**
     * MÃ©todo sobreescrito para obtener todos los datos del cliente por pantalla
     */
    public String toString(){
        return "Nombre: "+getNombre()+", NIF: "+getNif()+", DirecciÃ³n: "+getDireccion()+", TelÃ©fono: "+getTelefono()+", Deuda: "+getDeuda();
    }
    
    
   

}
