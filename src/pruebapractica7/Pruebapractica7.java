package Pruebapractica7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JosÃ© Luis
 * @version 6.1
 */
public class Pruebapractica7 {
    public static final long serialVersionUID = 1L;
    static BufferedReader dato = new BufferedReader(new InputStreamReader(System.in));
    private static File f=new File("clientes.dat");
    private static List<ClientePruebapractica7> personas=new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion=1;
        do{
            if(opcion <1 || opcion >6)
                System.err.println("Introduzca Ãºnicamente un valor entre 1 y 5");
            try{
                System.out.println("MenÃº principal");
                System.out.println("1 - AÃ±adir cliente");
                System.out.println("2 - Listar clientes");
                System.out.println("3 - Buscar cliente");
                System.out.println("4 - Borrar cliente");
                System.out.println("5 - Borrar fichero de clientes completamente");
                System.out.println("6 - Salir de la aplicaciÃ³n");
                System.out.println("Introduzca la opciÃ³n deseada (1-6)");
                opcion=Integer.parseInt(dato.readLine());
                switch(opcion){
                    case 1:
                        introducirDatos();
                        break;
                    case 2:
                        verDatos();
                        break;
                    case 3:
                        buscarDato();
                        break;
                    case 4:
                        eliminarUno();
                        break;
                    case 5:
                        eliminarTodos();
                        break;
                    case 6:
                        System.out.println("GRACIAS POR UTILIZAR LA APLICACIÃ“N");
                        break;
                }
            }catch (NumberFormatException nfe){
                System.err.println("SÃ³lo son vÃ¡lidos valores enteros entre 1 y 6");
            }catch(IOException ioe){
                System.err.println("Error de entrada de datos: "+ioe.getMessage());
            }
        }while(opcion !=6);
    }
    
    /****************
     * Introducimos los datos del cliente comprobando:
     * NIF      ->  Que tenga un valor entre 999999 y 99999999 y que termine en una letra
     * TelÃ©fono ->  Que sean valores numÃ©ricos entre 600000000 y 700000000 o entre 900000000 y 1000000000
     * Deuda    ->  Que sea un valor numÃ©rico
     */
    private static void introducirDatos() {
        String ni = null;
        long tel=0L;
        float de=0F;
        boolean noFloat,noNIF,noTEL;
        abrir();
        try{
            do{
                System.out.println("Introduzca el NIF del cliente: ");
                ni=dato.readLine();
                noNIF=compruebaNIF(ni);
                if(!noNIF)
                    System.err.println("NIF ERRÃ“NEO");
            }while(!noNIF);
            System.out.println("Introduzca el nombre del cliente: ");
            String nom = dato.readLine();
            do{
                noTEL=false;
                try{
                    System.out.println("Introduzca el telÃ©fono del cliente ");
                    tel=Long.parseLong(dato.readLine());
                    noTEL=compruebaTel(tel);
                    if(!noTEL)
                        System.err.println("NÂº DE TELÃ‰FONO ERRÃ“NEO");
                }catch(IOException | NumberFormatException e){
                    System.err.println("Introduzca nÃºmeros vÃ¡lidos");
                }
            }while(!noTEL);
            System.out.println("Introduzca la direcciÃ³n del cliente: ");
            String dir=dato.readLine();
            do{
                try{
                    noFloat=false;
                    System.out.println("Introduzca la deuda del cliente: ");
                    de=Float.parseFloat(dato.readLine());
                }catch(IOException | NumberFormatException e){
                    System.err.println("Introduzca nÃºmeros vÃ¡lidos");
                    noFloat=true;
                }
            }while(noFloat);
            if(personas.isEmpty()){
                personas=new ArrayList<ClientePruebapractica7>();
            }
            personas.add(new ClientePruebapractica7(ni,nom,tel,dir,de));
            escribirArchivo();
            System.out.println("Cliente "+personas.size()+" aÃ±adido");
        }catch (IOException | NumberFormatException ex){
            System.err.println(ex.getMessage());
        }
    }

    /*****************
     * FunciÃ³n que nos permite visualizar todos los datos almacenados
     * Primero comprobamos que el fichero exista.
     * Si existe, lo abrimos y comprobamos que no este vacÃ­o recorriendo todo su contenido
     */
    private static void verDatos() {
        if(!f.exists()){
            System.err.println("NO EXISTEN DATOS");
        } else{
            abrir();
            if(personas != null){
                int con=1;
                for(ClientePruebapractica7 p:personas){
                    System.out.println("Registro nÂº "+con+" - "+p.toString());
                    con++;
                }
            }else{
                System.out.println("No existen clientes dados de alta.");
            }
        }
    }

    /*****************
     * FunciÃ³n que nos permite buscar un registro concreto buscÃ¡ndolo por su NIF
     * 
     */
    private static void buscarDato(){
        try{
            String res;
            boolean repetir, encontrado;
            do{
                repetir=false;
                System.out.println("Introduzca el nif del cliente que desea buscar");
                String ni=dato.readLine();
                abrir();
                int i=0;
                encontrado=false;
                for(ClientePruebapractica7 p:personas){
                    if(p.getNif().equals(ni)){
                        encontrado=true;
                        System.out.println("Registro nÂº"+i+" - "+p.toString());
                    }
                    i++;
                }
                if(!encontrado)
                    System.err.println("REGISTRO NO ENCONTRADO");
                System.out.println("Desea buscar otro registro (S/N)");
                do{
                    res=dato.readLine().toUpperCase();
                    if(!res.equals("N")&&!res.equals("S"))
                        System.err.println("SÃ³lo 'S' para buscar otro y 'N' para salir");
                    if(res.equals("S")){
                        repetir=true;
                    }
                }while(!res.equals("S") && !res.equals("N"));
            }while(repetir);
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /****************
     * FunciÃ³n similar a la anterior pero que en lugar de limitarse a mostrarnos el resultado
     * de la bÃºsqueda, nos permite decidir si deseamos eliminar el registro encontrado
     */
    private static void eliminarUno() {
        try{
            System.out.println("Introduzca el nif del cliente que desea eliminar");
            String ni=dato.readLine();
            abrir();
            int i=0;
            boolean encontrado=false;
            for(ClientePruebapractica7 p:personas){
                if(p.getNif().equals(ni)){
                    encontrado=true;
                    System.out.println("Registro nÂº"+i+" - "+p.toString());
                    System.out.println("EstÃ¡ seguro que desea eliminarlo (S/N)");
                    String res;
                    do{
                        res=dato.readLine().toUpperCase();
                        if(!res.equals("N")&&!res.equals("S"))
                            System.err.println("SÃ³lo 'S' para borrar y 'N' para mantenerlo");
                        if(res.equals("S")){
                            personas.remove(i);
                            escribirArchivo();
                            System.out.printf("REGISTRO NÂº%d ELIMINADO\n",i);
                        }
                    }while(!res.equals("S") && !res.equals("N"));
                }
                i++;
            }
            if(!encontrado)
                System.err.println("REGISTRO NO ENCONTRADO");
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /******************
     * FunciÃ³n que nos elimina el fichero de datos del disco
     */
    private static void eliminarTodos() {
         String res;
         boolean borrado=false;
         try{
            System.out.println("EstÃ¡ seguro que desea eliminar el fichero (S/N)");
            do{
                res=dato.readLine().toUpperCase();
                if(!res.equals("N")&&!res.equals("S"))
                    System.err.println("SÃ³lo 'S' para borrar y 'N' para mantenerlo");
                if(res.equals("S")){
                    
                    borrado=f.delete();
                    if(borrado){
                        System.out.println("FICHERO DE DATOS ELIMINADO");
                        personas.clear();
                    }else{
                        System.err.println("No ha sido posible eliminar el fichero");
                    }
                }
            }while(!res.equals("S") && !res.equals("N"));
         }catch(FileNotFoundException fnf){
             System.err.println("Fichero inexistente: "+fnf.getMessage());
         }catch(Exception ex){
             ex.getMessage();
         }
    }

    /*****************
     * FunciÃ³n que nos abre el fichero de datos para cagar su contenido en el arreglo 'personas'.
     * Previamente comprueba si el fichero existe, y si es asÃ­ carga su contenido en el ArrayList
     * y cierra el fichero. Si por cualquier motivo no se puede leer del disco (estÃ¡ creado pero no
     * contiene datos) nos avisa que el fichero estÃ¡ vacÃ­o.
     */
    private static void abrir(){
        try{
            if(!f.exists()){
                crearArchivo();
            }else{
                if(f.canRead()){
                    FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    personas = (ArrayList<ClientePruebapractica7>)ois.readObject();
                    ois.close();
                    fis.close();
                }else{
                    System.err.println("FICHERO VACÃ�O");
                }
            }
        }catch(IOException | ClassNotFoundException ex){
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    /***********************
     * FunciÃ³n que nos crea el fichero de datos (vÃ¡lido para cuando se comprueba que no existe)
     */
    private static void crearArchivo(){
        try{
            f.createNewFile();
            System.out.println("Fichero creado");
        }catch(Exception ex){
            System.err.println("ERROR: "+ex.getMessage());
        }
    }
     
    /************************
     * FunciÃ³n que nos escribe el ArrayList en el fichero de disco.
     */
    private static void escribirArchivo(){
        try{
            if(!f.exists()) f=new File("clientes.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personas);
            oos.close();
            fos.close();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    /******************
     * FunciÃ³n que comprueba que el nif es correcto (nÂº entre 6 y 8 dÃ­gitos 
     * finalizados en una letra vÃ¡lida)
     * @param ni
     * @return boolean
     */
    private static boolean compruebaNIF(String ni) {
        boolean miNIF=false;
        int lon=ni.length();
        String letraValida="TRWAGMYFPDXBNJZSQVHLCKET";
        try{
            char letra=Character.toUpperCase(ni.charAt(lon-1));
            int numero=Integer.parseInt(ni.substring(0, lon-1));
            if((numero>999999 && numero<99999999) && (letra>=65 && letra<=90)){
                int numLetra=(int)numero%23;
                if(letraValida.charAt(numLetra)!=letra){
                    System.err.println("Letra del NIF no vÃ¡lida");
                }else{
                    miNIF=true;
                }
            }
        }catch(Exception e){
            System.err.println("NÃºmero NO vÃ¡lido");
        }
        return miNIF;
    }

    /*******************
     * FunciÃ³n que comprueba que el telÃ©fono es vÃ¡lido comienza por 6 Ã³ por 9 y tiene 9 dÃ­gitos
     * @param tel
     * @return boolean
     */
    private static boolean compruebaTel(long tel) {
        boolean miTEL=false;
        if((tel>600000000&&tel<700000000)||(tel>900000000&&tel<1000000000)) miTEL=true;
        return miTEL;
    }


}
