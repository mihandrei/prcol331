package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Resource implements IsSerializable{
    public String description;
    public String resourceName;
    
    public Resource(){
    	
    }
	public Resource(String name, String resourceUrl) {
		this.description = name;
		this.resourceName = resourceUrl;
	}
}
