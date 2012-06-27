package dimesVisGui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class ShellWithLabelAndTextField3 {
	
	private Label l;
	private Text t;
	
	public static void main(String[] args) {
		ShellWithLabelAndTextField3 shell = new ShellWithLabelAndTextField3();
		shell.createShell();
	}
	
	public void createShell() {
		Display display = new Display ();
		Shell shell = new Shell (display);
		
		GridLayout gl = new GridLayout();
		shell.setLayout(gl);
		
		l = new Label (shell, SWT.CENTER);
		l.setText ("Type text and press [ENTER]");

		t = new Text(shell, SWT.LEFT | SWT.BORDER);
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					l.setText(t.getText());
					t.setText("");
				}
			}
		});
	
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
