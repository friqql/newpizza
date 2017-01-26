/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Teilnehmer
 */
@Stateless
public class ConversionHelper implements ConversionHelperRemote {

  
    /**
     * Ändert das Format eines Datums
     * @param date
     * @return 
     */
    @Override
    public Date ConvertDate(Date date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(date);
        String result = s;
        try {
            date = df.parse(result);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
/**
 * Hasht ein Passwort
 * @param input
 * @return 
 */
    @Override
    public String hash(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            String password = input;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }
        } catch (NoSuchAlgorithmException ex) {
            
        }

        return sb.toString();
    }

}
