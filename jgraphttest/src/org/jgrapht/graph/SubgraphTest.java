/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* ------------------------
 * SubgraphTest.java
 * ------------------------
 * (C) Copyright 2003-2007, by Michael Behrisch and Contributors.
 *
 * Original Author:  Michael Behrisch
 * Contributor(s):   -
 *
 * $Id: SubgraphTest.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 * 21-Sep-2004 : Initial revision (MB);
 *
 */
package org.jgrapht.graph;

import java.util.*;

import junit.framework.*;

import org.jgrapht.*;


/**
 * Unit test for {@link Subgraph} class.
 *
 * @author Michael Behrisch
 * @since Sep 21, 2004
 */
public class SubgraphTest
    extends TestCase
{
    //~ Instance fields --------------------------------------------------------

    private String v1 = "v1";
    private String v2 = "v2";
    private String v3 = "v3";
    private String v4 = "v4";

    //~ Constructors -----------------------------------------------------------

    /**
     * @see junit.framework.TestCase#TestCase(java.lang.String)
     */
    public SubgraphTest(String name)
    {
        super(name);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * .
     */
    public void testInducedSubgraphListener()
    {
        UndirectedGraph<String, DefaultEdge> g = init(true);
        UndirectedSubgraph<String, DefaultEdge> sub =
            new UndirectedSubgraph<String, DefaultEdge>(g, null, null);

        assertEquals(g.vertexSet(), sub.vertexSet());
        assertEquals(g.edgeSet(), sub.edgeSet());

        g.addEdge(v3, v4);

        assertEquals(g.vertexSet(), sub.vertexSet());
        assertEquals(g.edgeSet(), sub.edgeSet());
    }

    /**
     * Tests Subgraph.
     */
    public void testSubgraph()
    {
        UndirectedGraph<String, DefaultEdge> g = init(false);
        UndirectedSubgraph<String, DefaultEdge> sub =
            new UndirectedSubgraph<String, DefaultEdge>(g, null, null);

        assertEquals(g.vertexSet(), sub.vertexSet());
        assertEquals(g.edgeSet(), sub.edgeSet());

        Set<String> vset = new HashSet<String>(g.vertexSet());
        g.removeVertex(v1);
        assertEquals(vset, sub.vertexSet()); // losing track

        g = init(false);
        vset = new HashSet<String>();
        vset.add(v1);
        sub = new UndirectedSubgraph<String, DefaultEdge>(g, vset, null);
        assertEquals(vset, sub.vertexSet());
        assertEquals(0, sub.degreeOf(v1));
        assertEquals(Collections.EMPTY_SET, sub.edgeSet());

        vset.add(v2);
        vset.add(v3);
        sub =
            new UndirectedSubgraph<String, DefaultEdge>(
                g,
                vset,
                new HashSet<DefaultEdge>(g.getAllEdges(v1, v2)));
        assertEquals(vset, sub.vertexSet());
        assertEquals(1, sub.edgeSet().size());
    }

    /**
     * .
     */
    public void testSubgraphListener()
    {
        UndirectedGraph<String, DefaultEdge> g = init(true);
        UndirectedSubgraph<String, DefaultEdge> sub =
            new UndirectedSubgraph<String, DefaultEdge>(g, null, null);

        assertEquals(g.vertexSet(), sub.vertexSet());
        assertEquals(g.edgeSet(), sub.edgeSet());

        Set<String> vset = new HashSet<String>(g.vertexSet());
        g.removeVertex(v1);
        vset.remove(v1);
        assertEquals(vset, sub.vertexSet()); // not losing track
        assertEquals(g.edgeSet(), sub.edgeSet());
    }

    private UndirectedGraph<String, DefaultEdge> init(boolean listenable)
    {
        UndirectedGraph<String, DefaultEdge> g;

        if (listenable) {
            g = new ListenableUndirectedGraph<String, DefaultEdge>(
                DefaultEdge.class);
        } else {
            g = new SimpleGraph<String, DefaultEdge>(
                DefaultEdge.class);
        }

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v1);
        g.addEdge(v1, v4);

        return g;
    }
}

// End SubgraphTest.java
