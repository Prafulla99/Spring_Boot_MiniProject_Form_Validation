package com.pit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pit.model.Employee;
import com.pit.repository.IDepartmentRepository;
import com.pit.repository.IEmployeeRepository;

@Service
public class IEmployeeMgmtImpl implements IEmployeeMgmtService {

	@Autowired
	private IEmployeeRepository iempRepo;
	
	@Autowired
	private IDepartmentRepository ideptRepo;

	@Override
	public List<Employee> fetchAllEmployees() {

		return iempRepo.findAll();
	}

	@Override
	public String registerEmployee(Employee emp) {

		int idVal = iempRepo.save(emp).getEmpno();

		return "Employeee is Saved with idval :: " + idVal;
	}

	@Override
	public Employee getEmployeeByNo(int no) {
         
		Employee emp = iempRepo.getReferenceById(no);
		return emp;
	}

	@Override
	public String updatEmployee(Employee emp) {
		
		  Optional<Employee> opt = iempRepo.findById(emp.getEmpno());
		  
		  if(opt.isPresent()) {
			  
			  iempRepo.save(emp);
			  return emp.getEmpno()+" Employee is Updated";
		  }
		
		return emp.getEmpno()+"Employee is not available for the updation";
	}

	@Override
	public String deleteEmployee(int no) {
		
		iempRepo.deleteById(no);
		return "Employee is Deleted";
	}

	@Override
	public List<Integer> showAllDeptNos() {
		
		List<Integer> deptnoList = ideptRepo.fetchAllDeptNos();
		
		return deptnoList;
	}

	@Override
	public boolean isDesgInRejectList(String desg) {
		
		if(desg.equalsIgnoreCase("TeamLeader")) {
			return true;
		}
				
		return false;
	}

}
