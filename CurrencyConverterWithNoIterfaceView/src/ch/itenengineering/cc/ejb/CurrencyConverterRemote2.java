package ch.itenengineering.cc.ejb;

import javax.ejb.Remote;

import ch.itenengineering.cc.domain.CurrencyType;

@Remote
public interface CurrencyConverterRemote2 {

	/**
	 * convert the given CHF value to the desired currency
	 * 
	 * @param type
	 *            currency type
	 * @param value
	 *            CHF to convert
	 * @return converted value
	 */
	public Double convertCHF(Double value, CurrencyType type);

}
