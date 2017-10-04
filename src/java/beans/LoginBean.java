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
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Emad_N
 */
@ManagedBean
@RequestScoped
public class LoginBean
{

    private String email;
    private String password;
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public LoginBean()
    {
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public String checkLogin()
    {
        
        try
        {
        DB_Manager dbm = new DB_Manager();
        ResultSet rs = dbm.select("select * from users where Email='"+email+"' and Password='"+password+"'" );
        boolean found = rs.next();
        dbm.releaseResources();
        if(!found)
            throw new Exception("You are no autherized to access the system");
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
