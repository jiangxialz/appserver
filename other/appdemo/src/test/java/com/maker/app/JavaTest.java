package com.maker.app;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JavaTest {

	@Test
	public void testStrFormat(){
		System.out.println(String.format("{0},{1},{2}", 1,2,3));
	}
	
	@Test
	public void testStrs(){
		String id = "1,2,";
		String[] ids = id.split(",");
		List<String> idList = Arrays.asList(ids);
		System.out.println(idList.size());
		for (int i = 0; i < idList.size(); i++) {
			System.out.println(idList.get(i));
		}
	}
	
}
