package fileManipulation;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.nio.file.Path;

public class Run {
	public static void main(String[] args) {
		
		if (args.length < 3) {
			System.err.println("missing input, please insert: \n 1.file name. \n 2.name for new file. \n 3.action. ");
			System.exit(0);
		}
		
		String txtFileName = args[0];
		File file = new File(System.getProperty("user.dir"));
		File[] matchingFiles = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(txtFileName) && name.endsWith("txt");
			}
		});

		if (matchingFiles.length == 0) {
			System.err.println("the file " + txtFileName + " not found in current directory");
			System.exit(0);
		}
		Path path = matchingFiles[0].toPath();

		String manipulationFileName = args[1];
		if (manipulationFileName.trim().isEmpty()) {
			System.err.println("the name for new file is empty ");
		}
		Util.manipulationFileName = manipulationFileName;

		String actionName = args[2];
		Actions action = new Actions();
//		System.out.println(Arrays.deepToString(action.getClass().getMethods()));
		Method method = null;
		try {
			method = Actions.class.getDeclaredMethod(actionName, Path.class);
		} catch (NoSuchMethodException e) {
			System.err.println("the action " + actionName + " is missing");
			System.exit(0);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}

		try {
			method.invoke(action, path);
			System.out.println("the manipuliation file crated");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		try {
			Util.save(txtFileName, manipulationFileName, actionName);
		} catch (Exception e) {
			System.err.println("faild to insert row to db");
			System.out.println(e.getMessage());
		}

	}

}
