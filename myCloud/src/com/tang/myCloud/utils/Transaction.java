package com.tang.myCloud.utils;

public interface Transaction extends AbstractDBUtils {
	void start();

	void close();
}
