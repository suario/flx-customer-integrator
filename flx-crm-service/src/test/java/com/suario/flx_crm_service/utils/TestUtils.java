/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.utils;

import java.util.Random;

public class TestUtils {

	public static String getRandomString(int targetStringLength) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength + 1)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
