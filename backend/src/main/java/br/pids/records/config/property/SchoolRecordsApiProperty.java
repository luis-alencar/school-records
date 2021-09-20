package br.pids.records.config.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("backend")
@Component
public class SchoolRecordsApiProperty {
	
	private String originPermitida = "http://localhost:4200";


	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
}
