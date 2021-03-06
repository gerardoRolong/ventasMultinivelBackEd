/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EndPointsVentas;

import datos.ClienteDAO;
import datos.PrivilegiosDAO;
import datos.GeneraUsuarioDAO;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import negocio.Cliente;
import negocio.Privilegio;
import org.json.simple.JSONObject;
import util.RHException;
import util.Util;

/**
 *
 * @author sebastianbd
 */
@Path("regCliente")
public class RegistrarCliente extends Util {
    
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject regCliente (@QueryParam("user") String usuario, @QueryParam("pass") String password, 
            @QueryParam("codigoRep") String codRepVentas,@QueryParam("codigo") String cedula,
            @QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
            @QueryParam("direccion") String direccion,@QueryParam("ciudad") String ciudad,
            @QueryParam("telefono") String telefono, @QueryParam("correo") String correo
            )throws RHException, Exception{
        
        conectarBD(usuario,password);
        GeneraUsuarioDAO regCliDao = new GeneraUsuarioDAO();
        ClienteDAO cliDao = new ClienteDAO();
        JSONObject obj = new JSONObject();
        Cliente cliente = new Cliente();
        try{
            cliente.setCodigo(cedula);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setCiudad(ciudad);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            cliDao.regCliente(cliente, codRepVentas);
            
            regCliDao.crearCliente(nombre);
            System.out.println("SE CREO EL Cliente");
            regCliDao.darPermisos(nombre);
        obj.put("estado", "correctamente ingresado");
        return obj;
        }catch(Exception e){
            obj.put("error", e.getMessage());
            return obj;
        }
         
    } 
}
