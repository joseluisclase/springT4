/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapractica7;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author VictorMan1
 */
public class PrincipalSuperclass {

    public static void main(String[] args) {
        ArrayList<Cliente> ac = new ArrayList<Cliente>();
        Cliente cl = new Cliente();
        File f = new File("clientes.dat");
        Menu(f, cl, ac);
    }

    public static void Menu(File f, Cliente cl, ArrayList ac) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir = false;
        System.out.println("Bienvenido a aplicacion clientes.");
        do {
            try {
                System.out.println("Elige una opcion de la lista.");
                System.out.println("\t 1. Añadir un cliente.");
                System.out.println("\t 2. Listar clientes.");
                System.out.println("\t 3. Buscar clientes.");
                System.out.println("\t 4. Borrar un cliente.");
                System.out.println("\t 5. Borrar fichero de clientes.");
                System.out.println("\t 6. Salir.");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Ha escogido introducir nuevo cliente");
                        IntroducirDatos(f, cl, ac);
                        break;
                    case 2:
                        System.out.println("Ha escogido listar los datos actuales");
                        VerDatosFichero(f);
                        break;
                    case 3:
                        System.out.println("Ha escogido buscar un cliente");
                        buscarDatoEnArray(f, ac);
                        break;
                    case 4:
                        System.out.println("Ha escogido borrar un cliente");
                        borrarUnocliente(f, ac);
                        break;
                    case 5:
                        System.out.println("Ha escogido borrar todos los clientes");
                        borrarTodosClientes(f);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Introduzca un valor entre 0 y 6");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Sólo son válidos valores enteros entre 0 y 6");
            } catch (IllegalArgumentException iae) {
                System.err.println(iae);
            }
        } while (!salir);
    }

    public static void IntroducirDatos(File f, Cliente cl, ArrayList ac) {
        String nif;
        String nom;
        String tel;
        String dir;
        double deb = 0;
        int nclientes;
        boolean nifv;
        boolean telv;
        boolean debv;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca los datos requeridos a continuacion");
        System.out.println("Introduzca numero de clientes a añadir");
        nclientes = sc.nextInt();
        for (int i = 0; i < nclientes; i++) {
            sc.nextLine();
            System.out.println("Introduzca nombre del cliente");
            nom = sc.nextLine();
            //setNombre(nom);
            do {
                System.out.println("Introduzca Nif del cliente");
                nif = sc.nextLine();
                nifv = ComprobarNifValido(nif);
                if (nifv) {
                } else {
                    System.err.println("Ha introducido un NIF incorrecto, intentelo de nuevo");
                }
            } while (!nifv);
            do {
                System.out.println("Introduzca telefono del cliente");
                tel = sc.nextLine();
                telv = ComprobarTelValido(tel);
                if (telv) {
                } else {
                    System.err.println("Ha introducido un telefono incorrecto, intentelo de nuevo");
                }
            } while (!telv);
            System.out.println("Introduzca direccion del cliente");
            dir = sc.nextLine();
            //setDireccion(dir);
            do {
                try {
                    debv = true;
                    System.out.println("Introduzca deuda del cliente");
                    deb = sc.nextDouble();
                } catch (NumberFormatException e) {
                    System.err.println("Introduzca números válidos");
                    debv = false;
                }
            } while (!debv);
            cl = new Cliente(nif, nom, tel, dir, deb);
            GuardarCliente(ac, cl);
        }
        escribirArchivoEnFichero(f, ac, nclientes);
    }

    protected static void VerDatosFichero(File f) {
        try {
            // Se crea un ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            // Se lee el primer objeto
            Object aux = ois.readObject();
            // Mientras haya objetos
            while (aux != null) {
                if (aux instanceof Cliente) {
                    System.out.println(aux);
                }
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
            System.out.println("Fin de fichero");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected static void abrirfichero(File f, ArrayList ac) {
        try {
            // Se crea un ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            // Se lee el primer objeto
            Object aux = ois.readObject();
            // Mientras haya objetos
            while (aux != null) {
                if (aux instanceof Cliente) {
                    ac.add(aux);
                }
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected static boolean ComprobarNifValido(String nif) {
        int nnif;
        char letra;
        letra = nif.charAt(8);
        nnif = Integer.parseInt(nif.substring(0, 7));
        if (nnif > 999999 && nnif < 100000000) {
            if (Character.isLetter(letra)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean ComprobarTelValido(String tel) {
        int telef;
        telef = Integer.parseInt(tel);
        if ((telef > 600000000 && telef < 700000000) || (telef > 900000000 && telef < 1000000000)) {
            return true;
        }
        return false;
    }

    protected static void GuardarCliente(ArrayList ac, Cliente cl) {
        ac.add(new Cliente(cl.getDNI(), cl.getNombre2(), cl.getTelefono2(), cl.getDireccion2(), cl.getDeuda2()));
        System.out.println("Cliente guardado con exito");
    }

    protected static void escribirArchivoEnFichero(File f, ArrayList ac, int i) {
        try {
            if (!f.exists()) {
                f = new File("clientes.dat");
            }
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int e = 0; e < i; e++) {
                oos.writeObject(ac.get(e));
            }
            oos.close();
            fos.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected static void buscarDatoEnArray(File f, ArrayList ac) {
        Scanner sc = new Scanner(System.in);
        try {
            String res;
            boolean repetir;
            boolean encontrado;
            do {
                repetir = false;
                System.out.println("Introduzca el nif del cliente que desea buscar");
                String ni = sc.nextLine();
                abrirfichero(f, ac);
                encontrado = false;
                for (int i = 0; i < ac.size(); i++) {
                    Cliente clt = (Cliente) ac.get(i);
                    if (clt.getDNI().equals(ni)) {
                        encontrado = true;
                        System.out.println("Registro nº" + i + " - " + clt.toString());
                    }
                }
                if (!encontrado) {
                    System.err.println("REGISTRO NO ENCONTRADO");
                }
                System.out.println("Desea buscar otro registro (S/N)");
                do {
                    res = sc.nextLine().toUpperCase();
                    if (!res.equals("N") && !res.equals("S")) {
                        System.err.println("Sólo 'S' para buscar otro y 'N' para salir");
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

    protected static void borrarUnocliente(File f, ArrayList ac) {
        Scanner sc = new Scanner(System.in);
        try {
            String res;
            boolean repetir;
            boolean encontrado;
            do {
                repetir = false;
                System.out.println("Introduzca el nif del cliente que desea borrar");
                String ni = sc.nextLine();
                abrirfichero(f, ac);
                encontrado = false;
                for (int i = 0; i < ac.size(); i++) {
                    Cliente clt = (Cliente) ac.get(i);
                    if (clt.getDNI().equals(ni)) {
                        encontrado = true;
                        System.out.println("Registro nº" + i + " - " + clt.toString());
                        ac.remove(i);
                        escribirArchivoEnFichero(f, ac, ac.size());
                        System.out.println("Registro borrado con exito.");
                    }
                }
                if (!encontrado) {
                    System.err.println("REGISTRO NO ENCONTRADO");
                }
                System.out.println("Desea borrar otro registro (S/N)");
                do {
                    res = sc.nextLine().toUpperCase();
                    if (!res.equals("N") && !res.equals("S")) {
                        System.err.println("Sólo 'S' para buscar otro y 'N' para salir");
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

    protected static void borrarTodosClientes(File f) {
        f.delete();
    }
    
}
