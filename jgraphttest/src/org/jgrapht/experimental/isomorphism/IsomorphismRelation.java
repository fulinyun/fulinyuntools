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
/* -----------------
 * IsomorphismRelation.java
 * -----------------
 * (C) Copyright 2005-2007, by Assaf Lehr and Contributors.
 *
 * Original Author:  Assaf Lehr
 * Contributor(s):   -
 *
 * $Id: IsomorphismRelation.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 */
package org.jgrapht.experimental.isomorphism;

import java.util.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;


/**
 * Holds an isomorphism relation for two graphs. It contains a mapping between
 * the two graphs.
 *
 * <p>Usage:
 *
 * <ol>
 * <li>use <code>getVertexCorrespondence()</code> or <code>
 * getEdgeCorrespondence()</code> to get the mapped object in the other graph.
 * </ol>
 *
 * <p>
 * <p>It consists of two vertexes array , the i-th vertex in the 1st array is
 * the isomorphic eqv. of the i-th in 2nd array. Note that the getters are
 * unsafe (they return the array and not a copy of it).
 *
 * @author Assaf
 * @since May 27, 2005
 */
public class IsomorphismRelation<V, E>
    implements GraphMapping<V, E>
{
    //~ Instance fields --------------------------------------------------------

    private List<V> vertexList1;
    private List<V> vertexList2;

    private GraphMapping<V, E> graphMapping = null;

    private Graph<V, E> graph1;
    private Graph<V, E> graph2;

    //~ Constructors -----------------------------------------------------------

    /**
     */
    public IsomorphismRelation(
        List<V> aGraph1vertexArray,
        List<V> aGraph2vertexArray,
        Graph<V, E> g1,
        Graph<V, E> g2)
    {
        this.vertexList1 = aGraph1vertexArray;
        this.vertexList2 = aGraph2vertexArray;
        this.graph1 = g1;
        this.graph2 = g2;
    }

    //~ Methods ----------------------------------------------------------------

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("vertexList1: ").append(
            this.vertexList1.toString());
        sb.append("\tvertexList2: ").append(
            this.vertexList2.toString());
        return sb.toString();
    }

    public V getVertexCorrespondence(V vertex, boolean forward)
    {
        // lazy initializer for graphMapping
        if (graphMapping == null) {
            initGraphMapping();
        }

        return graphMapping.getVertexCorrespondence(vertex, forward);
    }

    public E getEdgeCorrespondence(E edge, boolean forward)
    {
        // lazy initializer for graphMapping
        if (graphMapping == null) {
            initGraphMapping();
        }

        return graphMapping.getEdgeCorrespondence(edge, forward);
    }

    /**
     * We currently have the vertexes array. From them we will construct two
     * maps: g1ToG2 and g2ToG1, using the array elements with the same index.
     */
    private void initGraphMapping()
    {
        int mapSize = vertexList1.size();
        Map<V, V> g1ToG2 = new HashMap<V, V>(mapSize);
        Map<V, V> g2ToG1 = new HashMap<V, V>(mapSize);

        for (int i = 0; i < mapSize; i++) {
            V source = this.vertexList1.get(i);
            V target = this.vertexList2.get(i);
            g1ToG2.put(source, target);
            g2ToG1.put(target, source);
        }
        this.graphMapping =
            new DefaultGraphMapping<V, E>(
                g1ToG2,
                g2ToG1,
                this.graph1,
                this.graph2);
    }
}

// End IsomorphismRelation.java
