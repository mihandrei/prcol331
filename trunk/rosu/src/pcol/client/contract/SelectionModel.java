package pcol.client.contract;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface SelectionModel<T>{
		void setSelected(T object, Boolean inscris);
		boolean isSelected(T object);
		Set<T> getSelected();
}

class MultipleSelectionModel<T> implements SelectionModel<T>{
	private Set<T> selection = new HashSet<T>();
	
	@Override
	public void setSelected(T object, Boolean inscris) {
		if(inscris) {
			selection.add(object);
		}else{
			selection.remove(object);
		}
	}

	@Override
	public Set<T> getSelected(){
		return Collections.unmodifiableSet(selection);
	}
	
	public boolean isSelected(T object) {
		return selection.contains(object);
	}
}

class SingleSelectionModel<T> implements SelectionModel<T>{
	T selection = null;

	@Override
	public void setSelected(T object, Boolean inscris) {
		selection = inscris? object: null;
	}

	@Override
	public boolean isSelected(T object) {
		return selection!=null && selection.equals(object);
	}

	@Override
	public Set<T> getSelected() {
		if(selection == null) 
			return Collections.emptySet();
		else 
			return Collections.singleton(selection);
	}
}