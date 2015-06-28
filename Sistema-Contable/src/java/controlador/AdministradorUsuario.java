/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.ArchivoXmlUsuario;
import modelo.Usuario;
import org.jdom2.JDOMException;

/**
 *
 * @author Erick Gonález
 */
@ManagedBean
@RequestScoped
public class AdministradorUsuario {
@ManagedProperty(value = "#{users}")
private Usuario users;
    
    public AdministradorUsuario() {
    }

    public Usuario getUsers() {
        return users;
    }

    public void setUsers(Usuario users) {
        this.users = users;
    }
    public ArchivoXmlUsuario getUsesXml(){
        File archivo= new File("usuarios.xml");
        ArchivoXmlUsuario registro= null;
        if(archivo.exists()){
            try {
                registro= ArchivoXmlUsuario.abrirDocumento("usuarios.xml");
            } catch (JDOMException ex) {
                Logger.getLogger(AdministradorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AdministradorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                registro= ArchivoXmlUsuario.crearDocumento("usuarios.xml");
            } catch (IOException ex) {
                Logger.getLogger(AdministradorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
    }
    public String addUsers(ActionEvent e) throws IOException{
        ArchivoXmlUsuario registro= getUsesXml();
        registro.addUsers(users);
        return "PaginaPrincipal.xhtml";
    }
}
