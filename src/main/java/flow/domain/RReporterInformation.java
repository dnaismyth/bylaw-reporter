package flow.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Basic user information that should be included when making a bylaw report.
 * @author DN
 *
 */
@Embeddable
public class RReporterInformation {

	@Column(name = "reporter_name")
	private String name;
	
	@Column(name = "reporter_address")
	private String address;
	
	@Column(name = "reporter_phone")
	private String phone;
	
	@Column(name = "emailaddress")
	private String emailAddress;
	
	public String getReporterName() {
		return name;
	}

	public void setReporterName(String name) {
		this.name = name;
	}

	public String getReporterAddress() {
		return address;
	}

	public void setReporterAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
