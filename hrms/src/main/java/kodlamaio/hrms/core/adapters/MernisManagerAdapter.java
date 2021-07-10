package kodlamaio.hrms.core.adapters;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.mernis.MernisManager;

@Service
public class MernisManagerAdapter implements MernisService {

	@Override
	public void activateMernis() {
		MernisManager mernisManager = new MernisManager();
		mernisManager.activateMernis();
	}

}
