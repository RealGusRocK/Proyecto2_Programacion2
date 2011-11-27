/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class RegistrarUsuario {
    /**
     *  Formato de Creacion:
     * String nombre
     * Genero char
     * Date Fecha como Long
     * Telefono Int
     * Fecha Date, Long
     * Email String
     * Contra String
     * Boolean Activo
     */
    public RandomAccessFile gerencia=null;
    
    public RegistrarUsuario(){
        try{
            gerencia=new RandomAccessFile("Gerencia\\gerencia.fbn","rw");
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    /**
     * Verificar si el Correo Ingresado A la hora de crear una nueva Cuenta No existe.
     * @param correo Es el correo a guardar.
     * @return true si esta disponible.
     */
    public boolean verificarDispCorreo(String correo) throws IOException{
        gerencia.seek(0);
        while(gerencia.getFilePointer()< gerencia.length()){
            if(correo.equals(gerencia.readUTF())){
                return false;
            }
            gerencia.readUTF();
            gerencia.readBoolean();    
        }
        return true;
    }
    private void agregarReg(String email,String contra)throws IOException{
        gerencia.seek(gerencia.length());
        gerencia.writeUTF(email);
        gerencia.writeUTF(contra);
        gerencia.writeBoolean(true);
    }
    public boolean agregarUsuario(String nom, char genero,long fechaNac,int tel,long fechaEntrada,String email,String contra){
        try{
            crearCarpetaUser(email);
            String direc="Users\\"+email+"\\profile.fbn";
            
            RandomAccessFile perfil=new RandomAccessFile(direc,"rw");
            if(verificarDispCorreo(email)){
                perfil.seek(perfil.length());
                perfil.writeUTF(nom);
                perfil.writeChar(genero);
                perfil.writeLong(fechaNac);
                perfil.writeInt(tel);
                perfil.writeLong(fechaEntrada);
                perfil.writeUTF(email);
                perfil.writeUTF(contra);
                agregarReg(email,contra);
                return true;
            }
        }catch (FileNotFoundException e){
            System.out.println("Archivo No encontrado.");
        
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }       
        return false;
    }
    
    private void crearCarpetaUser(String correo) throws IOException{
        File f=new File("Users\\"+correo);
        f.mkdir();
        f=new File("Users\\"+correo+"\\manageFriends.fbn");
        f.createNewFile();
        f=new File("Users\\"+correo+"\\posts.fbn");
        f.createNewFile();
    }
    
    public boolean IniciarSesion(String correo , String contra){
        try{
            gerencia.seek(0);
            while(gerencia.getFilePointer()<gerencia.length()){
                if(gerencia.readUTF().equals(correo)){
                    if(gerencia.readUTF().equals(contra)){
                        if(gerencia.readBoolean()){
                            return true;
                        }
                    }else{
                        gerencia.readBoolean();
                    }
                        
                }else{
                    gerencia.readUTF();
                    gerencia.readBoolean();
                }
            }
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }
    
}
