package dimesVisGui;
/* 
 * example snippet: ExpandBar example
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.2
 */

import java.util.Date;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;

public class visualizationStartMenuMain {

public static void main (String [] args) {
	//initials
	final Details details=new Details();
	
	Display display = new Display ();
	final Shell shell = new Shell (display);
	shell.setMinimumSize(480,600);
	shell.setText("Welcome to Visual Distance");
	ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
	bar.setBounds(0, 0, 471, 566);
	ExpandItem items[] = new ExpandItem[6];
	Image image = display.getSystemImage(SWT.ICON_QUESTION);
	
	/*
 	 * item 0
 	 */	
	Composite composite = new Composite (bar, SWT.NONE);
	composite.setBounds(0, 0, 400, 500);
	GridLayout layout = new GridLayout (4,false);
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
	layout.verticalSpacing = 10;//distance between lines
	composite.setLayout(layout);
	
	
	//creation of radio buttons
	final Button[] firstbuttonArr=new Button[5];
	for (int i=0; i<5; i++) {
		firstbuttonArr[i] = new Button (composite, SWT.RADIO);
		firstbuttonArr[i].setText ("("+i+")");
		if (i == 0) firstbuttonArr[i].setSelection (true);
		Label label1 = new Label(composite, SWT.BORDER );
		label1.setImage(new Image(display,(i+1)+".bmp"));
	}
	
	// item configuration
	items[0] = new ExpandItem (bar, SWT.NONE, 0);
	items[0].setText("visual preferences");
	items[0].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[0].setControl(composite);
	items[0].setImage(image);
	
	
	/*
	 * item 1
	 */
	composite = new Composite (bar, SWT.NONE);
	composite.setSize(400, 500);
	layout = new GridLayout (4,false);
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
	layout.verticalSpacing = 10;//distance between lines
	composite.setLayout(layout);


	//creation of radio buttons
	final Button[] secondbuttonArr=new Button[2];
	for (int i=0; i<2; i++) {
		secondbuttonArr[i] = new Button (composite, SWT.RADIO);
		secondbuttonArr[i].setText ("("+i+")");
		if (i == 0) secondbuttonArr[i].setSelection (true);
		Label label1 = new Label(composite, SWT.BORDER );
		label1.setImage(new Image(display,(i+5)+".bmp"));
	}

	// item configuration
	items[1] = new ExpandItem (bar, SWT.NONE, 1);
	items[1].setText("visual data");
	items[1].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[1].setControl(composite);
	items[1].setImage(image);
	items[1].addListener(1, new Listener() {

		public void handleEvent(Event event) {
			System.out.println("aaa");
		}
		
	});
	
	
	/*
	 * item 2
	 */
	composite = new Composite (bar, SWT.NONE);
	layout = new GridLayout (7, false);
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
	layout.verticalSpacing = 10;
	composite.setLayout(layout);
	final Spinner spinner[] = new Spinner[4];
	
	for (int i=0; i<4; i++){
	spinner[i]=new Spinner(composite, SWT.NONE);
	// don't allow decimal places
	spinner[i].setDigits(0);
	// set the minimum value to 0
	spinner[i].setMinimum(0);
	// set the maximum value to 255
	spinner[i].setMaximum(255);
	// set the increment value to 1
	spinner[i].setIncrement(1);
	// set the selection to 255
	spinner[i].setSelection(255);
	if (i<3) new Label (composite, SWT.NONE).setText(":");
	}
	
	items[2] = new ExpandItem (bar, SWT.NONE, 2);
	items[2].setText("Source IP");
	items[2].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[2].setControl(composite);
	items[2].setImage(image);
	
	
	/*
	 * item 3
	 */
	composite = new Composite (bar, SWT.NONE);
	composite.setSize(400, 500);
	layout = new GridLayout ();
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
	layout.verticalSpacing = 10;//distance between lines
	composite.setLayout(layout);

	//creation of radio buttons
	final Button[] thirdbuttonArr=new Button[3];
	for (int i=0; i<3; i++) {
		thirdbuttonArr[i] = new Button (composite, SWT.RADIO);
		if (i==0) thirdbuttonArr[i].setText ("( average )");
		if (i==1) thirdbuttonArr[i].setText ("( best time )");
		if (i==2) thirdbuttonArr[i].setText ("( worst time )");
		
		if (i == 0) thirdbuttonArr[i].setSelection (true);
	}

	// item configuration
	items[3] = new ExpandItem (bar, SWT.NONE, 3);
	items[3].setText("time measurement");
	items[3].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[3].setControl(composite);
	items[3].setImage(image);

	/*
	 * item 4
	 */
	composite = new Composite (bar, SWT.NONE);
	composite.setSize(400, 500);
	layout = new GridLayout (2,false);
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
	layout.verticalSpacing = 10;//distance between lines
	composite.setLayout(layout);
	Date cur_date=new Date();
	final int date[]={cur_date.getDay(),cur_date.getMonth()+1,cur_date.getYear()+1900};
	Button open = new Button (composite, SWT.PUSH);
	open.setText ("Change Date");
	final Label label = new Label (composite, SWT.CENTER);
	label.setText ("     "+date[0]+" / "+date[1]+" / "+date[2]+"   ");
	
	
	open.addSelectionListener (new SelectionAdapter () {
		public void widgetSelected (SelectionEvent e) {
			
			final Shell dialog = new Shell (shell, SWT.DIALOG_TRIM);
			dialog.setLayout (new GridLayout (3, false));
			final DateTime calendar = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
			new Label (dialog, SWT.NONE);
			new Label (dialog, SWT.NONE);
			Button ok = new Button (dialog, SWT.PUSH);
			ok.setText ("OK");
			ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
			ok.addSelectionListener (new SelectionAdapter () {
				public void widgetSelected (SelectionEvent e) {
					date[0]=calendar.getDay();
					date[1]=calendar.getMonth()+1;
					date[2]=calendar.getYear();
					label.setText ("     "+date[0]+" / "+date[1]+" / "+date[2]);
					dialog.close ();
				}
			});
			dialog.setDefaultButton (ok);
			dialog.pack ();
			dialog.open ();
		}
	});
	
	// item configuration
	items[4] = new ExpandItem (bar, SWT.NONE, 4);
	items[4].setText("choose date");
	items[4].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[4].setControl(composite);
	items[4].setImage(image);
	
	
	// Last item
	composite = new Composite (bar, SWT.NONE);
	layout = new GridLayout ();
	//layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
	//layout.verticalSpacing = 10;
	composite.setLayout(layout);
	Button doneButton = new Button (composite, SWT.PUSH);
	doneButton.setText ("done");
	
	doneButton.addSelectionListener(new ButtonHandler() {

		public void widgetSelected(SelectionEvent e) {
			
			if (e.getSource() instanceof Button) {
				
				// first view radio button
				int x=-1;
				for (int i=0; i<5; i++){
					if (firstbuttonArr[i].getSelection()==true) x=i;
				}
				details.setFirstRadioButton(x);
				
				// second view radio button
				x=-1;
				for (int i=0; i<2; i++){
					if (secondbuttonArr[i].getSelection()==true)x=i;
				}
				details.setSecondRadioButton(x);
				
				// user IP
				details.setSourceIp(spinner[0].getSelection(), spinner[1].getSelection(), spinner[2].getSelection(), spinner[3].getSelection());
				
				// time choice radio button
				x=-1;
				for (int i=0; i<3; i++){
					if (thirdbuttonArr[i].getSelection()==true)x=i;
				}
				details.setTimeChoiceRadioButton(x);
				
				// date selection
				details.setDate(date[0], date[1], date[2]);
				
			}
			
			MessageBox messageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION |SWT.CANCEL);
			messageBox.setText("Please Confirm Your Preferences");
			messageBox.setMessage(
								"first view choice - "+details.getFirstRadioButton()+"\n\n"+
								"second view choice - "+details.getSecondRadioButton()+"\n\n"+
								"source ip - "+details.getSourceIp()[0]+":"+details.getSourceIp()[1]+":"+details.getSourceIp()[2]+":"+details.getSourceIp()[3]+"\n\n"+
								"time measurement method - "+details.getTimeChoiceRadioButton()+"\n\n"+
								"date of measurement - "+details.getDate()[0]+":"+details.getDate()[1]+":"+details.getDate()[2]+"\n\n"
								);
			//messageBox.open();
			if (messageBox.open() == SWT.OK)
			{
				System.out.println("Ok is pressed.");
				try {
	                Runtime rt = Runtime.getRuntime();
	                //Process pr = rt.exec("cmd /c dir");
	                Process pr = rt.exec("c:\\runMatlab.cmd");
	 
	                int exitVal = pr.waitFor();
	                System.out.println("Exited with error code "+exitVal);
	 
	            } catch(Exception exception) {
	                System.out.println(exception.toString());
	                exception.printStackTrace();
	            }
				shell.close();
			}
		}
		public void widgetDefaultSelected(SelectionEvent e){
			// TODO Auto-generated method stub
		}
	});
	items[5] = new ExpandItem (bar, SWT.NONE, 5);
	items[5].setText("finish");
	items[5].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	items[5].setControl(composite);
	items[5].setImage(image);
	
	items[0].setExpanded(true);
	bar.setSpacing(8);
	
	/*Label label2 = new Label(bar, SWT.BORDER );
	label2.setImage(new Image(display,"startPagePic.bmp"));*/
	
	shell.setSize(480, 600);
	shell.open();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) {
			display.sleep ();
		}
	}
	image.dispose();
	display.dispose();
}

}
