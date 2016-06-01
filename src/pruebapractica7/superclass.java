/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapractica7;

import Pruebapractica7.Prueba;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 * @version 1.0
 */
public class superclass {
    protected static long serialVersionUID = 1L;
    protected static BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
    protected static File f = new File("clientes.dat");
    protected static List<Prueba> personas = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = 1;
        do {
            if (opcion < 1 || opcion > 6) {
                System.err.println("Introduzca Ãºnicamente un valor entre 1 y 5");
            }
            try {
                System.out.println("MenÃº principal");
                System.out.println("1 - AÃ±adir cliente");
                System.out.println("2 - Listar clientes");
                System.out.println("3 - Buscar cliente");
                System.out.println("4 - Borrar cliente");
                System.out.println("5 - Borrar fichero de clientes completamente");
                System.out.println("6 - Salir de la aplicaciÃ³n");
                System.out.println("Introduzca la opciÃ³n deseada (1-6)");
                opcion = Integer.parseInt(getDato().readLine());
                switch (opcion) {
                    case 1:
                        meterDatos();
                        break;
                    case 2:
                        mirarDatos();
                        break;
                    case 3:
                        encontrarDatos();
                        break;
                    case 4:
                        borrarUno();
                        break;
                    case 5:
                        borrarTodos();
                        break;
                    case 6:
                        System.out.println("GRACIAS POR UTILIZAR LA APLICACIÃ“N");
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("SÃ³lo son vÃ¡lidos valores enteros entre 1 y 6");
            } catch (IOException ioe) {
                System.err.println("Error de entrada de datos: " + ioe.getMessage());
            }
        } while (opcion != 6);
    }

