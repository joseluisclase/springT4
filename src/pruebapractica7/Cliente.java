/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapractica7;

import java.io.Serializable;

/**
 *
 * @author alumno
 */
public class Cliente implements Serializable{
    private String DNI,Nombre2, Telefono2, Direccion2;
    private double Deuda2;
    
    public Cliente(){
    };
    
    public Cliente(String DNI, String Nombre2, String Telefono2, String Direccion2, double Deuda2){
    this.DNI=DNI;
    this.Nombre2=Nombre2;
    this.Telefono2=Telefono2;
    this.Direccion2=Direccion2;
    this.Deuda2=Deuda2;
    }

    /**
     * @return the DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * @return the Nombre2
     */
    public String getNombre2() {
        return Nombre2;
    }

    /**
     * @param Nombre2 the Nombre2 to set
     */
    public void setNombre2(String Nombre2) {
        this.Nombre2 = Nombre2;
    }

    /**
     * @return the Telefono2
     */
    public String getTelefono2() {
        return Telefono2;
    }

    /**
     * @param Telefono2 the Telefono2 to set
     */
    public void setTelefono2(String Telefono2) {
        this.Telefono2 = Telefono2;
    }

    /**
     * @return the Direccion2
     */
    public String getDireccion2() {
        return Direccion2;
    }

    /**
     * @param Direccion2 the Direccion2 to set
     */
    public void setDireccion2(String Direccion2) {
        this.Direccion2 = Direccion2;
    }

    /**
     * @return the Deuda2
     */
    public double getDeuda2() {
        return Deuda2;
    }

    /**
     * @param Deuda2 the Deuda2 to set
     */
    public void setDeuda2(double Deuda2) {
        this.Deuda2 = Deuda2;
    }

    @Override
    public String toString() {
        return "Cliente{" + "DNI=" + DNI + ", Nombre2=" + Nombre2 + ", Telefono2=" + Telefono2 + ", Direccion2=" + Direccion2 + ", Deuda2=" + Deuda2 + '}';
    }
    
}
