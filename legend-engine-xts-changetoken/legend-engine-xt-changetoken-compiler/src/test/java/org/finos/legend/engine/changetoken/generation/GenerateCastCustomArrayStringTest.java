//  Copyright 2024 Goldman Sachs
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

package org.finos.legend.engine.changetoken.generation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GenerateCastCustomArrayStringTest extends GenerateCastTestBase
{
    @BeforeClass
    public static void setupSuite() throws IOException, ClassNotFoundException
    {
        setupSuiteFromJson("{\n" +
                "  \"@type\": \"meta::pure::changetoken::Versions\",\n" +
                "  \"versions\": [\n" +
                "    {\n" +
                "      \"@type\": \"meta::pure::changetoken::Version\",\n" +
                "      \"version\": \"ftdm:abcdefg123\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"@type\": \"meta::pure::changetoken::Version\",\n" +
                "      \"version\": \"ftdm:abcdefg456\",\n" +
                "      \"prevVersion\": \"ftdm:abcdefg123\",\n" +
                "      \"changeTokens\": [\n" +
                "        {\n" +
                "          \"@type\": \"meta::pure::changetoken::AddField\",\n" +
                "          \"fieldName\": \"abc\",\n" +
                "          \"fieldType\": \"String[1..*]\",\n" +
                "          \"defaultValue\": {\n" +
                "            \"@type\": \"meta::pure::changetoken::ConstValue\",\n" +
                "            \"value\": [\n" +
                "              \"Foo\",\n" +
                "              \"Bar\"\n" +
                "            ]\n" +
                "          },\n" +
                "          \"safeCast\": true,\n" +
                "          \"class\": \"meta::pure::changetoken::tests::SampleClass\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n");
    }

    @Test
    public void testUpcast() throws JsonProcessingException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        expect(upcast("{\n" +
                        "  \"version\":\"ftdm:abcdefg123\", \n" +
                        "  \"@type\": \"meta::pure::changetoken::tests::SampleClass\",\n" +
                        "  \"innerObject\": {\"@type\": \"meta::pure::changetoken::tests::SampleClass\"},\n" +
                        "  \"innerNestedArray\":[\n" +
                        "    {\"@type\": \"meta::pure::changetoken::tests::SampleClass\"}, \n" +
                        "    [{\"@type\": \"meta::pure::changetoken::tests::SampleClass\"}]\n" +
                        "  ]\n" +
                        "}"),
                "{\n" +
                        "  \"version\":\"ftdm:abcdefg456\",\n" +
                        "  \"@type\": \"meta::pure::changetoken::tests::SampleClass\",\n" +
                        "  \"innerObject\": {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "  \"innerNestedArray\":[\n" +
                        "    {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "    [{\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]}]\n" +
                        "  ],\n" +
                        "  \"abc\": [\"Foo\", \"Bar\"]\n" +
                        "}\n");
    }

    @Test
    public void testDowncast() throws JsonProcessingException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        expect(downcast("{\n" +
                        "  \"version\":\"ftdm:abcdefg456\",\n" +
                        "  \"@type\": \"meta::pure::changetoken::tests::SampleClass\",\n" +
                        "  \"innerObject\": {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "  \"innerNestedArray\":[\n" +
                        "    {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "    [{\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]}]\n" +
                        "  ],\n" +
                        "  \"abc\": [\"Foo\", \"Bar\"]\n" +
                        "}", "ftdm:abcdefg123"),
                "{\n" +
                        "  \"version\":\"ftdm:abcdefg123\", \n" +
                        "  \"@type\": \"meta::pure::changetoken::tests::SampleClass\",\n" +
                        "  \"innerObject\": {\"@type\": \"meta::pure::changetoken::tests::SampleClass\"},\n" +
                        "  \"innerNestedArray\":[\n" +
                        "    {\"@type\": \"meta::pure::changetoken::tests::SampleClass\"}, \n" +
                        "    [{\"@type\": \"meta::pure::changetoken::tests::SampleClass\"}]\n" +
                        "  ]\n" +
                        "}\n");
    }

    @Test
    public void testDowncastNonDefault() throws JsonProcessingException, NoSuchMethodException
    {
        exception(() -> downcast("{\n" +
                        "  \"version\":\"ftdm:abcdefg456\",\n" +
                        "  \"@type\": \"meta::pure::changetoken::tests::SampleClass\",\n" +
                        "  \"innerObject\": {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "  \"innerNestedArray\":[\n" +
                        "    {\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]},\n" +
                        "    [{\"@type\": \"meta::pure::changetoken::tests::SampleClass\", \"abc\": [\"Foo\", \"Bar\"]}]\n" +
                        "  ],\n" +
                        "  \"abc\":[\"Something\",\"Else\"]\n" +
                        "}", "ftdm:abcdefg123"),
                "Cannot remove non-default value:[Something, Else]");
    }
}
