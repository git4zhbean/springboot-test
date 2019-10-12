package com.dxy.zhbean.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/12/26
 */
public class CustomFileUtils {

	/**
	 * 获取指定目录下的一级目录列表
	 * @param filePath
	 * @return
	 */
	public static List<File> listDirs4File(String filePath) {
		if (StringUtils.isBlank(filePath.trim())) {
			return null;
		}
		File file = new File(filePath);
		List<File> list = new ArrayList<>();
		for (File file1 : Objects.requireNonNull(file.listFiles())) {
			if (file1.isDirectory()) {
				list.add(file1);
			}
		}
		return list;
	}

}
