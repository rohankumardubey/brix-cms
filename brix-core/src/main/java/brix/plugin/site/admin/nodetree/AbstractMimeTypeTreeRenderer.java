/**
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

package brix.plugin.site.admin.nodetree;

import java.util.Arrays;
import java.util.List;

import brix.jcr.wrapper.BrixNode;
import brix.jcr.wrapper.ResourceNode;

/**
 * A renderer that decides if it is rendering the node based on mime type
 * 
 * @author Jeremy Thomerson
 */
public abstract class AbstractMimeTypeTreeRenderer extends AbstractNodeTreeRenderer
{
	private static final long serialVersionUID = 1L;

	private final List<String> mimeTypes;
	private final List<String> mimeTypePrefixes;

	protected AbstractMimeTypeTreeRenderer(String[] mimeTypes)
	{
		this(mimeTypes, new String[0]);
	}

	protected AbstractMimeTypeTreeRenderer(String[] mimeTypes, String[] mimeTypePrefixes)
	{
		this.mimeTypes = Arrays.asList(mimeTypes);
		this.mimeTypePrefixes = Arrays.asList(mimeTypePrefixes);
	}

	@Override
	protected boolean isForThisNode(BrixNode bn)
	{
		return super.isForThisNode(bn) && isCorrectMimeType(bn);
	}

	@Override
	protected final Class<? extends BrixNode> getNodeClass()
	{
		return ResourceNode.class;
	}

	protected boolean isCorrectMimeType(BrixNode bn)
	{
		ResourceNode rn = (ResourceNode)bn;
		if (bn == null || rn.getMimeType() == null)
		{
			return false;
		}
		// TODO: perhaps should only test on lower case here?
		// if so, need to lowercase all incoming types in the constructor
		String type = rn.getMimeType();
		String prefix = type.substring(0, type.indexOf('/'));
		return mimeTypes.contains(type) || mimeTypePrefixes.contains(prefix);
	}

}
