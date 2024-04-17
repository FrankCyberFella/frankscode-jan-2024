package com.frank.model.host;

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

import java.util.Objects;

public class Host {

    private String casinoName;
    private int    gamblerId;
    private String roomType;
    private double creditLine;
    private int    avgBet;

    public String getCasinoName() {
        return casinoName;
    }

    public void setCasinoName(String casinoName) {
        this.casinoName = casinoName;
    }

    public int getGamblerId() {
        return gamblerId;
    }

    public void setGamblerId(int gamblerId) {
        this.gamblerId = gamblerId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(double creditLine) {
        this.creditLine = creditLine;
    }

    public int getAvgBet() {
        return avgBet;
    }

    public void setAvgBet(int avgBet) {
        this.avgBet = avgBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host)) return false;
        Host host = (Host) o;
        return gamblerId == host.gamblerId && Double.compare(creditLine, host.creditLine) == 0 && avgBet == host.avgBet && Objects.equals(casinoName, host.casinoName) && Objects.equals(roomType, host.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casinoName, gamblerId, roomType, creditLine, avgBet);
    }

    @Override
    public String toString() {
        return "Host{" +
                "casinoName='" + casinoName + '\'' +
                ", gamblerId=" + gamblerId +
                ", roomType='" + roomType + '\'' +
                ", creditLine=" + creditLine +
                ", avgBet=" + avgBet +
                '}';
    }


} // End of Host POJO
