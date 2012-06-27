package dimesVisGui;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;


public class ButtonHandler implements SelectionListener {

	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() instanceof Button) {
			Button b = (Button) e.getSource();
			b.setText("Thanks!");
		}
	}
	public void widgetDefaultSelected(SelectionEvent e){
		// TODO Auto-generated method stub
	}
}