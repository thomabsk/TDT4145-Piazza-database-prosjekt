// package piazza;
// import java.sql.*;
// import java.io.BufferedReader;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.Reader;
// import org.apache.ibatis.jdbc.ScriptRunner;

// public class DatabaseCtrl extends DBConn {
//     public void initSchema(){
//         try{
//         //Initialize the script runner
//         ScriptRunner sr = new ScriptRunner(conn);
//         //Creating a reader object
//         Reader reader = new BufferedReader(new FileReader("project_sql_script.sql"));
//         //Running the script
//         sr.runScript(reader);
//         }
//         catch (Exception e) {
//             System.out.println("db init schema error = "+e);
//             return;
//         }
//     }
// }
