/*
 * Copyright 2016 The Johns Hopkins University Applied Physics Laboratory LLC
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.jhuapl.dorset.routing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import edu.jhuapl.dorset.Request;
import edu.jhuapl.dorset.agent.Agent;
import edu.jhuapl.dorset.config.MultiValuedMap;

public class KeywordRouterTest {

    @Test
    public void testRouting() {
        Agent agent1 = mock(Agent.class);
        MultiValuedMap params1 = new MultiValuedMap();
        params1.addString(KeywordRouter.KEYWORDS, "time");
        params1.addString(KeywordRouter.KEYWORDS, "date");
        Agent agent2 = mock(Agent.class);
        MultiValuedMap params2 = new MultiValuedMap();
        params2.addString(KeywordRouter.KEYWORDS, "twitter");

        RouterAgentConfig config = RouterAgentConfig.create();
        config.add(agent1, params1);
        config.add(agent2, params2);
        Router router = new KeywordRouter(config);

        Agent agents[] = router.route(new Request("What time is it"));
        Agent expected[] = new Agent[]{agent1};
        assertArrayEquals(expected, agents);

        Agent agents2[] = router.route(new Request("Post this on twitter"));
        Agent expected2[] = new Agent[]{agent2};
        assertArrayEquals(expected2, agents2);
    }

}
