package project.pd.microservice.model;

public class Decision {

	private Long idDecision;
	private String name;
	private Long iduser;
	
	public Long getIdDecision() {
		return idDecision;
	}
	public void setIdDecision(Long idDecision) {
		this.idDecision = idDecision;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
