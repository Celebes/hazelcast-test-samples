package io.github.celebes.hazelcast.test.samples.parameters;

public class UserParameters {
	private String secretName;
	private double x;
	private double y;
	private boolean visible;
	
	public UserParameters() {
		
	}

	public UserParameters(String secretName, double x, double y, boolean visible) {
		super();
		this.secretName = secretName;
		this.x = x;
		this.y = y;
		this.visible = visible;
	}

	public String getSecretName() {
		return secretName;
	}

	public void setSecretName(String secretName) {
		this.secretName = secretName;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "BookParameters [secretName=" + secretName + ", x=" + x + ", y="
				+ y + ", visible=" + visible + "]";
	}

}