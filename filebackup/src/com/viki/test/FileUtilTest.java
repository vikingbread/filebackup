package com.viki.test;

import java.io.File;
import org.junit.Test;
import com.viki.util.FileUtil;

/**
 * @author Viking
 * @version 创建时间：2013-10-29 上午09:00:50
 * 
 */
public class FileUtilTest {

	@Test
	public void testTotalAllFiles() {
		File directory = new File("E:\\books");
		System.out.println(directory.getAbsolutePath());
		System.out.println(directory.getFreeSpace() / 1024 / 1024 / 1024);
		System.out
				.println(FileUtil.totalFileInfo(directory).getTotalFileSize() / 1024 / 1024 / 1024);
	}

}
