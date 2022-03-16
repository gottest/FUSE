package com.redhat.fuse.boosters.rest.http;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.checkerframework.framework.qual.IgnoreInWholeProgramInference;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Greeting REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "CRA_API/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
                .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        
        rest("/greetings").description("Greeting to {name}")
            .get("/{name}").outType(Greetings.class)
            .route().routeId("greeting-api")
            .to("direct:greetingsImpl");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");     
        // @formatter:on

        // CRA_API
        rest("/v1/core")
            .get("/getVersion").description("Get Version API")
            .responseMessage().code(200).message("Ok").endResponseMessage()
            .to("bean:CRAService?method=getVersion")

            .get("/purchases/{ref}/items/{id}").description("TEST purchases code and Item id")
            .param().name("ref").description("Reference code of the purchase").type(RestParamType.path).endParam()
            .param().name("id").description("Item Id").type(RestParamType.path).endParam()
            .responseMessage().code(200).message("Ok").endResponseMessage()
            .to("bean:CRAService?method=purchases")

            .get("/BahtTextAlphabet").description("BahtText to Alphabet")
            .param().name("amount").description("amount").type(RestParamType.query).dataType("string").endParam()
            .responseMessage().code(200).message("Ok").endResponseMessage()
            .to("bean:CRAService?method=BahtTextAlphabet")

            .get("/eformRequest/{formType}").description("eform Request")
            .param().name("formType").description("eform01, eform02, eform03, eform04, eform05, eform06, eform07").type(RestParamType.path).dataType("string").endParam()
            .responseMessage().code(200).message("Ok").endResponseMessage()
            .to("bean:CRAService?method=eformRequest")

        ;
    }

}