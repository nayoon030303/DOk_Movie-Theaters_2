package User;

import java.sql.Statement;

import javax.jws.soap.SOAPBinding.Use;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB_userInfo {
	Connection con;
	Statement st;
	ResultSet rs;
	User user = new User();
	
	//생성자
	public DB_userInfo() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류:"+e.getMessage());
		}
	}
	
	
	//데이터 베이스에 user정보 추가
	public boolean addUser(String userID, String userPassword, String userName, String useryymmdd, String userGender, String userPhone , String userTaste1 ) {
		try {
			String SQL = "INSERT INTO USER (userID, userPassword, userName, useryymmdd, userGender,userPhone, userTaste1) "
					+ "VALUES(\""+userID+"\"," +"\""+ userPassword+"\"," + "\""+userName+"\",\"" + useryymmdd+"\",\""+userGender +"\","+ "\""+userPhone +"\","+ "\""+userTaste1+"\");";
			
			int success = st.executeUpdate(SQL);
				
			//성공
			if(success == 1) {
				return true;
			//실패
			}	
				
		}catch(Exception e) {
			System.out.println("데이터베이스 검색 오류:"+ e.getMessage());
		}
		return false;
	}

	
	//userID와 userPasswor가 일치하는지 확인
	public boolean isUser(String userID, String userPassword) {
		try {
			String SQL = "SELECT * FROM user WHERE userID = '" + userID+ "' and userPassword = '" +userPassword+"'";
					   
			rs = st.executeQuery(SQL);
			
			//일치
			if(rs.next()) {
				return true;
			//불일치
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("데이터베이스 검색 오류:"+ e.getMessage());
		}
		return false;
	}
	public boolean isLogin(String userID) {
		try {
			String SQL = "SELECT * FROM user WHERE userID = \"" + userID+ "\" and isLogin = false;";
			//System.out.println(SQL);
			rs = st.executeQuery(SQL);
			
			//일치
			if(rs.next()) {
				return true;
			//불일치
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("데이터베이스 검색 오류:"+ e.getMessage());
		}
		return false;
	}
	
	//데이터베이스에 있는 user정보 가져오기
	public User getUserInfo(String userID) {
		try {
			String SQL = " SELECT* FROM user WHERE userID =" +"\""+userID+"\"";
					   
			rs = st.executeQuery(SQL);
			//일치
			if(rs.next()) {
				user.setUserID(rs.getString("userID"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUseryymmdd(rs.getString("useryymmdd"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserPhone(rs.getString("userPhone"));
				user.setUserTaste1(rs.getString("userTaste1"));
				user.setUserIsLogin(rs.getBoolean("isLogin"));
				user.setUserProfile(rs.getString("userProfile"));
			//불일치
			}else {
				System.out.println("실패");
			}
		}catch(Exception e) {
			System.out.println("getUserInfo데이터베이스 검색 오류:"+ e.getMessage());
		}
		return user;
		
	}
	
	//업데이트 유저 정보
	public void updateUserInfo(String userID,String userPassword, String userPhone, String taste) {
		try {
			String SQL = "UPDATE user SET userPassword = \"" + userPassword + "\" , userPhone = \"" + userPhone +"\", userTaste1 = \""+taste +"\" WHERE userID = \""+userID+"\";";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
			
			if(success==1) {
				this.user = getUserInfo(userID);
				//System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//프로필 업데이트
	public void updateUserProfile(String userID,String userProfile) {
	      try {
	         String SQL = "UPDATE user SET userProfile = \"" + userProfile +"\" WHERE userID = \""+userID+"\";";
	         //System.out.println(SQL);
	         int success = st.executeUpdate(SQL);    
	         if(success == 1) {
	            this.user = getUserInfo(userID);
	         }else {
	            System.out.println(userProfile);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	   }

	
	//종료시 로그아웃 false
	public void updateIsLogout(String userID) {
		try {
			String SQL = "UPDATE user SET isLogin = false  WHERE userID = \""+userID+"\";";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
			
			if(success==1) {
				this.user = getUserInfo(userID);
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//로그인시 true
	public void updateIsLogin(String userID) {
		try {
			String SQL = "UPDATE user SET isLogin = true  WHERE userID = \""+userID+"\";";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
			
			if(success==1) {
				this.user = getUserInfo(userID);
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
