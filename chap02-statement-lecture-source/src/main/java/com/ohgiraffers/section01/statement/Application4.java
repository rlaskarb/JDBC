package com.ohgiraffers.section01.statement;

import com.ohgiraffers.mode.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {

        /*title 전체 사원 정보를 EmployeeDTO 를 통해 객체에 담아서 출력*/


        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        // 회원 한명의 정보를 담을 DTO
        EmployeeDTO emp = null;
        // 한명의 정보들을 하나의 인스턴스로 묶기위한 List
        List<EmployeeDTO> empList = null;


        String query = "SELECT * FROM EMPLOYEE ";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<>();

            /*조회한 결과를 객체에 담기 */
            while (rset.next()) {
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

            } catch(SQLException e){
                throw new RuntimeException(e);
            }finally{
                close(con);
                close(rset);
                close(stmt);

            }
        for(EmployeeDTO oneEmployee : empList){
            System.out.println("oneEmployee = " + oneEmployee);
        }

    }
}



