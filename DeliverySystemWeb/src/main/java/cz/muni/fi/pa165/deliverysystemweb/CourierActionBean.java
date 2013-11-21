/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliverysystemweb;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author janvorcak
 */
@UrlBinding("/couriers/${event}")
public class CourierActionBean extends BaseActionBean {

    @DefaultHandler
    public Resolution list() {
        return new ForwardResolution("/courier/list.jsp");
    }
    
}
