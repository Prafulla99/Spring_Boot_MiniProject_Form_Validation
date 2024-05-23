package com.pit.model;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

//Model Cum Entity Class


@Entity
@Data
@Table(name="BOOT_EMP")
@SQLDelete( sql="UPDATE BOOT_EMP SET STATUS='deleted' WHERE EMPNO=?")// for soft deletetion
@Where(clause ="STATUS <> 'deleted' ")// To Avoid  softly deleted record not participating in any persisteence operation
public class Employee implements Serializable{
	
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "EMP_ID_SEQ", initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer empno;
	private String ename;
	private String job="CLERK";
	private Double sal;
	private String status="active";
	private Integer deptno;

}
