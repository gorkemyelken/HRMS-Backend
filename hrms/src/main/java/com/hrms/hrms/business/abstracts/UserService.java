package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.results.DataResult;
import com.hrms.hrms.entities.abstracts.User;

public interface UserService {

	DataResult<List<User>> getAll();
}
