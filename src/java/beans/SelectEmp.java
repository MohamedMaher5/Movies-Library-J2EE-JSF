/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DB.DB_Manager;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.movies;

/**
 *
 * @author Emad_N
 */
@ManagedBean
@RequestScoped
public class SelectEmp
{

    List <movies> empData;
    public SelectEmp()
    {
        empData=new LinkedList<movies>();
        try
        {
            DB_Manager dbm= new DB_Manager();
            ResultSet rs=dbm.select("select * from movies");
            while(rs.next())
            {
                movies e=new movies();
                e.setMovieName(rs.getString("Name"));
                e.setRate(rs.getFloat("Rate"));
                e.setYear(rs.getInt("Year"));
                e.setCategory(rs.getString("Category"));
                e.setCountry(rs.getString("Country"));
                empData.add(e);
            }
            dbm.releaseResources();
        }
        catch(ClassNotFoundException ex)
        {
            String  message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
        }
        catch(Exception ex)
        {
            String  message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
        }
    }

    public List<movies> getEmpData()
    {
        return empData;
    }

    public void setEmpData(List<movies> empData)
    {
        this.empData = empData;
    }
    
    
    
    
}
