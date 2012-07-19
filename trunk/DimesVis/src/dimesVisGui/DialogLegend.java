package dimesVisGui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class DialogLegend
{
	public static void runDialogLegend(Display display, String[] info){
		final Shell shell = new Shell (display);
		shell.setLayout(new GridLayout (1,true));
		shell.setText("Index Info");
		final String[] localFinalInfo = info;
		
		int pointAmount = info.length;
		
		Label textlable;
		textlable = new Label (shell, SWT.CENTER);
		textlable.setText ("select index from 1 to "+ pointAmount +" : ");
		
		final Spinner spinner=new Spinner(shell, SWT.NONE);
		// don't allow decimal places
		spinner.setDigits(0);
		// set the minimum value to 0
		spinner.setMinimum(1);
		// set the maximum value to amount of points
		spinner.setMaximum(pointAmount);
		// set the increment value to 1
		spinner.setIncrement(1);
		// set the selection to 1
		spinner.setSelection(1);
		
		Button okbutton = new Button (shell, SWT.PUSH);
		okbutton.setText (" OK ");
		
		/*Label outputlable;
		outputlable = new Label (shell, SWT.CENTER);
		outputlable.setText ("to get point info press OK");*/
		final Text outputtext = new Text(shell, SWT.READ_ONLY | SWT.LEFT | SWT.BORDER);
		outputtext.setText("          to get point info press OK          ");
		
		okbutton.addSelectionListener(new ButtonHandler() {

			public void widgetSelected(SelectionEvent e) {
				outputtext.setText(" "+ localFinalInfo[spinner.getSelection()] +" ");
			}
			
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
		/*open.addSelectionListener (new SelectionAdapter () {
			
		}*/
		
		shell.pack ();
		shell.open ();
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
