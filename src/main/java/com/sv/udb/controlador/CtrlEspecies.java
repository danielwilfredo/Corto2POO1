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
    /*
    
    public boolean guar(Especies obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try {
        PreparedStatement cmd = cn.prepareStatement("INSERT INTO seresvivos VALUES (null, ?, ?, null)");
        cmd.setString(1, obje.getNombEspe());
        cmd.setString(2, obje.getDescEspe());
       // cmd.setInt(3, obje.getCodiRefeEspe());
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
    }*/
    
    // Guardar una nueva especie
    
    public boolean guar(Especies obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO seresvivos VALUES(NULL,?,?,?)");
            cmd.setString(1, obje.getNombEspe());
            cmd.setString(2, obje.getDescEspe());
            cmd.setInt(3, obje.getCodiRefeEspe());
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al guardar especie: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
    
    
    // guardar Renino o padre de especies
    public boolean guar2(Especies obje)
    {
        boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("INSERT INTO seresvivos VALUES(NULL,?,?,NULL)");
            cmd.setString(1, obje.getNombEspe());
            cmd.setString(2, obje.getDescEspe());
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al guardar el nuevo Reino: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
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
        try
        {
            PreparedStatement cmd = cn.prepareStatement("SELECT s2.codi_sere, s2.codi_refe_sere, s1.nomb_sere 'PERTENECE A', s2.nomb_sere, s2.desc_sere \n" +
                                                        "FROM seresvivos s1 RIGHT JOIN seresvivos s2 on s1.codi_sere = s2.codi_refe_sere\n" +
                                                        "ORDER BY s1.codi_sere");
            ResultSet rs = cmd.executeQuery();
            resp.add(new Especies(0,0,null,"Reino Padre",null));
            while(rs.next())
            {
                resp.add(new Especies(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            //Se carga el 
        }
        catch(Exception err)
        {
            err.printStackTrace();
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    } 
     
     //codigo para obtener el valor del id del cmb
     
     public Especies consUno(int id){
        Especies resp = new Especies();
        Connection cn = new Conexion().getConn();
        try {
            PreparedStatement cmd = cn.prepareStatement("Select * from seresvivos where codi_sere = ?;");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next())
            {
                resp.setCodiEspe(rs.getInt(1));
                resp.setNombEspe(rs.getString(2));
                resp.setDescEspe(rs.getString(3));
                resp.setCodiRefeEspe(rs.getInt(4));
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        finally 
        {
            try 
            {
                if (cn!=null)
                {
                    if (!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            } 
            catch (Exception e) 
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return resp;
    }
     
     //Eliminar un Ser Vivo
    public boolean elim(Especies obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("Delete from seresvivos where codi_sere = ?");
            cmd.setString(1, String.valueOf(obje.getCodiEspe()));
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al eliminar el Ser Vivo " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
     //modificar ser vivo
     public boolean modi(Especies obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("update seresvivos set nomb_sere = ?, desc_sere=?, codi_refe_sere = ?  where codi_sere=?;");
            cmd.setString(1, String.valueOf(obje.getNombEspe())); 
            cmd.setString(2, String.valueOf(obje.getDescEspe()));
            cmd.setString(3, String.valueOf(obje.getCodiRefeEspe()));
            cmd.setString(4, String.valueOf(obje.getCodiEspe()));
;
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al modificar el Ser vivo " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
     //modi un registro padre
     public boolean modi2(Especies obje)
    {
         boolean resp = false;
        Connection cn = new Conexion().getConn();
        try
        {
            PreparedStatement cmd = cn.prepareStatement("update seresvivos set nomb_sere = ?, desc_sere= ?, codi_refe_sere = null where codi_sere=?");
             cmd.setString(1, String.valueOf(obje.getNombEspe())); 
            cmd.setString(2, String.valueOf(obje.getDescEspe()));
            cmd.setString(3, String.valueOf(obje.getCodiEspe()));
;
            cmd.executeUpdate();
            resp=true;
        }
        catch(Exception ex)
        {
            System.err.println("Error al modificar el Ser vivo " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(cn!=null)
                {
                    if(!cn.isClosed())
                    {
                        cn.close();
                    }
                }
            }
            catch(SQLException err)
            {
                err.printStackTrace();
            }
        }
        return resp;
    }
}

    
     
     
    
    

