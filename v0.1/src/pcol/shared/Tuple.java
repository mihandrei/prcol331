package pcol.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Tuple<F, S> implements IsSerializable{
	public F f;
	public S s;
	public Tuple(){
		
	}
	public Tuple(F f, S s) {
		this.f = f;
		this.s = s;
	}
}
