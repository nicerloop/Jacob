package com.guujiang.jacob.maven;

import org.apache.maven.model.Build;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "enhance-test", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES)
public class EnhanceTestMojo extends EnhanceMojo {

	@Override
	protected String classesDir(Build build) {
		return build.getTestOutputDirectory();
	}

}
