package com.pj.multipledatabasesdemo;

import org.apache.commons.lang3.StringUtils;

public class StringUtilTest
{
	public static void main(String[] args)
	{
		System.out.println(StringUtils.leftPad("8012428", 9, "0"));
		System.out.println(StringUtils.rightPad(StringUtils.leftPad("8012428", 9, "0"), 10, " "));
	}
}
