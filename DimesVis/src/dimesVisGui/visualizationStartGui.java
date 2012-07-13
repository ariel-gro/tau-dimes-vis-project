package dimesVisGui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;

import dimesSqlBasics.DimesDbOperationsMain;

public class visualizationStartGui {

	public static Details startMenu()
	{
		final Details details=new Details();
		
		Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setMinimumSize(480,600);
		shell.setText("Welcome to Visual Distance");
		ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
		bar.setBounds(0, 0, 471, 566);
		ExpandItem items[] = new ExpandItem[10];
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
			label1.setImage(new Image(display,"Images\\"+(i+1)+".bmp"));
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
			label1.setImage(new Image(display,"Images\\"+(i+5)+".bmp"));
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
		// set the selection to 0
		spinner[i].setSelection(0);
		if (i<3) new Label (composite, SWT.NONE).setText(".");
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
			if (i==0) thirdbuttonArr[i].setText ("( Best time )");
			if (i==1) thirdbuttonArr[i].setText ("( Average time )");
			if (i==2) thirdbuttonArr[i].setText ("( Worst time )");
			
			//set average as default
			if (i == 1) thirdbuttonArr[i].setSelection (true);
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
		
		/*
		 * item 5
		 */
		composite = new Composite (bar, SWT.NONE);
		composite.setSize(400, 500);
		layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
		layout.verticalSpacing = 10;//distance between lines
		composite.setLayout(layout);

		//host name;
		final Label hostnameLabel;
		final Text hostnameText;
		hostnameLabel = new Label (composite, SWT.CENTER);
		hostnameLabel.setText ("Enter Host Name");
		hostnameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		hostnameText.setText("127.0.0.1");
		
		//port number;
		final Label portnumberLabel;
		final Text portnumberText;
		portnumberLabel = new Label (composite, SWT.CENTER);
		portnumberLabel.setText ("Enter Port Number");
		portnumberText = new Text(composite, SWT.LEFT | SWT.BORDER);
		portnumberText.setText("3306");
		
		//user name;
		final Label usernameLabel;
		final Text usernameText;
		usernameLabel = new Label (composite, SWT.CENTER);
		usernameLabel.setText ("UserName (optional)");
		usernameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		
		//password;
		final Label passwordLabel;
		final Text passwordText;
		passwordLabel = new Label (composite, SWT.CENTER);
		passwordLabel.setText ("Password (optional)");
		passwordText = new Text(composite, SWT.LEFT | SWT.BORDER);
		
		//schema name;
		final Label firstSchemaLabel;
		final Text firstSchemaText;
		firstSchemaLabel = new Label (composite, SWT.CENTER);
		firstSchemaLabel.setText ("Enter Schema Name for raw_res_main/traceroute");
		firstSchemaText = new Text(composite, SWT.LEFT | SWT.BORDER);
		firstSchemaText.setText("dimes_results_2012");
		
		//table name;
		final Label rawResMainTablenameLabel;
		final Text rawResMainTablenameText;
		rawResMainTablenameLabel = new Label (composite, SWT.CENTER);
		rawResMainTablenameLabel.setText ("Enter Main Results Table Name");
		rawResMainTablenameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		rawResMainTablenameText.setText("raw_res_main_2012_28");
		
		//table name;
		final Label rawResTraceTablenameLabel;
		final Text rawResTraceTablenameText;
		rawResTraceTablenameLabel = new Label (composite, SWT.CENTER);
		rawResTraceTablenameLabel.setText ("Enter Traceroute Results Table Name");
		rawResTraceTablenameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		rawResTraceTablenameText.setText("raw_res_traceroute_2012_28");
		

		// item configuration
		items[5] = new ExpandItem (bar, SWT.NONE, 5);
		items[5].setText("First Connection Parameters");
		items[5].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[5].setControl(composite);
		items[5].setImage(image);
		
		/*
		 * item 6
		 */
		composite = new Composite (bar, SWT.NONE);
		composite.setSize(400, 500);
		layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
		layout.verticalSpacing = 10;//distance between lines
		composite.setLayout(layout);

		//host name;
		final Label hostnameLabel2;
		final Text hostnameText2;
		hostnameLabel2 = new Label (composite, SWT.CENTER);
		hostnameLabel2.setText ("Enter Host Name");
		hostnameText2 = new Text(composite, SWT.LEFT | SWT.BORDER);
		hostnameText2.setText("127.0.0.1");
		
		//port number;
		final Label portnumberLabel2;
		final Text portnumberText2;
		portnumberLabel2 = new Label (composite, SWT.CENTER);
		portnumberLabel2.setText ("Enter Port Number");
		portnumberText2 = new Text(composite, SWT.LEFT | SWT.BORDER);
		portnumberText2.setText("3306");
		
		//user name;
		final Label usernameLabel2;
		final Text usernameText2;
		usernameLabel2 = new Label (composite, SWT.CENTER);
		usernameLabel2.setText ("UserName (optional)");
		usernameText2 = new Text(composite, SWT.LEFT | SWT.BORDER);
		
		//password;
		final Label passwordLabel2;
		final Text passwordText2;
		passwordLabel2 = new Label (composite, SWT.CENTER);
		passwordLabel2.setText ("Password (optional)");
		passwordText2 = new Text(composite, SWT.LEFT | SWT.BORDER);
		
		//schema name;
		final Label secondSchemaLabel;
		final Text secondSchemaText;
		secondSchemaLabel = new Label (composite, SWT.CENTER);
		secondSchemaLabel.setText ("Enter Schema Name for IPs Table");
		secondSchemaText = new Text(composite, SWT.LEFT | SWT.BORDER);
		secondSchemaText.setText("DIMES");
		
		//table name;
		final Label ipsTblFullTablenameLabel;
		final Text ipsTblFullTablenameText;
		ipsTblFullTablenameLabel = new Label (composite, SWT.CENTER);
		ipsTblFullTablenameLabel.setText ("Enter IPs Table Name");
		ipsTblFullTablenameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		ipsTblFullTablenameText.setText("IPsTblFull");
		

		// item configuration
		items[6] = new ExpandItem (bar, SWT.NONE, 6);
		items[6].setText("Second Connection Parameters");
		items[6].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[6].setControl(composite);
		items[6].setImage(image);
		
		/*
		 * item 7
		 */
		composite = new Composite (bar, SWT.NONE);
		composite.setSize(400, 500);
		layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
		layout.verticalSpacing = 10;//distance between lines
		composite.setLayout(layout);

		
		//limit number of returned lines
		final Label limitLabel = new Label (composite, SWT.CENTER);
		limitLabel.setText("Set limit to the number of returned lines (0 = Unlimited (Might cause memory problems))");
		final Spinner limitSpinner = new Spinner(composite, SWT.NONE);
		// don't allow decimal places
		limitSpinner.setDigits(0);
		// set the minimum value to 0
		limitSpinner.setMinimum(0);
		// set the maximum value to integer max
		limitSpinner.setMaximum(Integer.MAX_VALUE);
		// set the increment value to 1
		limitSpinner.setIncrement(1);
		// set the selection to 250
		limitSpinner.setSelection(250);
		
		// item configuration
		items[7] = new ExpandItem (bar, SWT.NONE, 7);
		items[7].setText("Query Parameters");
		items[7].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[7].setControl(composite);
		items[7].setImage(image);
		
		/*
		 * item 8
		 */
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (8, false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		final Spinner additionalIpSpinner[] = new Spinner[40];
		for (int i=0; i<10; i++){
			new Label (composite, SWT.NONE).setText(i+1+") ");
			for (int j=0; j<4; j++){
				additionalIpSpinner[i*4+j]=new Spinner(composite, SWT.NONE);
				// don't allow decimal places
				additionalIpSpinner[i*4+j].setDigits(0);
				// set the minimum value to 0
				additionalIpSpinner[i*4+j].setMinimum(0);
				// set the maximum value to 255
				additionalIpSpinner[i*4+j].setMaximum(255);
				// set the increment value to 1
				additionalIpSpinner[i*4+j].setIncrement(1);
				// set the selection to 0
				additionalIpSpinner[i*4+j].setSelection(0);
				if (j<3) new Label (composite, SWT.NONE).setText(".");
			}
		}
		Button addIpButton = new Button (composite, SWT.PUSH);
		addIpButton.setText ("Add IP");
		addIpButton.addSelectionListener(new ButtonHandler() {

			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() instanceof Button) {
					
				}
			}


			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
		
		// item configuration
		items[8] = new ExpandItem (bar, SWT.NONE, 8);
		items[8].setText("specific IP's");
		items[8].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[8].setControl(composite);
		items[8].setImage(image);
		
		// Last item
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout ();
		//layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		//layout.verticalSpacing = 10;
		composite.setLayout(layout);
		Button doneButton = new Button (composite, SWT.PUSH);
		doneButton.setText ("Done");
		
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
					
					// Host Name selection
					details.setFirstHostName(hostnameText.getText());
					
					// Host Name 2 selection
					details.setSecondHostName(hostnameText2.getText());
					
					// Port selection
					details.setFirstConnectionPort(Integer.parseInt(portnumberText.getText()));
					
					// Port 2 selection
					details.setSecondConnectionPort(Integer.parseInt(portnumberText2.getText()));
					
					// User Name selection
					details.setFirstUserName((usernameText.getText()));
					
					// User 2 Name selection
					details.setSecondUserName((usernameText2.getText()));
					
					// Password selection
					details.setFirstPassword((passwordText.getText()));
					
					// Password 2 selection
					details.setSecondPassword((passwordText2.getText()));
					
					// 1st schema name selection
					details.setFirstSchemaName(((firstSchemaText.getText())));
					
					// 2nd schema name selection
					details.setSecondSchemaName(((secondSchemaText.getText())));
					
					// table name selection
					details.setResMainTableName(((rawResMainTablenameText.getText())));
					details.setResTraceTableName(rawResTraceTablenameText.getText());
					details.setIpsTblTableName(ipsTblFullTablenameText.getText());
					
					// lines limit selection
					details.setLimit(((limitSpinner.getSelection())));
					
					int counter=0;
					ArrayList<Integer> listIP=new ArrayList<Integer>();
					for (int i=0; i<10; i++){
						if ( 0 < (additionalIpSpinner[i*4].getSelection() +
								additionalIpSpinner[i*4+1].getSelection() +
								additionalIpSpinner[i*4+2].getSelection() +
								additionalIpSpinner[i*4+3].getSelection())){

							counter++;
							listIP.add(additionalIpSpinner[i*4].getSelection());
							listIP.add(additionalIpSpinner[i*4+1].getSelection());
							listIP.add(additionalIpSpinner[i*4+2].getSelection());
							listIP.add(additionalIpSpinner[i*4+3].getSelection());
						}

					}
					details.setAdditionalIp(counter, listIP);
				}
				
				/*MessageBox messageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION);
				messageBox.setText("Please Confirm Your Preferences");
				messageBox.setMessage("");
				*/
				
				MessageBox mainMessageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION |SWT.CANCEL);
				mainMessageBox.setText("Please Confirm Your Preferences");
				mainMessageBox.setMessage(
									"first view choice - "+details.getFirstRadioButton()+"\n\n"+
									"second view choice - "+details.getSecondRadioButton()+"\n\n"+
									"source ip - "+details.getSourceIp()[0]+":"+details.getSourceIp()[1]+":"+details.getSourceIp()[2]+":"+details.getSourceIp()[3]+"\n\n"+
									"time measurement method - "+details.getTimeChoiceRadioButton()+"\n\n"+
									"date of measurement - "+details.getDate()[0]+":"+details.getDate()[1]+":"+details.getDate()[2]+"\n\n"+
									"host 1 name - "+details.getFirstHostName()+"\n\n" +
									"port 1 number - "+details.getFirstConnectionPort()+"\n\n" +
									"user 1 name - "+details.getFirstUserName()+"\n\n" +
									"password 1 - "+details.getFirstPassword()+"\n\n" +
									"host 2 name - "+details.getSecondHostName()+"\n\n" +
									"port 2 number - "+details.getSecondConnectionPort()+"\n\n" +
									"user 2 name - "+details.getSecondUserName()+"\n\n" +
									"password 2 - "+details.getSecondPassword()+"\n\n" +
									"1st schema name - "+details.getFirstSchemaName()+"\n\n" +
									"raw res main table name - "+details.getResMainTableName()+"\n\n" +
									"raw res traceroute table name - "+details.getResTraceTableName()+"\n\n"+
									"2nd schema name - "+details.getSecondSchemaName()+"\n\n" +
									"IPs table name - "+details.getIpsTblTableName()+"\n\n"+
									"line limit - "+details.getLimit()+"\n\n" +
									"additional ip's - \n"+details.getAdditionalIpAsString()+"\n\n"
									);
				if (mainMessageBox.open() == SWT.OK)
				{
					System.out.println("Ok is pressed.");
					String dbReturnd = "DB operation return value has no value yet";

					dbReturnd = DimesDbOperationsMain.startDimesDbOperations(details);
					
					if (!(dbReturnd.equalsIgnoreCase("Success")))
					{
						System.out.println("DB operations returned with error!!");
						System.out.println(dbReturnd);
						//TODO: exit? terminate? notify user with pop-up and let him try again?
					}
					
					try {
		                Runtime rt = Runtime.getRuntime();
		                //Process pr = rt.exec("cmd /c dir");
		                Process pr = rt.exec("d:\\testing java\\runMatlab.cmd");
		 
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
		items[9] = new ExpandItem (bar, SWT.NONE, 9);
		items[9].setText("finish");
		items[9].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[9].setControl(composite);
		items[9].setImage(image);
		
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
		
		return details;
	}

}
