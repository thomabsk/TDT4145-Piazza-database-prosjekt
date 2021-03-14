// package piazza;
// import java.sql.*;
// public class DatabaseCtrl {
    

//     public ResultSet executeQuery(Connection conn, String query, Object... o){
//         try{
//         PreparedStatement execQuery = conn.prepareStatement(query);
//         int it = 1;
//         for (Object obj : o){
//             if(obj instanceof Integer){
//                 execQuery.setInt(it,(Integer) obj);
//             }
//             if(obj instanceof String){
//                 execQuery.setString(it, (String) obj);
//             }

//             it += 1;
//         }
//         execQuery.executeQuery();
//         ResultSet rs = execQuery.getResultSet();
//         } catch (Exception e) {
//             System.out.println("db error during making of post= "+e);
//             ResultSet rs_fail = new ResultSet;
//             return rs;
//         }

//     }

// }
