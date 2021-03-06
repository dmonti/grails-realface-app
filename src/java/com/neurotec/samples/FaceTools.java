package com.neurotec.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.licensing.NLicense;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FaceTools {

    private static final Logger log = LoggerFactory.getLogger(FaceTools.class);

	// ===========================================================
	// Private static fields
	// ===========================================================

	private static final String ADDRESS = "/local";
	private static final String PORT = "5000";
	private static FaceTools instance;

	// ===========================================================
	// Public static methods
	// ===========================================================

	public static FaceTools getInstance() {
		synchronized (FaceTools.class) {
			if (instance == null) {
                log.debug("Initializing FaceTools...");
				instance = new FaceTools();
			}
			return instance;
		}
	}

	// ===========================================================
	// Private fields
	// ===========================================================

	private final NBiometricClient client;
	private final NBiometricClient defaultClient;
	private final Map<String, Boolean> obtainedLicenses;

	// ===========================================================
	// Private constructor
	// ===========================================================

	private FaceTools() {
		obtainedLicenses = new HashMap<String, Boolean>();
		client = new NBiometricClient();
		defaultClient = new NBiometricClient();
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	private boolean obtain(String address, String port, List<String> licenses) throws Exception {
		if (licenses == null) {
			throw new RuntimeException("Null license list");
		}

		boolean result = true;
		for (String license : licenses) {
			if (!isLicenseObtained(license)) {
				boolean state = NLicense.obtainComponents(address, port, license);
				obtainedLicenses.put(license, state);
				log.debug(license + ": " + (state ? "obtainted" : "not obtained"));
			} else {
				log.debug(license + ": " + " already obtained");
			}
		}
		return result;
	}

	private boolean isLicenseObtained(String license) {
		if (obtainedLicenses.containsKey(license)) {
			return obtainedLicenses.get(license);
		} else {
			return false;
		}
	}

	private synchronized void release(List<String> licenses) {
		if (licenses != null && !licenses.isEmpty()) {
			String components = licenses.toString().replace("[", "").replace("]", "").replace(" ", "");
			try {
				log.debug("Releasing licenses: " + components);
				NLicense.releaseComponents(components);
				licenses.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public void resetParameters() {
		client.reset();
	}

	public boolean obtainLicenses(List<String> licenses) throws Exception {
		return obtain(ADDRESS, PORT, licenses);
	}

	public void releaseLicenses() {
		release(new ArrayList<String>(obtainedLicenses.keySet()));
	}

	public NBiometricClient getClient() {
		return client;
	}

	public NBiometricClient getDefaultClient() {
		return defaultClient;
	}

	public Map<String, Boolean> getLicenses() {
		return obtainedLicenses;
	}
}