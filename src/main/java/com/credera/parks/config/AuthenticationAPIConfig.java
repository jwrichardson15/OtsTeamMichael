/*
 *
 *  Copyright 2017-2019 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package com.credera.parks.config;

import org.springframework.stereotype.Component;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static java.util.Collections.*;

@Component
public class AuthenticationAPIConfig implements ApiListingScannerPlugin {

  private final CachingOperationNameGenerator operationNames;

  /**
   * @param operationNames - CachingOperationNameGenerator is a component bean
   *                       that is available to be autowired
   */
  public AuthenticationAPIConfig(CachingOperationNameGenerator operationNames) {//<9>
    this.operationNames = operationNames;
  }

  @Override
  public List<ApiDescription> apply(DocumentationContext context) {
    return new ArrayList<>(
        Arrays.asList(
            new ApiDescription(
                "authenticate",
                "/api/authenticate",
                "responds to authentication with a JWT in the Authorization Header",
                Collections.singletonList(
                    new OperationBuilder(
                        operationNames)
                        .authorizations(new ArrayList<>())
                        .codegenMethodNameStem("auth")
                        .method(HttpMethod.POST)
                        .notes("responds to authentication with a JWT in the Authorization Header")
                        .parameters(
                            Arrays.asList(
                                             new ParameterBuilder()
                                             .description("username")
                                             .type(new TypeResolver().resolve(String.class))
                                             .name("username")
                                             .parameterType("form")
                                             .parameterAccess("access")
                                             .required(true)
                                             .modelRef(new ModelRef("string"))
                                             .build(),
                                             new ParameterBuilder()
                                             .description("password")
                                             .type(new TypeResolver().resolve(String.class))
                                             .name("password")
                                             .parameterType("form")
                                             .parameterAccess("access")
                                             .required(true)
                                             .modelRef(new ModelRef("string"))
                                             .build()
                                             ))
                    .responseMessages(responseMessages())
                        .responseModel(new ModelRef("string"))
                        .build()),
                false)));
  }

  /**
   * @return Set of response messages that overide the default/global response messages
   */
  private Set<ResponseMessage> responseMessages() {
      Set<ResponseMessage> responses = new HashSet<ResponseMessage>(Arrays.asList(new ResponseMessageBuilder()
                                                                                  .code(200)
                                                                                  .message("Successfully Authenticated")
                                                                                  .build(),
                                                                                  new ResponseMessageBuilder()
                                                                                  .code(403)
                                                                                  .message("Authentication Failed")
                                                                                  .build()));
      return responses;
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return DocumentationType.SWAGGER_2.equals(delimiter);
  }

}
