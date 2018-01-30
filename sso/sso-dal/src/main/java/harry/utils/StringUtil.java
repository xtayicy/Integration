package harry.utils;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @author harry
 *
 */
public class StringUtil {
	public static String join(final Object[] array, final String separator) {
		return join(Arrays.asList(array), separator);
	}
	
	public static String join(final Iterable<?> it, final String separator) {
		Iterator<?> iterator = null;
		if ((iterator = it.iterator()) == null || !iterator.hasNext()) {
			return null;
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return String.valueOf(first);
		}

		StringBuilder buf = new StringBuilder().append(first);
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(separator).append(obj);
			}
		}
		return buf.toString();
	}
}
