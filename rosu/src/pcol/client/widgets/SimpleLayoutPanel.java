package pcol.client.widgets;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple panel that {@link ProvidesResize} to its one child.
 */
public class SimpleLayoutPanel extends LayoutPanel implements AcceptsOneWidget {

	@Override
	public void setWidget(IsWidget w) {
		clear();
		Widget widget = Widget.asWidgetOrNull(w);
		if (widget != null) {
			add(w);
		}
	}

}
