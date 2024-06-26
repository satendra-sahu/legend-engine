// Copyright 2024 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.persistence.components.relational.bigquery.sql.visitor;

import org.finos.legend.engine.persistence.components.logicalplan.datasets.ExternalDataset;
import org.finos.legend.engine.persistence.components.optimizer.Optimizer;
import org.finos.legend.engine.persistence.components.physicalplan.PhysicalPlanNode;
import org.finos.legend.engine.persistence.components.relational.sqldom.schemaops.expresssions.table.Table;
import org.finos.legend.engine.persistence.components.transformer.LogicalPlanVisitor;
import org.finos.legend.engine.persistence.components.transformer.VisitorContext;

public class ExternalDatasetVisitor implements LogicalPlanVisitor<ExternalDataset>
{

    @Override
    public VisitorResult visit(PhysicalPlanNode prev, ExternalDataset current, VisitorContext context)
    {
        Table table = new Table(
            current.database().orElse(null),
            current.group().orElse(null),
            current.name(),
            current.alias(),
            context.quoteIdentifier());

        for (Optimizer optimizer : context.optimizers())
        {
            table = (Table) optimizer.optimize(table);
        }

        prev.push(table);
        return new VisitorResult(table);
    }
}