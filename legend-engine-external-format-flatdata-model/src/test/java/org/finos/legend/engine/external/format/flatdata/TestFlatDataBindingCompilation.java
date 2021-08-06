// Copyright 2021 Goldman Sachs
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

package org.finos.legend.engine.external.format.flatdata;

import org.finos.legend.engine.external.shared.format.model.test.ExternalSchemaCompilationTest;
import org.finos.legend.engine.language.pure.grammar.to.PureGrammarComposerUtility;
import org.junit.Test;

public class TestFlatDataBindingCompilation extends ExternalSchemaCompilationTest
{
    @Test
    public void testCompatibleModelExactNamingMatch()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "    gamma   : INTEGER;\n" +
                                               "    delta   : INTEGER;\n" +
                                               "    epsilon : INTEGER;\n" +
                                               "    zeta    : DECIMAL;\n" +
                                               "    eta     : DECIMAL;\n" +
                                               "    theta   : DATE;\n" +
                                               "    iota    : DATETIME;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n"
        );
    }

    @Test
    public void testCompatibleModelClassNameDoesNotMatterIfShapeIsOK()
    {
        test("###Pure\n" +
                     "Class test::model::Z\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "    gamma   : INTEGER;\n" +
                                               "    delta   : INTEGER;\n" +
                                               "    epsilon : INTEGER;\n" +
                                               "    zeta    : DECIMAL;\n" +
                                               "    eta     : DECIMAL;\n" +
                                               "    theta   : DATE;\n" +
                                               "    iota    : DATETIME;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::Z ];\n" +
                     "}\n"
        );
    }

    @Test
    public void testCompatibleModelClassNameDistinguishesIfMultipleMatchOnShape()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "Class test::model::Z\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "    gamma   : INTEGER;\n" +
                                               "    delta   : INTEGER;\n" +
                                               "    epsilon : INTEGER;\n" +
                                               "    zeta    : DECIMAL;\n" +
                                               "    eta     : DECIMAL;\n" +
                                               "    theta   : DATE;\n" +
                                               "    iota    : DATETIME;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A, test::model::Z ];\n" +
                     "}\n"
        );
    }

    @Test
    public void testCompatibleModelLooseNamingMatch()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alphaBetaGamma : String[1];\n" +
                     "  deltaEpsilon   : Decimal[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    'alpha beta gamma': STRING;\n" +
                                               "    'DELTA EPSILON'   : DECIMAL;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [\n" +
                     "    test::model::A\n" +
                     "  ];\n" +
                     "}\n"
        );
    }

    @Test
    public void testInompatibleModelMultipleMatchOnShapeAndNameCannotDifferentiate()
    {
        test("###Pure\n" +
                     "Class test::model::Y\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "Class test::model::Z\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "    gamma   : INTEGER;\n" +
                                               "    delta   : INTEGER;\n" +
                                               "    epsilon : INTEGER;\n" +
                                               "    zeta    : DECIMAL;\n" +
                                               "    eta     : DECIMAL;\n" +
                                               "    theta   : DATE;\n" +
                                               "    iota    : DATETIME;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::Y, test::model::Z ];\n" +
                     "}\n",
             "COMPILATION error at [33:1-38:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Ambiguous matches against classes in the model: (Y,Z)"
        );
    }

    @Test
    public void testIncompatibleModelMultiplicityMismatch()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : String[*];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n",
             "COMPILATION error at [14:1-19:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Could only partially match fields to class A matched fields: (beta[beta]) unmatched fields: (alpha[alpha wrong multiplicity])"
        );
    }

    @Test
    public void testIncompatibleModelDatatypeMismatch()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : Date[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n",
             "COMPILATION error at [14:1-19:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Could only partially match fields to class A matched fields: (beta[beta]) unmatched fields: (alpha[alpha wrong type])"
        );
    }

    @Test
    public void testIncompatibleModelAmbiguousMatch()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  'alphaBetaGamma:' : String[1];\n" +
                     "  'alphaBetaGamma;' : String[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    'ALPHA BETA GAMMA': STRING;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n",
             "COMPILATION error at [14:1-19:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Could only partially match fields to class A matched fields: () unmatched fields: (ALPHA BETA GAMMA[ambiguous for properties: alphaBetaGamma:|alphaBetaGamma;])"
        );
    }

    @Test
    public void testIncompatibleModelMultipleFieldsCouldMatchAProperty()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alphaBetaGamma : String[1];\n" +
                     "  deltaEpsilon   : Decimal[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    'alpha beta gamma': STRING;\n" +
                                               "    'ALPHA BETA GAMMA': STRING;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n",
             "COMPILATION error at [14:1-19:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Could only partially match fields to class A matched fields: (alpha beta gamma[alphaBetaGamma]) unmatched fields: (ALPHA BETA GAMMA[No matching properties])"
        );
    }

    @Test
    public void testIncompatibleModelComplex()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[1];\n" +
                     "  gamma   : Date[*];\n" +
                     "  epsilon : DateTime[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "Class test::model::Z\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : DateTime[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     flatDataSchemaSet("section A: DelimitedWithHeadings\n" +
                                               "{\n" +
                                               "  scope.untilEof;\n" +
                                               "  delimiter: ',';\n" +
                                               "\n" +
                                               "  Record\n" +
                                               "  {\n" +
                                               "    alpha   : STRING;\n" +
                                               "    beta    : BOOLEAN(optional);\n" +
                                               "    gamma   : INTEGER;\n" +
                                               "    delta   : INTEGER;\n" +
                                               "    epsilon : INTEGER;\n" +
                                               "    zeta    : DECIMAL;\n" +
                                               "    eta     : DECIMAL;\n" +
                                               "    theta   : DATE;\n" +
                                               "    iota    : DATETIME;\n" +
                                               "  }\n" +
                                               "}") +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A, test::model::Z ];\n" +
                     "}\n",
             "COMPILATION error at [28:1-33:1]: Model and schema are mismatched:\n" +
                     "Section 'A': Could only partially match fields to class A matched fields: (alpha[alpha],eta[eta],theta[theta],iota[iota]) unmatched fields: (beta[beta wrong multiplicity],gamma[gamma wrong type wrong multiplicity],delta[No matching properties],epsilon[epsilon wrong type],zeta[No matching properties])\n" +
                     "Section 'A': Could only partially match fields to class Z matched fields: (alpha[alpha],epsilon[epsilon],eta[eta],theta[theta],iota[iota]) unmatched fields: (beta[No matching properties],gamma[No matching properties],delta[No matching properties],zeta[zeta wrong type])"
        );
    }

    @Test
    public void testCompatibleModelExactNamingMatchForSpecificId()
    {
        test("###Pure\n" +
                     "Class test::model::A\n" +
                     "{\n" +
                     "  alpha   : String[1];\n" +
                     "  beta    : Boolean[0..1];\n" +
                     "  gamma   : Integer[1];\n" +
                     "  delta   : Float[1];\n" +
                     "  epsilon : Decimal[1];\n" +
                     "  zeta    : Float[1];\n" +
                     "  eta     : Decimal[1];\n" +
                     "  theta   : StrictDate[1];\n" +
                     "  iota    : DateTime[1];\n" +
                     "}\n" +
                     "###ExternalFormat\n" +
                     "SchemaSet test::SchemaSet\n" +
                     "{\n" +
                     "  format: FlatData;\n" +
                     "  schemas: [ \n" +
                     "    { id: fd1; content: " + PureGrammarComposerUtility.convertString("section Z: DelimitedWithHeadings\n" +
                                                                                                   "{\n" +
                                                                                                   "  scope.untilEof;\n" +
                                                                                                   "  delimiter: '|';\n" +
                                                                                                   "\n" +
                                                                                                   "  Record\n" +
                                                                                                   "  {\n" +
                                                                                                   "    omega : STRING;\n" +
                                                                                                   "  }\n" +
                                                                                                   "}", true) + "; },\n" +
                     "    { id: fd2; content: " + PureGrammarComposerUtility.convertString("section A: DelimitedWithHeadings\n" +
                                                                                                   "{\n" +
                                                                                                   "  scope.untilEof;\n" +
                                                                                                   "  delimiter: ',';\n" +
                                                                                                   "\n" +
                                                                                                   "  Record\n" +
                                                                                                   "  {\n" +
                                                                                                   "    alpha   : STRING;\n" +
                                                                                                   "    beta    : BOOLEAN(optional);\n" +
                                                                                                   "    gamma   : INTEGER;\n" +
                                                                                                   "    delta   : INTEGER;\n" +
                                                                                                   "    epsilon : INTEGER;\n" +
                                                                                                   "    zeta    : DECIMAL;\n" +
                                                                                                   "    eta     : DECIMAL;\n" +
                                                                                                   "    theta   : DATE;\n" +
                                                                                                   "    iota    : DATETIME;\n" +
                                                                                                   "  }\n" +
                                                                                                   "}", true) + "; }\n" +
                     " ];\n" +
                     "}\n" +
                     "\n" +
                     "Binding test::Binding\n" +
                     "{\n" +
                     "  schemaSet: test::SchemaSet;\n" +
                     "  schemaId: fd2;\n" +
                     "  contentType: 'application/x.flatdata';\n" +
                     "  modelIncludes: [ test::model::A ];\n" +
                     "}\n"
        );
    }

    private String flatDataSchemaSet(String flatData)
    {
        return "SchemaSet test::SchemaSet\n" +
                "{\n" +
                "  format: FlatData;\n" +
                "  schemas: [ { content: " + PureGrammarComposerUtility.convertString(flatData, true) + "; } ];\n" +
                "}\n";
    }
}
