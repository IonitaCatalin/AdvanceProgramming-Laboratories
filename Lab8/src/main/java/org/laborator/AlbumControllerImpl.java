package org.laborator;
import java.sql.*;

public class AlbumControllerImpl implements AlbumController {
    private Database db=Database.getInstance();

    @Override
    public int getAlbumId(Album album) {
        try( Connection conn = db.getConnection()) {
            String sqlQuery = "SELECT id FROM ALBUMS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, album.getName());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
            stmt.close();
        }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void create(String name, int artistId, int releaseYear)
    {
        try(Connection conn = db.getConnection()) {

            String sqlQuery = "INSERT INTO albums VALUES (albums_sequence.NEXTVAL,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setInt(2, artistId);
            stmt.setInt(3,releaseYear);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Album findByArtist(int artistId)
    {
        Album album=null;
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT id,artist_id ,name,release_year FROM ALBUMS WHERE artist_id=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1,artistId);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                album = new Album(result.getInt(2),result.getString(3), result.getInt(4));
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    @Override
    public Album findByName(String name) {
        Album album=null;
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT id,artist_id ,name,release_year FROM ALBUMS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,name);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                album = new Album(result.getInt(2),result.getString(3), result.getInt(4));
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return album;
    }

    @Override
    public void create(Album album) {
        try(Connection conn = db.getConnection()) {
            System.out.println(album.toString());
            String sqlQuery = "INSERT INTO albums VALUES (albums_sequence.NEXTVAL,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,album.getName());
            stmt.setInt(2, album.getArtistId());
            stmt.setInt(3,album.getReleaseYear());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
