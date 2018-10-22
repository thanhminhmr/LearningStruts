package mrmathami.struts2.utilities;

public enum ActionError {
	NONE,
	FAILED,
	EMPTY;

	public boolean isError() {
		return this != NONE;
	}

	public boolean isFailed() {
		return this == FAILED;
	}

	public boolean isEmpty() {
		return this == EMPTY;
	}
}