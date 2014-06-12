package poc.mockito;

public class InterfaceForTestImpl implements InterfaceForTest {

	private String stringValue;

	public void setStringValue(String value) {
		this.stringValue = value;
	}

	public String getStringValue() {
		return stringValue;
	}

	public int getIntValue() {
		return 156;
	}

	public void actionMethod() {
		System.out.println("RealImplementation: process action");
	}

	public String processMethod(String input) throws Exception {
		System.out.println("RealImplementation: process method, parameter [" + input + "]");
		return "response" + "." + String.valueOf(System.currentTimeMillis());
	}

}
