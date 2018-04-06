package Vista;
import javax.swing.*;

import model.TestClient;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * @author Eduardo Carbajal Reyes
 * 
 */

public class ImplementConsole extends JFrame {
	private static final long serialVersionUID = -6704196808117166844L;
	JTextArea console;
	JScrollPane sp_console;
	Thread hilo;
	JButton btnIniciar;

	boolean continuar = false;

	public ImplementConsole() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setSize(436, 565);
		setTitle("DESCARGA MASIVA DE PFD Y XML");
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		console = new javax.swing.JTextArea();
		console.setWrapStyleWord(true);
		console.setFont(new Font("Monospaced", Font.BOLD, 13));
		console.setForeground(Color.BLACK);
		console.setBackground(SystemColor.control);
		console.setEditable(false);
		console.setColumns(50);
		console.setRows(15);

		console.setText("RESULTADO DEL PROCESO...\n");

		sp_console = new javax.swing.JScrollPane(console);
		sp_console.setBounds(0, 70, 420, 457);

		getContentPane().add(sp_console);

		System.setOut(new PrintStream(new JTextAreaOutputStream(console)));
		System.setErr(new PrintStream(new JTextAreaOutputStream(console)));
		JLabel lblDescargaMasivaDe = new JLabel("DESCARGA MASIVA DE PDF Y XML");
		lblDescargaMasivaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescargaMasivaDe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescargaMasivaDe.setBounds(10, 11, 400, 14);
		getContentPane().add(lblDescargaMasivaDe);
		btnIniciar = new JButton("INICIAR");
		btnIniciar.setBackground(SystemColor.textHighlight);
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnIniciar.getText().equals("INICIAR")) {
					hilo.start();
				} else if (btnIniciar.getText().equals("SALIR")) {
					System.exit(0);
				}
			}
		});
		btnIniciar.setBounds(100, 36, 218, 23);
		getContentPane().add(btnIniciar);

		// starting new Thread for log writing
		hilo = new Thread(new Runnable() {
			public void run() {
				try {
					writeLog();
				} catch (Exception ie) {
				}
			}
		});
	}

	// inner class
	public class JTextAreaOutputStream extends OutputStream {
		JTextArea ta;

		public JTextAreaOutputStream(JTextArea t) {
			super();
			ta = t;
		}

		public void write(int i) {
			ta.append(Character.toString((char) i));
		}

		public void write(char[] buf, int off, int len) {
			String s = new String(buf, off, len);
			ta.append(s);
		}
	}

	// function to write console log
	public void writeLog() throws IOException {

		btnIniciar.setEnabled(false);
		btnIniciar.setText("TRABAJANDO");
		btnIniciar.setBackground(Color.DARK_GRAY);
		btnIniciar.setForeground(Color.WHITE);
		continuar = true;
		TestClient tc = new TestClient();
		tc.Consulta();
		detenerHilo();

		while (continuar) {
			{
				// Make sure the last line is always visible
				console.setCaretPosition(console.getDocument().getLength());

				// just taking pause of 50ms
				try {
					Thread.currentThread();
					Thread.sleep(50);
				} catch (Exception e) {
					System.out.println("Exception in Thread Sleep : " + e);
				}

				// to flush console log after specific number of lines.
				if (console.getLineCount() > 1000)
					console.setText("");
			}
		}
	}
	// for(int i=0; i< 10000; i++)
	//
	// }

	public void detenerHilo() {
		continuar = false;
		btnIniciar.setEnabled(true);
		btnIniciar.setText("SALIR");
		btnIniciar.setBackground(Color.RED);
		btnIniciar.setForeground(Color.WHITE);
	}
}