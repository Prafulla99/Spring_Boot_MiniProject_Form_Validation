package com.pit.service;

import java.util.List;

import com.pit.model.Employee;

public interface IEmployeeMgmtService {
	
	public List<Employee> fetchAllEmployees();
	public String registerEmployee(Employee emp);
	public Employee getEmployeeByNo(int no);
	public String updatEmployee(Employee emp);
	public String deleteEmployee(int no);
	
	public List<Integer> showAllDeptNos();
	
	public boolean isDesgInRejectList(String desg);

}
