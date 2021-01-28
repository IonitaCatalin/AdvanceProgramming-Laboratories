package org.laborator;

import java.sql.*;

public class ArtistControllerImpl implements ArtistController {
    private Database db = Database.getInstance();
    @Override
    public void create(String name, String country) {
        try(Connection conn = db.getConnection()) {

            String sqlQuery = "INSERT INTO artists VALUES (artists_sequence.nextval,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setString(2, country);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Artist findByName(String name) {
        Artist artist=null;
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT id,name,country FROM ARTISTS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,name);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                artist = new Artist(result.getInt(1),result.getString(2), result.getString(3));
                return artist;
            }
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    @Override
    public int getArtistId(Artist artist) {
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT id FROM ARTISTS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,artist.getName());
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
               return result.getInt(1);
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void create(Artist artist) {
        try(Connection conn = db.getConnection()) {
            String sqlQuery = "INSERT INTO artists VALUES (artists_sequence.nextval,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, artist.getName());
            stmt.setString(2, artist.getCountry());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
