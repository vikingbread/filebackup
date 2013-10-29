package com.viki.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import com.viki.entity.FileInfo;
import com.viki.exception.CopyFileErrorException;
import com.viki.exception.FileNotExistException;
import com.viki.util.FileUtil;

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
	private JProgressBar progressBar = new JProgressBar(0, 10);
	private JButton cancelButton;
	private JButton confirmButton;
	private JLabel targetLabel;
	private JLabel sourceLable;
	private JButton chooseFileTo;
	private JTextField fileTo;
	private JButton chooseFileFrom;
	private JTextField fileFrom;
	private JFileChooser fileChooser;
	private ConfirmHandler confirmHandler;

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
		initEventLisener();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
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

	private void initEventLisener() {
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File source = new File(fileFrom.getText());
				File target = new File(fileTo.getText());
				if (source.isDirectory() && target.isDirectory()) {
					confirmHandler = new ConfirmHandler(source, target);
					confirmButton.setEnabled(false);
					cancelButton.setEnabled(true);

				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirmHandler != null && !confirmHandler.isInterrupted()) {
					confirmHandler.setInterrupted(true);
					confirmHandler = null;
				}
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(false);
			}
		});

	}

	private void selectFile(JTextField fiedl) {
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			fiedl.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}

	class ConfirmHandler implements Runnable {

		private File sourceFile;
		private File toFile;
		private FileInfo fileInfo;
		private boolean isInterrupted = false;
		private int sleepTime = 0;

		public ConfirmHandler(File from, File to) {
			this.sourceFile = from;
			this.toFile = to;
			{
				progressBar.setVisible(true);
				panel.add(progressBar);
				progressBar.setBounds(113, 167, 146, 19);
				progressBar.setStringPainted(true);
			}
			new Thread(this).start();
		}

		@Override
		public void run() {

			initOperation();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (sourceFile.isFile()) {
				return;
			}

			action();//开始拷贝操作
			
			updateState();
		}

		private void action() {
			LinkedList<File> stack = new LinkedList<File>();
			stack.push(sourceFile);
			File[] files;
			int total = 0;
			int sourcePathNameLength = sourceFile.getAbsolutePath().length();
			String fileTail;
			File dest;
			while (!stack.isEmpty() && !isInterrupted) {
				files = stack.pop().listFiles();
				for (File src : files) {
					if (src.isDirectory()) {
						stack.push(src);
					} else {
						//除去sourceFile路径后的地址
						fileTail = src.getAbsolutePath().substring(
								sourcePathNameLength + 1);
						dest = new File(toFile, fileTail);
						startCopy(src, dest);
						progressBar.setValue(++total);
					}
				}
			}
		}

		private void startCopy(File src,File dest) {
			
			try {
				if (!dest.exists()) {
					FileUtil.copyFile(src, dest);
				} else {
					if (src.lastModified() > dest
							.lastModified()) {
						FileUtil.copyFile(src, dest);
					}
				}
			} catch (Exception e) {
				if (e instanceof FileNotExistException) {
					
				}else if(e instanceof CopyFileErrorException) {
					if (toFile.getFreeSpace() < fileInfo.getTotalFileSize()) {
						
					}
				}
			}
			
		}

		private void initOperation() {
			progressBar.setString("正在分析...");
			fileInfo = FileUtil.totalFileInfo(sourceFile);
			progressBar.setMaximum(fileInfo.getTotalFiles());
			progressBar.setString("正在执行...");
		}

		private void updateState() {
			
			if (isInterrupted) {
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				progressBar.setVisible(false);
			} else {
				progressBar.setString("任务完成");
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(false);
			}
		}

		public boolean isInterrupted() {
			return isInterrupted;
		}

		public void setInterrupted(boolean isInterrupted) {
			this.isInterrupted = isInterrupted;
		}

	}
}
