package ch.itenengineering.jndiview.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

@ManagedBean
@SessionScoped
public class Index implements Serializable {

	//
	// fields
	//
	private String name = "java:global/<MyContext>";

	private List<String> result = new ArrayList<String>();

	private String resultTitle;

	// --------------------------------------------------------------------------------------------
	// constructors
	// --------------------------------------------------------------------------------------------

	public Index() {
	}

	// --------------------------------------------------------------------------------------------
	// actions
	// --------------------------------------------------------------------------------------------

	public void list() {
		try {
			this.resultTitle = "Recursive list of JNDI tree entries";
			this.result.clear();

			InitialContext ctx = new InitialContext();
			traverseContextTree(ctx, "");
			ctx.close();

			Collections.sort(this.result);

		} catch (Exception e) {
			this.result.add("Sorry, list failed with execption: "
					+ e.toString());
		}
	}

	public void listContext() {
		try {
			this.resultTitle = "List of bindings for the context: " + this.name;
			this.result.clear();

			InitialContext ctx = new InitialContext();
			traverseContextTree(ctx, this.name);

			ctx.close();

			Collections.sort(this.result);

		} catch (Exception e) {
			this.result.add("Sorry, list context failed with execption: "
					+ e.toString());
		}
	}

	private void traverseContextTree(InitialContext ctx, String name) {
		try {
			name = (name == null ? "" : name);
			NamingEnumeration en = ctx.list(name);

			while (en != null && en.hasMoreElements()) {
				NameClassPair nc = (NameClassPair) en.next();

				String delim = (null != name && 0 < name.length()) ? "/" : "";
				this.result.add(name + delim + nc);

				traverseContextTree(ctx, nc.getName());
			}
		} catch (javax.naming.NamingException ex) {
			// ignore failure
		}
	}

	// --------------------------------------------------------------------------------------------
	// getters and setters
	// --------------------------------------------------------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public String getResultTitle() {
		return resultTitle;
	}

	public void setResultTitle(String resultTitle) {
		this.resultTitle = resultTitle;
	}

} // end
