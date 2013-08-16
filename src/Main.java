import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

public class Main {
	private static String localURL = "/Users/takayuki/works/src/JGitSample-github";
	private static String remoteURL = "https://github.com/takayukiyaguchi/JGitSample.git";

	public static void main(String[] args) {
		test02();
	}

	private static void test01() {
		try {
			Git.cloneRepository().setURI(remoteURL).setDirectory(new File(localURL)).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test02() {
		try {
			Repository repo = new FileRepository(localURL + ".git");
			Git git = new Git(repo);
			git.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}