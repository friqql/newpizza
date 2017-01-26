/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.friqql.mynewpizza.ejb;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Teilnehmer
 */
@Remote
public interface ConversionHelperRemote {
      public Date ConvertDate(Date date);
      public String hash(String input);
}
