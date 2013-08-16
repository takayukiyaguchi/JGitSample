import java.io.File;

import org.eclipse.jgit.api.Git;

public class Main {
	private static String localURL = "/Users/takayuki/works/src/JGitSample-github";
	private static String remoteURL = "https://github.com/takayukiyaguchi/JGitSample.git";

	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test01() {
		try {
			Git.cloneRepository().setURI(remoteURL).setDirectory(new File(localURL)).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}