package org.learn.app.manager;

import org.learn.app.App;
import org.learn.app.entity.MaterialEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialEntityManager {
    public static void insert(MaterialEntity material) throws SQLException {
        Connection c = App.getConnection();
        String sql = "INSERT INTO Material(Title, CountInPack, Unit, CountInStock, MinCount, Description, Cost, Image, MaterialType) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, material.getTitle());
        ps.setInt(2, material.getCountInPack());
        ps.setString(3, material.getUnit());
        ps.setInt(4, material.getCountInStock());
        ps.setInt(5, material.getMinCount());
        ps.setString(6, material.getDescription());
        ps.setDouble(7, material.getCost());
        ps.setString(8, material.getImagePath());
        ps.setString(9, material.getMaterialType());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if(keys.next()){
            material.setId(keys.getInt(1));
            return;
        }
        throw new SQLException("Entity not added");
    }

    public static void update(MaterialEntity material) throws SQLException {
        Connection c = App.getConnection();
        String sql = "UPDATE Material SET Title=?, CountInPack=?, Unit=?, CountInStock=?, MinCount=?, Description=?, Cost=?, Image=?, MaterialType=? WHERE ID=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, material.getTitle());
        ps.setInt(2, material.getCountInPack());
        ps.setString(3, material.getUnit());
        ps.setInt(4, material.getCountInStock());
        ps.setInt(5, material.getMinCount());
        ps.setString(6, material.getDescription());
        ps.setDouble(7, material.getCost());
        ps.setString(8, material.getImagePath());
        ps.setString(9, material.getMaterialType());
        ps.setInt(10, material.getId());
        ps.executeUpdate();
    }

    public static List<MaterialEntity> selectAll() throws SQLException {
        Connection c = App.getConnection();
        String sql = "SELECT * FROM Material";
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(sql);
        List<MaterialEntity> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new MaterialEntity(
                    rs.getInt("ID"),
                    rs.getString("Title"),
                    rs.getInt("CountInPack"),
                    rs.getString("Unit"),
                    rs.getInt("CountInStock"),
                    rs.getInt("MinCount"),
                    rs.getString("Description"),
                    rs.getDouble("Cost"),
                    rs.getString("Image"),
                    rs.getString("MaterialType")
            ));
        }
        return list;
    }

    public static void delete(MaterialEntity material) throws SQLException {
        Connection c = App.getConnection();
        String sql = "DELETE FROM Material WHERE ID=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, material.getId());
        ps.executeUpdate();
    }
}
