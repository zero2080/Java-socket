package org.example.api.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.example.api.dto.Member;

public class MemberRepository {

  private static MemberRepository INSTANCE;

  private static String DATABASE_URL = "jdbc:mysql://localhost:3306/java_prac";
  private static String USERNAME = "root";
  private static String PASSWORD = "robotry";
  private static String DRIVER = "com.mysql.cj.jdbc.Driver";
  private Connection conn;
  private Statement stmt;

  private MemberRepository(){
    try {
      Class.forName(DRIVER);
      conn = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
      stmt = conn.createStatement();
    } catch (ClassNotFoundException
             | SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static MemberRepository getInstance(){
    if(INSTANCE == null){
      INSTANCE = new MemberRepository();
    }
    return INSTANCE;
  }

  public List<Member> findAll(){
    String query = "SELECT * FROM member";
    ResultSet rs;
    List<Member> result = new ArrayList<>();
    try {
      rs = stmt.executeQuery(query);
      while(rs.next()){
        UUID id = UUID.fromString(rs.getString("id"));
        String loginId = rs.getString("login_id");
        String password = rs.getString("password");
        String nickname = rs.getString("nickname");

        Member member = new Member();
        member.setId(id);
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setNickname(nickname);

        result.add(member);
      }

    }catch(Exception e){
      e.printStackTrace();
    }

    return result;
  }
}
