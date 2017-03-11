/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;
import com.sv.udb.modelo.Especies;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DanielWilfredo
 */
public class CtrlEspecies {
    
    
    public boolean guar(Especies obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
        PreparedStatement cmd = cn.prepareStatement("INSERT INTO seresvivos VALUES (null, ?, ?, ?)");
        cmd.setString(1, obje.getNombEspe());
        cmd.setString(2, obje.getDescEspe());
        cmd.setInt(3, obje.getCodiRefeEspe());
        cmd.execute();
        resp = true;          
        } 
        catch (Exception e) 
        {
            System.err.println("Error al guardar Especies: " + e.getMessage());
        }
        finally
        {
            try {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
                
            } catch (SQLException err) 
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    
    
    
       
     public List<Especies> consTodo()
    {
        List<Especies> resp = new ArrayList();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("SELECT s2.codi_sere , s1.nomb_sere 'PERTENECE A', s2.nomb_sere, s2.desc_sere FROM seresvivos s1\n" +
"RIGHT JOIN seresvivos s2 on s1.codi_sere = s2.codi_refe_sere\n" +
"ORDER BY s2.codi_sere;");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Especies(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3)));
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            try {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        return resp;
    }
     
     /*para llenar el combo 
     
     SELECT s1.codi_sere , s1.nomb_sere 'PERTENECE A', s2.nomb_sere, s2.desc_sere FROM seresvivos s1
RIGHT JOIN seresvivos s2 on s1.codi_sere = s2.codi_refe_sere
ORDER BY s1.codi_sere;*/
    
    
    
}
