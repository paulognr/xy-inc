package br.com.paulognr.test.integrated.core;

import java.nio.charset.StandardCharsets;

import org.junit.Assume;
import org.junit.ClassRule;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

@Category(IntegratedTest.class)
public abstract class BaseIntegratedTest {

	private static String currentApiUrl = System.getProperty("current.api.url", "http://localhost:8080");

	@ClassRule
	public static TestRule classRule = BaseIntegratedTest::classRule;

	private static Statement classRule(final Statement base, final Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				try {
					RestAssured.baseURI = currentApiUrl;
					RestAssured.requestSpecification = new RequestSpecBuilder().build();
					RestAssured.requestSpecification.contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8));
				} catch (Exception e) {
					e.printStackTrace();
					Assume.assumeTrue("Setup failed - " + e.getMessage(), false);
				}

				base.evaluate();
			}
		};
	}

}
