package com.scc.dao;

import com.scc.model.AllocationEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllocationDao {

    public void insertAllocation(String studentName, String building, String roomNo, double monthlyRent) {
        String sql = "INSERT INTO allocations (student_name, building, room_no, monthly_rent) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentName);
            pstmt.setString(2, building);
            pstmt.setString(3, roomNo);
            pstmt.setDouble(4, monthlyRent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting allocation: " + e.getMessage());
        }
    }

    public List<AllocationEntity> getAllAllocations() {
        List<AllocationEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM allocations";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new AllocationEntity(
                        rs.getInt("id"),
                        rs.getString("student_name"),
                        rs.getString("building"),
                        rs.getString("room_no"),
                        rs.getDouble("monthly_rent")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving allocations: " + e.getMessage());
        }
        return list;
    }
}