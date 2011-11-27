/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class Posts {
    RandomAccessFile f;
    static int cont=0;
    
    public Posts(String correo){
        try{
            f=new RandomAccessFile("Users\\"+correo+"posts.fbn","rw");
            
        }catch(IOException e){
            System.out.println("Errore:"+e.getMessage());
        }
    }
    
    public void agregarPost(String post){
        try {
            f.seek(f.length());
            f.writeUTF(post);
            f.writeInt(cont);
            cont++;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String getPosts(long x){   //Mandar x=0
        try {
            
            f.seek(x);
            if(x<=f.length()){
                String p=f.readUTF();
                f.readInt();
                getPosts(f.getFilePointer());
                return p;
            }
        } catch (IOException ex) {
            System.out.println("Errooor:"+ex.getMessage());
        }
        return "";
        
    }
}
