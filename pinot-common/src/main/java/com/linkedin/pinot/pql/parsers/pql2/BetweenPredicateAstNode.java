/**
 * Copyright (C) 2014-2015 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.pql.parsers.pql2;

import com.linkedin.pinot.common.request.FilterOperator;
import com.linkedin.pinot.common.utils.request.FilterQueryTree;
import java.util.Collections;


/**
 * TODO Document me!
 *
 * @author jfim
 */
public class BetweenPredicateAstNode extends PredicateAstNode {
  private String _identifier;

  @Override
  public void addChild(AstNode childNode) {
    if (childNode instanceof IdentifierAstNode) {
      IdentifierAstNode node = (IdentifierAstNode) childNode;
      _identifier = node.getName();
    } else {
      super.addChild(childNode);
    }
  }

  public String getIdentifier() {
    return _identifier;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BetweenPredicateAstNode{");
    sb.append("_identifier='").append(_identifier).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public FilterQueryTree buildFilterQueryTree() {
    LiteralAstNode left = (LiteralAstNode) getChildren().get(0);
    LiteralAstNode right = (LiteralAstNode) getChildren().get(1);
    return new FilterQueryTree(_identifier, Collections.singletonList("[" + left + "\t\t" + right + "]"), FilterOperator.RANGE, null);
  }
}
