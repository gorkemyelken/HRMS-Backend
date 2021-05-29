package com.hrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.hrms.entities.concretes.ConfirmByEmployee;

public interface ConfirmByEmployeeDao extends JpaRepository<ConfirmByEmployee, Integer> {

	ConfirmByEmployee getByEmployerId(int employerId);
}
