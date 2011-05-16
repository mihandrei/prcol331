package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Resource implements IsSerializable{
    public String name;
    public String resourceUrl;
    
    public Resource(){
    	
    }
	public Resource(String name, String resourceUrl) {
		this.name = name;
		this.resourceUrl = resourceUrl;
	}
}
