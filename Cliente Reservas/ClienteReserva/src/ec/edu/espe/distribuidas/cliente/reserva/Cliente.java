/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.cliente.reserva;

import javafx.scene.text.Text;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author toshiba
 */
public class Cliente {
    
    ValidacionCedula validarced=new ValidacionCedula();
    String[] tipoId = {"PAS", "CED", "RUC"}; 
    JComboBox lista = new JComboBox(tipoId);
    
    private String tipoIdentificacion="";
    private String numeroIdentificacion="";
    private String nombres="";
    private String apellidos="";
    private String pais="";
    private String direccion="";
    private String telefono="";
    private String correo="";

    
    public void getDatos(JComboBox lista, JTextField txtid, 
            JTextField txtnombre,JTextField txtapellido , JTextField txtpais, 
            JTextField txtdireccion, JTextField txttelefono, JTextField txtcorreo)
    {
       tipoIdentificacion=(String)lista.getSelectedItem();
       numeroIdentificacion=(txtid.getText());
       //validarced.validadorDeCedula(numeroIdentificacion);
       nombres=txtnombre.getText();
       apellidos=txtapellido.getText();
       pais=txtpais.getText();
       direccion=txtdireccion.getText();
       telefono=txttelefono.getText();
       correo=txtcorreo.getText();
       
    }
    
    public String getCuerpo()
    {
        String cuerpo=tipoIdentificacion.concat("&")
                .concat(numeroIdentificacion).concat("&").concat(nombres)
                .concat("&").concat(apellidos)
                .concat("&").concat(pais).concat("&").concat(direccion)
                .concat("&").concat(telefono).concat("&").concat(correo);
        return cuerpo;
    }
    
    
    public String consulta(JTextField txtIdenticacion)
    {
        return txtIdenticacion.getText();
    }
}