    /****************
     * Introducimos los datos del cliente comprobando:
     * NIF      ->  Que tenga un valor entre 999999 y 99999999 y que termine en una letra
     * TelÃ©fono ->  Que sean valores numÃ©ricos entre 600000000 y 700000000 o entre 900000000 y 1000000000
     * Deuda    ->  Que sea un valor numÃ©rico
     */
    protected static void meterDatos() {
        String ni = null;
        long tel = 0L;
        float de = 0F;
        boolean noFloat;
        boolean noNIF;
        boolean noTEL;
        abrirFichero();
        try {
            do {
                System.out.println("Introduzca el NIF del cliente: ");
                ni = getDato().readLine();
                noNIF = compruebaNIF2(ni);
                if (!noNIF) {
                    System.err.println("NIF ERRÃ“NEO");
                }
            } while (!noNIF);
            System.out.println("Introduzca el nombre del cliente: ");
            String nom = getDato().readLine();
            do {
                noTEL = false;
                try {
                    System.out.println("Introduzca el telÃ©fono del cliente ");
                    tel = Long.parseLong(getDato().readLine());
                    noTEL = compruebaTel2(tel);
                    if (!noTEL) {
                        System.err.println("NÂº DE TELÃ‰FONO ERRÃ“NEO");
                    }
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Introduzca nÃºmeros vÃ¡lidos");
                }
            } while (!noTEL);
            System.out.println("Introduzca la direcciÃ³n del cliente: ");
            String dir = getDato().readLine();
            do {
                try {
                    noFloat = false;
                    System.out.println("Introduzca la deuda del cliente: ");
                    de = Float.parseFloat(getDato().readLine());
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Introduzca nÃºmeros vÃ¡lidos");
                    noFloat = true;
                }
            } while (noFloat);
            if (getPersonas().isEmpty()) {
                setPersonas(new ArrayList<Prueba>());
            }
            getPersonas().add(new Prueba(ni, nom, tel, dir, de));
            escribirFichero();
            System.out.println("Cliente " + getPersonas().size() + " aÃ±adido");
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /*****************
     * FunciÃ³n que nos permite visualizar todos los datos almacenados
     * Primero comprobamos que el fichero exista.
     * Si existe, lo abrimos y comprobamos que no este vacÃ­o recorriendo todo su contenido
     */
    protected static void mirarDatos() {
        if (!f.exists()) {
            System.err.println("NO EXISTEN DATOS");
        } else {
            abrirFichero();
            if (getPersonas() != null) {
                int con = 1;
                for (Prueba p : getPersonas()) {
                    System.out.println("Registro nÂº " + con + " - " + p.toString());
                    con++;
                }
            } else {
                System.out.println("No existen clientes dados de alta.");
            }
        }
    }

    /*****************
     * FunciÃ³n que nos permite buscar un registro concreto buscÃ¡ndolo por su NIF
     *
     */
    protected static void encontrarDatos() {
        try {
            String res;
            boolean repetir;
            boolean encontrado;
            do {
                repetir = false;
                System.out.println("Introduzca el nif del cliente que desea buscar");
                String ni = getDato().readLine();
                abrirFichero();
                int i = 0;
                encontrado = false;
                for (Prueba p : getPersonas()) {
                    if (p.getNif().equals(ni)) {
                        encontrado = true;
                        System.out.println("Registro nÂº" + i + " - " + p.toString());
                    }
                    i++;
                }
                if (!encontrado) {
                    System.err.println("REGISTRO NO ENCONTRADO");
                }
                System.out.println("Desea buscar otro registro (S/N)");
                do {
                    res = getDato().readLine().toUpperCase();
                    if (!res.equals("N") && !res.equals("S")) {
                        System.err.println("SÃ³lo 'S' para buscar otro y 'N' para salir");
                    }
                    if (res.equals("S")) {
                        repetir = true;
                    }
                } while (!res.equals("S") && !res.equals("N"));
            } while (repetir);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /****************
     * FunciÃ³n similar a la anterior pero que en lugar de limitarse a mostrarnos el resultado
     * de la bÃºsqueda, nos permite decidir si deseamos eliminar el registro encontrado
     */
    protected static void borrarUno() {
        try {
            System.out.println("Introduzca el nif del cliente que desea eliminar");
            String ni = getDato().readLine();
            abrirFichero();
            int i = 0;
            boolean encontrado = false;
            for (Prueba p : getPersonas()) {
                if (p.getNif().equals(ni)) {
                    encontrado = true;
                    System.out.println("Registro nÂº" + i + " - " + p.toString());
                    System.out.println("EstÃ¡ seguro que desea eliminarlo (S/N)");
                    String res;
                    do {
                        res = getDato().readLine().toUpperCase();
                        if (!res.equals("N") && !res.equals("S")) {
                            System.err.println("SÃ³lo 'S' para borrar y 'N' para mantenerlo");
                        }
                        if (res.equals("S")) {
                            getPersonas().remove(i);
                            escribirFichero();
                            System.out.printf("REGISTRO NÂº%d ELIMINADO\n", i);
                        }
                    } while (!res.equals("S") && !res.equals("N"));
                }
                i++;
            }
            if (!encontrado) {
                System.err.println("REGISTRO NO ENCONTRADO");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /******************
     * FunciÃ³n que nos elimina el fichero de datos del disco
     */
    protected static void borrarTodos() {
        String res;
        boolean borrado = false;
        try {
            System.out.println("EstÃ¡ seguro que desea eliminar el fichero (S/N)");
            do {
                res = getDato().readLine().toUpperCase();
                if (!res.equals("N") && !res.equals("S")) {
                    System.err.println("SÃ³lo 'S' para borrar y 'N' para mantenerlo");
                }
                if (res.equals("S")) {
                    borrado = getF().delete();
                    if (borrado) {
                        System.out.println("FICHERO DE DATOS ELIMINADO");
                        getPersonas().clear();
                    } else {
                        System.err.println("No ha sido posible eliminar el fichero");
                    }
                }
            } while (!res.equals("S") && !res.equals("N"));
        } catch (FileNotFoundException fnf) {
            System.err.println("Fichero inexistente: " + fnf.getMessage());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    /*****************
     * FunciÃ³n que nos abre el fichero de datos para cagar su contenido en el arreglo 'personas'.
     * Previamente comprueba si el fichero existe, y si es asÃ­ carga su contenido en el ArrayList
     * y cierra el fichero. Si por cualquier motivo no se puede leer del disco (estÃ¡ creado pero no
     * contiene datos) nos avisa que el fichero estÃ¡ vacÃ­o.
     */
    protected static void abrirFichero() {
        try {
            if (!f.exists()) {
                crearFichero();
            } else {
                if (getF().canRead()) {
                    FileInputStream fis = new FileInputStream(getF());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    setPersonas((ArrayList<Prueba>) ois.readObject());
                    ois.close();
                    fis.close();
                } else {
                    System.err.println("FICHERO VACÃ�O");
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /***********************
     * FunciÃ³n que nos crea el fichero de datos (vÃ¡lido para cuando se comprueba que no existe)
     */
    protected static void crearFichero() {
        try {
            getF().createNewFile();
            System.out.println("Fichero creado");
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
    }

    /************************
     * FunciÃ³n que nos escribe el ArrayList en el fichero de disco.
     */
    protected static void escribirFichero() {
        try {
            if (!f.exists()) {
                setF(new File("clientes.dat"));
            }
            FileOutputStream fos = new FileOutputStream(getF());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getPersonas());
            oos.close();
            fos.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /******************
     * FunciÃ³n que comprueba que el nif es correcto (nÂº entre 6 y 8 dÃ­gitos
     * finalizados en una letra vÃ¡lida)
     * @param ni
     * @return boolean
     */
    protected static boolean compruebaNIF2(String ni) {
        boolean miDNI = false;
        int lon = ni.length();
        String letraValida = "TRWAGMYFPDXBNJZSQVHLCKET";
        try {
            char letra = Character.toUpperCase(ni.charAt(lon - 1));
            int numero = Integer.parseInt(ni.substring(0, lon - 1));
            if ((numero > 999999 && numero < 99999999) && (letra >= 65 && letra <= 90)) {
                int numLetra = (int) numero % 23;
                if (letraValida.charAt(numLetra) != letra) {
                    System.err.println("Letra del NIF no vÃ¡lida");
                } else {
                    miDNI = true;
                }
            }
        } catch (Exception e) {
            System.err.println("NÃºmero NO vÃ¡lido");
        }
        return miDNI;
    }

    /*******************
     * FunciÃ³n que comprueba que el telÃ©fono es vÃ¡lido comienza por 6 Ã³ por 9 y tiene 9 dÃ­gitos
     * @param tel
     * @return boolean
     */
    protected static boolean compruebaTel2(long tel) {
        boolean miCEL = false;
        if ((tel > 600000000 && tel < 700000000) || (tel > 900000000 && tel < 1000000000)) {
            miCEL = true;
        }
        return miCEL;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the dato
     */
    public static BufferedReader getDato() {
        return dato;
    }

    /**
     * @param aDato the dato to set
     */
    public static void setDato(BufferedReader aDato) {
        dato = aDato;
    }

    /**
     * @return the f
     */
    public static File getF() {
        return f;
    }

    /**
     * @param aF the f to set
     */
    public static void setF(File aF) {
        f = aF;
    }

    /**
     * @return the personas
     */
    public static List<Prueba> getPersonas() {
        return personas;
    }

    /**
     * @param aPersonas the personas to set
     */
    public static void setPersonas(List<Prueba> aPersonas) {
        personas = aPersonas;
    }
    
}
