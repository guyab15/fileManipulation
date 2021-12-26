package fileManipulation;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Util {
	public static String manipulationFileName;

	public static List<String> readFile(Path path) throws Exception {
		List<String> lines = null;
		lines = Files.readAllLines(path);

		return lines;

	}

	public static void writeFile(List<String> lines) throws Exception {
		Path p = Paths.get(System.getProperty("user.dir").concat("/").concat(manipulationFileName).concat(".txt"));
		Files.write(p, lines, Charset.defaultCharset());

	}
	
	public static void save(String FileName,String manipuliationFileName,String Action) throws Exception {
		
			
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/repository";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "pass");
		    
		     
		      Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		      
		      String query = " insert into actions (file_name, manipuliation_file_name, action, time_stamp)"
		        + " values (?, ?, ?, ?)";

		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, FileName);
		      preparedStmt.setString (2, manipuliationFileName);
		      preparedStmt.setString (3, Action);
		      preparedStmt.setTimestamp (4, ts);
		      preparedStmt.execute();
		      
		      conn.close();
		
	}
	
}
