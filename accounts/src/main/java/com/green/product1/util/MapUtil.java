/**
 * 
 */
package com.green.product1.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahad
 * @param <T>
 * A until for working with map
 */
public class MapUtil {

	/**
	 * A simple method act just Like java 9 Map.of() method
	 * I am using java 8 and
	 * I could not find same thing in Apache common or in the other libraries
	 * @param <T>
	 * @param args
	 * @return
	 */
	@SafeVarargs
	public static <T> Map<T,T> mapOf(T ... args) {
		if(args.length%2!=0) throw new IllegalArgumentException();
		Map<T,T> map=new HashMap<T, T>(args.length);
		for (int i = 0; i < args.length;i++) {
			map.put(args[i], args[i+1]);
			i++;
		}
		return map;
	}
}
