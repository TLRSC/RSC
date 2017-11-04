package tech.teslex.mpes.rsc.config;

public class Config {

	private String host = "127.0.0.1";

	private int port = 9094;

	private String secret;


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
