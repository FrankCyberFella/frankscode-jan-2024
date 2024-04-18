package com.frank.model.host;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

/* Columns in the host table
    +-------------+--------------+------+-----+---------+-------+
    | Field       | Type         | Null | Key | Default | Extra |
    +-------------+--------------+------+-----+---------+-------+
    | casino_name | char(20)     | NO   | PRI | NULL    |       |
    | id          | smallint     | NO   | PRI | NULL    |       |
    | room_type   | char(15)     | NO   |     | NULL    |       |
    | credit_line | decimal(9,2) | NO   |     | NULL    |       |
    | avg_bet     | smallint     | NO   |     | NULL    |       |
    +-------------+--------------+------+-----+---------+-------+
 */
public class JdbcHostDao implements HostDao{

    JdbcTemplate theHostDataBase;

    public JdbcHostDao(BasicDataSource theDataSource) {
        theHostDataBase = new JdbcTemplate(theDataSource);
    }

    @Override
    public Host getAHostEntry(String casinoName, int gamblerId){

        Host entryFromHostTable = null;

        String findAHostRow = " select * from host where casino_name = ? && id = ?";

        SqlRowSet resultFromSelect = theHostDataBase.queryForRowSet(findAHostRow, casinoName, gamblerId);

        if(resultFromSelect.next()) {
            entryFromHostTable = createHostFromSqlResult(resultFromSelect);
        }
        return entryFromHostTable;

    }
    @Override
    public List<Host> getAllHost() {
    // Define object to be returned
        List<Host> allHosts = new ArrayList<Host>();
    // Define a String with SQL statement to be run on the data base amanger
        String getAllHostSQL = "select * from host";
    // Send the Strinh with the SQL to the data base manager and store the result
        SqlRowSet selectResults = theHostDataBase.queryForRowSet(getAllHostSQL);
    // Loop through result set
        while (selectResults.next()) {
            Host aHost = createHostFromSqlResult(selectResults);  // create an object for the current row in the result set
            allHosts.add(aHost);                                  // Add object to List to be returned
        }
    // Return the List of objects
        return allHosts;
    }
    @Override
    public List<Host> getHostByGamblerId(int gamblerId) {
        List<Host> gamblersHosted = new ArrayList();

        String findGamblersInHost = "select * from host where id = ?";

        SqlRowSet gamblersHostRows = theHostDataBase.queryForRowSet(findGamblersInHost, gamblerId);

        while(gamblersHostRows.next()) {
            gamblersHosted.add(createHostFromSqlResult(gamblersHostRows));
        }

        return gamblersHosted;
    }
    @Override
    public List<Host> getHostByCasinoName(String casinoName) {

        List<Host> entries4Casino = new ArrayList<>();

        String searchString = "%" + casinoName+"%";

        String findCasinos = "select * from host where casino_name like ?";

        SqlRowSet casinoRowsInHost = theHostDataBase.queryForRowSet(findCasinos, searchString);

        while(casinoRowsInHost.next()) {
            entries4Casino.add(createHostFromSqlResult(casinoRowsInHost));
        }

        return entries4Casino;
    }
    @Override
    public boolean addHost(Host aHost) {
         boolean rowWasAdded = false;

         String addToHost = " Insert into host "
                          + " (casino_name, id, room_type, credit_line, avg_bet) "
                          + " VALUES(?, ?, ?, ?, ?)";

         try {
             int numRowsAdded = theHostDataBase.update(addToHost
                     , aHost.getCasinoName()
                     , aHost.getGamblerId()
                     , aHost.getRoomType()
                     , aHost.getCreditLine()
                     , aHost.getAvgBet()
             );
             if (numRowsAdded == 1) {
                 rowWasAdded = true;
             }
             else {
                 rowWasAdded = false;
             }
         }
         catch (DataAccessException exceptionObject) {
             rowWasAdded = false;
         }
    return rowWasAdded;
    }
    @Override
    public boolean updateHost(Host aHost) {

        boolean rowWasUpdated = false;

        String updateHost = " update host set room_type   = ? "
                          + "               , credit_line = ? "
                          + "               , avg_bet     = ?"
                          + "   where casino_name = ? && id = ?";
        try {
            int numRowsAdded = theHostDataBase.update(updateHost
                                                    , aHost.getRoomType()
                                                    , aHost.getCreditLine()
                                                    , aHost.getAvgBet()
                                                    , aHost.getCasinoName()
                                                    , aHost.getGamblerId()
            );
            if (numRowsAdded == 1) {
                rowWasUpdated = true;
            }
            else {
                rowWasUpdated = false;
            }
        }
        catch (DataAccessException exceptionObject) {
            rowWasUpdated = false;
        }

        return rowWasUpdated;
    }

    @Override
    public boolean deleteAHostEntry(String casinoName, int gamblerId) {

        boolean rowWasDeleted = false;

        String deletetHostRow = "delete from host where casino_name = ? and id = ?";

        try {
            theHostDataBase.update(deletetHostRow, casinoName, gamblerId);
            rowWasDeleted = true;
        }
        catch (DataAccessException expectionObject) {
            rowWasDeleted = false;
        }
        return rowWasDeleted;
    }

    private Host createHostFromSqlResult(SqlRowSet selectResults) {
        // Define object to be returned
        Host newHost = new Host();

        // Extract data frim SQL result row and populate object to be returned
        newHost.setCasinoName(selectResults.getString("casino_name"));
        newHost.setGamblerId(selectResults.getInt("id"));
        newHost.setRoomType(selectResults.getString("room_type"));
        newHost.setAvgBet(selectResults.getInt("avg_bet"));
        newHost.setCreditLine(selectResults.getDouble("credit_line"));

        // return an object popu;ated with data from teh SQL result row
        return newHost;
    }

}
