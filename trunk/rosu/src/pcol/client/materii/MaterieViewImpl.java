package pcol.client.materii;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MaterieViewImpl extends Composite implements MaterieView {

	private static MaterieViewImplUiBinder uiBinder = GWT
			.create(MaterieViewImplUiBinder.class);
	@UiField
	Grid weekGrid;
	@UiField
	HTML materialeHTML;
	
	private Presenter presenter;

	interface MaterieViewImplUiBinder extends UiBinder<Widget, MaterieViewImpl> {
	}

	public MaterieViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(MaterieView.Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void addScheduleItem(int dayOfWeek, int startHour, int endHour,
			String name) {
		int column = dayOfWeek;
		int row1 = startHour - 8 + 1;

		weekGrid.setHTML(row1, column, name);
		weekGrid.getCellFormatter().getElement(row1, column).getStyle()
				.setBackgroundColor("#E0F0FF");

		for (int h = startHour + 1; h <= endHour; h++) {
			int row = h - 8 + 1;
			weekGrid.getCellFormatter().getElement(row, column).getStyle()
					.setBackgroundColor("#E0F0FF");
		}
	}

	@Override
	public void clearSchedule() {
		for (int row = 1; row < weekGrid.getRowCount(); row++) {
			for (int col = 1; col < 8; col++) {
				weekGrid.clearCell(row, col);
				weekGrid.getCellFormatter().getElement(row, col).getStyle()
						.clearBackgroundColor();
			}
		}
	}

	@Override
	public void setMateriale(String string) {
		materialeHTML.setHTML(string);
	}

}
