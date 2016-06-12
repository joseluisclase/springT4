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
    private String NIF,Nombre, Telefono, Direccion;
    private double Deuda;
    
    public Prueba(){
    };
    
    public Prueba(String NIF, String Nombre, String Telefono, String Direccion, double Deuda){
    this.NIF=NIF;
    this.Nombre=Nombre;
    this.Telefono=Telefono;
    this.Direccion=Direccion;
    this.Deuda=Deuda;
    }

    public String getNIF() {
        return NIF;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public double getDeuda() {
        return Deuda;
    }

    public void setDeuda(double Deuda) {
        this.Deuda = Deuda;
    }
    
    @Override
    public String toString(){
    String cadena;
    cadena="Nif: "+NIF+" Nombre: "+Nombre+" Telefono: "+Telefono+" Direccion: "+Direccion+" Deuda= "+Deuda;
    return cadena;
    }
}
