package algorithms.string;

/**
 * @author Yangxiao Wang on 2019-07-06
 */
public class Defanging_an_IP_Address {
	public String defangIPaddr(String address) {
		return address.replaceAll("\\.", "[\\.]");
	}
}
