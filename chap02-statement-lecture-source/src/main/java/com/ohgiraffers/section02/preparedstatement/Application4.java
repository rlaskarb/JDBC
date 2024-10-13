package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.mode.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {
        /*index 1 EMPLOYEE 테이블에서 조회할 사원의 성씨를 입력받아
        * 해당하는 성을 가지고 있는 사원의 정보를 모두 출력
        * */
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        List<EmployeeDTO> empList = new ArrayList<>();


        Scanner sc = new Scanner(System.in);
        System.out.print(" 이름의 성을 입력 하세요");
        String emp_Name = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')";



        try {
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1,emp_Name);


            rset = pstmt.executeQuery();
            while(rset.next()){
                emp = new EmployeeDTO();

                emp.setEmpId(rset.getString("EMP_ID"));
                emp.setEmpName(rset.getString("EMP_NAME"));
                emp.setEmpNo(rset.getString("EMP_NO"));
                emp.setEmail(rset.getString("EMAIL"));
                emp.setPhone(rset.getString("phone"));
                emp.setDeptCode(rset.getString("DEPT_CODE"));
                emp.setJobCode(rset.getString("JOB_CODE"));
                emp.setSaLevel(rset.getString("SAL_LEVEL"));
                emp.setSalary(rset.getString("SALARY"));
                emp.setBonus(rset.getString("BONUS"));
                emp.setManagerID(rset.getString("MANAGER_ID"));
                emp.setHireDate(rset.getDate("HIRE_DATE"));
                emp.setEntDate(rset.getDate("ENT_DATE"));
                emp.setEntYn(rset.getString("ENT_YN"));

                empList.add(emp);
            }
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for(EmployeeDTO e : empList){
            System.out.println("emp = " + e );
        }

    }
}
