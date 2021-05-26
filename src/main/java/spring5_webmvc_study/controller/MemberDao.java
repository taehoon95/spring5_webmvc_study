package spring5_webmvc_study.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/* 결과가 1행 이상인 경우 */
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select id,email, PASSWORD,name,REGDATE from member where email = ?"
				, new MemberRowMapper()
				, email);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectAll(){
		return jdbcTemplate.query("select id,email, PASSWORD,name,REGDATE from member", new MemberRowMapper());
	}
	
	/* 결과가 1행인 경우 */
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
	}
	
	public void insert(Member member) {
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into member (email, PASSWORD,name,REGDATE) values(?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql,new String[] {"id"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				System.out.println(pstmt);
				return pstmt;
			}
		};
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public void delete(Member member) {
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "delete from member where email = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getEmail());
				System.out.println(member.getEmail());
				return pstmt;
			}
		};
		jdbcTemplate.update(psc);
	}
	
	public void update(Member member) {
		jdbcTemplate.update("update member set name = ?, password = ? where email = ?"
				, member.getName()
				, member.getPassword()
				, member.getEmail());
	}
	
	/* 회원 가입 일자를 기준으로 검색하는 기능구현 */
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		String sql = "select * "  
					   + "  from `member` "  
				       + "where REGDATE between ? and ? " 
				        + "order by REGDATE desc;";
		return jdbcTemplate.query(sql, new MemberRowMapper(),from,to);
	}
	
	public Member selelctById(Long memId) {
		String sql = "select * from member where id = ?";
		List<Member> results = jdbcTemplate.query(sql,new MemberRowMapper(), memId);
		return results.isEmpty() ? null : results.get(0);
	}
	
	
}
