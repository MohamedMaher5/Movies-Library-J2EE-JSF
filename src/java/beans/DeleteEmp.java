/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DB.DB_Manager;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Emad_N
 */
@ManagedBean
@RequestScoped
public class DeleteEmp
{

    String movieName;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public DeleteEmp()
    {
    }

    
    private boolean Found() throws Exception 
    {
        
        DB_Manager dbm = new DB_Manager();
        ResultSet rs = dbm.select("select * from movies where Name='"+movieName+"'");
        boolean found = rs.next(); //if there is a row will return true
        dbm.releaseResources();
        
        if(found)
            return true;
        return false;
        
    }
    public String DeleteMovie()
    {
        try
        {
            DB_Manager dbm  =  new DB_Manager();
            if(Found())
            {
                String Q="Delete from movies where Name='"+movieName+"'";
                dbm.InsertUpdateDelete(Q);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has deleted", null);
                FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
                setMovieName("");
            }
            else
            { 
                throw new Exception("This Movie Name is Not Found");
            }
        }
        
        catch(Exception ex)
        {
            String  message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
            return "failure";
        }
        return "success";
    }
}
