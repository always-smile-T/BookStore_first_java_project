/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.DAO;

import java.io.Serializable;

/**
 *
 * @author Thao
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwpordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatchPassword;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswpordLengthErr() {
        return passwpordLengthErr;
    }

    public void setPasswpordLengthErr(String passwpordLengthErr) {
        this.passwpordLengthErr = passwpordLengthErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getConfirmNotMatchPassword() {
        return confirmNotMatchPassword;
    }

    public void setConfirmNotMatchPassword(String confirmNotMatchPassword) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
    
    
    
    
}
