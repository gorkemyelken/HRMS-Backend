package kodlamaio.hrms.core.adapters;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.email.EmailManager;

@Service
public class EmailManagerAdapter implements EmailService{

	@Override
	public void activateEmail() {
		EmailManager emailManager = new EmailManager();
		emailManager.activateEmail();
	}

}
