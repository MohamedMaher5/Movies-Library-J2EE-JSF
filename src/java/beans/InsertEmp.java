/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DB.DB_Manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
public class InsertEmp
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
    public InsertEmp()
    {
    }

    public String InsertData()
    {
        try
        {
            DB_Manager dbm  =  new DB_Manager();
            String Q="INSERT INTO movies (Name, Rate, Year, Category, Country) values('"+
                   movieName+"','"+rate+"', '"+year+"','"+category+"','"+country+"');";
            dbm.InsertUpdateDelete(Q);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "New record has inserted", null);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
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
