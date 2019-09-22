package algorithms.string;

import java.util.*;

/**
 * @author Yangxiao on 12/7/2018.
 */
public class Unique_Email_Addresses {
	
	public int numUniqueEmails(String[] emails) {
		if (emails == null || emails.length == 0) {
			return 0;
		}
		int n = emails.length;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			String email = emails[i];
			int atSign = email.indexOf('@');
			String domain = email.substring(atSign);
			String local = email.substring(0, atSign);
			int addSign = email.indexOf('+');
			int dotSign = email.indexOf('.');
			if (addSign == 0) {
				continue;
			}
			if (addSign > 0 && addSign < atSign) {
				local = email.substring(0, addSign);
			}
			if (dotSign < atSign) {
				local = local.replaceAll("\\.", "");
			}
			email = local + domain;
			set.add(email);
		}
		
		return set.size();
	}
	
	public int numUniqueEmails_Sol1(String[] emails) {
		Set<String> seen = new HashSet();
		for (String email: emails) {
			int i = email.indexOf('@');
			String rest = email.substring(i);
			seen.add(rest);
		}
		return seen.size();
	}
	
	public static void main(String[] args) {
		Unique_Email_Addresses uea = new Unique_Email_Addresses();
		String[] s = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		uea.numUniqueEmails(s);
	}
}
