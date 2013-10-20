package com.viki.main;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * @author Viking
 * @version ����ʱ�䣺2013-10-20 ����09:31:55
 * 
 */
public class Main {
	
	//����զ��
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/"));
		 chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): "
					+ chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : "
					+ chooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
		}
	}

}
