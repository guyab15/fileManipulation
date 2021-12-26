package fileManipulation;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Actions{
	
	
	
	public void sort(Path p) throws Exception {
		List<String> lines = Util.readFile(p);
		Collections.sort(lines);
		Util.writeFile(lines);
	}
	
	public void shuffle(Path p) throws Exception {
		List<String> lines = Util.readFile(p);
		Collections.shuffle(lines);
		Util.writeFile(lines);
	}
	
	
	public void reverse(Path p) throws Exception {
		List<String> lines = Util.readFile(p);
		Collections.reverse(lines);
		Util.writeFile(lines);
	}
	
	//add more function with same pattern 
	// 
//	public void *action-name*(Path p) throws Exception {
//		List<String> lines = Util.readFile(p);
//		*Manipulation lines*
//		Util.writeFile(lines);
//	}
	
}
