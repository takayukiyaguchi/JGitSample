import java.io.File;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class Main {
	private static String localURL = "/Users/takayuki/works/src/JGitSample-github";
	private static String remoteURL = "https://github.com/takayukiyaguchi/JGitSample.git";

	public static void main(String[] args) {
		test01();
//		test03();
//		test02();
//		test04();
//		test05();
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
			Repository repo = new FileRepository(localURL + "/.git");
			Git git = new Git(repo);
			git.pull().call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test03() {
		try {
			Repository repo = new FileRepository(localURL + "/.git");
			Git git = new Git(repo);
			git.clean().call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test04 () {
		try {
			Repository repo = new FileRepository(localURL + "/.git");
			Git git = new Git(repo);
			Iterable<RevCommit> iterable = git.log().call();

			for (RevCommit rev : iterable) {
				System.out.println(rev.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test05() {
		try {
			Repository repo = new FileRepository(localURL + "/.git");
			Git git = new Git(repo);
			ObjectId old = repo.resolve("93c505fef01908866e64228c6e777f1cd92e4b33" + "^{tree}");
			ObjectId head = repo.resolve("HEAD^{tree}");
			ObjectReader reader = repo.newObjectReader();
			CanonicalTreeParser oldParser = new CanonicalTreeParser();
			CanonicalTreeParser headParser = new CanonicalTreeParser();
			oldParser.reset(reader, old);
			headParser.reset(reader, head);
			List<DiffEntry> diffs = git.diff().setNewTree(headParser).setOldTree(oldParser).call();
			for (DiffEntry diff: diffs) {
				System.out.println(diff.toString());
				System.out.println("  " + diff.getChangeType());
				System.out.println("  " + diff.getNewPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}