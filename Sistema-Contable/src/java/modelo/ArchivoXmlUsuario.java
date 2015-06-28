/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Erick Gonález
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class ArchivoXmlUsuario {
    Element raiz;
    Document documento;
    String ruta;

    private ArchivoXmlUsuario(String ruta, String nombreRaiz) throws IOException {
        this.raiz = new Element(nombreRaiz);
        this.documento = new Document(raiz);
        this.ruta = ruta;
        guardar();
    }

    private ArchivoXmlUsuario(String rutaArchivo) throws JDOMException, IOException {
        SAXBuilder saxB = new SAXBuilder();
        saxB.setIgnoringElementContentWhitespace(true);
        this.documento = saxB.build(rutaArchivo);
        this.raiz = this.documento.getRootElement();
        this.ruta = rutaArchivo;
        
        
    }

    public void guardar() throws FileNotFoundException, IOException {
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.output(this.documento, new PrintWriter(this.ruta));
        xmlOut.output(this.documento, System.out);
    }

    public static ArchivoXmlUsuario crearDocumento(String rutaDocumento) throws IOException {
        return new ArchivoXmlUsuario(rutaDocumento, "usuario");
    }

    public static ArchivoXmlUsuario abrirDocumento(String rutaDocumento) throws JDOMException, IOException {
        return new ArchivoXmlUsuario(rutaDocumento);
    }
    public void addUsers(Usuario users) throws IOException{
        Element eUsuario = new Element("Usuario");
        
        Element eNombre = new Element("nombre");
        eNombre.addContent(users.getNombre());
        
        Element eApellido = new Element("apellido");
        eApellido.addContent(users.getApellido());
        
         Attribute aCedula = new Attribute("cedula", users.getCedula());
        
        Element eTelefono = new Element("telefono");
        eTelefono.addContent(users.getTelefono());
        
        Element eGanancias = new Element("ganancias");
        eGanancias.addContent(users.getGanancias());
        
        Element eCorreo = new Element("correo");
        eCorreo.addContent(users.getCorreo());
        
        Element eDireccion = new Element("direccion");
        eDireccion.addContent(users.getDireccion());
        
        Element eEstadoCivil = new Element("estadoCivil");
        eEstadoCivil.addContent(users.getEstadoCivil());
        
        Element eOficio = new Element("oficio");
        eOficio.addContent(users.getOficio());
        
        eUsuario.addContent(eNombre);
        eUsuario.addContent(eApellido);
        eUsuario.setAttribute(aCedula);
        eUsuario.addContent(eCorreo);
        eUsuario.addContent(eDireccion);
        eUsuario.addContent(eTelefono);
        eUsuario.addContent(eEstadoCivil);
        eUsuario.addContent(eGanancias);
        eUsuario.addContent(eOficio);
        raiz.addContent(eUsuario);
        guardar();
    }
    public ArrayList<Usuario> getArrayUsuario(){
        List<Element> elementoUsers = (List<Element>) raiz.getChildren();
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        for(Element e: elementoUsers){
            Usuario usuario= new Usuario();
            usuario.setNombre(e.getChildText("nombre"));
            usuario.setApellido(e.getChildText("apellido"));
            usuario.setCedula(e.getChildText("cedula"));
            usuario.setCorreo(e.getChildText("correo"));
            usuario.setDireccion(e.getChildText("direccion"));
            usuario.setEstadoCivil(e.getChildText("estadoCivil"));
            usuario.setGanancias(e.getChildText("ganancias"));
            usuario.setOficio(e.getChildText("oficio"));
            usuario.setTelefono(e.getChildText("telefono"));
            users.add(usuario);
        }
        return users;
    }
}
