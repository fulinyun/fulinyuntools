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
 * ArrayPermutationsIter.java
 * -----------------
 * (C) Copyright 2005-2007, by Assaf Lehr and Contributors.
 *
 * Original Author:  Assaf Lehr
 * Contributor(s):   -
 *
 * $Id: ArrayPermutationsIter.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 */
package org.jgrapht.experimental.permutation;

/**
 * An interface to iterate over array permutations. Similiar to Iterator, but
 * with specific return types and without the remove() method.
 *
 * @author Assaf
 * @since Jul 29, 2005
 */
public interface ArrayPermutationsIter
{
    //~ Methods ----------------------------------------------------------------

    public int [] nextPermutation();

    public boolean hasNextPermutaions();
}

// End ArrayPermutationsIter.java
