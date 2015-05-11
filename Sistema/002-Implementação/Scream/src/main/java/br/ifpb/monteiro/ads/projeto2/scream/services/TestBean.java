package br.ifpb.monteiro.ads.projeto2.scream.services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Hugo
 */
@ManagedBean
@SessionScoped
public class TestBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
