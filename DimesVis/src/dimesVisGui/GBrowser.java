package dimesVisGui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

public class GBrowser {

	private Shell shell = null;
	private Button button = null;
	private Text text = null;
	private Browser browser = null;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		GBrowser app = new GBrowser();
		app.createShell();
		while (!app.shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void createShell() {
		// create the GUI
		shell = new Shell();
		shell.setText("Browser Example");
		shell.setLayout(new GridLayout(2, false)); 	// layout manager: a grid
													// with 2 unequal columns
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, // horizontal alignment
				SWT.CENTER, // vertical alignment
				true, // grab horizontal space
				false)); // don't grab vertical space

		button = new Button(shell, SWT.NONE);
		button.setText("I'm feeling lucky");
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		browser = new Browser(shell, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, // fill both ways
				false, true, // row grabs vertical space
				2, 1));

		button.addSelectionListener(new SelectionListener() {
			
			@Override public void widgetSelected(SelectionEvent e) {
				String query = text.getText();
				String url = null;
				try {
					url = search(query);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				shell.setText(url);
				browser.setUrl(url);
			}

			@Override public void widgetDefaultSelected(SelectionEvent e) {}
		});

		shell.pack(); // causes the layout manager to lay out the shell
		shell.open(); // opens the shell on the screen
	}

	private static String search(String q) throws IOException, JSONException {

		URL url = new URL(
				"http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q="
						+ URLEncoder.encode(q, "UTF-8"));
		URLConnection connection = url.openConnection();
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		JSONObject json = new JSONObject(builder.toString())
				.getJSONObject("responseData");
		JSONArray jsonArr = json.getJSONArray("results");
		json = (JSONObject) jsonArr.get(0);
		return (String) json.get("url");

	}
}