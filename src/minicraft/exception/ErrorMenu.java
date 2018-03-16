package minicraft.exception;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ErrorMenu(Throwable throwable) {
		setTitle("OH NO, An ERROR!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 632, 515);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnCopyError = new JButton("Copy Error");
		btnCopyError.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection select = new StringSelection(textArea.getText());
				clipboard.setContents(select, select);
			}
		});
		btnCopyError.setBounds(10, 535, 117, 23);
		contentPane.add(btnCopyError);

		StringWriter stackTraceWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stackTraceWriter));
		textArea.setText("// I blame you\n\n" + "Exception Description, \"" + throwable.toString() + ", (" + throwable.getMessage() + ")\"\n\n" + stackTraceWriter.toString());
		
		JButton btnAbort = new JButton("Abort");
		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnAbort.setBounds(139, 533, 98, 26);
		contentPane.add(btnAbort);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnContinue.setBounds(249, 533, 98, 26);
		contentPane.add(btnContinue);


	}
}
