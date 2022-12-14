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

package org.brixcms.web.util;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public abstract class TextLink<T> extends Link<T> {
// ------------------------------ FIELDS ------------------------------

    private final IModel<String> textModel;

// --------------------------- CONSTRUCTORS ---------------------------

    public TextLink(String id, IModel<String> textModel) {
        super(id);
        this.textModel = wrap(textModel);
    }

    public TextLink(String id, IModel<T> model, IModel<String> textModel) {
        super(id, model);
        this.textModel = wrap(textModel);
    }

// -------------------------- OTHER METHODS --------------------------

    protected void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        replaceComponentTagBody(markupStream, openTag, textModel.getObject());
    }

    @Override
    protected void onDetach() {
        textModel.detach();
        super.onDetach();
    }
}
