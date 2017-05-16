package bh.gov.cio.gbs.util;

public class CPRUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("is Valid " + isCPRValid(821313306));

	}

	public static Boolean isCPRValid(Integer cprnumber) {

		try {

			String cprString = getStringCpr(cprnumber);

			String withOutCheckDigit = cprString.substring(0, 8);

			String checkDigit = calculateCheckDigit(withOutCheckDigit);

			if (cprString.endsWith(checkDigit)) {

				String lastNumbers = cprString.substring(4, 9);

				char[] cs = lastNumbers.toCharArray();

				char same = ' ';

				int count = 1;
				int countTop = 0;

				for (char c : cs) {
					if (same == c)
						count++;
					else
						same = c;

					if (count > countTop)
						countTop = count;
				}

				return true;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public static String calculateCheckDigit(String withOutCheckDigit) {
		int total = 0, value, check;
		for (int i = 9; i > 1; i--) {
			total += Integer.parseInt(withOutCheckDigit
					.substring(9 - i, 10 - i)) * i;
		}
		value = total % 11 == 1 ? 0 : total % 11;
		if (value > 0)
			check = 11 - value;
		else
			check = 0;
		return String.valueOf(check);
	}

	public static String getStringCpr(Integer cpr) {

		if (cpr == null)
			return "";

		cpr = 1000000000 + cpr;

		String stringCpr = cpr.toString().substring(1);

		return stringCpr;
	}
}
