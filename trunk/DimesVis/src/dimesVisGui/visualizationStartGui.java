package dimesVisGui;
import java.util.ArrayList;
import java.util.Calendar;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;

import dimesSqlBasics.DimesDbOperationsMain;

public class visualizationStartGui {

	public static void startMenu()
	{
		final Details details=new Details();

		final Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		shell.setMinimumSize(180,300);
		shell.setText("Welcome to Visualization of  Network Distances");
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		ExpandBar bar = new ExpandBar (sashForm, SWT.V_SCROLL);
		bar.setBounds(0, 0, 471, 566);
		ExpandItem items[] = new ExpandItem[11];
		Image image = display.getSystemImage(SWT.ICON_QUESTION);

		/*
		 * item 0
		 */	
		Composite composite = new Composite (bar, SWT.NONE);
		composite.setBounds(0, 0, 400, 500);
		GridLayout layout = new GridLayout (2,false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
		layout.verticalSpacing = 10;//distance between lines
		composite.setLayout(layout);


		//creation of radio buttons
		final Button[] firstbuttonArr=new Button[5];
		
		firstbuttonArr[0] = new Button (composite, SWT.RADIO);
		firstbuttonArr[0].setText ("View angle of <30':<60'");
		firstbuttonArr[1] = new Button (composite, SWT.RADIO);
		firstbuttonArr[1].setText ("View angle of <-90':<0', side map 2D");
		
		Label label_0 = new Label(composite, SWT.BORDER );
		label_0.setImage(new Image(display,"../Images/1.bmp"));
		Label label_1 = new Label(composite, SWT.BORDER );
		label_1.setImage(new Image(display,"../Images/2.bmp"));
		
		Label label_empty = new Label(composite, SWT.CENTER);
		label_empty = new Label(composite, SWT.CENTER );
		
		firstbuttonArr[2] = new Button (composite, SWT.RADIO);
		firstbuttonArr[2].setText ("View angle of <0':<90', flat map 2D");
		firstbuttonArr[3] = new Button (composite, SWT.RADIO);
		firstbuttonArr[3].setText ("View angle of <-30':<30'");
		
		Label label_2 = new Label(composite, SWT.BORDER );
		label_2.setImage(new Image(display,"../Images/3.bmp"));
		Label label_3 = new Label(composite, SWT.BORDER );
		label_3.setImage(new Image(display,"../Images/4.bmp"));
		
		label_empty = new Label(composite, SWT.CENTER );
		label_empty = new Label(composite, SWT.CENTER );
		
		firstbuttonArr[4] = new Button (composite, SWT.RADIO);
		firstbuttonArr[4].setText ("Multiple view angles (all 4)");
		
		label_empty = new Label(composite, SWT.CENTER );
		
		Label label_4 = new Label(composite, SWT.BORDER );
		label_4.setImage(new Image(display,"../Images/5.bmp"));
		
		label_empty = new Label(composite, SWT.CENTER );
		label_empty.setText("For best appearnace choose low limit");
		
		firstbuttonArr[0].setSelection (true);

		// item configuration
		items[0] = new ExpandItem (bar, SWT.NONE, 0);
		items[0].setText("Visual Preferences");
		items[0].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[0].setControl(composite);
		items[0].setImage(image);


		/*
		 * item 1
		 */
		composite = new Composite (bar, SWT.NONE);
		composite.setSize(400, 500);
		layout = new GridLayout (2,false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;//distance from border
		layout.verticalSpacing = 10;//distance between lines
		composite.setLayout(layout);


		//creation of radio buttons
		final Button[] secondbuttonArr=new Button[2];

		secondbuttonArr[0] = new Button (composite, SWT.RADIO);
		secondbuttonArr[0].setText ("Without Info");
		secondbuttonArr[0].setSelection (true);
		secondbuttonArr[1] = new Button (composite, SWT.RADIO);
		secondbuttonArr[1].setText ("With Info Data");
		
		Label label1 = new Label(composite, SWT.BORDER );
		label1.setImage(new Image(display,"../Images/1.bmp"));
		
		Label label2 = new Label(composite, SWT.BORDER );
		label2.setImage(new Image(display,"../Images/6.bmp"));
		
		Label label_info = new Label(composite, SWT.LEFT);
		label_info.setText("*Note: When choosing points with info,\nhaving a large number of points \nmay block some of the plot view");

		// item configuration
		items[1] = new ExpandItem (bar, SWT.NONE, 1);
		items[1].setText("Visual Info Data");
		items[1].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[1].setControl(composite);
		items[1].setImage(image);


		/*
		 * item 2
		 */
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (8, false);
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
		
		Label label_sourcIP = new Label(composite, SWT.CENTER);
		label_sourcIP.setText("   Please choose the source IP");
		

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
			if (i==0) thirdbuttonArr[i].setText (" Best time ");
			if (i==1) thirdbuttonArr[i].setText (" Average time ");
			if (i==2) thirdbuttonArr[i].setText (" Worst time ");

			//set average as default
			if (i == 1) thirdbuttonArr[i].setSelection (true);
		}

		// item configuration
		items[3] = new ExpandItem (bar, SWT.NONE, 3);
		items[3].setText("Time Measurement");
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

		final Button dateCheckBox = new Button(composite, SWT.CHECK);
		dateCheckBox.setSelection(false);
		dateCheckBox.setText("Get results for a specific date?");
		dateCheckBox.setLocation(50,200);
		dateCheckBox.pack();

		new Label (composite, SWT.CENTER);

		Calendar cur_date = Calendar.getInstance();
		final int date[]={cur_date.get(Calendar.DAY_OF_MONTH),cur_date.get(Calendar.MONTH)+1,cur_date.get(Calendar.YEAR)};
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
		items[4].setText("Choose Date");
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
		hostnameLabel.setText ("Enter host name");
		hostnameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		hostnameText.setText("127.0.0.1");

		//port number;
		final Label portnumberLabel;
		final Text portnumberText;
		portnumberLabel = new Label (composite, SWT.CENTER);
		portnumberLabel.setText ("Enter port number");
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
		Calendar cal = Calendar.getInstance();
		int currWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		int currYear = cal.get(Calendar.YEAR);

		final Label firstSchemaLabel;
		final Text firstSchemaText;
		firstSchemaLabel = new Label (composite, SWT.CENTER);
		firstSchemaLabel.setText ("Enter schema name for raw_res_main/traceroute");
		firstSchemaText = new Text(composite, SWT.LEFT | SWT.BORDER);
		firstSchemaText.setText("dimes_results_"+currYear);

		//table name;
		final Label rawResMainTablenameLabel;
		final Text rawResMainTablenameText;
		rawResMainTablenameLabel = new Label (composite, SWT.CENTER);
		rawResMainTablenameLabel.setText ("Enter main results table name");
		rawResMainTablenameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		rawResMainTablenameText.setText("raw_res_main_"+currYear+"_"+currWeekOfYear);

		//table name;
		final Label rawResTraceTablenameLabel;
		final Text rawResTraceTablenameText;
		rawResTraceTablenameLabel = new Label (composite, SWT.CENTER);
		rawResTraceTablenameLabel.setText ("Enter traceroute results table name");
		rawResTraceTablenameText = new Text(composite, SWT.LEFT | SWT.BORDER);
		rawResTraceTablenameText.setText("raw_res_traceroute_"+currYear+"_"+currWeekOfYear);

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
		hostnameLabel2.setText ("Enter host name");
		hostnameText2 = new Text(composite, SWT.LEFT | SWT.BORDER);
		hostnameText2.setText("127.0.0.1");

		//port number;
		final Label portnumberLabel2;
		final Text portnumberText2;
		portnumberLabel2 = new Label (composite, SWT.CENTER);
		portnumberLabel2.setText ("Enter port number");
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
		secondSchemaLabel.setText ("Enter schema name for IPs table");
		secondSchemaText = new Text(composite, SWT.LEFT | SWT.BORDER);
		secondSchemaText.setText("DIMES");

		//table name;
		final Label ipsTblFullTablenameLabel;
		final Text ipsTblFullTablenameText;
		ipsTblFullTablenameLabel = new Label (composite, SWT.CENTER);
		ipsTblFullTablenameLabel.setText ("Enter IPs table name");
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
		final Label limitLabel = new Label (composite, SWT.LEFT);
		limitLabel.setText("Set limit to the number of returned lines (0 = Unlimited (Might cause memory problems))\n\n"
						  +"* Note: Limit does not include additional IPs");
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
		limitSpinner.setSelection(50);
		
		final Button saveFileCheckBox = new Button(composite, SWT.CHECK);
		saveFileCheckBox.setSelection(false);
		saveFileCheckBox.setText("Check to save data file for future use");
		saveFileCheckBox.setLocation(50,200);
		saveFileCheckBox.pack();

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

		//creation of radio buttons
				final Button[] additionalIpRadioButton=new Button[3];

				for (int i=0; i<3; i++) {
					additionalIpRadioButton[i] = new Button (composite, SWT.RADIO);
					switch (i)
					{
						case Details.addIpRadioOptDontUse:
							additionalIpRadioButton[Details.addIpRadioOptDontUse].setText ("Don't use additional IPs");
							break;
						case Details.addIpRadioOptAdd:
							additionalIpRadioButton[Details.addIpRadioOptAdd].setText ("Add as additional IPs");
							break;
						case Details.addIpRadioOptOnlyAdd:
							additionalIpRadioButton[Details.addIpRadioOptOnlyAdd].setText ("Use only these IPs");
							break;
					}
					if (i == 0) additionalIpRadioButton[i].setSelection (true);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
					new Label (composite, SWT.CENTER);
				}
				
				
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

		// item configuration
		items[8] = new ExpandItem (bar, SWT.NONE, 8);
		items[8].setText("Additional Specific IPs");
		items[8].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[8].setControl(composite);
		items[8].setImage(image);

		/*
		 * item 9
		 */
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (8, false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		//creation of radio buttons
		final Button[] excludeIpRadioButton=new Button[2];
		for (int i=0; i<2; i++) {
			excludeIpRadioButton[i] = new Button (composite, SWT.RADIO);
			switch (i)
			{
				case Details.excIpRadioOptDontUse:
					excludeIpRadioButton[Details.excIpRadioOptDontUse].setText ("Don't exclude IPs");
					break;
				case Details.excIpRadioOptUse:
					excludeIpRadioButton[Details.excIpRadioOptUse].setText ("Exclude these IPs");
					break;

			}
			if (i == 0) excludeIpRadioButton[i].setSelection (true);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
			new Label (composite, SWT.CENTER);
		}

		final Spinner excludeIpSpinner[] = new Spinner[40];
		for (int i=0; i<10; i++){
			new Label (composite, SWT.NONE).setText(i+1+") ");
			for (int j=0; j<4; j++){
				excludeIpSpinner[i*4+j]=new Spinner(composite, SWT.NONE);
				// don't allow decimal places
				excludeIpSpinner[i*4+j].setDigits(0);
				// set the minimum value to 0
				excludeIpSpinner[i*4+j].setMinimum(0);
				// set the maximum value to 255
				excludeIpSpinner[i*4+j].setMaximum(255);
				// set the increment value to 1
				excludeIpSpinner[i*4+j].setIncrement(1);
				// set the selection to 0
				excludeIpSpinner[i*4+j].setSelection(0);
				if (j<3) new Label (composite, SWT.NONE).setText(".");
			}
		}

		// item configuration
		items[9] = new ExpandItem (bar, SWT.NONE, 9);
		items[9].setText("Exclude Specific IPs");
		items[9].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[9].setControl(composite);
		items[9].setImage(image);

		// Last item (10)
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout ();
		//layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		//layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		new Label(composite, SWT.CENTER);
		
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
					details.setFirstRadioButton(x,firstbuttonArr[x].getText());

					// second view radio button
					x=-1;
					for (int i=0; i<2; i++){
						if (secondbuttonArr[i].getSelection()==true)x=i;
					}
					details.setSecondRadioButton(x,secondbuttonArr[x].getText());

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

					// if specific date selection
					details.setIsDate(dateCheckBox.getSelection());

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

					// additional ip's
					int additionalcounter=0;
					ArrayList<Integer> additionallistIP=new ArrayList<Integer>();
					for (int i=0; i<10; i++){
						if ( 0 < (additionalIpSpinner[i*4].getSelection() +
								additionalIpSpinner[i*4+1].getSelection() +
								additionalIpSpinner[i*4+2].getSelection() +
								additionalIpSpinner[i*4+3].getSelection())){

							additionalcounter++;
							additionallistIP.add(additionalIpSpinner[i*4].getSelection());
							additionallistIP.add(additionalIpSpinner[i*4+1].getSelection());
							additionallistIP.add(additionalIpSpinner[i*4+2].getSelection());
							additionallistIP.add(additionalIpSpinner[i*4+3].getSelection());
						}

					}
					details.setAdditionalIp(additionalcounter, additionallistIP);

					// additional ip's radio button
					int additionalChoice=-1;
					String additionalInfo="";
					for (int i=0; i<3; i++){
						if (additionalIpRadioButton[i].getSelection()==true) {
							additionalChoice=i;
							additionalInfo=additionalIpRadioButton[i].getText();
						}
					}
					details.setAdditionalIpRadioButton(additionalChoice, additionalInfo);

					// exclude ip's
					int excludecounter=0;
					ArrayList<Integer> excludelistIP=new ArrayList<Integer>();
					for (int i=0; i<10; i++){
						if ( 0 < (excludeIpSpinner[i*4].getSelection() +
								excludeIpSpinner[i*4+1].getSelection() +
								excludeIpSpinner[i*4+2].getSelection() +
								excludeIpSpinner[i*4+3].getSelection())){

							excludecounter++;
							excludelistIP.add(excludeIpSpinner[i*4].getSelection());
							excludelistIP.add(excludeIpSpinner[i*4+1].getSelection());
							excludelistIP.add(excludeIpSpinner[i*4+2].getSelection());
							excludelistIP.add(excludeIpSpinner[i*4+3].getSelection());
						}

					}
					details.setExcludeIp(excludecounter, excludelistIP);

					// exclude ip's radio button
					int excludeChoice=-1;
					String excludeInfo="";
					for (int i=0; i<2; i++){
						if (excludeIpRadioButton[i].getSelection()==true) {
							excludeChoice=i;
							excludeInfo=excludeIpRadioButton[i].getText();
						}
					}
					details.setExcludeIpRadioButton(excludeChoice, excludeInfo);
					
					Calendar currTime = Calendar.getInstance();
					details.setIsSaveFile(saveFileCheckBox.getSelection());
					details.setSaveFileName("../OutputFiles/TimesFile_"+currTime.get(Calendar.DAY_OF_MONTH)+"."+(currTime.get(Calendar.MONTH)+1)+"."+currTime.get(Calendar.YEAR)+"_"+currTime.get(Calendar.HOUR_OF_DAY)+"_"+currTime.get(Calendar.MINUTE)+".txt");

					/*MessageBox messageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION);
					messageBox.setText("Please Confirm Your Preferences");
					messageBox.setMessage("");
					 */

					MessageBox mainMessageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION |SWT.CANCEL);
					mainMessageBox.setText("Please Confirm Your Preferences");
					String msg = "first view choice - "+details.getFirstRadioButtonInfo()+"\n\n"+
							"second view choice - "+details.getSecondRadioButtonInfo()+"\n\n"+
							"source IP - "+details.getSourceIp()[0]+"."+details.getSourceIp()[1]+"."+details.getSourceIp()[2]+"."+details.getSourceIp()[3]+"\n\n"+
							"time measurement method - "+details.getTimeChoiceRadioButton()+"\n\n"+
							"use specific date? - "+details.getIsDate()+"\n\n";
					if (details.getIsDate())
					{
						msg += "date of measurement - "+details.getDate()[0]+"/"+details.getDate()[1]+"/"+details.getDate()[2]+"\n\n";
					}

					msg +=	"host 1 name - "+details.getFirstHostName()+"\n\n" +
							"port 1 number - "+details.getFirstConnectionPort()+"\n\n" +
							"user 1 name - "+((details.getFirstUserName().equals(""))?"Default":details.getFirstUserName())+"\n\n" +
							"password 1 - "+((details.getFirstPassword().equals(""))?"Default":details.getFirstPassword())+"\n\n" +
							"host 2 name - "+details.getSecondHostName()+"\n\n" +
							"port 2 number - "+details.getSecondConnectionPort()+"\n\n" +
							"user 2 name - "+((details.getSecondUserName().equals(""))?"Default":details.getSecondUserName())+"\n\n" +
							"password 2 - "+((details.getSecondPassword().equals(""))?"Default":details.getSecondPassword())+"\n\n" +
							"1st schema name - "+details.getFirstSchemaName()+"\n\n" +
							"raw res main table name - "+details.getResMainTableName()+"\n\n" +
							"raw res traceroute table name - "+details.getResTraceTableName()+"\n\n"+
							"2nd schema name - "+details.getSecondSchemaName()+"\n\n" +
							"IPs table name - "+details.getIpsTblTableName()+"\n\n"+
							"line limit - "+details.getLimit()+"\n\n" +
							"additional ip's choice - "+details.getAdditionalIpRadioButtonInfo()+"\n"+
							(details.getAdditionalIpRadioButton()!=0?((1 > details.getAdditionalIpAsString().length())?"":(details.getAdditionalIpAsString())+"\n"):"") +
							"\n"+"exclude IPs choice - "+details.getExcludeIpRadioButtonInfo()+"\n"+
							(details.getExcludeIpRadioButton()!=0?((1 > details.getExcludeIpAsString().length())?"":(details.getExcludeIpAsString())+"\n\n"):"") +
							(details.getIsSaveFile()?"\n"+"saving data to "+details.getSaveFileName():"");
					mainMessageBox.setMessage(msg);
					
					if (mainMessageBox.open() == SWT.OK)
					{
						boolean dataValid=true;
						int[][] additional,exclude;
						String errormsg="";
						
						additional=details.getAdditionalIp();
						exclude=details.getExcludeIp();
						
						for (int n=0; n<additional.length; n++){
							for (int i=0; i<exclude.length; i++){
								
								//if there is a match between the n'th ip and the i'th ip
								if (((details.getAdditionalIpRadioButton() > 0) && (details.getExcludeIpRadioButton() > 0)) &&
									(additional[n][0]==exclude[i][0])&&(additional[n][1]==exclude[i][1])&&(additional[n][2]==exclude[i][2])&&(additional[n][3]==exclude[i][3])){
									dataValid=false;
									errormsg=errormsg+"You have the same IP in additional IPs, and exclude IPs !!!\n";
									break;
								}
							}
						}
						
						if ((details.getAdditionalIpRadioButton()!=Details.addIpRadioOptDontUse)&&(details.getAdditionalIp().length<1)){
							dataValid=false;
							errormsg=errormsg+"You want to choose additional IPs but didn't choose any !!!\n";
						}
						
						if ((details.getExcludeIpRadioButton()!=Details.excIpRadioOptDontUse)&&(details.getExcludeIp().length<1)){
							dataValid=false;
							errormsg=errormsg+"You chose to exclude IPs but didn't choose any !!!\n";
						}
						
						if (dataValid){
							shell.close();
							System.out.println("Ok is pressed.");
							String dbReturnd = "DB operation return value has no value yet";

							dbReturnd = DimesDbOperationsMain.startDimesDbOperations(details);

							String allDestIPsStringsArray[] = null;

							if (!(dbReturnd.equalsIgnoreCase("Success")))
							{
								System.out.println("DB operations returned with error!!");
								System.out.println(dbReturnd);
								final Shell shell2 = new Shell (display);
								shell2.setMinimumSize(480,600);
								shell2.setText("Error");
								MessageBox errorDBMessageBox = new MessageBox(shell2, SWT.OK |SWT.ICON_INFORMATION);
								errorDBMessageBox.setMessage("DB returned with the following ERROR : "+dbReturnd);
								errorDBMessageBox.open();
							}
							else //DB operations succeeded, open legend and Matlab
							{
								allDestIPsStringsArray = DimesDbOperationsMain.getDestStringsArray();
								try {
									Runtime rt = Runtime.getRuntime();
									Process pr = rt.exec("../runMatlab.cmd");

									if (null != allDestIPsStringsArray)
									{
										DialogLegend.runDialogLegend(display, allDestIPsStringsArray);
									}

									int exitVal = pr.waitFor();
									System.out.println("Exited with error code "+exitVal);

								} catch(Exception exception) {
									System.out.println(exception.toString());
									exception.printStackTrace();
								}
							}
						}
						else{//dataValid==false
							MessageBox ErrorMessageBox = new MessageBox(shell, SWT.OK |SWT.ICON_INFORMATION);
							ErrorMessageBox.setText("Error in inputs");
							errormsg=errormsg+"\n\nPlease try again ...";
							ErrorMessageBox.setMessage(errormsg);
							ErrorMessageBox.open();
						}
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		items[10] = new ExpandItem (bar, SWT.NONE, 10);
		items[10].setText("Finish");
		items[10].setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		items[10].setControl(composite);
		items[10].setImage(image);
		
		items[10].setExpanded(true);

		bar.setSpacing(8);

		shell.setSize(480, 600);
		shell.open();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
		image.dispose();
		display.dispose();
		
		return;
	}

}
