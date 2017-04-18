/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package com.sliu.framework.app.common.uuid;

import java.security.SecureRandom;

/**
 * @author bsli@starit.com.cn
 * @date 2012-6-4 上午9:33:48
 */
public class RandomUniqueIdGenerator {
	/** The array of printable characters to be used in our random string. */
    private static final char[] PRINTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679"
        .toCharArray();

    /** The default maximum length. */
    private static final int DEFAULT_MAX_RANDOM_LENGTH = 32;

    /** An instance of secure random to ensure randomness is secure. */
    private static SecureRandom randomizer = new SecureRandom();

    public static String getNewString() {
        final byte[] random = getNewStringAsBytes();

        return convertBytesToString(random);
    }
    
    public static String getNewString(int length) {
        final byte[] random = getNewStringAsBytes(length);

        return convertBytesToString(random);
    }

    private static byte[] getNewStringAsBytes() {
    	return getNewStringAsBytes(DEFAULT_MAX_RANDOM_LENGTH);
    }

    private static byte[] getNewStringAsBytes(int maximumRandomLength) {
        final byte[] random = new byte[maximumRandomLength];

        randomizer.nextBytes(random);
        
        return random;
    }

    private static String convertBytesToString(final byte[] random) {
        final char[] output = new char[random.length];
        for (int i = 0; i < random.length; i++) {
            final int index = Math.abs(random[i] % PRINTABLE_CHARACTERS.length);
            output[i] = PRINTABLE_CHARACTERS[index];
        }

        return new String(output);
    }
}
