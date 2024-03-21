//  Copyright 2023 Goldman Sachs
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package org.finos.legend.engine.language.memsqlFunction.grammar.test;

import org.finos.legend.engine.language.pure.grammar.test.TestGrammarRoundtrip;
import org.junit.Test;

public class TestMemSqlFunctionRoundtrip extends TestGrammarRoundtrip.TestGrammarRoundtripTestSuite
{
    @Test
    public void testMemSqlFunction()
    {
        test("###MemSql\n" +
                "MemSqlFunction <<a::A.test>> {a::A.val = 'ok'} xx::MyApp\n" +
                "{\n" +
                "   functionName : 'MyApp';\n" +
                "   function : zxx(Integer[1]):String[1];\n" +
                "   owner : 'sasate';\n" +
                "   description : 'Test TVF injection!';\n" +
                "   activationConfiguration : com::gs::test::TestConnection;\n" +
                "}\n");
    }

    @Test
    public void testMemSqlFunctionMinimal()
    {
        test("###MemSql\n" +
                "MemSqlFunction xx::MyApp\n" +
                "{\n" +
                "   functionName : 'MyApp';\n" +
                "   function : zxx(Integer[1]):String[1];\n" +
                "}\n");
    }
}