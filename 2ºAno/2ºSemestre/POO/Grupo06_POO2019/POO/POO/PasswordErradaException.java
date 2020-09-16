/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Excessao de password errada
 */
public class PasswordErradaException extends Exception {

    public PasswordErradaException(String passWord_Errada) {
        super(passWord_Errada);
    }
    
}
