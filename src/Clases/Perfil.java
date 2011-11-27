/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class Perfil {
    RandomAccessFile f=null;
    String correo;
    public Perfil(String direccion){
        correo=direccion;
        try {
            f=new RandomAccessFile("Users\\"+direccion+"\\profile.fbn","rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado");
        }
        
        
    }
    public String getNombre(){
            try{
                f.seek(0);
                String nombre=f.readUTF();
                return nombre;
            }catch(IOException e){
                System.out.println("Error: "+e.getMessage());
            }
            return "";
    }
    
    public int getTel(){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            f.readLong();
            int tel=f.readInt();
            return tel;
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
            return 0;
        }
    }
    public long getFechaNacimiento() {
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            long fecha=f.readLong();
            return fecha;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return 0;
    }
    public String getGenero(){
        try{
          f.seek(0);
          f.readUTF();
          char c=f.readChar();
          if(c=='M'){
              return "Masculino";
          }else if(c=='F'){
              return "Femenino";
          }
        }catch(IOException e){
            e.getMessage();
        }
        return "";
    }
    
    public String getEmail(){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            f.readLong();
            f.readInt();
            f.readLong();
            String e=f.readUTF();
            return e;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    

    public void cambiarTelefono(int c){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            f.readLong();
            f.writeInt(c);
        }catch(IOException e){
            e.getMessage();
        }
    }
    
    public void cambiarFecha(Date fecha){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            long fe=f.readLong();
            if(fe!=fecha.getTime()){
               fe=fecha.getTime();
               f.seek(f.getFilePointer()-8);
               f.writeLong(fe);
            }
            
            
        }catch(IOException e){
            System.out.println( e.getMessage());
           
        }
    }
    public void modificarPerfil(int t,Date fe){
        cambiarTelefono(t);
        cambiarFecha(fe);
    }
    
    public int getDia(){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            Date x=new Date(f.readLong());
            Calendar y=Calendar.getInstance();
            y.setTime(x);
            return y.get(Calendar.DAY_OF_MONTH);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int getMes(){
         try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            Date x=new Date(f.readLong());
            Calendar y=Calendar.getInstance();
            y.setTime(x);
            return y.get(Calendar.MONTH);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public int getAnio(){
        try{
            f.seek(0);
            f.readUTF();
            f.readChar();
            Date x=new Date(f.readLong());
            Calendar y=Calendar.getInstance();
            y.setTime(x);
            return y.get(Calendar.YEAR);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
