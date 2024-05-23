package com.pit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pit.model.Employee;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // Checks whether the correct command/Model class is being validated
        return clazz.isAssignableFrom(Employee.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Type cast to model class
        Employee emp = (Employee) target;

        // Write server-side form validation logic

        // Validate employee name
        if (emp.getEname() == null || emp.getEname().isEmpty()) { // Required rule
            errors.rejectValue("ename", "emp.name.required");
        } else if (emp.getEname().length() < 5 || emp.getEname().length() > 15) { // Length rule
            errors.rejectValue("ename", "emp.name.length");
        }

        // Validate job
        if (emp.getJob() == null || emp.getJob().isEmpty()) { // Required rule
            errors.rejectValue("job", "emp.job.required");
        } else if (emp.getJob().length() < 4 || emp.getJob().length() > 10) { // Length rule
            errors.rejectValue("job", "emp.job.length");
        }

        // Validate salary
        if(!errors.hasFieldErrors("sal")) {
        if (emp.getSal() == null) { // Required rule
            errors.rejectValue("sal", "emp.sal.required");
        } else if (emp.getSal() < 10000 || emp.getSal() > 20000) { // Range rule
            errors.rejectValue("sal", "emp.salary.range");
        }
       }
        // Validate department number
        if (emp.getDeptno() == null) { // Required rule
            errors.rejectValue("deptno", "emp.deptno.required");
        }
    }
}
