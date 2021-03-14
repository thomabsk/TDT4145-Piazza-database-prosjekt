package piazza;

/**
 *
 * @author thomabsk
 */

import java.sql.*;
import java.util.*;

public class thread extends ActiveDomainObject{
    private int threadID;
    private String tag;
    private String color;
    private int folderID;
    private int postID;

    
    @Override
    public void initialize(Connection conn) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void refresh(Connection conn) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void save(Connection conn) {
        // TODO Auto-generated method stub
        
    }
    
}