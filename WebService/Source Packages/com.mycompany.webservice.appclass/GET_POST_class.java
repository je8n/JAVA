/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appclass;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import vtkontrol.VtClass;

/**
 *
 * @author serkan.ozdemir
 */
@Path("/database")
public class getclass {
    @GET
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ac")
    public VtClass ac(loginclass giris) throws ClassNotFoundException, SQLException{
        VtClass vtclass=new VtClass();
        vtclass.setAdres("ip:port:SID");
        vtclass.setKullanici_Adi(giris.kadi);
        vtclass.setSifre(giris.sifre);
        vtclass.connect();
        return vtclass;
    }
    @GET
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cek")
    public VtClass cek(loginclass giris) throws ClassNotFoundException, SQLException{
        VtClass vtclass=new VtClass();
        vtclass.setAdres("ip:port:SID");
        vtclass.setKullanici_Adi(giris.kadi);
        vtclass.setSifre(giris.sifre);
        vtclass.connect();
        vtclass.icerikal("select b.tablespace_name, tbs_size, a.free_space from  (select tablespace_name, round(sum(bytes)/1024/1024 ,2) as free_space from dba_free_space group by tablespace_name) a, (select tablespace_name, sum(bytes)/1024/1024 as tbs_size from dba_data_files group by tablespace_name) b where a.tablespace_name(+)=b.tablespace_name");
        return vtclass;
    }
}
