package br.unb.cic.api_evolution;

import org.junit.Assert;
import org.junit.Test;

import br.unb.cic.api_evolution.util.StringUtils;

public class StringUtilsTest {
	
	@Test
	public void testNoAtom() {
		Assert.assertEquals("class().", StringUtils.generateFact("class"));
	}
	
	@Test
	public void testSingleAtom() {
		Assert.assertEquals("class(\"Cipher\").", StringUtils.generateFact("class", "Cipher"));
	}
	
	@Test
	public void testSeveralAtoms() {
		Assert.assertEquals("class(\"Cipher\", \"Test\", \"ABC\").", StringUtils.generateFact("class", "Cipher", "Test", "ABC"));
	}
	
	@Test
	public void testAtomsAndList() {
		
	}

}
