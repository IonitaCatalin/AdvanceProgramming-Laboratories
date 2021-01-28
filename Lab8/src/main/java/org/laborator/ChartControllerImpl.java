package org.laborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartControllerImpl implements ChartController {
    private Database db = Database.getInstance();

    @Override
    public void create(String name) {
        try (Connection conn = db.getConnection()) {
            String sqlQuery = "INSERT INTO charts VALUES (chart_sequence.nextval,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAlbumToChart(Album album, Chart chart) {
        try (Connection conn = db.getConnection()) {
            ChartController chartController = new ChartControllerImpl();
            AlbumController albumController = new AlbumControllerImpl();
            System.out.println(chartController.getChartId(chart)+" "+albumController.getAlbumId(album));
            String sqlQuery = "INSERT INTO chart_albums VALUES (?,(SELECT NVL(MAX(RANKING),0)+1 FROM CHART_ALBUMS WHERE CHART_ID=?),?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1,chartController.getChartId(chart));
            stmt.setInt(2, chartController.getChartId(chart));
            stmt.setInt(3, albumController.getAlbumId(album));
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getChartId(Chart chart) {
        try (Connection conn = db.getConnection()) {
            String sqlQuery = "SELECT id FROM CHARTS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, chart.getName());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
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
    public Chart findByName(String name) {
        Chart chart=null;
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT name FROM CHARTS WHERE name=?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1,name);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                chart = new Chart(result.getString(1));
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chart;

    }

    @Override
    public void create(Chart chart) {
        try (Connection conn = db.getConnection()) {
            String sqlQuery = "INSERT INTO charts VALUES (chart_sequence.nextval,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, chart.getName());
            stmt.executeUpdate();
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chart getRandomChart() {
        Chart chart=null;
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT name FROM (SELECT name FROM CHARTS ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM=1";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                chart = new Chart(result.getString(1));
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chart;
    }
    @Override
    public void printArtistsRanking()
    {
        try( Connection conn = db.getConnection()){
            String sqlQuery = "SELECT arts.name,NVL(SUM(charts.ranking),0) as overall_value FROM CHART_ALBUMS charts JOIN ALBUMS albs ON albs.id=charts.album_id RIGHT OUTER JOIN ARTISTS arts ON arts.id=albs.artist_id GROUP BY arts.id,arts.name ORDER BY overall_value DESC";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            ResultSet result = stmt.executeQuery();
            System.out.println("Artists ranking considering their presence in charts:");
            int counter=1;
            while(result.next()) {
                System.out.println((counter++)+")"+result.getString(1));
            }
            stmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
