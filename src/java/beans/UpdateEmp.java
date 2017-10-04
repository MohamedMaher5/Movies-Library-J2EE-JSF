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
public class UpdateEmp
{

    private String movieName;
    private float rate;
    private int year;
    private String category;
    private String country;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public UpdateEmp()
    {
    }

    
    private boolean Found() throws Exception 
    {
        
        DB_Manager dbm = new DB_Manager();
        ResultSet rs = dbm.select("select * from movies where Name='"+movieName+"'");
        boolean found = rs.next(); //if there is a row will return true
        dbm.releaseResources(); //must close rs only after checking its number of rows.
        
        if(found)
            return true;
        return false;
        
    }
    public String UpdateData()
    {
        try
        {
            DB_Manager dbm  =  new DB_Manager();
            if(Found())
            {
            
                String Q="update movies set Rate = '"+rate+"', Year='"+year+"', Category ='"+category+"', Country ='"+country+"' where Name ='"+movieName+"'";
                dbm.InsertUpdateDelete(Q);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your record has updated", null);
                FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
            }
            else
            { 
                throw new Exception("This Movies Name Is Not Found");
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
