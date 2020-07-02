package ch.itenengineering.env.jsf;

import ch.itenengineering.env.ejb.EnvRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Index implements Serializable {

    @EJB
    EnvRemote remote;
    
    public Index() {
    }

    public String getMessages() {
        return remote.getMessages();
    }

} // end
