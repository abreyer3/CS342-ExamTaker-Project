
import java.util.*;

public class ScannerFactory
{
	// The private static variable holds the one and only system keyboard Scanner
	private static Scanner keyboardScanner = null;
	
	public static Scanner getKeyboardScanner()
	{
		if (keyboardScanner == null)
		{
			keyboardScanner = new Scanner(System.in);
		}
		return keyboardScanner;
	}
	
	public void finalize()
	{
		keyboardScanner.close();
	}
}
