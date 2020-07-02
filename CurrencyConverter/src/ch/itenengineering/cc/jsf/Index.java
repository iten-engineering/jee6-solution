package ch.itenengineering.cc.jsf;

import ch.itenengineering.cc.domain.CurrencyType;
import ch.itenengineering.cc.ejb.CurrencyConverterRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Index implements Serializable {

    @EJB
    CurrencyConverterRemote remote;

    Double valueCHF = 12.5;
    
    List<String> result = new ArrayList<String>();


    public Index() {
        // consturct object
    }

    public void convert() {
        result.clear();
        for (CurrencyType currency : CurrencyType.values()) {
            if (currency != CurrencyType.CHF) {
                Double value = remote.convertCHF(valueCHF, currency);
                result.add(valueCHF + " CHF = " + value + " " + currency);
            }
        }
    }

    public Double getValueCHF() {
        return valueCHF;
    }

    public void setValueCHF(Double valueCHF) {
        this.valueCHF = valueCHF;
    }

    public List<String> getResult() {
        return result;
    }

} // end
