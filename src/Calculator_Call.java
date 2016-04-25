//this class determines the operating system and calls a temporary executable
//in place of the calculator


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Calculator_Call {

	//Get operating system as a string
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static void main(String[] args) throws InterruptedException, IOException {

		String commandName = "";

		//set name of cpp program based on operating system
		if (isWindows()) {
			commandName = "src/resources/cpp_executable_win64.exe";
		} else if (isMac()) {
			commandName = "./cpp_executable_unix.exe";
		} else if (isUnix()) {
			commandName = "./resources/cpp_executable_unix.exe";
		} else {
			commandName = "cpp_executable_win64.exe";
		}

		ProcessBuilder pb = new ProcessBuilder(commandName, "");
		Process process = pb.start();
		int errCode = process.waitFor();
		System.out.println("Operating System: " + OS);
		System.out.println("Errors? " + (errCode == 0 ? "No" : "Yes"));
		System.out.println("Output:\n" + output(process.getInputStream()));

	}

	//displays output from the cpp call
	private static String output(InputStream inputStream) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	private static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	private static boolean isUnix() {
		return (OS.indexOf("nux") >= 0);
	}
}//end Calculator_Call
