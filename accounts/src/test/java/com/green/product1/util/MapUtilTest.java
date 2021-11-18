package com.green.product1.util;


import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class MapUtilTest {

	@Test
	final void testOf() {
		Map<String, String> map = MapUtil.mapOf("A1","V1","A2","V2","A3","V3");
		assertThat(map,hasEntry("A1", "V1"));
		assertThat(map,hasEntry("A2", "V2"));
		assertThat(map,hasEntry("A3", "V3"));
		assertThat(map.size(), is(3));
	}

}
