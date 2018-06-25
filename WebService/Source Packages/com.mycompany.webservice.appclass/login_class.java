/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclass;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serkan.ozdemir
 */
@XmlRootElement
public class loginclass {
 @XmlElement String kadi;
 @XmlElement String sifre;
}
