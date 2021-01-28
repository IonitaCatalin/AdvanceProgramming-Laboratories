package com.rest.demo.controllers;

import com.rest.demo.models.ChangeNameModel;
import com.rest.demo.models.Player;
import com.rest.demo.services.Database;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private static final String SECRET = "4ba3a0b8801218417e8324445daf9d82bc0f6cc51a5824bef732f8130920b892a8b83b1c457098e8f8e14f6a3324198e8cf3a5d6516d021ea8fb308f8a2043b9";

    @PostMapping(value="/register",produces = "application/json",consumes = "application/json")
    public Map<String,String> register(@RequestBody Player player)
    {
        Database db=Database.getInstance();
        try( Connection conn = db.getConnection()) {
            String checkPlayerName="SELECT COUNT(ID) FROM PLAYER WHERE name=? ";
            PreparedStatement checkPlayerStmt=conn.prepareStatement(checkPlayerName);
            checkPlayerStmt.setString(1,player.getName());
            ResultSet nameCountSet=checkPlayerStmt.executeQuery();
            if(nameCountSet.next()) {
                if (nameCountSet.getInt(1) != 0) {
                    Map<String, String> response = new HashMap<String, String>();
                    response.put("status", "error");
                    response.put("message", "Username already taken");
                    return response;
                }
            }
            String addQuery = "INSERT INTO PLAYER VALUES(PLAYER_SEQUENCE.NEXTVAL,?,?)";
            PreparedStatement addPlayerStmt = conn.prepareStatement(addQuery);
            addPlayerStmt.setString(1, player.getName());
            addPlayerStmt.setString(2, player.getPassword());
            addPlayerStmt.executeQuery();
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "success");
            response.put("message", "Player added succesfully");
            return response;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @DeleteMapping(produces = "application/json")
    public Map<String,String> deletePlayer(@RequestHeader("Auth-Token") String token)
    {
        int id;
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token).getBody();
            System.out.println(claims);
            id = Integer.parseInt(claims.getId());

        }catch(Exception e){
            Map<String,String> response=new HashMap<>();
            response.put("status","error");
            response.put("message","Invalid or missing authorization token");
            return response;
        }
        Database db=Database.getInstance();
        try( Connection conn = db.getConnection()) {
            String checkPlayerQuery="SELECT COUNT(ID) FROM PLAYER WHERE ID=?";
            PreparedStatement checkPlayerStmt=conn.prepareStatement(checkPlayerQuery);
            checkPlayerStmt.setInt(1,id);
            ResultSet playerCountSet=checkPlayerStmt.executeQuery();
            if(playerCountSet.next()) {
                if (playerCountSet.getInt(1) == 0) {
                    Map<String, String> response = new HashMap<String, String>();
                    response.put("status", "error");
                    response.put("message", "There is no user with the specified id");
                    return response;
                }
            }
            String deleteQuery="DELETE FROM PLAYER WHERE ID=?";
            PreparedStatement deleteStmt=conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1,id);
            deleteStmt.executeQuery();
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "success");
            response.put("message", "User deleted succesfully");
            return response;

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @PutMapping(value="/",produces = "application/json",consumes = "application/json")
    public Map<String,String> changePlayerName(@RequestBody ChangeNameModel name,@RequestHeader("Auth-Token") String token) {
        int id;
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token).getBody();
            System.out.println(claims);
            id = Integer.parseInt(claims.getId());
        }catch(Exception e){
            Map<String,String> response=new HashMap<>();
            response.put("status","error");
            response.put("message","Invalid or missing authorization token");
            return response;
        }
        Database db = Database.getInstance();
        try (Connection conn = db.getConnection()) {
            String checkPlayerQuery = "SELECT COUNT(ID) FROM PLAYER WHERE ID=?";
            PreparedStatement checkPlayerStmt = conn.prepareStatement(checkPlayerQuery);
            checkPlayerStmt.setInt(1, id);
            ResultSet playerCountSet = checkPlayerStmt.executeQuery();
            if (playerCountSet.next()) {
                if (playerCountSet.getInt(1) == 0) {
                    Map<String, String> response = new HashMap<String, String>();
                    response.put("status", "error");
                    response.put("message", "There is no user with the specified id");
                    return response;
                }
            }
            String checkIfAvailb="SELECT COUNT(ID) FROM PLAYER WHERE name=?";
            PreparedStatement checkIfAvailbStmt=conn.prepareStatement(checkIfAvailb);
            checkIfAvailbStmt.setString(1,name.getName());
            ResultSet checkIfAlailbSet=checkIfAvailbStmt.executeQuery();
            if(checkIfAlailbSet.next()) {
                if (checkIfAlailbSet.getInt(1) != 0) {
                    Map<String, String> response = new HashMap<String, String>();
                    response.put("status", "error");
                    response.put("message", "Name is already taken");
                    return response;
                }
            }

            String changeQuery = "UPDATE PLAYER SET name=? WHERE ID=?";
            PreparedStatement changeStmt = conn.prepareStatement(changeQuery);
            changeStmt.setString(1,name.getName());
            changeStmt.setInt(2,id);
            changeStmt.executeQuery();
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "success");
            response.put("message", "Name updated succesfully");
            return response;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping(produces = "application/json")
    public Map<String,List<Map<String,String>>> getAllPlayers(@RequestHeader("Auth-Token") String token)
    {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token).getBody();

        }catch(Exception e){
            Map<String,String> response=new HashMap<>();
            response.put("status","error");
            response.put("message","Invalid or missing authorization token");
            return null;
        }
        Database db=Database.getInstance();
        try (Connection conn = db.getConnection()) {
            String getPlayersQuery="SELECT NAME FROM PLAYER";
            PreparedStatement getPlayersStmt=conn.prepareStatement(getPlayersQuery);
            ResultSet players=getPlayersStmt.executeQuery();
            List<Map<String,String>> details=new ArrayList<>();
            Map<String,List<Map<String,String>>> response =new HashMap<>();
            while(players.next())
            {
                Map<String,String> player=new HashMap<String,String>();
                player.put("player",players.getString(1));
                details.add(player);
            }
            response.put("players",details);
            return response;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value="/login",produces = "application/json",consumes = "application/json")
    public Map<String,String> login(@RequestBody Player player) {
        Database db=Database.getInstance();
        try (Connection conn = db.getConnection()) {
            String loginQuery="SELECT COUNT(ID),ID FROM PLAYER WHERE NAME=? AND PASSWORD=? GROUP BY ID";
            PreparedStatement loginStmt=conn.prepareStatement(loginQuery);
            loginStmt.setString(1,player.getName());
            loginStmt.setString(2,player.getPassword());
            ResultSet loginCount=loginStmt.executeQuery();
            if(loginCount.next())
            {
                    int playerId=loginCount.getInt(2);
                    System.out.println("Player:"+player+" "+playerId);
                    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
                    long nowMillis = System.currentTimeMillis();
                    Date now = new Date(nowMillis);
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
                    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
                    JwtBuilder builder=Jwts.builder().setId(String.valueOf(playerId)).setSubject("Authorization").setIssuer("Laborator").signWith(signatureAlgorithm,signingKey);
                    long expMillis = nowMillis + 1000000L;
                    Date exp = new Date(expMillis);
                    builder.setExpiration(exp);

                    Map<String,String> response=new HashMap<>();
                    response.put("status","success");
                    response.put("token",builder.compact());
                    return response;
            }
            else
            {
                    Map<String,String> response=new HashMap<String,String>();
                    response.put("status","error");
                    response.put("message","Username or password are incorrect");
                    return response;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

