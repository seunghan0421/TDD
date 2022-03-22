package me.develop_han.TDD_start;

public class PasswordStrengthMeter {

	public PasswordStrength meter(String s) {
		if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

		int metCounts = 0;
		if (s.length() >= 8) metCounts++;
		if (meetsContainingNumberCriteria(s)) metCounts++;
		if (meetsContainingUppercaseCreteria(s)) metCounts++;

		if(metCounts == 1) return PasswordStrength.WEEK;
		if(metCounts == 2) return PasswordStrength.NORMAL;
		return PasswordStrength.STRONG;
	}

	private boolean meetsContainingUppercaseCreteria(String s) {
		for (char ch : s.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}

	private boolean meetsContainingNumberCriteria(String s) {
		for (char ch : s.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
		}
		return false;
	}
}
