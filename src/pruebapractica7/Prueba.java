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
public class Prueba implements Serializable{
    private String DNI,Nombre2, Telefono2, Direccion2;
    private double Deuda2;
    
    public Prueba(){
    };
    
    public Prueba(String DNI, String Nombre2, String Telefono2, String Direccion2, double Deuda2){
    this.DNI=DNI;
    this.Nombre2=Nombre2;
    this.Telefono2=Telefono2;
    this.Direccion2=Direccion2;
    this.Deuda2=Deuda2;
    }

    public String getNIF() {
        return DNI;
    }

    public String getNombre() {
        return Nombre2;
    }

    public String getTelefono() {
        return Telefono2;
    }

    public String getDireccion() {
        return Direccion2;
    }

    public void setNIF(String NIF) {
        this.DNI = NIF;
    }

    public void setNombre(String Nombre) {
        this.Nombre2 = Nombre;
    }

    public void setTelefono(String Telefono) {
        this.Telefono2 = Telefono;
    }

    public void setDireccion(String Direccion) {
        this.Direccion2 = Direccion;
    }

    public double getDeuda() {
        return Deuda2;
    }

    public void setDeuda(double Deuda) {
        this.Deuda2 = Deuda;
    }
    
    @Override
    public String toString(){
    String cadena;
    cadena="Nif: "+DNI+" Nombre: "+Nombre2+" Telefono: "+Telefono2+" Direccion: "+Direccion2+" Deuda= "+Deuda2;
    return cadena;
    }
}
