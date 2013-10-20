package com.viki.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel panel;
	private JButton cancelButton;
	private JButton confirmButton;
	private JLabel targetLabel;
	private JLabel sourceLable;
	private JButton chooseFileTo;
	private JTextField fileTo;
	private JButton chooseFileFrom;
	private JTextField fileFrom;
	private JFileChooser fileChooser;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame inst = new MainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public MainFrame() {
		super();
		setTitle("欢迎使用,文件拷贝系统");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("/"));
			fileChooser
					.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.setAcceptAllFileFilterUsed(false);
			{
				panel = new JPanel();
				getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				{
					fileFrom = new JTextField();
					panel.add(fileFrom);
					fileFrom.setText(".");
					fileFrom.setBounds(123, 78, 119, 22);
					fileFrom.setEditable(false);
				}
				{
					chooseFileFrom = new JButton();
					panel.add(chooseFileFrom);
					chooseFileFrom.setText("\u8bf7\u9009\u62e9");
					chooseFileFrom.setBounds(268, 78, 75, 22);
					chooseFileFrom.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							selectFile(fileFrom);
						}
					});
				}
				{
					fileTo = new JTextField();
					panel.add(fileTo);
					fileTo.setText(".");
					fileTo.setBounds(123, 129, 119, 22);
					fileTo.setEditable(false);
				}
				{
					chooseFileTo = new JButton();
					panel.add(chooseFileTo);
					chooseFileTo.setText("\u8bf7\u9009\u62e9");
					chooseFileTo.setBounds(268, 129, 75, 22);
					chooseFileTo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							selectFile(fileTo);
						}
					});
				}
				{
					sourceLable = new JLabel();
					panel.add(sourceLable);
					sourceLable.setText("\u6e90\u5730\u5740\uff1a");
					sourceLable.setBounds(64, 78, 49, 21);
				}
				{
					targetLabel = new JLabel();
					panel.add(targetLabel);
					targetLabel.setText("\u76ee\u6807\u5730\u5740\uff1a");
					targetLabel.setBounds(52, 129, 61, 21);
				}
				{
					confirmButton = new JButton();
					panel.add(confirmButton);
					confirmButton.setText("\u786e\u5b9a");
					confirmButton.setBounds(123, 196, 57, 23);
				}
				{
					cancelButton = new JButton();
					panel.add(cancelButton);
					cancelButton.setText("\u53d6\u6d88");
					cancelButton.setBounds(218, 196, 57, 23);
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void selectFile(JTextField fiedl){
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			fiedl.setText(fileChooser.getSelectedFile()
					.getAbsolutePath());
		}		 
	}
}
