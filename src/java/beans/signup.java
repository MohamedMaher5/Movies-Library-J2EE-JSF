package beans;

import DB.DB_Manager;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class signup {
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String age;
   
    public signup(){
    
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    public String signupCheck(){
        try{
            DB_Manager dbm = new DB_Manager();
            
            String query = "INSERT INTO users (Email, Password, Username, MobileNumber, Age) VALUES('"+email+"', '"+password+"', '"+username+"', '"+mobileNumber+"', '"+age+"')";
            dbm.InsertUpdateDelete(query);
            dbm.releaseResources();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "New record has inserted", null);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
        }catch(Exception ex){
            String  message = ex.getMessage();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
            return "failure";
        }
        return "success";
    }
}
