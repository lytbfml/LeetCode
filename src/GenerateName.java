import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

/**
 * @author Yangxiao on 10/24/2018.
 */

class GenerateName {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		StringSelection selection = new StringSelection(in.replaceAll(" ", "_"));
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
}
